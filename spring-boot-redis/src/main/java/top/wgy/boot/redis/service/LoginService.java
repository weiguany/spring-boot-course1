package top.wgy.boot.redis.service;

import top.wgy.boot.redis.dto.LoginRequest;
import top.wgy.boot.redis.vo.LoginResponse;

public interface LoginService {
    /**
     * 验证码登录
     *
     * @param loginRequest 登录请求参数
     * @return 登陆响应
     */
    LoginResponse login(LoginRequest loginRequest);
}
