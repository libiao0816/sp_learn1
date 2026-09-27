package com.libiao.lblearn1.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Product {

    private long id;

    private String name;

    private long categoryId;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String image;

    private Short status;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Integer deleted;
}
