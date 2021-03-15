package kr.or.ddit.admin.student.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchMemFormVO {
	private String college;
	private Integer department;
	private String state;
	private String grade;
	private String memType;
	private String searchType;
	private String searchWord;
	
}
