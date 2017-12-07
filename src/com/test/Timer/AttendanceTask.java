package com.test.Timer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.entity.Attendances;
import com.test.entity.Employee;
import com.test.mapper.EmployeeMapper;
import com.test.service.AttendanceService;

@Component  
public class AttendanceTask {

	@Autowired
	private EmployeeMapper emapper;
	
	@Autowired
	private AttendanceService aService;
	
	@Scheduled(cron="0 0 23 ? * MON-FRI"   ) //间隔5秒执行  
	public void taskCycle(){ 
		System.out.println("定时器执行..............");
		List<Employee> list = emapper.selectAll();
		for(Employee e : list) {
			Attendances a = aService.getAttendanceRecord(e.getId());
			if(a == null ) {
				System.out.println("id为："+e.getId()+" 的员工今天没有打卡");
				aService.addAbsenceRecord(e.getId());
			}
		}
		 
	}  
}
