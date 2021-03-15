package kr.or.ddit.myclassroom.test.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// 주관식
@Data
@EqualsAndHashCode(of="testNo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectiveVO implements Serializable {
	@NotNull @Min(0) private Integer testNo;
	@NotNull @Min(0) private Integer testQno;
	@NotBlank@Size(max=2000) private String stestQ;
	@NotBlank@Size(max=30) private String stestA;
	@NotNull @Min(0) private Integer stestScore;
	
	private int totalSb;
}
