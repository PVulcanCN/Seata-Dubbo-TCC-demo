package com.example.product.controller;


import com.example.product.entity.ProductSalesReq;
import com.example.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    /**
     * 产品销售
     *
     * @param productSalesReq 请求
     * @return 结果
     */
    @PostMapping("/product/sales")
    public Boolean sales(@RequestBody ProductSalesReq productSalesReq) {
        productService.sales(productSalesReq);
        return true;
    }
}
