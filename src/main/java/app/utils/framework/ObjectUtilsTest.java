package app.utils.framework;

import org.springframework.util.ObjectUtils;

/**
 * @Author: bai
 * @Date: 2021/11/25 17:07
 */
public class ObjectUtilsTest {
    public static void main(String[] args) {

        Object obj = new Object();

        boolean bol = ObjectUtils.nullSafeEquals(obj,obj);

        System.out.println(bol);

        Object[] objs = new Object[0];

        boolean bols= ObjectUtils.isEmpty(objs);

        System.out.println(bols);

    }
}
