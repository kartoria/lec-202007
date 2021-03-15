package kr.or.ddit.admin.professor.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.admin.student.vo.AdminMemVO;

@Repository
public interface IAdminProfessorDAO {

	int insertProfessor(AdminMemVO memVO);

	AdminMemVO selectProfessor(AdminMemVO memVO);

	int updateProfessor(AdminMemVO memVO);

}
