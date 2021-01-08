package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
@RequestMapping("/member/modifyMember.do")
public class MemberUpdateController{
	@Inject
	private IMemberService service;
	
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "MODIFY");
	}
	
	@GetMapping
	public String doGet(HttpServletRequest req) throws ServletException, IOException {
		addCommandAttribute(req);
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMem_id());
		req.setAttribute("member", member);
		return "member/memberForm";
	}
	
	@PostMapping
	public String doPost(@ModelAttribute("member") MemberVO member, HttpServletRequest req){
		addCommandAttribute(req);
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
//		CommonValidator<MemberVO> validator = new CommonValidator<>();
//		boolean valid = validator.validate(member, errors, UpdateGroup.class);
		
		String goPage = null;
		
		if(true) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				goPage = "member/memberForm";
				req.setAttribute("message", NotyMessageVO.builder("비번 오류:").build());
				break;
			case FAILED:
				goPage = "member/memberForm";
				req.setAttribute("message", NotyMessageVO.builder("서버 오류:").build());
				break;
			default:
				goPage =  "redirect:/mypage.do";
				MemberVO authMember =(MemberVO) req.getSession().getAttribute("authMember");
				try {
					BeanUtils.copyProperties(authMember, member);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}else {
			goPage = "member/memberForm";
		}
		
		return goPage;
	}
}
















