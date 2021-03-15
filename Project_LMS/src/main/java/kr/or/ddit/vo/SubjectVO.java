package kr.or.ddit.vo;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.validate.groups.InsertGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="subCode")
@Builder
public class SubjectVO {
	private String subCode;   //과목코드
	@NotBlank
	@Size(max=40) 
	private String subName;   //과목명
	@NotNull 
	@Min(0) 
	private Integer subCredit;   //학점
	@NotBlank
	@Size(max=10) 
	private String subDetail;   //이수구분
	@NotBlank
	@Size(max=4) 
	private String subDate;   //개설년도
	@NotNull(groups=InsertGroup.class)
	@Min(0) 
	private Integer depNo;   //학과번호
	
	// 학과
	private String depName;   //학과명
	// 학부
	private String colCode;   //학부코드
	// 코드테이블
	private String groupCode;   //그룹코드
	private String code;   //코드
	private String codeName;   //코드명  학부명
}
