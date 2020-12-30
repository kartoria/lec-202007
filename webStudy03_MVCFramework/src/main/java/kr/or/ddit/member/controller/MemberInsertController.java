package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class MemberInsertController {
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@RequestMapping("/member/registMember.do")
	public String InsertMemberGet(){
		return "member/memberForm";
	}
	
	@RequestMapping(value="/member/registMember.do", method=RequestMethod.POST)
	public String InsertMemberPost(@ModelAttribute("member") MemberVO member ,HttpServletRequest req) throws IOException{
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(member, errors, InsertGroup.class);
		
		String goPage = null;
		
		if(valid) {
			ServiceResult result = service.registMember(member);
			switch (result) {
			case PKDUPLICATED:
				goPage = "member/memberForm";
				req.setAttribute("message", NotyMessageVO.builder("아이디 중복").build());
				break;
			case FAILED:
				goPage = "member/memberForm";
				req.setAttribute("message", NotyMessageVO.builder("서버 오류").build());
				break;
			case OK:
				goPage =  "redirect:/login/loginForm.do";
				break;
			}
		}else {
			goPage = "member/memberForm";
		}
		return goPage;
	}
	
	@RequestMapping("/member/idCheck.do")
	public String IdCheckGet(@RequestParam("mem_id") String inputId, HttpServletResponse resp) throws IOException {
		boolean canUse = false;
		try {
			service.retrieveMember(inputId);
		}catch (UserNotFoundException e) {
			canUse = true;
		}
		resp.setContentType("text/plain");
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.println(canUse);
		}
		return null;
	}
	
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.POST)
	public String IdCheckPost(@RequestParam("mem_id") String inputId, HttpServletResponse resp) throws IOException {
		boolean canUse = false;
		try {
			service.retrieveMember(inputId);
		}catch (UserNotFoundException e) {
			canUse = true;
		}
		resp.setContentType("text/plain");
		try(PrintWriter out = resp.getWriter();){
			out.println(canUse?ServiceResult.OK.name():ServiceResult.FAILED.name());
		}
		return null;
	}
}
