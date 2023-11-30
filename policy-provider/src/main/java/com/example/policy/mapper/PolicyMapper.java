package com.example.policy.mapper;


import org.example.common.domain.Policy;

public interface PolicyMapper {

    int insertSelective(Policy record);

    Policy selectByPrimaryKey(String applyFromId);

    int updateByPrimaryKeySelective(Policy record);

}