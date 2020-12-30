package kr.or.ddit.mvc.annotation.resolvers;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

import kr.or.ddit.filter.fileupload.FileUploadRequestWrapper;
import kr.or.ddit.filter.fileupload.MultipartFile;
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
			
			if(req instanceof FileUploadRequestWrapper) {
				Enumeration<String> names = ((FileUploadRequestWrapper) req).getFileName();
				while (names.hasMoreElements()) {
					String partName = (String) names.nextElement();
					try {
						PropertyDescriptor pd = new PropertyDescriptor(partName, parameterType);
						Class<?> propertyType = pd.getPropertyType();
						if(propertyType.equals(MultipartFile.class)) {
							// prod.setProd_image(file);
							MultipartFile file = ((FileUploadRequestWrapper) req).getFile(partName);
							pd.getWriteMethod().invoke(vo, file);
						}else if(List.class.isAssignableFrom(propertyType)) {
							List<MultipartFile> files = ((FileUploadRequestWrapper) req).getFiles(partName);
							pd.getWriteMethod().invoke(vo, propertyType.cast(files));
						}else if(propertyType.isArray() && propertyType.getComponentType().equals(MultipartFile.class)) {
							List<MultipartFile> files = ((FileUploadRequestWrapper) req).getFiles(partName);
							MultipartFile[] array = new MultipartFile[files.size()];
							array = files.toArray(array);
							pd.getWriteMethod().invoke(vo, array);
						}
					}catch(IntrospectionException e) {
						continue;
					}
				}
			}
			return vo;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
