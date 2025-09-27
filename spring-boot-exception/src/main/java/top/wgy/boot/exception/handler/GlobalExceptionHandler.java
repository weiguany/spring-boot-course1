package top.wgy.boot.exception.handler;


import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import top.wgy.boot.exception.commom.Result;
import top.wgy.boot.exception.exception.ServerException;


/**
 * @Author: wgy
 * @Date: 2021/7/31 10:07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ============== 自定义异常处理 ==============
    @ExceptionHandler(ServerException.class)
    public Result<String> handleServerException(ServerException e) {
        // 这里可以按需记录日志
        log.warn("自定义异常: code={}, msg={}", e.getCode(), e.getMsg());
        return Result.error(e.getCode(), e.getMsg());
    }

    // ============== 参数校验: @Valid @RequestBody 触发（JSON 请求体） ==============
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream().findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("请求参数不合法");
        return Result.error(400, msg);
    }

    // ============== 参数校验: 表单/路径绑定失败（非 @RequestBody 场景） ==============
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException ex) {
        FieldError fe = ex.getFieldError();
        String msg = (fe != null) ? (fe.getField() + " " + fe.getDefaultMessage()) : "请求参数不合法";
        return Result.error(400, msg);
    }

    // ============== 单个参数校验: @RequestParam/@PathVariable 上的约束（如 @Min） ==============
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolation(ConstraintViolationException ex) {
        String msg = ex.getConstraintViolations().stream().findFirst().map(v -> v.getPropertyPath() + " " + v.getMessage()).orElse("请求参数不合法");
        return Result.error(400, msg);
    }

    // ============== 请求体反序列化/类型不匹配等常见 400 ==============
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    public Result<String> handleBadRequest(Exception ex) {
        return Result.error(400, "请求格式错误");
    }

    // ============== 兜底: 未知异常 ==============
    @ExceptionHandler(Exception.class)
    public Result<String> handleUnknown(Exception ex) {
        log.error("未知异常: ", ex);
        return Result.error(500, "服务器异常, 请稍后再试");
    }
}

