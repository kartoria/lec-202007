package b_operation;

/****************************
 * 산술,사칙,쉬프트,비교,대소 연산등	*
 * @author PC-18			*
 * @since 2020.07.23		*
 ****************************/

public class operation_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		1. 산술연산자
//		- 사칙연산자(+,-,*,/), 나머지 연산자(%), 쉬프트 연산자(<<, >>, >>>)는 두 개의 피연산자를 취하는 이항연산자 이다.
//		- 이항연산시에는 두 개의 피연산자 모두 4byte형보다 작은 경우에는 둘 다 4byte형인 int로 변환하여 연산을 수행한다.
		
//		2. 사칙연산자(+,-,*,/)
//		- 두 개의 피연산자 중 하나 이상이 4byte형보다 큰 경우에는 큰 자료형으로 변환 후 연산한다.
//		  : int + float => float + float -> float
//		- 두 개의 피연산자 모두 4byte형보다 작은 경우에는 둘 다 int형으로 변환 후 연산한다.
//		  : byte + short => int + int
//		- 정수형 간의 나눗셈에서 0으로 나누는 것은 금지되어 있다.
		
//		2-8) 주석에 적당한 코드를 작성
//		1. byte형 변수 b1을 선언하고 80의 값으로 초기화 하여라.
		byte b1 = 80;
//		2. byte형 변수 b2를 선언하고 100의 값으로 초기화 하여라.
		byte b2 = 100;
//		3. long형 변수 lo1을 선언하고 642의 값으로 초기화 하여라.
		long lo1 = 642L;
//		4. lo1과 b1의 합을 변수 result1에 저장 하여라.
		long result1 = lo1 + b1;
//		5. result1의 값을 출력 하여라.
		System.out.println(result1);
//		6. b1과 b2의 합을 변수 result2에 저장 하여라.
		int result2 = b1 + b2;
//		7. result2의 값을 출력 하여라.
		System.out.println(result2);
//		8. byte형 변수 result3을 선언하고 b1과 b2의 합으로 초기화 하여라.
		byte result3 = (byte)(b1+b2);
//		9.result3의 값을 출력 하여라.
		System.out.println(result3);
//		10. 변수 lo2를 선언하고 60000과 80000의 곱을 저장 하여라.
		long lo2 = 60000*80000L;
//		11. 변수 lo2에 저장된 값을 출력 하여라.
		System.out.println(lo2);
		
//		3. 나머지 연산자(%)
//		- 왼쪽의 피연산자를 오른쪽의 피연산자로 나누고 난 나머지 값을 반환하는 연산자.
//		- boolean형을 제외한 모든 기본형 변수에 사용할 수 있다.
		
		int a1 = 12;
		int b3 = 6;
		
		int share = a1/b3; //1
		int remain = a1%b3; //2
		
		//xx을 xx로 나눈 몫은 x이고 나머지는 xx이다.
		System.out.println(a1 + "을 " + b3 + "로  나눈 몫은 " + share + "이고 나머지는 " + remain + "이다");
		
//		4. 쉬프트 연산자 (<<, >>, >>>)
//		- 정수형 변수에만 사용이 가능하다.
//		- 피연산자의 각자리(2진수)를 오른쪽 또는 왼쪽으로 이동한다.
//		- 곱셈과 나눗셈을 할 때 연산속도가 매우 뛰어나다.
//		- << : x<<n -> x * 2^n
//		- >> : x>>n -> x / 2^n
		
		int shift = 10;
		System.out.println(shift<<33);
		System.out.println(shift>>3);
		
//		5. 비교연산자
//		- 두 개의 변수 또는 리터럴을 비교하는데 사용된다.
//		- 주로 조건문과 반복문의 조건식에 사용되며 연산결과는 true 또는 false 이다.
//		- 이항연산자이다.
		
//		6. 대소비교연산자 ( <, >, <=, >= )
//		- 기본 자료형 중에 boolean을 제외한 나머지 자료형에 사용이 가능하다.
		
		
//		7. 등가비교연산자 ( ==, != )
//		- 모든 자료형에 사용 가능하다.
		
//		수식     | 연산결과
//		x>y  | x가 y보다 클 때만 true, 그 외에는 false
//		x<y  | x가 y보다 작을 때만 true, 그 외에는 false
//		x>=y | x가 y보다 크거나 같을 때만 true, 그 외에는 false
//		x<=y | x가 y보다 작거나 같을 때만 true, 그 외에는 false
//		x==y | x가 y보다 같을 때만 true, 그 외에는 false
//		x!=y | x가 y보다 같을 때만 true, 그 외에는 false
		
