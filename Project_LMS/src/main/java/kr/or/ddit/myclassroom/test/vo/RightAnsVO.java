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

// 교수가 입력한 정답 
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RightAnsVO implements Serializable{
	@NotNull
	@Min(0)
	private Integer testNo;
	@NotNull
	@Min(0)
	private Integer testQno;
	// 정답
	@NotBlank
	@Size(max = 2000)
	private String  ans;
	// 배점
	private Integer ansScore;

}
