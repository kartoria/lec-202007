package kr.or.ddit.cyber.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.LectureVO;

@Repository
public interface ICyberDAO {
	
	public List<LectureVO> studentMyLectureList(MemberVO member);
	public List<LectureVO> professorMyLectureList(MemberVO member);
	public List<LectureVO> adminLectureList();
	public List<LectureVO> lectureTimeList(MemberVO member);
}
