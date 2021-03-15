package kr.or.ddit.admin.lecture.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecSearchVO {
	private String college;     //학부
	private Integer department; //학과
	private String professor; //교수
	private String lecDelete; //폐강여부
	private String smst;     // 개설학기
	private String searchType;
	private String searchWord;
	
	// 과목 조회
	private String subDetail; // 이수구분
}
