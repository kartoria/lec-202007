package kr.or.ddit.admin.scholarship.vo;

import java.util.List;

import javax.validation.Valid;

import kr.or.ddit.vo.ScholarshipFundVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipFundListVO {
	private int sfundNo;
	@Valid
	List<ScholarshipFundVO> applyList;
}
