package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.or.ddit.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemesterScoreVO {
@NotNull @Min(0) 
private Integer scrNo;   //점수번호
@Size(max=10) 
private String memId;   //학번
 private Integer scrApply;   //총신청학점
 private Integer scrCredit;   //총취득학점
 private float scrTotal;   //총백분율
 private float scrAgv;   //총평점평균
@Size(max=8) 
private String scrSmst;   //학기
private String CurrentSmst;

private int rank;   // 순위

MemberVO memberVO;
DepartmentVO departmentVO;
CodeVO codeVO;
ScholarshipFundVO schoFundVO;
}
