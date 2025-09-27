package top.wgy.boot.redis.service;

public interface SmsService {
    /**
     * 发送短信
     *
     * @param phone 手机号
     */

    boolean sendSms(String phone);

}
