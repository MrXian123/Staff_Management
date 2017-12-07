package com.test.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.Department;
import com.test.entity.Employee;
import com.test.service.DepartmentService;

@RequestMapping("/admin")
@Controller
public class DeptController {

	@Autowired
	private DepartmentService departmentService;

	/**
	 * 查询所有部门信息
	 * @param request
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/select_department", method = RequestMethod.POST) // 查询所有部门
	public JSON selectDepartment() {
		JSONArray array = (JSONArray) JSON.toJSON(departmentService.selectDept());// 将数据转换为JSONarray
		System.out.println(array.toJSONString());
		return array;
	}
	 
	
	/**
	 * 添加部门
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/add_department", method = RequestMethod.POST)
	public JSON addDepartment(HttpServletRequest request) {
		String name = request.getParameter("dname");
		String number = request.getParameter("ddesc");
		JSONObject result = new JSONObject();
		Department department = new Department();
		department.setDname(name);
		department.setDdesc(number);
		boolean res = departmentService.addDept(department);// 添加部门，成功返回true，失败返回false
		if (res) {
			result.put("success", "添加成功");
			System.out.println("添加成功");
		} else {
			result.put("errorMsg", "添加失败");
			System.out.println("添加失败");
		}
		return result ;
	}

	/**
	 * 删除部门
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/del_department", method = RequestMethod.POST)
	public JSON delDepartment(@RequestParam("delId") int delId) {
		// String name =request.getParameter("name");
		// String number = request.getParameter("sex");

		Department department = new Department();
		department.setId(delId);

		JSONObject result = new JSONObject();
		boolean res = departmentService.delDept(department);// 删除部门，成功返回true，失败返回false
		 if(res){
				result.put("success", "true");
			}else{
				result.put("errorMsg", "删除失败或该部门还有人员未调动");
		 }
		 return result;
	}

	/**
	 * 更新部门信息
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/updata_department", method = RequestMethod.POST)
	public JSON  updataDepartment(HttpServletRequest request,HttpServletResponse response) {// 更新部门信息
		int  id = Integer.parseInt(request.getParameter("id"));
		String name =request.getParameter("dname");
		String ddesc = request.getParameter("ddesc");
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("UTF-8");

		Department department = new Department();
		department.setId(id);
		department.setDname(name);
		department.setDdesc(ddesc);

		boolean res = departmentService.updataDept(department);// 更新部门，成功返回true，失败返回false
		if (res) {
			System.out.println("更新成功");
			result.put("success", "更新成功");

		} else {
			System.out.println("更新失败");
			result.put("errorMsg", "更新失败");
		}
		return result;
	}
	 

}
