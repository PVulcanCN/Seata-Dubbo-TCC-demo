package com.example.product.mapper;


import org.example.common.domain.Product;


public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

}