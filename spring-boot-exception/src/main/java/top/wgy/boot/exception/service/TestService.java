package top.wgy.boot.exception.service;


import org.springframework.stereotype.Service;
import top.wgy.boot.exception.enums.ErrorCode;
import top.wgy.boot.exception.exception.ServerException;

/**
 *
 * @author wgy
 * @date 2020/12/27
 */
@Service
public class TestService {

    public void method1(){
        throw new ServerException("余额不足");
    }

    public void method2(){
        throw new ServerException(ErrorCode.FORBIDDEN);
    }
}
