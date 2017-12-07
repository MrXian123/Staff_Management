package com.test.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.Admin;
import com.test.service.AdminService;




@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminservice;
	
	/**
	 *管理员登陆 
	 * @param request
	 * @return 登陆成功返回true,失败返回false
	 */
	@ResponseBody
	@RequestMapping(value ="/admin_login", method = RequestMethod.POST)
	public boolean adminLogin(HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println(name);
		String password = request.getParameter("password");
		
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(password);
		
		if(adminservice.SelectAdminByT(admin) != null) {
			request.getSession().setAttribute("admin", name);
			System.out.println("登录成功过！session中存入管理员："+name);
			return true;
		}else {
			System.out.println("登录失败！用户名或密码错误。");
			return false;
		}

	}
	
	/**
	 * 获取管理员名字
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/admin/admin_name", method = RequestMethod.GET)
	public JSON getAdmin(HttpServletRequest request) {
		String name = (String) request.getSession().getAttribute("admin");
		JSONObject json = new JSONObject();
		json.put("name", name);
		return json;
	}
	
	/**
	 * 用户退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/admin/loginout", method = RequestMethod.GET)
	public String loginOut(HttpServletRequest request) {
		request.getSession().removeAttribute("name");
		return "login";
	}

}
