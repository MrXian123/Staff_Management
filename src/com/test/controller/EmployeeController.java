package com.test.controller;

import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.test.entity.Employee;
import com.test.entity.PageBean;
import com.test.service.AttendanceService;
import com.test.service.EmployeeService;

@RequestMapping("/admin")
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService eservice;
	
	/**
	 * 保存用户信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save_employee", method = RequestMethod.POST)
	public JSON addEmployee(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			String id = request.getParameter("id");
			String name =request.getParameter("name");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String number = request.getParameter("number");
			Integer deptId = Integer.parseInt(request.getParameter("departmentId"));
			
			Employee employee = new Employee();
			employee.setName(name);
			employee.setSex(sex);
			employee.setBirthday(birthday);
			employee.setDepartmentId(deptId);
			employee.setNumber(number);
			
			if(id==null || "".equals(id)) {
				//添加用户
				employee.setJoinDate(new Date());
			    eservice.addEmployee(employee);
			}else {
				//修改用户
				employee.setId(Integer.parseInt(id));
			    eservice.modifyEmployee(employee);
			}	
			result.put("Msg", "保存成功");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("Msg", "保存的员工编号已存在");
		}
		return result;
	}
	
	
	/**
	 * 分页查询员工信息
	 * @param page 起始页
	 * @param rows 每页数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "select_employee", method = RequestMethod.POST)
	public JSON selectEmployee(@RequestParam("page") int page, @RequestParam int rows) {
		JSONObject json = new JSONObject();
		try {
			JSONArray array = (JSONArray) JSON.toJSON( eservice.selectEmployee(new PageBean(page, rows)));
			json.put("rows", array);
			int count = eservice.countRecord();
			json.put("total", count);
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 根据员工姓名查询员工
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/select_employee_byname", method = RequestMethod.POST)
	public JSON selectEmployeeByName(HttpServletRequest request) {
		String name = request.getParameter("name");
		JSONObject json = new JSONObject();
		System.out.println(name);
		Employee employee = new Employee();
		employee.setName(name);
		try {
			JSONArray array = (JSONArray) JSON.toJSON(eservice.selectEmployeeByName(employee));
			json.put("rows", array);
			int count = eservice.countRecord();
			json.put("total", array.size());
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	

	/**
	 * 根据员工部门查询员工
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/select_employee_bydeptId", method = RequestMethod.POST)
	public JSON selectEmployeeByDept(@RequestParam("deptId") Integer deptId) {
		JSONObject json = new JSONObject();
		System.out.println(deptId);
		Employee employee = new Employee();
		employee.setDepartmentId(deptId);
		try {
			JSONArray array = (JSONArray) JSON.toJSON(eservice.selectEmployeeByName(employee));
			json.put("rows", array);
			int count = eservice.countRecord();
			json.put("total", array.size());
			System.out.println(json.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 删除员工
	 * @param delId 需要删除员工的id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete_employee", method = RequestMethod.POST)
	public JSON deletEmployee(@RequestParam("delId") int delId) {
		JSONObject result = new JSONObject();
		try {
		 int delNums= eservice.deletEmployee(delId);
		 if(delNums==1){
				result.put("success", "true");
			}else{
				result.put("errorMsg", "删除失败");
		 }
		}catch (Exception e) {
			e.printStackTrace();
			result.put("errorMsg", "删除失败");
		}
		 return result;
	}
	

}
