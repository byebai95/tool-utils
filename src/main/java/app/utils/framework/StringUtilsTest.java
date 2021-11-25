package app.utils.framework;

import org.springframework.util.StringUtils;

/**
 * @Author: bai
 * @Date: 2021/11/25 14:57
 *
 * org.springframework.util 工具类使用 ,String 字符串工具类
 *
 *
 */
public class StringUtilsTest {

    public static void main(String[] args) {

        String str1 = "aA";

        //不能判断有空格的空串，使用 trim()
        System.out.println( StringUtils.isEmpty(str1));

        //判断字符串是否包含字符，空格不计算，弥补上述接口
        System.out.println( StringUtils.hasText(str1));

        //检测是否包含空格
        System.out.println( StringUtils.containsWhitespace(str1));

        //给字符串上单引号
        System.out.println( StringUtils.quote(str1));
    }

}
