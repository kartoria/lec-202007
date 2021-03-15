package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = { "depNo" })
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentVO implements Serializable {
	@NotNull @Min(0) 
	private Integer depNo;   //학과번호
	@NotBlank@Size(max=20) 
	private String depName;   //학과명
	@Size(max=30) 
	private String depTel;   //학과전화번호
	@NotNull @Min(0) 
	private Integer depFee;   //학과등록금
	@NotBlank@Size(max=4) 
	private String depBorn;   //학과개설년도
	@NotBlank@Size(max=20) 
	private String colCode;   //학부코드
	
	CodeVO codeVO;
	MemberVO memberVO;
	
}
