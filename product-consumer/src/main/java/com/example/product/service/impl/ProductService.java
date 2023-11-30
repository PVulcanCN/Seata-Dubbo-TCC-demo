package com.example.product.service.impl;


import com.example.product.entity.ProductSalesReq;
import com.example.product.mapper.ProductMapper;
import com.example.product.service.IProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.common.domain.Insured;
import org.example.common.domain.Order;
import org.example.common.domain.Policy;
import org.example.common.domain.Product;
import org.example.common.dubbo.api.IOrderService;
import org.example.common.dubbo.api.IPolicyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService implements IProductService {

    @DubboReference(interfaceClass = IPolicyService.class, check = false)
    private final IPolicyService policyService;

    @DubboReference(interfaceClass = IOrderService.class, check = false)
    private final IOrderService orderService;

    private final ProductMapper productMapper;

    @Override
    @GlobalTransactional(name = "sales", rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public Boolean sales(ProductSalesReq productSalesReq) {
        log.info("ProductService#sales#XID:{}", RootContext.getXID());
        Product product = productMapper.selectByPrimaryKey(productSalesReq.getProductId());
        String innerOrderNo = createOrder(product);
        createPolicy(productSalesReq, product, innerOrderNo);
        modifyProduct(product);
        return true;
    }

    private void modifyProduct(Product product) {
        product.setSalesCount(product.getSalesCount() + 1);
        productMapper.updateByPrimaryKeySelective(product);
    }

    private String createOrder(Product product) {
        Order order = new Order();
        order.setOrderStatus("1");
        order.setPayPrice(product.getPrem());
        return orderService.createOrder(null, order);
    }

    private void createPolicy(ProductSalesReq productSalesReq, Product product, String innerOrderNo) {
        Policy policy = new Policy();
        policy.setProductId(product.getId());
        policy.setAmnt(product.getAmnt());
        policy.setPrem(policy.getPrem());
        policy.setInnerOrderNo(innerOrderNo);
        // 构建被保人信息
        Insured insured = new Insured();
        insured.setInsuredName(productSalesReq.getInsuredName());
        insured.setInsuredSex(productSalesReq.getInsuredSex());
        policy.setInsured(insured);
        policyService.createPolicy(null, policy);
    }
}
