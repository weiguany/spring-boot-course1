package top.wgy.boot.config.tool;

import java.io.Serializable;

/**
 * 统一响应结果封装类
 * 用于接口返回标准化的数据格式
 * @param <T> 响应数据的类型
 */
public class Result<T> implements Serializable {
    // 状态码：200表示成功，其他表示错误
    private int code;
    // 响应消息：成功或错误的描述
    private String msg;
    // 响应数据：成功时返回的数据
    private T data;

    /**
     * 私有构造方法，防止直接实例化
     */
    private Result() {}

    /**
     * 成功响应的静态方法
     * @param data 要返回的数据
     * @param <T> 数据类型
     * @return 封装好的成功响应对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = "操作成功";
        result.data = data;
        return result;
    }

    /**
     * 成功响应的静态方法（不带数据）
     * @param <T> 数据类型
     * @return 封装好的成功响应对象
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 错误响应的静态方法
     * @param code 错误状态码
     * @param msg 错误消息
     * @param <T> 数据类型
     * @return 封装好的错误响应对象
     */
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.data = null;
        return result;
    }

    /**
     * 默认错误响应（500状态码）
     * @param msg 错误消息
     * @param <T> 数据类型
     * @return 封装好的错误响应对象
     */
    public static <T> Result<T> error(String msg) {
        return error(500, msg);
    }

    // getter和setter方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}