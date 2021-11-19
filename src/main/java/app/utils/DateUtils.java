package app.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author: bai
 * @Date: 2021/11/19 14:44
 */
public class DateUtils {

    public static final String DATE = "yyyy-MM-dd";

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取当前服务器时间
     * @return
     */
    public static Date now(){
        return new Date();
    }


    /**
     * 日期转换为字符串
     * @param date
     * @return
     */
    public static String date2Str(Date date){
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME);
        try{
            return format.format(date);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 字符串转换为 yyyy-MM-dd
     * @param str
     * @return
     */
    public static Date str2Date(String str){
        SimpleDateFormat format = new SimpleDateFormat(DATE);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * @param str
     * @return
     */
    public static Date str2DateTime(String str){
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取当前时间戳 13位
     * @return
     */
    public static long currentTimeStamp(){
        return System.currentTimeMillis();
    }


    /**
     * 获取今日最后的时间
     * @return
     */
    public static String endOfDay(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date date = calendar.getTime();
        return date2Str(date);
    }

}
