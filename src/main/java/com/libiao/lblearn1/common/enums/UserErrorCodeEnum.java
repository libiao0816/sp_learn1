package com.libiao.lblearn1.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCodeEnum {

    USER_NOT_FOUND(1001, "用户不存在"),

    PASSWORD_ERROR(1002, "密码错误"),

    USERNAME_ALREADY_EXISTS(1003, "用户名已存在");

    private final Integer code;

    private final String msg;
}
