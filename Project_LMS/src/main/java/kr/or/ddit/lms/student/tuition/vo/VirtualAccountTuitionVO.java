package kr.or.ddit.lms.student.tuition.vo;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VirtualAccountTuitionVO{
	private int rnum;
	@NotNull
	@Min(0)
	private Integer payNo;   //등록금번호
	@NotBlank
	@Size(max = 10)
	private String memId;    //학번
	@NotBlank
	@Size(max = 20)
	private String memName;  // 이름
	@Size(max = 20)
	private String memGrd;   // 학기
	@Size(max = 50)
	private String memMail; // 이메일
	private int payNumber;   // 납부 회차
	@NotBlank@Size(max=8)
	private String payStart; // 납부 시작일
	@NotBlank@Size(max=8)
	private String payEnd;   // 납부 마감일
	@Size(max=20)
	private String payAcn;   // 가상계좌 번호
	@Size(max=30)
	private String payBank;  // 은행
	@Size(max=7) private String payDate;   //납부일자
    private Integer payExcpect;   //납부예정금액
	private int payAmount;   // 납입 금액
	@Size(max=15) 
	private String schAmount;  // 장학금액
	@NotBlank
	@Size(max=50) 
	private String schName;  // 장학이름
	@Size(max=7) 
	private String sfundGetdate;   //장학 수혜일자
	@NotNull 
	@Min(0) 
	private Integer sfundValue;   //액수
	@Size(max=20)
	private String payImp;  // 은행
	@NotNull
	@Min(0) 
	private Integer depFee;   //학과등록금
	@NotBlank@Size(max=8) private String smst;   //학기
	@Size(max=1) private String payCheck;   //납부여부
	@NotBlank@Size(max=20) 
	private String depName;   //학과명
	@NotBlank@Size(max=1) private String payDivision;   //분할납부여부
	private String splanCode;
	private String splanName;
	private String splanStart;
	private String splanEnd;
}
