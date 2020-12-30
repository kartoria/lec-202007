package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlindFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	private Map<String, String> blindMap = new HashMap<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화", getClass().getSimpleName());
//		blindMap.put("127.0.0.1", "그냥 나니까 차단.");
//		blindMap.put("0:0:0:0:0:0:0:1", "그냥 나니까 차단.");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String clientIp = request.getRemoteAddr();
		String reason = blindMap.get(clientIp);
		if(reason!=null) {
			request.setAttribute("reason", reason);
			request.getRequestDispatcher("/html/12/messageView.jsp").forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}
	@Override
	public void destroy() {
		logger.info("{} 소멸", getClass().getSimpleName());
	}

}
