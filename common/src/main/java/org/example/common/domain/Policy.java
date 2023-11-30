package org.example.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 保单
 * @author yuhang
 * @date 2023/11/16 11:20
 */
@Data
public class Policy implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 投保单号
     */
    private String applyFromId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 内部订单号
     */
    private String innerOrderNo;

    /**
     * 保费
     */
    private BigDecimal prem;

    /**
     * 保额
     */
    private BigDecimal amnt;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 是否上锁
     */
    private Boolean isLock;

    /**
     * 被保人姓名
     */
    private String insuredName;

    /**
     * 被保人信息
     */
    private Insured insured;
}
