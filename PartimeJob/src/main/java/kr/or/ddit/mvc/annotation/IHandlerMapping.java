package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapping {
	public RequestMappingInfo findCommandHandler(HttpServletRequest req);
}
