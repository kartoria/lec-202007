package kr.or.ddit.lms.student.scholarship.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StuScholarshipVO {
	// ScholarshipFund
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
	// Scholarship 
	private String schName;   //장학명
	// Code
	private String codeName;   //코드명
}
