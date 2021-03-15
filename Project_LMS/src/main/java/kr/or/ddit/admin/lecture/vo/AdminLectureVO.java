package kr.or.ddit.admin.lecture.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLectureVO {
	private String lecCode; // 강의번호
	private String subCode; // 과목코드
	private String memId; // 사용자번호
	private Integer lecFull; // 정원
	private Integer lecNmt; // 수강인원
	private String roomCode; // 강의실코드
	private String lecGrd; // 개설학년
	private Integer lecDays; // 강좌일수
	private String lecMbk; // 주교재
	private String lecSbk; // 부교재
	private String lecSmst; // 개설학기
	private String lecDelete; // 폐강여부
	private String colCode;   //학부코드
	private Integer depNo;   //학과번호
	private String depName;   //학과명
	
	private String memName;
	private String subName;
	
	
	private String code;
	private String codeName;
	
}
