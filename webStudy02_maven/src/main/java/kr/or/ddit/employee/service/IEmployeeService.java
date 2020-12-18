package kr.or.ddit.employee.service;

import java.util.List;
import java.util.Set;

import kr.or.ddit.vo.EmployeeVO;

public interface IEmployeeService {
	
	public Set<EmployeeVO> selectEmployee();
}
