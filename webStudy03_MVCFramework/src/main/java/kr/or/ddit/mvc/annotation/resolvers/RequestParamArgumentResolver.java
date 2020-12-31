package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;

public class RequestParamArgumentResolver implements IHanderMethodArgumentResolver{

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		boolean supported = parameter.getAnnotation(RequestParam.class)!=null;
		supported = supported 
				&& ClassUtils.isPrimitiveOrWrapper(parameterType) 
				|| String.class.equals(parameterType);
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		Class<?> parameterType = parameter.getType();
		RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
		String reqParamName =requestParam.value();
		boolean requried = requestParam.required();
		String paramValue = req.getParameter(reqParamName);
		if(requried && (paramValue==null || paramValue.isEmpty())) {
			throw new IllegalArgumentException("필수 파라미터 누락");
		}else if(!requried && (paramValue==null || paramValue.isEmpty())) {
			paramValue = requestParam.defaultValue();
		}
		
		Object value = null;
		try {
			if(byte.class.equals(parameterType) || byte.class.equals(parameterType)) {
				value = Byte.parseByte(paramValue);
			}else if(short.class.equals(parameterType) || short.class.equals(parameterType)) {
				value = Short.parseShort(paramValue);
			}else if(int.class.equals(parameterType) || int.class.equals(parameterType)) {
				value = Integer.parseInt(paramValue);
			}else if(long.class.equals(parameterType) || long.class.equals(parameterType)) {
				value = Long.parseLong(paramValue);
			}else if(float.class.equals(parameterType) || float.class.equals(parameterType)) {
				value = Float.parseFloat(paramValue);
			}else if(double.class.equals(parameterType) || double.class.equals(parameterType)) {
				value = Double.parseDouble(paramValue);
			}else if(boolean.class.equals(parameterType) || boolean.class.equals(parameterType)) {
				value = Boolean.parseBoolean(paramValue);
			}else if(char.class.equals(parameterType) || char.class.equals(parameterType)) {
				value = paramValue.charAt(0);
			}else {
				value = paramValue;
			}
			return value;
		}catch(Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
