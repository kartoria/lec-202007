package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

import kr.or.ddit.vo.MemberVO;

public class ModelAttributeArgumentResolver implements IHanderMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		boolean supported = parameter.getAnnotation(ModelAttribute.class) != null;
		Class<?> parameterType = parameter.getType();
		supported = supported 
				&& !ClassUtils.isPrimitiveOrWrapper(parameterType) 
				&& !String.class.equals(parameterType);
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		Class<?> parameterType = parameter.getType();
		try {
			Object vo = parameterType.newInstance();
			ModelAttribute modelAttribute = parameter.getAnnotation(ModelAttribute.class);
			String attrName = modelAttribute.value();
			req.setAttribute(attrName, vo);
			Map<String, String[]> parameterMap = req.getParameterMap();
			BeanUtils.populate(vo, parameterMap);
			return vo;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
