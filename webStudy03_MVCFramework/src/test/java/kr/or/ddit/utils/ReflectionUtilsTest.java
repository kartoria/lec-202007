package kr.or.ddit.utils;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import kr.or.ddit.mvc.streotype.Controller;

public class ReflectionUtilsTest {

	@Test
	public void testGetMethodsWithAnnotationAtClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassesWithAnnotationAtBasePackages() {
		Map<Class<?>, Annotation> classes = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, "kr.or.ddit.member.controller");
		for(Entry<Class<?>, Annotation> entry : classes.entrySet()) {
			Class<?> handlerType = entry.getKey();
			Annotation annotation = entry.getValue();
			System.out.printf("%s : %s \r\n", handlerType, annotation);
		}
	}

}
