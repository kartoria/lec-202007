package a_variable;

/**
 * doc주석
 * @author PC-18
 * @since 2020.07.21
 */

public class VariableBasic { // VariableBasic << 클래스명

	public static void main(String[] args) { // main << 어떠한 일을 수행 (method)
//		1. 변수 - 단 하나의 값을 저장할 수 있는 공간이다.
//		2. 변수의 선언 - [변수타입] 변수명;
//		3. 변수의 초기화 - 변수명 = 값;
		int name;
		name = 10; //변수의 초기화		
		int name2 = 20; //변수의 선언&초기화
		name2 = 40;
		System.out.println(name2);
		
		
//		명명규칙 
//		1. 대소문자를 구분하며, 길이의 제한이 없다.
		byte nAme;
		
//		2. 예약어(keyword, reserved)는 사용할 수 없다.
//		int true;
		
//		3. 숫자로 시작하면 안 된다.
//		int 3top;
		
//		4. 특수문자는 '_', '$'만 사용가능하다.
		int _name;
//		int @name;
		
		
//		개발자간의 암묵적인 약속들(가독성을 위해)
//		1. 클래스명의 첫 글자는 대문자로 쓴다.
//		- 변수명과 메서드명은 첫 글자를 소문자로 쓴다.
		
//		2. 여러단어로 이루어진 경우에는 첫 번째 이후 단어의 첫 글자는 대문자로 써야한다.
//		lastIndexOf
		
		
//		3. 상수의 이름은 모두 대문자로 써야한다.
		final int MAX_VALUE = 100;
		
//		4. 한글은 사용하지 않는다.
		
		
		
		
	}

}
