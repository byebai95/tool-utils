package app.utils.framework;

import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

/**
 * @Author: bai
 * @Date: 2021/11/25 17:01
 */
public class IdGeneratorTest {
    public static void main(String[] args) {


        /**
         * JdkIdGenerator（）  jdk ID生成 uuid
         *
         * SimpleIdGenerator（） 从 0 开始累加
         *
         * AlternativeJdkIdGenerator() spring core 自定义id生成器，类似于 uuid
         */

        IdGenerator generator = new AlternativeJdkIdGenerator();


        for(int i=0;i<100;i++)
        System.out.println(generator.generateId());

    }
}
