package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScholarshipFundVO {

private Integer sfundNo;   //지급번호
@NotBlank
@Size(max=10) 
private String scharCode;   //장학코드

private String codeResult;   //심사결과
@NotNull 
@Min(0) 
private Integer sfundValue;   //액수

private String sfundSmst;   //학기
@Size(max=7) 
private String sfundResdate;   //장학금신청일자
@Size(max=7) 
private String sfundGetdate;   //장학 수혜일자
@NotBlank@Size(max=10) 
private String sfundStudent;   //후보자
@Size(max=10) 
private String sfundProfessor;   //추천자
@Size(max=600) 
private String sfundReason;   //신청사유

private String memName;
MemberVO memberVO;
ScholarshipVO schVO;
DepartmentVO departVO;
CodeVO codeVO;
	
}
