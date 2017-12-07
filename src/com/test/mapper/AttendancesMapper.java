package com.test.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.entity.Attendances;

public interface AttendancesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attendances record);

    int insertSelective(Attendances record);

    Attendances selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attendances record);

    int updateByPrimaryKey(Attendances record);
    
    Attendances selectByAttendances(Attendances record);
    
    int selectRecordCount();

    List<Map<String , Object>> selectAllByParam(@Param("eid") Integer eid,@Param("date") Date date,
       		@Param("status") Integer status,@Param("start") Integer start,@Param("rows") Integer rows,
       		@Param("ename") String ename);
   
}