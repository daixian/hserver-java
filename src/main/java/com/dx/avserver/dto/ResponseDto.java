package com.dx.avserver.dto;

import lombok.Data;

/**
 * 一般的服务器返回客户端信息
 */
@Data
public class ResponseDto {

    private Status status = Status.FAIL;

    private Object data;

    /**
     * 服务器处理状态定义
     */
    public enum Status {
        SUCCESS(10000, "成功"),
        FAIL(10001, "失败"),
        LOGIN_FAIL(10100, "登陆失败"),
        LOGIN_FAIL_PWD_ERROR(10101, "登陆失败-密码错误");

        private int errCode;
        private String errMsg;

        Status(int errCode, String errMsg) {
            this.errCode = errCode;
            this.errMsg = errMsg;
        }
    }
}
