package com.libiao.lblearn1.domain.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cart {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer quantity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
