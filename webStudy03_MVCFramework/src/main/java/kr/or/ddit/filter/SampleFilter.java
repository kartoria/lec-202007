package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SampleFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// request filtering
		System.out.println("요청 필터링");
		chain.doFilter(request, response);
		// response filtering
		System.out.println("응답 필터링");
	}
	@Override
	public void destroy() {
		
	}
	
}
