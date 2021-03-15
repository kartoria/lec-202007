package kr.or.ddit.enrolment.vo;

import kr.or.ddit.vo.LectureVO;
import lombok.Data;
@Data
public class EnrolmentVO extends LectureVO {

  public String subDetail; // 이수구분
  public String subName ;// 과목명
  public String subCredit; // 학점 
  public String ltimeDay ;// 요일
  public String ltimeStart; // 시작시간
  public String ltimeEnd ;// 끝시간
  public String roomCode ;// 강의실
  public String memName ;//  교수명
  public String memTel ;// 전화번호
  public String memMail ;// 이메일
  
	
	
}
