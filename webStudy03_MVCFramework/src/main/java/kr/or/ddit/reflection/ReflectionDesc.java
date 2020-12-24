package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject(); // obj의 실체는 vo이다.
		Class<?> type = obj.getClass();
		System.out.println(obj);
		try {
			PropertyDescriptor pd = new PropertyDescriptor("mem_id", type);
			Method getter = pd.getReadMethod();
			Method setter = pd.getWriteMethod();
			setter.invoke(obj, "a001");
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			e1.printStackTrace();
		}

//		      try {
//		         Field searchFld = type.getDeclaredField("mem_id");
//		         String setterName = "setMem_id";
//		         Method setter = type.getDeclaredMethod(setterName, searchFld.getType());
//		         setter.invoke(obj, "a001");
//		      } catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
//		         e1.printStackTrace();
//		      }

		Field[] fields = type.getDeclaredFields();
		for (Field fld : fields) {
			String varName = fld.getName();
			Class<?> fldType = fld.getType();
//		         String getterName = "get"+varName.substring(0,1).toUpperCase()+varName.substring(1);
			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, type);
				Method getter = pd.getReadMethod();
				Object fldValue = getter.invoke(obj);
				System.out.printf(" %s %s = %s; \n", fldType.getSimpleName(), varName, fldValue);
			} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
