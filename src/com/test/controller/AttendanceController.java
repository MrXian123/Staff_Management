package com.test.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.Attendances;
import com.test.entity.PageBean;
import com.test.service.AttendanceService;
import com.test.utils.DateUtil;



@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	/**
	 * 员工上班打卡
	 * @param eId 员工Id
	 * @return
	 */
	public String addAttendRecord(Integer eId) {
		String message = "";
		try {
		 attendanceService.addAttendanceRecord(eId);
		 message = "上班打卡成功";
		}catch (Exception e) {
			e.printStackTrace();
			message = "下班打卡失败,请检查机器是否故障";
		}
		return message;
	}
	

	/**
	 * 员工上下班打卡接口
	 * @param eId 员工Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addrecord")
	public JSON addEndTimeRecord(@RequestParam("eId") Integer eId) {
		String message = "";
		JSONObject json = new JSONObject();
		Attendances att = attendanceService.getAttendanceRecord(eId);
		if(att == null) {
			message = addAttendRecord(eId);
		}else {
			if(attendanceService.addAttendanceRecord2(att)) {
				message = "下班打卡成功";
			}else {
				message = "下班打卡成失败，请检查机器是否故障";
			}
		}
		json.put("message", message);
		return json;
	}
	
	
	/**
	 * 查询某个员工的考勤记录
	 * @param eId 员工Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/getrecord", method = RequestMethod.POST)
	public JSON getAttendanceRecord(@RequestParam("eId") Integer eId) {
		JSONObject json = new JSONObject();
		try {
			JSONArray array = (JSONArray) JSON.toJSON(attendanceService.getAttendanceRecordByeId(eId));
			json.put("rows", array);
			json.put("total", array.size());
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 查询某一天的考勤记录
	 * @param eId 员工Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/selectDateRecord", method = RequestMethod.POST)
	public JSON selectDateRecord(@RequestParam("date") String date) {
		JSONObject json = new JSONObject();
		try {
			JSONArray array = (JSONArray) JSON.toJSON(attendanceService.getAttendanceRecordByeDate(DateUtil.StringToDate(date)));
			json.put("rows", array);
			json.put("total", array.size());
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 根据员工姓名查询考勤记录
	 * @param ename 员工姓名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/select_attendance_byename", method = RequestMethod.POST)
	public JSON selectRecordByEname(@RequestParam("ename") String ename) {
		JSONObject json = new JSONObject();
		try {
			JSONArray array = (JSONArray) JSON.toJSON(attendanceService.getAttendanceByEname(ename));
			json.put("rows", array);
			json.put("total", array.size());
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 查询迟到缺勤记录
	 * @param   String status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/selectRecordByStatus", method = RequestMethod.POST)
	public JSON selectRecordByStatus(@RequestParam("status") String status) {
		JSONObject json = new JSONObject();
		try {
			JSONArray array = (JSONArray) JSON.toJSON(attendanceService.getAttendanceState(Integer.parseInt(status)));
			json.put("rows", array);
			json.put("total", array.size());
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 分页查询考勤记录
	 * @param page 起始页
	 * @param rows 每页数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/select_attendance", method = RequestMethod.POST)
	public JSON selectEmployee(@RequestParam("page") int page, @RequestParam int rows) {
		JSONObject json = new JSONObject();
		try {
			System.out.println("page"+page+"rows:"+rows);
			JSONArray array = (JSONArray) JSON.toJSON(attendanceService.getAttendancePage(new PageBean(page, rows)));
			json.put("rows", array);
			int count = attendanceService.getCount();
			json.put("total", count);
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	

}
