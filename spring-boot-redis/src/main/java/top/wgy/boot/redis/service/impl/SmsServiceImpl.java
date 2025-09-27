package top.wgy.boot.redis.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.wgy.boot.redis.cache.RedisCache;
import top.wgy.boot.redis.cache.RedisKeys;
import top.wgy.boot.redis.config.CloopenConfig;
import top.wgy.boot.redis.enums.ErrorCode;
import top.wgy.boot.redis.exception.ServerException;
import top.wgy.boot.redis.service.SmsService;
import top.wgy.boot.redis.utils.CommonUtils;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final CloopenConfig cloopenConfig;
    private final RedisCache redisCache;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return 发送成功返回true，失败返回false
     */
    @Override
    public boolean sendSms(String phone) {
        // 1.校验手机号
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PHONE_ERROR);
        }
        // 2.生成验证码，并存入 Redis，60秒有效
        int code = CommonUtils.generateCode();
        redisCache.set(RedisKeys.getSmsKey(phone), code, 60);
        log.info("发送短信验证码：{}", code);
        boolean flag = true;
        flag = send(phone, code);
        return flag;
    }

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 发送成功返回true，失败返回false
     */
    private boolean send(String phone, int code) {
        try {
            String serverIp = cloopenConfig.getServerIp();
            String serverPort = cloopenConfig.getPort();
            String accountSid = cloopenConfig.getAccountSid();
            String accountToken = cloopenConfig.getAccountToken();
            String appId = cloopenConfig.getAppId();
            String templateId = cloopenConfig.getTemplateId();

            CCPRestSmsSDK sdk = new CCPRestSmsSDK();
            sdk.init(serverIp, serverPort);
            sdk.setAccount(accountSid, accountToken);
            sdk.setAppId(appId);
            sdk.setBodyType(BodyType.Type_JSON);
            String[] datas = {String.valueOf(code), "1"};
            HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, datas, "1234", UUID.randomUUID().toString());

            if ("000000".equals(result.get("statusCode"))) {
                log.info("短信发送成功");
                return true;
            } else {
                log.error("短信发送失败，错误码：{}，错误信息：{}", result.get("statusCode"), result.get("statusMsg"));
                return false;
            }
        } catch (Exception e) {
            log.error("短信发送过程中发生异常", e);
            return false;
        }
    }
}
