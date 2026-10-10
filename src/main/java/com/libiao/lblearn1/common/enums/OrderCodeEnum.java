package com.libiao.lblearn1.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderCodeEnum {

    ORDER_DELETE_FAILED(10001, "订单删除失败");

    private final Integer code;
    private final String msg;
}
