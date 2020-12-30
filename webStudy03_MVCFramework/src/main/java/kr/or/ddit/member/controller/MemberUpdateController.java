package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class MemberUpdateController{
	private IMemberService service = MemberServiceImpl.getInstance();
	
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "MODIFY");
	}
	
	@RequestMapping("/member/modifyMember.do")
	public String doGet(HttpServletRequest req){
		addCommandAttribute(req);
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMem_id());
		req.setAttribute("member", member);
		return "member/memberForm";
	}
	
	@RequestMapping(value="/member/modifyMember.do", method=RequestMethod.POST)
	public String doPost(@ModelAttribute("member") MemberVO member, HttpServletRequest req) {
		addCommandAttribute(req);
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(member, errors, UpdateGroup.class);
		
		String goPage = null;
		if(valid) {
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
















