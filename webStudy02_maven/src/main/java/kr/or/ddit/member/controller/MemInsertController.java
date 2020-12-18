package kr.or.ddit.member.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemService;
import kr.or.ddit.member.service.MemServiceImpl;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/registMember.do")
public class MemInsertController extends HttpServlet{
	private IMemService memService = MemServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/member/memberForm.tiles").forward(req, resp);;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		Class<MemberVO> memberType = MemberVO.class;
		
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		member.setMem_pass(SecurityUtils.encryptSha512(member.getMem_pass()));
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean vaild = validate(member, errors);
		String goPage = null;
		ServiceResult result = null;
		if(vaild) {
			result = memService.registMember(member);
			switch(result) {
			case PKDUPLICATED :
				goPage = "member/memberForm";
				break;
			case FAILED:
				goPage = "member/memberForm";
				break;
			case OK : 
				goPage = "redirect:/login/loginForm.do";
				break;
			}
		}else {
			goPage = "member/memberForm";
		}
		
		boolean redirect = goPage.startsWith("redirect:");
		boolean forward = goPage.startsWith("forword:");
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage.substring("redirect:".length()));
		}else if(forward){
			req.getRequestDispatcher(goPage.substring("forward:".length())).forward(req, resp);
		}else {
			req.getRequestDispatcher("/" + goPage + ".tiles").forward(req, resp);
		}
		
//		parameterMap.keySet().iterator()
//		Enumeration<String> names = req.getParameterNames();
//		while(names.hasMoreElements()) {
//			String parameterName = (String) names.nextElement();
//			String ParameterValue = req.getParameter(parameterName);
//			try {
//				PropertyDescriptor pd = new PropertyDescriptor(parameterName, memberType);
//				pd.getWriteMethod().invoke(member, ParameterValue);
//			}catch(Exception e) {
//				continue;
//			}
//		}
		
//		String mem_id = (String) req.getParameter("mem_id");
//		String mem_pass = (String) req.getParameter("mem_pass");
//		String mem_pass2 = (String) req.getParameter("mem_pass2");
//		String mem_name = (String) req.getParameter("mem_name");
//		String mem_regno1 = (String) req.getParameter("mem_regno1");
//		String mem_regno2 = (String) req.getParameter("mem_regno2");
//		String mem_bir = (String) req.getParameter("mem_bir");
//		String mem_zip = (String) req.getParameter("mem_zip");
//		String mem_add1 = (String) req.getParameter("mem_add1");
//		String mem_add2 = (String) req.getParameter("mem_add2");
//		String mem_hometel = (String) req.getParameter("mem_hometel");
//		String mem_comtel = (String) req.getParameter("mem_comtel");
//		String mem_mail = (String) req.getParameter("mem_mail");
		
	}
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "아이디는 필수입니다.");
		}
		if(StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호는 필수입니다.");
		}
		return valid;
	}
}
