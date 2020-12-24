package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHanderMethodArgumentResolver {
	public boolean isSupported(Parameter parameter);
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException;
}
