package com.example.product.service;


import com.example.product.entity.ProductSalesReq;


public interface IProductService {

    /**
     * 产品销售
     *
     * @param productSalesReq 请求
     * @return 成功或者失败
     */
    Boolean sales(ProductSalesReq productSalesReq);
}
