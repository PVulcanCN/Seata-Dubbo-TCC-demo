package org.example.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单
 *
 * @author yuhang
 * @date 2023/11/16 11:24
 */
@Data
@TableName(value = "seata_demo.order")
@ToString
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内部订单号
     */
    @TableField(value = "inner_order_no")
    private String innerOrderNo;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private String orderStatus;

    /**
     * 支付金额
     */
    @TableField(value = "pay_price")
    private BigDecimal payPrice;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Boolean isDelete;

    /**
     * 是否上锁
     */
    @TableField(value = "is_lock")
    private Boolean isLock;
}
