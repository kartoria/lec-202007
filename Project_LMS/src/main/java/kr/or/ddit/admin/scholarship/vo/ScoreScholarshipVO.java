package kr.or.ddit.admin.scholarship.vo;

import kr.or.ddit.stereotype.ExcelPropetyHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreScholarshipVO {
	@ExcelPropetyHeader("학부")
	private String codeName;   // 단과대
	@ExcelPropetyHeader("학과")
	private String depName;   //학과명 
	@ExcelPropetyHeader("학번")
	private String memId; // 사용자번호
	@ExcelPropetyHeader("학년")
	private String memGrd; // 학년
	@ExcelPropetyHeader("이름")
	private String memName; // 이름
	
	//학생성적==============================================================================
	@ExcelPropetyHeader("총 취득학점")
	private Integer scrCredit;   //총취득학점
	@ExcelPropetyHeader("총 백분율")
	private float scrTotal;   //총백분율
	@ExcelPropetyHeader("총 평점평균")
	private float scrAgv;   //총평점평균
	@ExcelPropetyHeader("학기") 
	private String scrSmst;   //학기 
	@ExcelPropetyHeader("석차")  
	private Integer scrGrade;//석차
	 
	//장학금================================================
	@ExcelPropetyHeader("장학코드")
	private String scharCode;   //장학코드
	@ExcelPropetyHeader("장학명")
	private String schName;   //장학명
	@ExcelPropetyHeader("액수")
	private Integer sfundValue;   //액수
	@ExcelPropetyHeader("선발여부")
	private Integer acceptYN;   //선발여부
}
