package h_javaLang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaLangBasic {

	public static void main(String[] args) {
		
//		1. java.lang패키지
//			- java 프로그래밍에 기본적으로 필요한 클래스들을 모아놓은 패키지 이다.
//			- String, Object, System...
		
//		2. Object
//			- Object클래스는 멤버변수없이 11개의 메서드로 되어있다.
//			
//			- equals()
//			: 참조변수가 가르키는 주소를 비교한다.
//			: 주소가 아닌 해당 인스턴스가 가지고 있는 값을 비교하게 하려면 equals()를 오버라이드 해야한다.
//			: equals()가 오버라이드 되어있는 클래스를
//				-> String, File, Date...
//			
//			- hashCode() - 10진수로 이루어져 있다.
//				: 객체의 주소에서 해시코드를 만들어 반환한다.
//				: String클래스는 같은 문자열을 가지고 있다면 동일한 해시코드를 반환하게 만들어져 있다.
//			
//			- toString()
//				: Object의 toString()
//					-> return getClass().getName()+"@"+Integer.toHexString(hashCode());
//				: toString() 오버라이드 되어있는 클래스들
//					-> String...
//			
//			- getClass()
//				: 클래스의 정보를 얻어올때 사용한다.
//					(1) 생성된 객체로 부터 얻는 방법
//						Class obj = new Person().getClass();
//					(2) 클래스 리터럴로 부터 얻는 방법
//						Class obj = Person.class;
//					(3) 클래스명으로부터 얻는 방법
//						Class obj = Class.forName("Person");
//					(문자열로 찾을때는 항상 not found exeception을 고려해야한다)
			
		
//		3. String
//			- 다른 언어에서는 문자열을 char형 배열로 다룬다. 하지만 java에서는 문자열을 다룰수 있는 String 클래스를제공한다.
//			- 문자열을 합칠때는 합쳐진 문자열을 저장할 인스턴스 생성된다.
//			- 문자열의 비교
//				: 문자열 리터럴을 만드는 방법과, 객체의 생성자를 이용할 수 있다.
//			
//			- 인코딩 변환
//				: 이클립스의 기본 인코딩 방식은 "MS949"
//				: 한글 윈도우의 기본 방식은 "CP949"
//				: 우리가 사용하는 인코딩 방식은 "UTF-8"
//			
		
//			- 문자열 format
//				: 기본형 타입을 String타입으로 변환
//					1) 빈문자열을 더하는 방식
//						int a = 10;
//						String b = a + "";
//					2) valueOf메서드
//						Sting b = String.valueOf(a);
//				: String타입을 기본형으로 변환
//					1) wrapper클래스를 이용하는 방식 // wrapper는 기본형 타입 8개의 각각 클래스의 집합을 의미
//						String b = "123";
//						int c = Integer.parseInt(b);		
//						int c = Integer.valueOf(b); // 모든wrapper가 공통으로 사용하는거
//					2) wrapper 클래스의 진수
//						String b = "234";
//						int c = Integer.parseInt(b,8);
//						System.out.println(c);
						
		
//		4. StringBuffer, StringBuilder
//			- 문자열을 합치기 위해서 사용한다.
//			- 버퍼는 동기화 보장, 빌더는 비동기화, 속도는 빌더가 더 빠름 
		
//		5. wrapper 클래스
//			- 자바는 모든 것을 객체로 다루어야 한다.
//				기본형		wrapper클래스
//			  	boolean 	Boolean
//			  	char		Character ***
//				byte		Byte
//			  	short		Short
//			  	int			Integer ***
//			  	long		Long
//			  	float		Float
//			  	double		Double
		
//			- 기본형타입 -> wrapper클래스 : auto-boxing
//			- wrapper클래스 -> 기본형타입 : unBoxing
		
		
//		6. 정규식
//			- 텍스트 데이터에서 원하는 형태의 문장을 찾기 위해 만들어 졌다.
//			- 정규식 순서
//				: 패턴정의
//				-> Pattern클래스를 이용하여 패턴을 정의한다.
//				Pattern p = Pattern.compile();
		
//				: 텍스트와 비교
//				-> Matcher클래스를 이용하여 패턴과 텍스트를 비교한다.
//				Matcher m = p.matcher("text");
		
//				m.matches()
				
//			- 정규식 문법
//				^  : 문자열의 시작
//				$  : 문자열의 끝
//				.  : 임의의 한문자, 역슬러시는 포함되지 않는다.
//				*  : 0개 또는 무한정 있을 수 있다.
//				+  : 한개 이상 있을 수 있다.
//				?  : 0개 또는 한 개 있을 수 있다.
//				() : 문자열을 하나의 문자로 인지한다.
//			    {} : 반복횟수를 지정한다.
//				[] : 범위를 지정할 때 사용한다.
//				|  : or연산을 수행할 때 사용된다.
//				\s : 공백문자
//				\S : 공백을 제외한 모든 문자
//				\w : 영어 대문자 또는 소문자 또는 숫자
//				\d : 숫자 [0-9]
		
				Pattern p = Pattern.compile("[a-z]+");
				Matcher m = p.matcher("a");
				System.out.println(m.matches());
				
				
				
				
				
	}

}
