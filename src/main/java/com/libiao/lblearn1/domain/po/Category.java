package com.libiao.lblearn1.domain.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Long id;

    private String name;

    private Integer sort;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}
