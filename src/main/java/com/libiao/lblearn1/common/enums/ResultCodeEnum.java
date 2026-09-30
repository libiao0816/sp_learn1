package com.libiao.lblearn1.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(500, "系统繁忙，请稍后重试"),
    BAD_REQUEST(400, "请求参数错误"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    NOT_FOUND(404, "资源不存在"),
    AUTHENTICATION_FAILED(401, "认证失败"),
    FORBIDDEN(403, "权限不足");

    private final Integer code;
    private final String msg;
}
