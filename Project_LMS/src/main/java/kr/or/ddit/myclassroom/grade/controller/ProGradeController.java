package kr.or.ddit.myclassroom.grade.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.test.vo.TestVO;

@Controller
public class ProGradeController {
	
	/** 성적 리스트 조회 **/
//	@RequestMapping("/myclass/{lecCode}/gradeList.do")
//	public String gradeList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
////			@PathVariable(value ="lecCode", required=true) String lecCode,
//			Model model, PagingVO) {
//		return "myClassRoom/grade/proGradeList";
//	}
}
