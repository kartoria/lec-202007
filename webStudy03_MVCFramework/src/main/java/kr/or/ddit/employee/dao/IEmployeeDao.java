package kr.or.ddit.employee.dao;

import java.util.Set;

import kr.or.ddit.vo.EmployeeVO;

public interface IEmployeeDao {
	
	public Set<EmployeeVO> selectEmployee();
}
