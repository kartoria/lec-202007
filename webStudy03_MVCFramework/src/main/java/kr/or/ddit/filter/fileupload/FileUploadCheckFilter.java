package kr.or.ddit.filter.fileupload;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadCheckFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(FileUploadCheckFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화", getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String contentType = request.getContentType();
		// 1. multipart request 여부 판단
		if(contentType!=null && contentType.startsWith("multipart/")) {
		// 2. multipart request일 경우, request 대신 wrapper 전달
			HttpServletRequest req = (HttpServletRequest) request;
			FileUploadRequestWrapper wrapper = new FileUploadRequestWrapper(req);
			chain.doFilter(wrapper, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸", getClass().getSimpleName());
	}
}
