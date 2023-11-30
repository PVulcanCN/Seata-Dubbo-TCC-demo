package com.example.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.common.domain.Order;


public interface OrderMapper extends BaseMapper<Order> {

    int insertSelective(Order order);

    Order selectByPrimaryKey(String innerOrderNo);

    int updateByPrimaryKeySelective(Order record);

}