package kr.or.ddit.employee.dao;

import java.util.List;

import kr.or.ddit.vo.EmployeeVO;

public interface IEmployeeDAO {
	public List<EmployeeVO> selectEmployeeHierarchy(String manager_id);
}
