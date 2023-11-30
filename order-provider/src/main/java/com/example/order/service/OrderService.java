package com.example.order.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.order.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.common.domain.Order;
import org.example.common.dubbo.api.IOrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 订单service
 */
@Slf4j
@DubboService(interfaceClass = IOrderService.class)
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(BusinessActionContext businessActionContext, Order order) {
        log.info("这是orderTry:{}", RootContext.getXID());
        // 生成订单号
        String innerOrderNo = UUID.randomUUID().toString().replaceAll("-", "");
        // 预处理投保单
        order.setInnerOrderNo(innerOrderNo);
        //order.setIsLock(Boolean.TRUE);
        orderMapper.insert(order);
        // 设置上下文
        businessActionContext.setUpdated(Boolean.TRUE);
        businessActionContext.getActionContext().put("order",order);
        return innerOrderNo;
    }

    @Override
    public boolean commitOrder(BusinessActionContext businessActionContext) {
        log.info("这是orderCommit:{}",businessActionContext.getXid());
        return true;
    }

    @Override
    public boolean rollBackOrder(BusinessActionContext businessActionContext) {
        log.info("这是orderRollBack:{}",businessActionContext.getXid());
        // 获取订单信息
        String context = JSONObject.toJSONString(businessActionContext.getActionContext("order"));
        Order request = JSONObject.parseObject(context, Order.class);
        // 删除订单
        Order order = new Order();
        order.setIsDelete(Boolean.TRUE);
        order.setInnerOrderNo(request.getInnerOrderNo());

        orderMapper.update(order,new LambdaQueryWrapper<Order>().eq(Order::getInnerOrderNo,order.getInnerOrderNo()));
        return true;
    }
}
