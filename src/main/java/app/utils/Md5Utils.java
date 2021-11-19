package app.utils;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: bai
 * @Date: 2021/11/15 14:44
 */
public class Md5Utils {

    /**
     * 加盐防破解
     */
    public static final String SALT = "fdg5466@*[]/";

    /**利用MD5进行加密*/
    public static String encoderByMd5(String str) throws NoSuchAlgorithmException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        return base64en.encode(md5.digest((str + SALT).getBytes(StandardCharsets.UTF_8)));
    }

}
