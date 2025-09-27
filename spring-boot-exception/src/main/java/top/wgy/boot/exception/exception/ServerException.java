package top.wgy.boot.exception.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import top.wgy.boot.exception.enums.ErrorCode;

import java.io.Serial;

/**
 * 服务异常
 * @author wgy
 * @Date 2020/12/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServerException(String msg) {
        super(msg);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(String msg, Throwable e){
        super(msg, e);
        this.code =ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }
}
