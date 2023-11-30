package com.example.policy.mapper;


import org.example.common.domain.Insured;


public interface InsuredMapper {

    int insertSelective(Insured record);

    Insured selectByPrimaryKey(String applyFromId);

    int updateByPrimaryKeySelective(Insured record);

}