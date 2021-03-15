package kr.or.ddit.commons.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuedVO {
	@NotNull @Min(0) private Integer issNo;   //발급번호
	@Size(max=10) private String memId;   //발급자
	@Size(max=20) private String issName;   //발급문서명
	@Size(max=7) private String issDate;   //발급날짜
	private String memName;
	private String code;
}
