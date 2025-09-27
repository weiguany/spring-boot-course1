package top.wgy.boot.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 * @author wgy
 * @Date 2021/9/27
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "登录失效，请重新登录"),
    FORBIDDEN(403, "无权限访问"),
    NOT_FOUND(404, "资源不存在"),
    BAD_REQUEST(400, "请求参数不合法"),
    CONFLICT(409, "数据冲突"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试"),
    UNKNOWN(999, "系统繁忙，请稍后再试");

    private final int code;
    private final String msg;
}
