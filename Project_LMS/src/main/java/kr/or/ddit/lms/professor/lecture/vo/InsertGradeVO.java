package kr.or.ddit.lms.professor.lecture.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = { "tlecNo" })
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertGradeVO {
	
	
	private Integer tlecNo;
	private String lecCode;
	
	private String subCode;
	private String subName;
	
	private String memId;
	private String memName;
	                         
	private String scrAttend;
	private String scrTask;
	private String scrMiddle;
	private String scrFinal;
	private String totalScore;
	private String slistCode;
	
	private String codeName;
	
	private String lecMbk;
	private String lecSbk;
	
	private Integer rank;
	private String howToSort;
}