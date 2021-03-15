package kr.or.ddit.admin.lecture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.admin.lecture.vo.AdminLectureVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SubjectVO;

@Repository
public interface AdminSubjectDAO {

	int selectSubjectCount(PagingVO<SubjectVO> pagingVO);

	List<SubjectVO> selectSubjectList(PagingVO<SubjectVO> pagingVO);

	int insertSubject(SubjectVO subVO);

	SubjectVO selectSubject(SubjectVO subVO);

	int updateSubject(SubjectVO subVO);

	int deleteSubject(SubjectVO subVO);

}
