package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

/**
 * 인증(authentication) : 신원확인
 * 인가(authorization)  : 자원에 대한 권한 획득 여부 확인
 */
public class AuthorizationFilter implements Filter{
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("{} 초기화", getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, List<String>> securedResources = 
				(Map<String, List<String>>) request.getServletContext().getAttribute(AuthenticationFilter.SECUREDNAME);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		int length =  req.getContextPath().length();
		uri = uri.substring(length).split(";")[0];
		
		boolean pass = false;
		if(securedResources.containsKey(uri)){
			MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
			String userRole = authMember.getMem_role();
			List<String> resourceRoles = securedResources.get(uri);
			if(resourceRoles.contains(userRole)) pass=true;
			else pass=false;
		}else {
			pass = true;
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	@Override
	public void destroy() {
		LOGGER.info("{} 소멸", getClass().getSimpleName());
	}

}
