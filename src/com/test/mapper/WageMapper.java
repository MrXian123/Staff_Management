package com.test.mapper;

import com.test.entity.Wage;

public interface WageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wage record);

    int insertSelective(Wage record);

    Wage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wage record);

    int updateByPrimaryKey(Wage record);
}