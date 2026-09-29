package com.libiao.lblearn1.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class Order {
    private Long id;

    private String orderNo;

    private Long userId;

    private BigDecimal totalAmount;

    /** 状态: 0-待支付 1-已支付 2-已发货 3-已完成 4-已取消 */
    private Short status;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
