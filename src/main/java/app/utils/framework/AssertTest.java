package app.utils.framework;

import org.springframework.util.Assert;

/**
 * @Author: bai
 * @Date: 2021/11/25 16:17
 *
 * 断言工具类，不满足要求即抛出异常，代表程序已经非正常执行
 *
 */
public class AssertTest {

    public static void main(String[] args) {

        String str = "";
        //Assert.hasText(str,"字符串必须包含字符");


        //Assert.isNull(str,"必须为空");

        Assert.notNull(str,"字符串不为空");
    }

}
