package com.test.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Attendances;
import com.test.entity.Employee;
import com.test.entity.PageBean;
import com.test.entity.Attendances;
import com.test.mapper.AttendancesMapper;
import com.test.mapper.AttendancesMapper;
import com.test.mapper.ConfigMapper;
import com.test.mapper.EmployeeMapper;
import com.test.utils.DateUtil;

@Service
public class AttendanceService {

	@Autowired
	private AttendancesMapper attendanceMapper;
	
	@Autowired
	private ConfigService configService;

	/**
	 * 添加员工上班记录
	 * @param employId
	 */
	public boolean addAttendanceRecord(Integer employId) {
		Attendances attendance = new Attendances();
		try {
			String nowDate = DateUtil.formatTime(new Date());
			String startTime = DateUtil.formatTime(configService.getStartWorkTime());
			attendance.setEid(employId);
			attendance.setDate(new Date());
			attendance.setStarttime(new Date());
			
		if (DateUtil.compare_date(nowDate, startTime) == 1) {
			double lateTime = DateUtil.betweenHours(startTime, nowDate);
			System.out.println("id为" + employId + "的员工上班迟到：" + lateTime + "小时");
			attendance.setStatus(1);
			attendance.setLatetime(lateTime);
		} else {
			attendance.setStatus(0);
		}
		 //向数据库中添加记录
			attendanceMapper.insertSelective(attendance);
			System.out.println("Id为：" + employId + "的上班记录添加成功");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 添加员工下班记录
	 * @param employId
	 */
	public boolean addAttendanceRecord2(Attendances attendance) {
		try {
			String nowDate = DateUtil.formatTime(new Date());
			String endTime = DateUtil.formatTime(configService.getEndWorkTime());
			attendance.setEndtime(new Date());
			double Time = DateUtil.betweenHours(endTime, nowDate);
			if (Time > 1.0) {
				attendance.setOvertime(Time);
			} else if (Time < 0) {
				if (attendance.getStatus() == 1) {
					attendance.setStatus(4);
				} else {
					attendance.setStatus(3);
				}
			}
			//更新数据库中的数据
			attendanceMapper.updateByPrimaryKeySelective(attendance);
			System.out.println("Id为：" + attendance.getEid() + "的下班记录添加成功");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 添加员工的缺勤记录
	 * @param employId
	 * @return
	 */
	public void addAbsenceRecord(Integer employId) {
		Attendances attendance = new Attendances();
		attendance.setEid(employId);
		attendance.setStatus(2);
		attendance.setDate(new Date());
		try {
			attendanceMapper.insertSelective(attendance);
			System.out.println("id为： "+employId +" 的员工缺勤记录添加成功。");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用员工当天的上班记录
	 * @param eid
	 * @return
	 */
	public Attendances getAttendanceRecord(Integer eid) {
		String date = DateUtil.format(new Date());
		Attendances attendance = new Attendances();
		attendance.setEid(eid);
		attendance.setDate(DateUtil.StringToDate(date));
		return attendanceMapper.selectByAttendances(attendance);
	}
	
	/**
	 * 根据员工的id查询考勤记录
	 * @param eid
	 * @return
	 */
	public List<Map<String,Object>> getAttendanceRecordByeId(Integer eid){
		List<Map<String,Object>> list = attendanceMapper.selectAllByParam(eid,null,null,null, null,null);
		return list;
	}
	
	/**
	 * 根据所有員工的某一天查询考勤记录
	 * @param eid
	 * @return
	 */
	public List<Map<String,Object>> getAttendanceRecordByeDate(Date date){
		List<Map<String,Object>> list = attendanceMapper.selectAllByParam(null,date,null,null, null,null);
		return list;
	}
	
	/**
	 * 查詢迟到、缺勤的记录
	 * @param eid
	 * @return
	 */
	public List<Map<String,Object>> getAttendanceState(Integer status ){
		List<Map<String,Object>> list = attendanceMapper.selectAllByParam(null,null,status,null, null,null);
		return list;
	}
	
	/**
	 * 分页查询考勤记录
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Map<String,Object>> getAttendancePage(PageBean page){
		List<Map<String,Object>> list = 
				attendanceMapper.selectAllByParam(null,null,null,page.getStart(),page.getRows(),null);
		return list;
	}
	
	/**
	 * 根据员工姓名查询考勤记录
	 * @param ename
	 * @return
	 */
	public  List<Map<String,Object>> getAttendanceByEname(String ename){
		List<Map<String,Object>> list = 
				attendanceMapper.selectAllByParam(null,null,null,null,null,ename);
		return list;
	}
	
	/**
	 * 获取数据库中的记录总数
	 * @return
	 */
	public int getCount() {
		return attendanceMapper.selectRecordCount();
	}
}
