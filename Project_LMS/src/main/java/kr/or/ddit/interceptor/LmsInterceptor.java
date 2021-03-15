package kr.or.ddit.interceptor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.commons.vo.LeftMenuVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.MemberWrapper;

public class LmsInterceptor extends HandlerInterceptorAdapter{
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	WebApplicationContext container;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ServletContext application = container.getServletContext();
			
		MemberWrapper memberWrapper = (MemberWrapper) authentication.getPrincipal();
		MemberVO authMember = memberWrapper.getRealMember();
		
		List<LeftMenuVO> perentMenuList = null;
		List<LeftMenuVO> childMenuList = null;
		
		if("ROLE_STUDENT".equals(authMember.getMemType())) {
			perentMenuList = (List<LeftMenuVO>) application.getAttribute("lmsStudentPerentMenuList");
			childMenuList = (List<LeftMenuVO>) application.getAttribute("lmsStudentChildMenuList");
		}else if("ROLE_PROFESSOR".equals(authMember.getMemType())) {
			perentMenuList = (List<LeftMenuVO>) application.getAttribute("lmsProfessorPerentMenuList");
			childMenuList = (List<LeftMenuVO>) application.getAttribute("lmsProfessorChildMenuList");
		}
		
		LOGGER.info("perentMenuList >>> {}",perentMenuList);
		LOGGER.info("childMenuList >>> {}",childMenuList);
		request.setAttribute("topbarColor", "#e0efd8");
		request.setAttribute("perentMenuList", perentMenuList);
		request.setAttribute("childMenuList", childMenuList);
		request.setAttribute("main", "lms/lmsmain.do");
		
		return super.preHandle(request, response, handler);
	}
}
