package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class MemberDeleteController {
	private IMemberService service = MemberServiceImpl.getInstance();

	@RequestMapping(value="/member/deleteMember.do", method=RequestMethod.POST)
	public String DeleteMember(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String mem_pass = req.getParameter("mem_pass");
		HttpSession session = req.getSession(false);
		
		if(StringUtils.isBlank(mem_pass) || session == null || session.isNew()) {
			resp.sendError(400);
			return null;
		}
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		ServiceResult result = service.removeMember(MemberVO.builder().mem_id(mem_id)
													.mem_pass(mem_pass)
													.build());
		switch (result) {
		case INVALIDPASSWORD:
			session.setAttribute("message", NotyMessageVO.builder("비번 오류:").build());
			return "redirect:/mypage.do";
		case FAILED:
			session.setAttribute("message", NotyMessageVO.builder("서버 오류:").build());
			return "redirect:/mypage.do";
		default:
			return "forward:/login/logout.do";
		}
	}
}