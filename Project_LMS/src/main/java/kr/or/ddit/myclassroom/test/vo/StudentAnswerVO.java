package kr.or.ddit.myclassroom.test.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentAnswerVO implements Serializable{
	@NotNull
	@Min(0)
	private Integer testNo;
	@NotNull
	@Min(0)
	private Integer testQno;
	@NotBlank
	@Size(max = 10)
	private String memId;
	@Size(max = 2000)
	private String sansAns;
	private Integer sansScore;
	
	private String lecCode;
}
