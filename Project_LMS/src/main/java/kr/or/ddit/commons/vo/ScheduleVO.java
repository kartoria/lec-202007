package kr.or.ddit.commons.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleVO {
	@NotNull 
	@Min(0) 
	private Integer scheNo;   //일정코드
	@NotBlank
	@Size(max=100) 
	private String scheTitle;   //일정내용
	@Size(max=7) 
	private String scheStart;   //일정시작일
	@Size(max=7) 
	private String scheEnd;   //일정종료일
	@NotBlank
	@Size(max=1000) 
	private String scheContent;   //비고
	@NotBlank
	@Size(max=20) 
	private String scheSort;   //일정분류
}
