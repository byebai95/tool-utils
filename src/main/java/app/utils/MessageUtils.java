package app.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 *
 * 国际化查询工具类
 * @Author: bai
 * @Date: 2021/11/19 14:34
 */
@Component
public class MessageUtils {

    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }


    /**
     * 通过 key 查询
     * @param key
     * @return
     */
    public static String getMsg(String key) {
        return getMsg(key, null, LocaleContextHolder.getLocale());
    }


    /**
     * 传入 key 与 参数查询
     *
     * @param key
     * @param params
     * @return
     */
    public static String getMsg(String key, Object[] params) {
        return getMsg(key, params, LocaleContextHolder.getLocale());
    }


    /**
     * key 与 地区查询
     *
     * @param key
     * @param local
     * @return
     */
    public static String getMsg(String key, Locale local) {
        return getMsg(key, null, local);
    }


    /**
     * 全参数，键 key ,需要传入的参数数组 params，地区 locale
     *
     * @param key
     * @param params
     * @param locale
     * @return
     */
    public static String getMsg(String key, Object[] params, Locale locale) {
        try {
            return messageSource.getMessage(key, params, locale);
        } catch (Exception e) {
            return key;
        }
    }
}
