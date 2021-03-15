package kr.or.ddit.enrolment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.InterestLecVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.TakeLecVO;

@Repository
public interface IEnrolmentDAO {

	int selectLectureCount(PagingVO<LectureVO> paging);

	List<LectureVO> selectLectureList(PagingVO<LectureVO>pagingVO);

	int insertEnrolment(TakeLecVO takeLec);


	List<LectureVO> selectDetailLectureList(MemberVO authMember);

	int deleteEnrolment(TakeLecVO takeLec);

	int interestEnrolment(InterestLecVO interest);
	
	List<LectureVO> interstList(InterestLecVO interest);

	int deleteInterest(InterestLecVO interest);

	int insertCount(TakeLecVO takeLec);

	int deleteCount(TakeLecVO takeLec);

	List<EnrolmentVO> selectLectureTime(MemberVO authMember);

//	List<EnrolmentVO> selectLecturePlan(EnrolmentVO lecture);

	int selectLectureSubNameCount(PagingVO<LectureVO> paging);

	int selectLectureLecGrdCount(PagingVO<LectureVO> paging);

	LectureVO selectLectureplan1(EnrolmentVO lecture);

	List<LectureTimeVO> selectLectureplan2(EnrolmentVO lecture);

	SubjectVO selectLectureplan3(EnrolmentVO lecture);

	MemberVO selectLectureplan4(EnrolmentVO lecture);

}
