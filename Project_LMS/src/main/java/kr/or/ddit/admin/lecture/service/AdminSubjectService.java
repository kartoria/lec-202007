package kr.or.ddit.admin.lecture.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.lecture.dao.AdminSubjectDAO;
import kr.or.ddit.admin.lecture.vo.AdminLectureVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SubjectVO;

@Service
public class AdminSubjectService {
	@Inject
	private AdminSubjectDAO subDAO;

	public int retrieveSubjectCount(PagingVO<SubjectVO> pagingVO) {
		return subDAO.selectSubjectCount(pagingVO);
	}

	public List<SubjectVO> retrieveSubjectList(PagingVO<SubjectVO> pagingVO) {
		return subDAO.selectSubjectList(pagingVO);
	}

	public int createSubject(SubjectVO subVO) {
		return subDAO.insertSubject(subVO);
	}

	public SubjectVO retrieveSubject(SubjectVO subVO) {
		return subDAO.selectSubject(subVO);
	}

	public int modifySubject(SubjectVO subVO) {
		return subDAO.updateSubject(subVO);
	}

	public int removeSubject(SubjectVO subVO) {
		return subDAO.deleteSubject(subVO);
	}
	
}
