package kr.or.ddit.lms.professor.schoalarship.vo;

import kr.or.ddit.vo.SearchVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFormVO {
	private String memType;
	private String proId;
	SearchVO searchVO;
}
