package com.dx.avserver.dto;

import lombok.Data;

/**
 * 一般的服务器返回客户端信息
 */
@Data
public class ResponseDto {
    private int status;
//    private String error;
    private String message;
    private Object data;

    ResponseDto(int status, String msg) {
        this.status = status;
        this.message = msg;
    }

    public static ResponseDto SUCCESS() {
        return new ResponseDto(200, "OK");
    }

    public static ResponseDto FAIL() {
        return new ResponseDto(10001, "Fail");
    }

    public static ResponseDto LOGIN_FAIL() {
        return new ResponseDto(10100, "登陆失败");
    }

    public static ResponseDto LOGIN_FAIL_PWD_ERROR() {
        return new ResponseDto(10101, "登陆失败-密码错误");
    }

}
