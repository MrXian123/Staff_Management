package com.test.mapper;

import java.util.List;

import com.test.entity.Department;
import com.test.entity.Employee;


public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);
    
    List<Department> selectDept();
    
    int updateByPrimaryKey(Department record);
    

}