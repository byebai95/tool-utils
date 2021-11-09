package app.utils;

import java.util.UUID;

public class UuidUtils {

    /**
     * 使用 UUID 获取唯一 id ,目的是用于分布式环境中唯一的标志码， 由 32个16进制数组成
     *
     * 组成： 当前日期+时间 , 时钟序列 ， 全局唯一的机器识别码
     * @return
     */
    public static String getUniqueId(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
