package kr.or.ddit.myclassroom.test.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// 객관식
@Data
@EqualsAndHashCode(of="testNo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjectiveVO implements Serializable {
	@NotNull @Min(0) private Integer testNo;
	@NotNull @Min(0) private Integer testQno;
	@NotBlank@Size(max=2000) private String otestQ;
	@NotBlank@Size(max=30) private String otestA;
	@NotNull @Min(0) private Integer otestScore;
	
	// 문항
	List<DetailVO> deList;
	
	private int totalOb;
}
