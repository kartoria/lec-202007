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

public class AdminInterceptor extends HandlerInterceptorAdapter{
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	WebApplicationContext container;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("****** AdminInterceptor ******");
		ServletContext application = container.getServletContext();
		List<LeftMenuVO> perentMenuList = (List<LeftMenuVO>) application.getAttribute("adminPerentMenuList");
		List<LeftMenuVO> childMenuList = (List<LeftMenuVO>) application.getAttribute("adminChildMenuList");
		
		request.setAttribute("topbarColor", "#c5daea");
		request.setAttribute("main", "");
		request.setAttribute("hideHomeBtn", "true");
		request.setAttribute("perentMenuList", perentMenuList);
		request.setAttribute("childMenuList", childMenuList);
		return super.preHandle(request, response, handler);
	}
}
