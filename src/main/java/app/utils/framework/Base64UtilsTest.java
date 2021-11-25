package app.utils.framework;

import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

/**
 * @Author: bai
 * @Date: 2021/11/25 16:22
 */
public class Base64UtilsTest {

    public static void main(String[] args) {

        String str = "Hello";

        byte[] code = Base64Utils.encode(str.getBytes(StandardCharsets.UTF_8));

        String res = new String(code);

        System.out.println(res);


    }

}
