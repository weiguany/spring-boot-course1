package top.wgy.boot.redis.cache;


/**
 * Redis缓存的Key常量类
 *
 * @author wgy
 * @date 2023/12/14 10:20
 */
public class RedisKeys {
    /**
     * 验证码 Key
     */
    public static String getSmsKey(String phone){
        return "sms:captcha:" + phone;
    }
}
