package app.utils.framework;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: bai
 * @Date: 2021/11/25 16:54
 */
public class CollectionUtilsTest {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);

        boolean bol = CollectionUtils.isEmpty(list);

        System.out.println(bol);

        boolean isContain = CollectionUtils.contains(list.iterator(),1);

        System.out.println(isContain);


    }

}
