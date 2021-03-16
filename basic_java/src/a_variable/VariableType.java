package a_variable;

public class VariableType {

	public static void main(String[] args) {
		
//		변수의 타입
//		1. 기본형 타입(primitive type)
//		- boolean, char, byte, short, int, long, float, double
		
//		2. 참조형 타입(reference type)
//		- 8가지 기본형을 제외한 나머지 타입, 객체의 주소를 저장.
		
//		3. 기본형 타입 크기(1byte = 8bit)
//	 	- 1byte: boolean, byte
//		- 2byte: char, short
//		- 4byte: int, float
//		- 8byte: long, double
		
//		4. 종류
//		- 논리형: true, false 중 하나를 값으로 가진다. boolean
//		- 문자형: 문자를 하나 저장하는데 사용된다. char
//		- 정수형: 정수 값을 저장하는데 사용된다. byte, short, int, long
//		- 실수형: 실수 값을 저장하는데 사용된다. float, double
		
//		5. 논리형 - boolean(기본 값 : false)
//		- boolean형 변수에는 true또는 false 값 중 하나만 저장할 수 있다.
//		- boolean형 변수는 대답(yes, no), 스위치(on/off)등의 논리 구조에 사용된다.
//		- 데이터를 다루는 가장 작은 단위는 byte이기 때문에 1byte의 크기를 가진다.
		
//		문제 1. 변수  power를 선언하고 true의 값으로 초기화 하여라.
		boolean power = true;
		System.out.println("문제 1: " + power);
		
//		6. 문자형 - char
//		- java는 Unicode문자체계를 이용한다. 2byte의 크기를 가진다.
//		- 문자 하나를 저장하기 위해서 사용한다. 'A' 
//		
//		문제 2. 변수 ch를 선언하고 'A'의 값으로 초기화 하여라.
		char ch = 'A';
		char ch2 = '\u0041';
		char ch3 = 65;
		System.out.println("문제 2: " + ch + " & " + ch2 + " & " + ch3);
		
//		7. 정수형 - byte, short, int, long
//		- 기본 자료형은 int이다.
//		- 변수에 저장하려는 정수 값의 범위에 따라 4개의 정수형 중에 하나를 선택한다.
//		- byte, short의 경우 크기가 작아서 범위를 넘어서는 경우가 많다.
//		그래서 int형을 사용하는 것이 좋다.
//		표현할 수 있는 수 byte -> 1byte -> 8bit -> 7칸(8칸 중 첫 칸은 부호) -2e7 ~ 2e7-1
//					char -> 2byte -> 16bit -> 16칸 0 ~ 2e16-1
		
//	문제 3-1. 변수 b1에 50의 값을 저장하여라.
		byte b1 = 50;
//		3-2. 변수 s1에 25000의 값을 저장하여라.
		short s1 = 25000;
//		3-3. 변수 i1에 15억의 값을 저장하여라.
		int i1 = 150000000;
//		3-4. 변수 l1에 53억의 값을 저장하여라.
		long l1 = 5300000000L; // int보다 큰 수기 때문에 뒤에 L을 붙혀 long이라는 것을 알려줘야 한다.
				
//		8. 실수형 - float, double
//		- 실수형 값을 저장하는데 사용된다.
//		- float : 1+8+23
//		- double : 1+11+52
//		- 실수형 중 자료형을 선택할 때는 값의 범위만이 아니라 정밀도가 중요하다.
		
//	문제 4-1. 변수 f1에 3.141592를 저장
		float f1 = 3.1415923f;
//		4-2. 변수 d1에 3.141592531548를 저장해주세요
		double d1 = 3.14159231548;
		
		
//		9. String - 문자열을 다루는 클래스, 기본값이 null이다.
		String name = "Kim SunJoon";
		String gibon = null;
		
		String r1 = "========\n------------";
		System.out.println(r1);
		
	
//	  문제 5-1. 변수 a1에 7의 값을 저장
		int a1 = 7;
//		5-2. 변수 s1에 "이름" 값 저장
		String s2 = "9";
//		5-3. a1과 ㄴ2의 합을 변수 result1에 저장
		String result1 = a1 + s2; // int + String => String + String
		System.out.println(result1);
		
		System.out.println(""+null);
		
//		10. overflow - 변수 자신이 저장할 수 있는 범위를 넘어섰을 때 최솟값으로 돌아가는 현상
		byte b2 = 126;
		b2++;
		System.out.println(b2); // 127
		b2++;
		System.out.println(b2); // -128
		
//		11. 형변환(casting) - 캐스트 연산자라는 것을 이용하여 형변환을 할 수 있다.
//		'A' => 65
		
//	  문제 6-1. byte형 변수 b3에 100의 값을 저장
		byte b3 = 100; 
//		6-2. char형 변수 c3에 'K'의 값을 저장해주세요
		char c3 = 'K';
//		6-3. int형 변수 i3에 400000의 값을 저장
		int i3 = 400000;
//		6-4. float형 변수 f3에 3.14를 저장
		float f3 = 3.14f;
//		6-5. c3에 저장된 값의 10진수 값을 변수 result2에 저장
		int result2 = (int)c3;
//		6-6. result2 출력
		System.out.println(result2);
//		6-7. f3에 저장된 값 중 정수 값만 취득하여 result3에 저장
		int result3 = (int)f3;
//		6-8. result3를 출력
		System.out.println(result3);

//	  문제 7-1. byte 타입의 변수 b6을 선언, 15의 값으로 초기화
		byte b6 = 15;
//		7-2. char 타입의 변수 c6을 선언, 'A'의 값으로 초기화.
		char c6 = 'A'; // 65
//		7-3. b6와 c6의 합을 byte형 변수 result6에 저장
		byte result6 = (byte)(b6 + c6); // java는 4byte가 기준이기 때문에 4byte 밑의 형은 자동으로 int로 변환한다.
		System.out.println(result6);
		
		
	}

}
