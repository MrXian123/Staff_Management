package com.test.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.ConfigMapper;

@Service
public class ConfigService {

	@Autowired
	private ConfigMapper configMapper;
	
	/**
	 * 获取系统配置中的上班时间
	 * @return
	 */
	public Date getStartWorkTime() {
		return configMapper.selectByPrimaryKey(1).getTime();
	}
	
	/**
	 * 获取系统配置中的下班时间
	 * @return
	 */
	public Date getEndWorkTime() {
		return configMapper.selectByPrimaryKey(2).getTime();
	}
	
}
