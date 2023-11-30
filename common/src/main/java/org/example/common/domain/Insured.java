package org.example.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 被保人
 * @author yuhang
 * @date 2023/11/16 11:24
 */
@Data
public class Insured implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 投保单号
     */
    private String applyFromId;

    /**
     * 被保人姓名
     */
    private String insuredName;

    /**
     * 被保人性别
     */
    private String insuredSex;
}
