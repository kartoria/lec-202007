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

@Data
@EqualsAndHashCode(of="testNo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestVO implements Serializable {
	// 전체 시험
	@NotNull @Min(0) private int testNo;
	@NotNull @Min(0) private Integer testQno;
	@NotBlank@Size(max=10) private String lecCode;
	@NotBlank@Size(max=1) private String testQtype;
	@Size(max=7) private String testStart;
	@NotBlank@Size(max=1) private String testType;
	@Size(max=7) private String testEnd;

	// 주관식 리스트
	List<SubjectiveVO> subList;
	// 객관식 리스트
	List<ObjectiveVO> obList;
	
	// 총시간
	private Integer totalTime;
	private String memId;
	private List<TestVO> resultList;

	// 판별
	private String testStatus;
	
}
