package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.IHanderMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletSpecArgumentResolver;

public class HandlerAdapter implements IHandlerAdapter {

	private List<IHanderMethodArgumentResolver> argumentResolvers;
	
	{
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletSpecArgumentResolver());
		argumentResolvers.add(new RequestParamArgumentResolver());
		argumentResolvers.add(new ModelAttributeArgumentResolver());
	}
	
	private IHanderMethodArgumentResolver getArgumentResolver(Parameter parameter) {
		IHanderMethodArgumentResolver matched = null; 
		for(IHanderMethodArgumentResolver resolver : argumentResolvers) {
			if(resolver.isSupported(parameter)) {
				matched = resolver;
				break;
			}
		}
		return matched;
	}
	
	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object controllerObject = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		int paramCount = handlerMethod.getParameterCount();
		Parameter[] parameters = handlerMethod.getParameters();
		try {
			String viewName = null;
			if(paramCount==0) {
				viewName = (String) handlerMethod.invoke(controllerObject);
			}else {
				Object[] parameterValues = new Object[parameters.length];
				
				for(int idx = 0; idx < parameters.length; idx++) {
					Parameter parameter = parameters[idx];
					IHanderMethodArgumentResolver resolver = getArgumentResolver(parameter);
					if(resolver==null) {
						throw new ServletException("핸들러 메소드의 파라미터를 해결할 수 없음." + parameter.getName());
					}
					parameterValues[idx] =  resolver.argumentResolve(parameter, req, resp);
				}
				viewName = (String) handlerMethod.invoke(controllerObject, parameterValues);
			}
			return viewName;
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		} catch (IllegalArgumentException e) {
			resp.sendError(400, "필수파라미터가 누락되거나, 파라미터 데이터 타입이 맞지 않음.");
			return null;
		}
	}

}
