package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.entity.Employee;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    //分页查询数据库
    List<Map<String, Object>> selectAllByPage(@Param("start") int start,@Param("rows") int rows);
    
    //根据部门id查询该部门下的所有员工
    List<Employee> selectAllByDeptId(Integer id);
    
    //查询表表中记录总条数
    int selectRecordCount();
    
    //根据员工
    List<Map<String, Object>> selectEployeeByName(Employee employee);
    
    //查询所有员工
    List<Employee> selectAll();
    
}