package kr.or.ddit.admin.scholarship.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipPayedDetailVO {
	private Integer sfundNo;   //지급번호
	private String scharCode;   //장학코드
	private String codeResult;   //심사결과
	private Integer sfundValue;   //액수
	private String sfundSmst;   //학기
	private String sfundResdate;   //장학금신청일자
	private String sfundGetdate;   //장학 수혜일자
	private String sfundStudent;   //후보자
	private String sfundProfessor;   //추천자
	private String sfundReason;   //신청사유
	private String memName;   // 학생이름
	private String codeName;  // 상태 코드이름
	private String schName;   // 장학명
	
}
