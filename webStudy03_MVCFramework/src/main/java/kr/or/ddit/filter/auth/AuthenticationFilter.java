package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

public class AuthenticationFilter implements Filter{
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
	public static final String SECUREDNAME = "securedResources";
	private Map<String, List<String>> securedResources;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("{} 초기화", getClass().getSimpleName());
		securedResources = new LinkedHashMap<>();
		filterConfig.getServletContext().setAttribute(SECUREDNAME, securedResources);
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.auth.securedResources");
		Enumeration<String> en = bundle.getKeys();
		while (en.hasMoreElements()) {
			String uri = (String) en.nextElement();
			String roleStr = bundle.getString(uri);
			List<String> roles = Arrays.asList(roleStr.replaceAll("\\s+", "").split(","));
			securedResources.put(uri.trim(), roles);
			LOGGER.info("{}:{}", uri, roles);
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		int length =  req.getContextPath().length();
		uri = uri.substring(length).split(";")[0];
		boolean pass = false;
		if(securedResources.containsKey(uri)) {
			Object authMember = req.getSession().getAttribute("authMember");
			if(authMember != null) pass = true;
		}else pass = true;
		
		if(pass) chain.doFilter(req, resp);
		else resp.sendRedirect(req.getContextPath() + "/login/loginForm.do");
	}
	
	@Override
	public void destroy() {
		
	}
	
}
