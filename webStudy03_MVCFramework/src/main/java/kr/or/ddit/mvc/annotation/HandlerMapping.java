package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.utils.ReflectionUtils;

public class HandlerMapping implements IHandlerMapping {
	private static final Logger logger = LoggerFactory.getLogger(HandlerMapping.class);
	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	public HandlerMapping(String...basePackages) throws ServletException{
		handlerMap = new LinkedHashMap<>();
		if(basePackages!=null && basePackages.length>0) {
			Map<Class<?>, Annotation> classes = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
			Iterator<Class<?>> it = classes.keySet().iterator();
			try {
				while(it.hasNext()) {
					Class<?> controllerClass = (Class<?>) it.next();
					Object commandHandler = controllerClass.newInstance();
					Map<Method, Annotation> methods = ReflectionUtils.getMethodsWithAnnotationAtClass(
							controllerClass, RequestMapping.class, String.class);
					for(Entry<Method, Annotation> entry : methods.entrySet()) {
						Method handlerMethod = entry.getKey();
						RequestMapping mapping = (RequestMapping) entry.getValue();
						String uri = mapping.value();
						RequestMethod requestMethod = mapping.method();
						RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, requestMethod);
						RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
						handlerMap.put(mappingCondition, mappingInfo);
						logger.info("수집된 핸들러 정보 : {}", mappingInfo);
					}
				}
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}
	
	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest req) {
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		url = url.substring(contextPath.length()).split(";")[0];
		RequestMethod method = RequestMethod.valueOf(req.getMethod().toUpperCase());
		RequestMappingCondition mappingCondition = new RequestMappingCondition(url, method);
		RequestMappingInfo mappingInfo = handlerMap.get(mappingCondition);
		if(mappingInfo!=null)
			logger.info("url : {}, handler : {}", url, mappingInfo.getCommandHandler().getClass().getSimpleName()+"."+mappingInfo.getHandlerMethod().getName());
		else 
			logger.error("url{}에 대한 핸들러가 없음.", url);
		return mappingInfo;
	}

	
}
