package kr.or.ddit.myclassroom.test.vo;

import java.util.List;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StuAnsVO {
	@Valid
	List<StudentAnswerVO> ansList;
	
	private String testType;
	private String memId;
}
