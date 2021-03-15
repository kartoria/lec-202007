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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TuitionVO {
	@NotNull @Min(0) private Integer payNo;   //등록금번호
	@NotBlank@Size(max=10) private String memId;   //학번
	@NotBlank@Size(max=8) private String smst;   //학기
	@NotBlank@Size(max=8) private String payStart;   //납부기간시작일
	@NotBlank@Size(max=8) private String payEnd;   //납부기간마감일
	 private Integer payExcpect;   //납부예정금액
	 private Integer payAmount;   //납부금액
	@Size(max=7) private String payDate;   //납부일자
	@NotBlank@Size(max=1) private String payDivision;   //분할납부여부
	 private Integer payNumber;   //납부회차
	@Size(max=1) private String payCheck;   //납부여부
	@Size(max=20) private String payAcn;   //계좌번호
	@Size(max=30) private String payBank;   //은행
	@NotNull @Min(0) 
	private Integer depFee;   //학과등록금
	 
	 
	 private Integer sfundValue;
	 
	 
	 
}
