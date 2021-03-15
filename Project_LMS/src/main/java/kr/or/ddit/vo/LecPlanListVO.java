package kr.or.ddit.vo;

import java.util.List;

import javax.validation.Valid;

import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecPlanListVO {
	@Valid
	List<ScholarshipFundVO> applyList;
	
	private List<LecPlanVO> lecPlanList;
}