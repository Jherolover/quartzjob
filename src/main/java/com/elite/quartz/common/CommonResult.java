package com.elite.quartz.common;

import com.elite.quartz.constants.Status;
import lombok.Data;

/**
 *返回结果类统一封装
 */
@Data
public class CommonResult {

    // 状态码
    private Integer code;
    // 消息
    private String msg;
    // 数据对象
    private Object data;

    private Integer total;

    /**
     * 无参构造器
     */
    public CommonResult() {
        super();
    }

    public CommonResult(Status status) {
        super();
        this.code = status.code;
        this.msg = status.message;
    }

    public CommonResult result(Object result) {
        this.data = result;
        return this;
    }

    public CommonResult message(String message) {
        this.msg = message;
        return this;
    }
    public CommonResult total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 只返回状态，状态码，消息
     *
     * @param code
     * @param message
     */
    public CommonResult(Integer code, String message) {
        super();
        this.code = code;
        this.msg = message;
    }

    /**
     * 只返回状态，状态码，数据对象
     *
     * @param code
     * @param result
     */
    public CommonResult(Integer code, Object result) {
        super();
        this.code = code;
        this.data = result;
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     *
     * @param code
     * @param message
     * @param result
     */
    public CommonResult(Integer code, String message, Object result) {
        super();
        this.code = code;
        this.msg = message;
        this.data = result;
    }
    /**
     * 成功
     * @param code
     * @param message
     * @param result
     * @return
     */
    public static CommonResult success(Integer code, String message, Object result) {

       return new CommonResult(code,message,result);
    }

    /**
     * 只返回代码值和数据
     * @param code
     * @param result
     */
    public static CommonResult success(Integer code, Object result) {
     return  new CommonResult(code,result);
    }
    /**
     * 只返回代码值和数据
     * @param code
     * @param msg
     */
    public static CommonResult fail(Integer code, String msg) {
        return  new CommonResult(code,msg);
    }
}