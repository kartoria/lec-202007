package kr.or.ddit.employee.service;

import java.util.Set;

import kr.or.ddit.employee.dao.EmployeeDaoImpl;
import kr.or.ddit.employee.dao.IEmployeeDao;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeServiceImpl implements IEmployeeService{
	IEmployeeDao empDao = new EmployeeDaoImpl();
	public Set<EmployeeVO> selectEmployee() {
		return empDao.selectEmployee();
	}
}
