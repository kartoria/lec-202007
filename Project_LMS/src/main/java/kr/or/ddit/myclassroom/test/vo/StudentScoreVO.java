package kr.or.ddit.myclassroom.test.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentScoreVO implements Serializable {

	@NotNull
	@Min(0)
	private Integer tlecNo;
	private Integer scrAttend;
	private Integer scrTask;
	private double scrMiddle;
	private double scrFinal;
	private Integer totalScore;
	@Size(max = 10)
	private String slistCode;
	
	// 필요
	private String memId;
	private String lecCode;
	private String testType;
	private String memName;
}
