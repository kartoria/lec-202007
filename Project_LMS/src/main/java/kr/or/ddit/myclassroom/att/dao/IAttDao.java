package kr.or.ddit.myclassroom.att.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.myclassroom.att.AttVO;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.myclassroom.video.vo.ViewRecordVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.TakeLecVO;

@Repository
public interface IAttDao {

	List<AttVO> selectLecMember(LectureVO lectureVO);

	List<AttVO> selectAttendCode(AttVO attvo);

	int updateAtt(AttVO attVO);

	SubjectVO selectSubName(AttVO attVO);

	List<ViewRecordVO> selectViewRecordList(AttVO attvo);

	TakeLecVO selectTakeLecNo(AttVO attvo);

	int insertAttScore(StudentScoreVO studentScoreVO);

	StudentScoreVO selectScrAttend(TakeLecVO takeLecVO);


}
