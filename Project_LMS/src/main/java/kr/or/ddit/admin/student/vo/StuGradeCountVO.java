package kr.or.ddit.admin.student.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuGradeCountVO {
	private Integer IngCnt;
	private Integer RestCnt;
	private Integer LeftCnt;
	private Integer EndCnt;
	private Integer TotalCnt;
	
}
