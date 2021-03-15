package kr.or.ddit.myclassroom.att.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.IAttachDAO;
import kr.or.ddit.myclassroom.att.AttVO;
import kr.or.ddit.myclassroom.att.dao.IAttDao;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.myclassroom.video.vo.ViewRecordVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.TakeLecVO;

@Service
public class AttService {
	@Inject
	IAttDao dao;

	public List<AttVO> selectLecMember(LectureVO lectureVO) {
		return dao.selectLecMember(lectureVO);
	}


	public List<AttVO> selectAttendCode(AttVO attvo) {
		return dao.selectAttendCode(attvo);
	}


	public int updateAtt(AttVO attVO) {
		return dao.updateAtt(attVO);
	}


	public SubjectVO selectSubName(AttVO attVO) {
		return dao.selectSubName(attVO);
	}


	public List<ViewRecordVO> selectViewRecordList(AttVO attvo) {
		return dao.selectViewRecordList(attvo);
	}


	public TakeLecVO selectTakeLecNo(AttVO attvo) {
		return dao.selectTakeLecNo(attvo);
	}


	public int insertAttScore(StudentScoreVO studentScoreVO) {
		return dao.insertAttScore(studentScoreVO);
	}


	public StudentScoreVO selectScrAttend(TakeLecVO takeLecVO) {
		return dao.selectScrAttend(takeLecVO);
	}


}
