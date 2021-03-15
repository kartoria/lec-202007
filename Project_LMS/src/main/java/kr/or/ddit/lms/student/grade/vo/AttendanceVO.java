package kr.or.ddit.lms.student.grade.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = {"attenDate", "ltimeNo", "memId"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceVO {
	
	private String  attenNo  ;	   // 출석번호
	private String  attenDate;     // 출석일
	private String  lecCode ;      // 강의코드
	private String  ltimeNo  ;     // 강의시간번호
	private String  memId    ;     // 학번
	private Integer week     ;     // 주차
	private String  attenNote;     // 비고
	
	private String  attenTime;     // 출석시간
	private String  codeName ;     // 출결내용
	
	//db에 없는 데이터
	private String  ltime;         // 시간
	private Integer totalTime;     // 총 강의시간
	private Integer countAttend;   // 출석일수
	private Integer countLate;     // 지각
	private Integer countEarly;    // 조퇴
	private Integer countAbsent;   // 결석
	private Float   avgAtten;      // 출석률
	
	private String attenCode; 		//출결
	
	
}
