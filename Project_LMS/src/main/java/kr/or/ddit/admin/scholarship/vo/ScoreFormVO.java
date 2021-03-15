package kr.or.ddit.admin.scholarship.vo;


import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreFormVO {
	private Integer colleage;
	private String scrSmst;   //학기
	private Integer depNo; // 학과번호
	@Size(max = 20)
	private String memGrd; // 학년
	private String schCode;
	private String searchWord;
	
	private String currentSmst; // 현재학기
	
}
