/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.admin.tuition.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Repository
public interface IAdminTuitionDAO {

	List<CodeVO> selectCodeName();

	List<MemberVO> selectMember(PagingVO<MemberVO> paging);

	int memberCount(PagingVO<MemberVO> paging);

	List<DepartmentVO> selectDepName(CodeVO code);

	int memberDepCount(PagingVO<MemberVO> paging);

	List<MemberVO> selectDepStuList(PagingVO<MemberVO> paging);

	int adminTuitionUpdateSucess(MemberVO member);

	int adminTuitionUpdateFail(MemberVO member);

	CodeVO selectSmst();

	int insertPayDiv1(MemberVO member);

	int insertPayDiv2(MemberVO member);

	int insertPayDiv3(MemberVO member);

	List<VirtualAccountTuitionVO> selectVirtualAccountTuition(PagingVO<VirtualAccountTuitionVO> paging);
	
	int selectVirttualCount();
	
}
