package app.utils;

import app.model.SensitiveWordMapper;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *  敏感词校验工具类
 *
 *  修改为每次都读取一次 ，也可以系统启动读取一次（不实时）
 */
@Component
public class SensitiveWordUtils {

    private final SensitiveWordMapper sensitiveWordMapper;

    @SuppressWarnings("rawtypes")
    private Map sensitiveWordMap = null;
    public static int minMatchTYpe = 1;
    public static int maxMatchType = 2;


    public SensitiveWordUtils(SensitiveWordMapper sensitiveWordMapper){
        this.sensitiveWordMapper = sensitiveWordMapper;
//        Set<String> keyWordSet  = sensitiveWordDao.list();
//        addSensitiveWordToHashMap(keyWordSet);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(keyChar);

                if(wordMap != null){
                    nowMap = (Map) wordMap;
                }
                else{
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }



    public boolean isContainSensitiveWord(String txt, int matchType){
        boolean flag = false;
        for(int i = 0 ; i < txt.length() ; i++){
            int matchFlag = this.CheckSensitiveWord(txt, i, matchType);
            if(matchFlag > 0){
                flag = true;
            }
        }
        return flag;
    }


    public Set<String> getSensitiveWord(String txt ){
        Set<String> sensitiveWordList = new HashSet<String>();

        for(int i = 0 ; i < txt.length() ; i++){
            int length = CheckSensitiveWord(txt, i, 1);
            if(length > 0){
                sensitiveWordList.add(txt.substring(i, i+length));
                i = i + length - 1;
            }
        }

        return sensitiveWordList;
    }


    public String replaceSensitiveWord(String txt,int matchType,String replaceChar){
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }


    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }

        return resultReplace;
    }


    @SuppressWarnings({ "rawtypes"})
    public int CheckSensitiveWord(String txt,int beginIndex,int matchType){
        Set<String> keyWordSet  = sensitiveWordMapper.list();
        addSensitiveWordToHashMap(keyWordSet);
        boolean  flag = false;
        int matchFlag = 0;
        char word = 0;
        Map nowMap = sensitiveWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);
            if(nowMap != null){
                matchFlag++;
                if("1".equals(nowMap.get("isEnd"))){
                    flag = true;
                    if(SensitiveWordUtils.minMatchTYpe == matchType){
                        break;
                    }
                }
            }
            else{
                break;
            }
        }
        if(matchFlag < 2 || !flag){
            matchFlag = 0;
        }
        return matchFlag;
    }
}
