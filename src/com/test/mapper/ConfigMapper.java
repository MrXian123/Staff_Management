package com.test.mapper;

import java.util.List;

import com.test.entity.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
    
    List<Config> selectAll();

}