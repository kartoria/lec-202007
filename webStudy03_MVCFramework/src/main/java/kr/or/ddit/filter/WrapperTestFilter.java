package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//@WebFilter("/prod/prodView.do")
public class WrapperTestFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
			@Override
			public String getParameter(String name) {
				if("prod_id".equals(name)) {
					return "P101000001";
				}else {
					return super.getParameter(name);
				}
			}
		};
		chain.doFilter(wrapper, response);
	}

	@Override
	public void destroy() {
		
	}
	
}
