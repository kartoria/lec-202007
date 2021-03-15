package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

public class EnromentInterceptor extends HandlerInterceptorAdapter{
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session != null) {
			String inter = (String) session.getAttribute("INTER");
			String tlec  = (String) session.getAttribute("TLEC");
			LOGGER.info("inter : {} , tlec : {}", inter, tlec);
			if(inter !=null || tlec != null) {
				return true;
			}
		}
        
		String preUrl = request.getHeader("referer");
		LOGGER.info("preUrl >>> {}", preUrl);
		if(preUrl != null) {
			response.sendRedirect(preUrl);
		}else {
			response.sendRedirect(request.getContextPath()+"/");
		}
		session.setAttribute("msg", NotyMessageVO.builder("수강신청 기간이 아닙니다.").layout(NotyLayout.topCenter).type(NotyType.warning).timeout(3000).build());
        return false;
	}
	

}
