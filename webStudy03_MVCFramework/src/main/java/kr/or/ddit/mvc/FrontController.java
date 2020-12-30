package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.IHandlerAdapter;
import kr.or.ddit.mvc.annotation.IHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.prod.controller.ProdRetrieveController;


/**
 * entry point : 모든 요청이 집중됨 , POJO(Plain Old Java Object) 
 */
public class FrontController extends HttpServlet{
	private IHandlerMapping handlerMapping;
	private IHandlerAdapter handlerAdapter;
	private IViewResolver viewResolver;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String basePackage = config.getInitParameter("basePackage");
		handlerMapping = new HandlerMapping(basePackage);
		handlerAdapter = new HandlerAdapter();
		viewResolver= new ViewResolver();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req);
		
		if(mappingInfo == null) {
			resp.sendError(404, "해당 서비스는 지원하지 않음.");
			return;
		}
		
		String goPage = handlerAdapter.invokeHandler(mappingInfo, req, resp);
		
		if(goPage == null) {
			if(!resp.isCommitted())
				resp.sendError(500, "논리적인 뷰네임이 null일 수 없음");
			return;
		}
		
		viewResolver.viewResolve(goPage, req, resp);
	}
	
	
}
