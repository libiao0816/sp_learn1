package com.libiao.lblearn1.common.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "结果基类")
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    @Schema(description = "状态码")
    private Integer code;

    @Schema(description = "状态描述")
    private String msg;

    @Schema(description = "数据")
    private T data;

    public static <T> Result<T> Success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = "success";
        result.data = data;
        return result;
    }

    public static <T> Result<T> Error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> Error(String msg) {
        Result<T> result = new Result<>();
        result.code = 500;
        result.msg = msg;
        return result;
    }
}
