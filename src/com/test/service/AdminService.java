package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Admin;
import com.test.mapper.AdminMapper;

@Service
public class AdminService {

	@Autowired
	private AdminMapper adminmapper;
	
	/**
	 * 根据对象查询管理信息
	 * @param admin
	 * @return 
	 */
	public Admin SelectAdminByT(Admin admin) {
		Admin DBadmin = adminmapper.selectByAdmin(admin);
		return DBadmin;
	}
	
	
}
