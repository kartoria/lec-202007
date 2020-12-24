package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletSpecArgumentResolver implements IHanderMethodArgumentResolver{

	@Override
	public boolean isSupported(Parameter parameter) {
		return parameter.getType().equals(HttpServletRequest.class) ||
		parameter.getType().equals(HttpServletResponse.class) ||
		parameter.getType().equals(HttpSession.class);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) {
		Class<?> parameterType = parameter.getType();
		Object value = null;
		if(HttpServletRequest.class.equals(parameterType)){
			value=req;
		}else if(HttpServletResponse.class.equals(parameterType)) {
			value = resp;
		}else {
			value=req.getSession();
		}
		return value;
	}

}
