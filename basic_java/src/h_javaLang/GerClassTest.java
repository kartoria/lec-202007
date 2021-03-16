package h_javaLang;

import java.io.Serializable;
import java.util.Arrays;

public class GerClassTest {

	public static void main(String[] args) throws ClassNotFoundException {
		//1. 클래스의 정보를 객체로부터 얻는 방법
		Class re1 = new Person(23433234L).getClass();
		System.out.println(re1.getClass());
		System.out.println(Arrays.toString(re1.getInterfaces()));
		
		//2. 클래스 리터럴로부터 얻는 방법
		Class re2 = Person.class;
		System.out.println(re1.getClass());
		System.out.println(Arrays.toString(re1.getInterfaces()));
		
		//3. 클래스명으로부터 얻는 방법
		Class re3 = Class.forName("h_javaLang.Person");
		System.out.println(re1.getClass());
		System.out.println(Arrays.toString(re1.getInterfaces()));
	}
}
