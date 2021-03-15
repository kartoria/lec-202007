package kr.or.ddit.myclassroom.dash.vo;

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
public class AttChartVO {
	@NotNull
	@Min(0)
	private Integer assNo;
	@NotBlank
	@Size(max = 10)
	private String lecCode;
	@NotNull
	@Min(0)
	private Integer assAttend;
	@NotNull
	@Min(0)
	private Integer assTask;
	@NotNull
	@Min(0)
	private Integer assMiddle;
	@NotNull
	@Min(0)
	private Integer assFinal;
}
