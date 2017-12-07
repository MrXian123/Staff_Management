package com.test.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.Employee;
import com.test.entity.PageBean;
import com.test.mapper.EmployeeMapper;
import com.test.utils.DateUtil;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper emapper;
	
	/**
	 * 向数据库中添加员工
	 * @param employee 需要添加员工对象
	 * @return
	 */
	@Transactional
	public void addEmployee(Employee employee) throws Exception{
			emapper.insert(employee);
	}
	
	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	public void modifyEmployee(Employee employee) throws Exception{
		emapper.updateByPrimaryKeySelective(employee) ;
	}
	
	/**
	 * 分页查询员工
	 * @param pageBean
	 * @return
	 */
	public List<Map<String, Object>> selectEmployee(PageBean pageBean){
		List<Map<String, Object>> list = emapper.selectAllByPage(pageBean.getStart(),
				pageBean.getRows());
		for(Map map :list) {
			map.put("join_date", DateUtil.format((Date)map.get("join_date")));
		}
		return list;
	}
	
	/**
	 *查询记录总数
	 * @return
	 */
	public int countRecord() {
		return emapper.selectRecordCount();
	}

	/**
	 * 根据员工id删除员工
	 * @param id
	 * @return
	 */
	public int deletEmployee(int id) {
		return emapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据员工姓名查询员工
	 * @param name
	 * @return
	 */
	public List<Map<String, Object>> selectEmployeeByName(Employee e){
		List<Map<String, Object>> list = emapper.selectEployeeByName(e);
		for(Map map :list) {
			map.put("join_date", DateUtil.format((Date)map.get("join_date")));
		}
		return list;
	}
}
