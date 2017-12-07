package com.test.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.entity.Department;
import com.test.entity.Employee;
import com.test.mapper.DepartmentMapper;
import com.test.mapper.EmployeeMapper;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;// 操作部门的接口接口

	@Autowired
	private EmployeeMapper employeeMapper;// 操作员工接口

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<Department> selectDept() {// 调用查询部门接口
		return departmentMapper.selectDept();
	}

	/**
	 * 插入部门
	 * 
	 * @param department
	 * @return
	 */
	public boolean addDept(Department department) {
		try {
			departmentMapper.insert(department);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 根据部门id删除部门
	 * 
	 * @param department
	 * @return
	 */
	public boolean delDept(Department department) {
		try {
			System.out.println(department.getId());
			List<Employee> selDeptEmployee=employeeMapper.selectAllByDeptId(department.getId());
			System.out.println(selDeptEmployee.size());
			if(selDeptEmployee.size()<1){
				System.out.println("空部门");
				int rs=departmentMapper.deleteByPrimaryKey(department.getId());//根据部门id删除该部门
				if(rs>0){
					System.out.println(rs);
					return true;
				}else {
					return false;
				}
			}else {
				System.out.println("该部门还有人员未调动");
				return false;
			}
		} catch (Exception e) {
			System.out.println("删除失败抛错");
			return false;
		}

	}
	
	/**
	 * 根据部门id更新部门信息
	 * 
	 * @param department
	 * @return
	 */
	public boolean updataDept(Department department) {
		try {
			 departmentMapper.updateByPrimaryKey(department);
			 System.out.println("更新成功");
			 return true;
		} catch (Exception e) {
			System.out.println("更新抛错");
			return false;
		}

	}

}
