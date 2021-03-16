package b_operation;

/****************************
 * 증감,부호,비트전환,논리부정 연산자	*
 * @author PC-18			*
 * @since 2020.07.23		*
 ****************************/

public class operation_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		1. 증감 연산자(++, --)
//		- 증가연산자(++) : 피연산자의 값을 1 증가시킨다.
//		- 감소연산자(--) : 피연산자의 값을 1 감소시킨다.
		
		byte b1= 50;
		b1++; 			// b1 = (byte)(b1+1); 와 같다.
		char c1 = 'A';
		c1++; 			//c1 = (char)(c1 + 1);
		System.out.println("c1: "+ c1);
		++c1; // 'C'
		
		int i1 = 40;
		int i2 = 50;
		int i3 = i1 + i2++;
		System.out.println("i3: " + i3);
		
//		1. int형 변수 number를 선언하고 30의 값으로 초기화 하여라.
		int num = 30;
//		2. char형 변수 ch를 선언하고 'C'의 값으로 초기화 하여라.
		char ch = 'C';
//		3. 다음의 출력결과를 작성하여라.
		int result = num++ + 3 + ++ch + ++num; // 30 + 3 + 'D' + 31 = 30 + 3 + 68 + 31 = 132
		System.out.println("num: " + num); // num++에서 한번 ++num에서 한번 증가해서 32
		System.out.println("char: " + ch); // D
		System.out.println("num++ + 3 + ++ch + ++num = " + result); // 후위형으로 132에서 하나 증가해서 133
		 
//		2. 부호연산자( +, -) - 기본 자료형 중에 boolean, char를 제외한 나머지 자료형에 사용 가능 
//		
//		3. 비트전환 연산자( ~ ) 
//		- 정수형과 char형에만 사용이 가능하다.
//		- 피연산자를 2진수로 표현하였을 때 0은 1로 1은 0으로 바꾼다. 
		
//		 10 00001010
//		~10 11110101 => 1의 보수 -11 // 원래의 수의 부호를 바꾼 후 -1을 하면 나오는 값
//	  ~10+1 11110110 => 2의 보수 -10 // 원래의 수에 부호를 바꾼 값
		
		byte b2 = 10;
		byte b3 = (byte)~b2;
		System.out.println("b3: "+b3);
		
//		4. 논리부정 연산자( ! )
//		- boolean형에만 사용가능하다.
//		- true -> false, false -> true
		
		
		boolean power = false;
//		power의 값을 부정하여 다시 power변수에 저장
		power = !power;
		System.out.println("전원 on/off: " + power);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
