package org.example.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品
 * @author yuhang
 * @date 2023/11/16 11:31
 */
@Data
public class Product implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 销售次数
     */
    private Integer salesCount;

    /**
     * 保费
     */
    private BigDecimal prem;

    /**
     * 保额
     */
    private BigDecimal amnt;
}