//		2-10) 주석에 적당한 코드를 작성해 주세요.
//		1. 변수 f01을 선언하고 0.1f의 값으로 초기화 하여라.
		float f01 = 0.1f;
//		2. 변수 f02를 선어나고 10.0f의 값으로 초기화 하여라.
		float f02 = 10.0f;
//		3. 변수 do01을 선언하고 0.1의 값으로 초기화 하여라.
		double do01 = 0.1;
//		4. 변수 do02를 선언하고 10.0의 값으로 초기화 하여라.
		double do02 = 10.0;
//		5. 변수 str1을 선언하고 "화이팅"의 값으로 초기화 하여라.
		String str1 = "화이팅";
//		6. 다음 질문의 결과를 출력하여라 (변수 f01과 do01의 값은 같은가?)
		System.out.println(f01 == do01);
//		7. 다음 질문의 결과를 출력하여라 (변수 f02와 do02의 값은 같은가?)
		System.out.println(f02 == do02);
//		8. 다음 질문의 결과를 출력하여라 (str1의 값이 "화이팅"과 값은 같은가?)
		System.out.println(str1.equals("화이팅"));
		
//		8. 비트연산자 ( &, |, ^ )
//		- 이진비트연산을 수행한다.
//		- 실수형을 제외한 모든 곳에 사용 가능하다.
//		| (OR 연산) - 피연산자중 한 쪽의 값이 1이면 1의 결과를 얻는다.
//		& (AND 연산) - 피연산자 양쪽 모두 1이면 1의 결과를 얻는다.
//		^ (XOR 연산) - 피연산자의 값이 서로 다르면 1의 결과를 얻는다.
		
		int a = 3;
		int b = 5;
		System.out.println(a|b); // 00000011
								 // 00000110
								 // 00000111 = 7
		System.out.println(a&b);
		System.out.println(a^b);
		
//		9. 논리연산자 ( &&, || )
//		- 피연산자로 boolean형 또는 boolean형 값을 결과로 하는 조건식만 허용한다.
//		- 조건식의 결합에 사용된다.
//		- '&&'이 '||' 보다 연산 우선순위가 높다.
		
//		|| (OR결합) - 피연산자 중 어느 한쪽만 true이면 true의 결과를 얻는다.
//		&& (AND결합) - 피연산자 양쪽 모두 true일 때 true의 결과를 얻는다.
		
		int a2 = 7;
		System.out.println(a2>=5 && a2<=15);
		
		
		char ch01 = 'B';
		System.out.println("A~Z " + (ch01 >= 'A' && ch01 <= 'Z'));
		System.out.println("a~z " + (ch01 >= 'a' && ch01 <= 'z'));
		System.out.println("A~Z or a~z " + ( ch01 >= 'A' && ch01 <= 'Z' || ch01>= 'a' && ch01 <= 'z'));
		System.out.println("0~9 " + (ch01 >= '0' && ch01 <= '9'));
		

//		10. 삼항연산자
//		- 세 개의 피연산자를 필요로 하기 때문에 삼항연산자로 이름지어 졌다.
//		- 삼항연산자의 조건식에는 연산 결과가 true 또는 false인 식이 사용되어야 한다.
//		- 기본구조
//		(조건식) ? true일 때 수행 : false일 때 수행

		int a3 = -592323;
		int abs = a3 >= 0 ? a3 : -a3;
		System.out.println(abs);
		
//		11. 대입연산자 ( =, op= )
//		- 변수에 값 또는 수식 연산결과를 저장하는데 사용한다.
//		- 연산자중 우선순위가 가장 낮다
		int b4 = 10;
		
//		2-11) b4의 값에 3을 곱한 결과를 b4에 저장
		b4 = b4 * 3;
		System.out.println(b4);
		
		char c3 = 'A';
		c3 = (char)(c3+5);
		c3 += 5;
		System.out.println(c3);
		
//		반올림
		float abc = 3.8f;
		System.out.println((int)(abc+0.5));
		float pi = 3.141592f;
		System.out.println((int)(pi*1000+0.5)/1000f);
		
//		랜덤
		int m = (int)(Math.random()*6+1);
		System.out.println(m);
	
		
		int x = (int)(Math.random()*100+1);
		System.out.println("x : " + x);
		
		
		int x2 = 0;
		while ( x2 != 43 ) {
		x2 = (int)(Math.random()*331+44);
		System.out.println("x2 : " + x2);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
	
	
}
