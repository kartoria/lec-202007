package z_exam;
/**
 * 2-1 연습문제
 * @author PC-18
 * @since 2020-07-22
 */
public class Exam_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		2-1) 다음 표의 빈칸에 8개의 기본형  
	
// 			 	1byte	2byte	4byte	8byte
//			----------------------------------
//		논리형ㅣ	boolean
//		문자형ㅣ			char
//		정수형ㅣ	byte	short	int		long
//		실수형 ㅣ					float	double

		
//		2-2) 다음의 문장에서 리터럴, 변수, 상수, 키워드를 적으시오.
//		int i = 100;
//		long l = 100L;
//		final float PI = 3.14f;
//		- 리터럴 : 100, 100L, 3.14f 		// 리터럴이란 값을 뜻한다.
//		- 변수 : i, l 					// 변수타입 뒤에 오는 게 변수이다.
//		- 키워드 : int, long, final, float // 변수타입을 포함한 예약어를 말한다. 이클립스에서 자주색으로 표현된다.
//		- 상수 : PI 						// final로 변수를 초기화 또는 변경하면 더이상 값이 변할 수 없기 때문에 변수가 아닌 상수로 취급한다.
		
//		2-3) 다음 중 기본형(primitive type)이 아닌 것은? 답: 2 // 대소문자를 구별하기에 byte는 기본형이지만 Byte는 아니다.
//		1. int
//		2. Byte
//		3. double
//		4. boolean
		
//		2-4) 다음 문장들의 출력결과를 적으세요.
		System.out.println("1" + "2");
//		답: 12 					// 둘 다 문자열이 때문에 3이 아닌 12가 된다. 
		System.out.println(true + "");
//		답: true 				// ""가 문자열(String)이기 때문에 true도 문자열로 변해서 더해진다. 
		System.out.println('A' + 'B');
//		답: 131					// 4byte 밑은 int로 자동변환되기 때문에 char에서 int로 자동 변환해서 더하는데, ASCII에서 A는 10진수로 65, B는 66이다. 더하면 131이 된다.
		System.out.println('1' + '2');
//		답: 99 					// 위와 마찬가지로 문자형 1은 10진수로 49 2는 50이다.
		System.out.println(4 + 24.3715F);
//		답: 28.3715 				// 4는 int 24.3715F는 float, 4가 int보다 큰 float로 바껴서 계산해 실수형으로 출력
		System.out.println('A' + 3.14);
//		답: 68.14 				// char보다 큰 double로 자동변환되어 최종값이 double인 실수형으로 출력
		System.out.println('J' + "ava");
//		답: Java 				// 문자열이 크기때문에 문자형이 문자열로 변해 더해진다.
//		System.out.println(true + null);
//		답: 오류 					// null은 참조형 주소의 기본 값으로 기본형과 더해질 수 없다.
	
//		2-5) 다음 중 키워드가 아닌 것은? (모두)
//		1. if
//		2. True
//		3. NULL
//		4. Class
//		5. System 
//		답: 2,3,4,5 				// 키워드는 예약어를 말한다. (자주색)
		
//		2-6) 다음 중 변수의 이름으로 사용할 수 있는 것은? (모두)
//		1. $ystem 		// 특수문자중 _와 $는 사용 가능하다
//		2. channel#5 	// #은 사용 불가하다.
//		3. 7eleven		// 숫자는 맨 앞에 올 수 없다.
//		4. If 			// if는 예약어로 사용할 수 없지만 If는 가능하다
//		5. 자바 			// 암묵적으론 한글을 사용하지 않지만 사용할 순 있다.
//		6. new 			// 예약어는 사용 불가하다.
//		7. $MAX_NUM 	// 암묵적으론 상수를 뜻하지만 사용가능 하다.
//		8. hello@com 	// @는 사용 불가하다.
//		답: 1,4,5,7
		
//		2-7) 참조형 변수와 같은 크기의 기본형은? (모두)
//		1. int 					// 4byte
//		2. long 				// 8byte
//		3. short 				// 2byte
//		4. float 				// 4byte
//		5. double 				// 8byte
//		답: 1, 4 				// 참조형은 4byte다.
		
//		2-8) 다음 중 형변환을 생략할 수 있는 것은? (모두)
//		byte b = 10;
//		char ch = 'A';
//		int i = 100;
//		long l = 1000L;
		
//		1. b = (byte)i;
//		2. ch = (char)b;
//		3. short s = (short)ch;
//		4. float f = (float)l;
//		5. i = (int)ch;
//		답: 4,5 			// long인 l보다 float가 크고, char인 ch보다 int가 크다.
		
//		2-9) char타입의 변수에 저장될 수 있는 정수 값의 범위는?
//		답: 0 ~ 2^16-1 			// char는 2byte > 16bit > 문자형이기때문에 부호없음 > 16칸활용
		
//		2-10) 다음 중 변수를 잘 못 초기화 한 것은?
//		1. byte b = 256; 		// byte는 127까지 담을 수 있다.
//		2. char c = ''; 		// 문자열은 공백이 가능하지만 문자형은 불가하다.
//		3. char answer = 'no';		// 문자형은 한 문자만 들어가야 한다.
//		4. float f = 3.14			// 3.14뒤에 f와 ;이 없다.
//		5. double d = 1.4e3f; 		// double에 float의 값을 넣어도 자동변환된다.
//		답: 1,2,3,4
		
		
	
		
	}

}
