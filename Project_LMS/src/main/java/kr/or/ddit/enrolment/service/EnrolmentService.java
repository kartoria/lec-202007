package kr.or.ddit.enrolment.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enrolment.dao.IEnrolmentDAO;
import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.InterestLecVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.TakeLecVO;

@Service
public class EnrolmentService {
	@Inject
	IEnrolmentDAO dao;
	
	
	public int retrieveLectureCount(PagingVO<LectureVO> paging) {
		return dao.selectLectureCount(paging);
	}
	
	public int retrieveLectureSubNameCount(PagingVO<LectureVO> paging) {
		return dao.selectLectureSubNameCount(paging);
	}
	
	public int retrieveLectureLecGrdCount(PagingVO<LectureVO> paging) {
		return dao.selectLectureLecGrdCount(paging);
	}
	
	//수강신청 전체 조회
	public List<LectureVO> retrieveLectureList(PagingVO<LectureVO> paging){
		return dao.selectLectureList(paging);
	}
	//수강신청
	public int insertEnrolment(TakeLecVO takeLec) {
		return dao.insertEnrolment(takeLec);
	}
	
	//수강신청 내역
	public List<LectureVO> detailEnrolment(MemberVO authMember){
		return dao.selectDetailLectureList(authMember);
	}
	
	//수강취소
	public int deleteEnrolment(TakeLecVO takeLec) {
		return dao.deleteEnrolment(takeLec);	
	}
	
	//관심  등록
	public int interestEnrolment(InterestLecVO interest) {
		return dao.interestEnrolment(interest);
	}
	//수강신청 내역
	public List<LectureVO> retrieveLectureInterestList(InterestLecVO interest) {
		return dao.interstList(interest);
	}
	//관심 취소
	public int deleteInterest(InterestLecVO interest) {
		return dao.deleteInterest(interest);
	}
	//수강 정원 추가
	public int insertCount(TakeLecVO takeLec) {
		return dao.insertCount(takeLec);
	}
	//수강 정원 빼는거
	public int deleteCount(TakeLecVO takeLec) {
		return dao.deleteCount(takeLec);
	}
	//시간표 갖고 오는거
	public List<EnrolmentVO> selectLectureTime(MemberVO authMember) {
		return dao.selectLectureTime(authMember);
	}
//	public List<EnrolmentVO> selectLecturePlan(EnrolmentVO lecture) {
//		return dao.selectLecturePlan(lecture);
//	}

	//강의 계획서 받아오기
	public LectureVO selectLecturePlan1(EnrolmentVO lecture) {
		return dao.selectLectureplan1(lecture);
	}

	//강의 계획서 받아오기2
	public List<LectureTimeVO> selectLecturePlan2(EnrolmentVO lecture) {
		return dao.selectLectureplan2(lecture);
	}

	//강의 계획서 받아오기3
	public SubjectVO selectLecturePlan3(EnrolmentVO lecture) {
		return dao.selectLectureplan3(lecture);
	}

	//강의 계획서 받아오기4
	public MemberVO selectLecturePlan4(EnrolmentVO lecture) {
		return dao.selectLectureplan4(lecture);
	}
}
