package z_exam;


/**
 * 2-2 연습문제
 * @author PC-18
 * @since 2020-07-22
 */

public class Exam_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		3-1) 다음 중 형변환을 생략할 수 있는 것은? (모두)
		byte b = 10;
		char ch = 'A';
		int in = 100;
		long lo = 1000L;
//		(1) b = (byte) in; // int -> byte 생략불가
//		(2) ch = (char) b; // byte -> char 생략불가
//		(3) short s = (short) ch; // char -> short 생략불가
//		(4) float f = (float) lo; // long -> float 생략가능
//		(5) in = (int) ch; // char -> int 생략가능
//		답: 4, 5
		
		
//		3-2) 다음 연산의 결과를 적으시오
		System.out.println("3-2 문제");
		int x = 2;
		int y = 5;
		char c = 'A';
		
		System.out.println(1 + x << 33); 
//		답: 6
//		1+x는 3, 3을 오른쪽으로 33칸 쉬프트. 
//		이 때 변수타입이 int이기 때문에 총 32칸중 33칸이동 결과적으로 오른쪽으로 한칸이동한 셈이므로 3의 2진수 0011 -> 0110 = 6이 된다.
	
		System.out.println(y >= 5 || x < 0 && x > 2);
//		답: true 
//		비교연산을 먼저해서 왼쪽부터 차례로 true, false, false
//		왼쪽부터 오른쪽으로 연산할 경우 true||false = true -> true&&false = false지만
//		and와 or가 같이 있을 경우 and를 먼저 연산한다.
//		false && false => false , true || false => true로 true가 값이다.
		
		System.out.println(y += 10 - x++); 
//		답: 13
//		10-2를 y에 대입해서 더해준다, 5 + 8 = 13 . x++는 후위형 증가이기 때문에 대입할 당시엔 변하지 않는다.
		
		System.out.println(x+=2);
//		답: 5 
//		앞의 코드에서 x++ 후위증감을 했기때문에 x의 값이 3으로 되어있다. 고로 3+2 = 5
		
		System.out.println(!('A' <= c && c <='Z'));
//		답: false
//		괄호 안에서 비교연산부터 하면 true && true로 !(true)가 된다. 결과는 false
		
		System.out.println('C'-c);
//		답: 2 
//		문자형을 10진수로 변환하면 'C'=67 'A'=65 -> 67-65=2
	
		System.out.println('5'-'0');
//		답: 5 
//		'0'=48, '5'=53 -> 53-48=5
		
		System.out.println(c+1);
//		답: 66
//		65+1=66
		
		System.out.println(++c);
//		답: B
//		'A'에 1을 증감하면 'B'가된다.
		
		System.out.println(c++);
//		답: B
//		후위형이기때문에 다음 줄부터 증가한다.
		
		System.out.println(c + "\n");
//		답: C
//		전 줄에서 B에서 C로 후위 증가시켰기 때문에 C가 된다.
		
		
//		3-3) 아래는 변수 num의 값에 따라 "양수", "음수", "0"을 출력하는 코드이다. 삼항 연산자를 이용해서 (1)에 알맞은 코드를 넣으시오. 
//		int num = 10;
//		String result = /* (1) */;
//		System.out.println(result);
		
		int num3 = 10;
		String result3 = (num3 > 0) ? "양수" : (num3 == 0) ? "0" : "음수";
		System.out.println("3-3 " + num3 + "은 " + result3 +"\n");
//		먼저 num이 0보다 클 때 양수라는 문자열을 출력, 0인지 비교해서 맞을경우 0, 아닐경우 음수를 출력하게 한다.
		
		
//		3-4) 아래의 코드는 사과를 담는데 필요한 바구니의 수를 구하는 코드이다. 만일 사과의 수가 123개이고 하나의 바구니에는 10개의 사과를 담을 수 있다면, 13개의 바구니가 필요할 것이다.
//		(1)에 알맞은 코드를 넣으시오.
		int apples = 123; // 사과개수
		int bucket = 10; // 바구니 1개에 들어가는 사과 개수
		int numOfBucket = apples%bucket != 0 ? apples/bucket + 1 : apples/bucket;
		// 사과 수를 바구니에 들어가는 사과 개수로 나누어 나머지가 있는지 체크한 후 나머지가 있으면 사과 개수를 10개로 나눠 바구니의 개수를 구하고 +1을 해 출력한다. 나머지가 없을 경우 사과 개수 나누기 10의 몫을 출력한다. 
		System.out.println("3-4 사과를 담는데 필요한 바구니의 수: " + numOfBucket + "\n");
		
		
//		3-5) 아래의 코드는 변수 num의 값 중에서 백의 자리 이하를 버리는 코드이다. 만일 변수 num의 값이 '456'이라면 '400'이 되고, '111' '100'이 된다. (1)에 알맞은 코드를 넣으시오.
		int num5 = 456;
		int result5 = num5/100*100; // 정수인 상태로 나누게되면 나머지는 버린채로 몫만 출력되는데 거기에 다시 100을 곱하면 100의 자리를 제외한 수는 버려지게 된다. 
		System.out.println("3-5 백의 자리 이하를 버린 값: " + result5 + "\n"); // 결과 400
		
		
//		3-6) 아래 코드의 문제점을 수정해서 실행 결과와 같은 결과를 얻도록 하시오.
//		------------------ 기존 소스 -----------------
//		byte B = 20;
//		byte C = A + B;
//		char Ch = 'A';
//		ch = Ch + 2;
//		float F = 3 / 2;
//		long L = 3000 * 3000 * 3000;
//		float F2 = 0.1f;
//		double D = 0.1;
//		boolean result2 = D==F2;
//		System.out.println("C=" + C);
//		System.out.println("Ch=" + ch);
//		System.out.println("F=" + F);
//		System.out.println("L=" + L);
//		System.out.println("result2=" + result2);
//		------------------------------------------
//		나와야하는 결과 
//		C = 30
//		Ch = C
//		F = 1.5
//		L = 27000000000
//		result2 = true
		
		
		
		byte A = 10;					//A가 없어서 써준다
		byte B = 20;
		byte C = (byte)(A + B);			//int로 자동적으로 변하는걸 byte로 캐스팅 시켜준다
		char Ch = 'A';
		Ch = (char)(Ch + 2);			//int로 자동적으로 변하는걸 char로 캐스팅 시켜준다
		float F = 3 / 2f;				//float에는 f를 붙힌다
		long L = 3000L * 3000 * 3000;	//long에서는 L을 붙힌다
		float F2 = 0.1f;				
		double D = 0.1;
		boolean result6 = (float)D == F2; //float는 2^n승을 기준으로 소수점이 표현되지만 double은 근사치이기 때문에 값을 비교하려면 float로 캐스팅하여 float형의 범위에 없는 소수점을 제거하고  비교해준다.
		System.out.println("3-6 C = " + C);				// 30
		System.out.println("3-6 Ch = " + Ch);			// C
		System.out.println("3-6 F = " + F);				// 1.5
		System.out.println("3-6 L = " + L); 			// 27000000000
		System.out.println("3-6 result2 = " + result6 + "\n"); // true
		
		
//		3-7) 아래는 변수 num의 값보다 크면서도 가장 가까운 10의 배수에서 변수 num의 값을 뺀 나머지를 구하는 코드이다. 
//		예를 들어, 24의 크면서도 가장 가까운 10의 배수는 30이다. 19의 경우 20이고, 81의 경우 90이 된다. 
//		30에서 24를 뺀 나머지는 6이기 때문에 변수 num의 값이 24라면 6을 결과로 얻어야한다. (1)에 알맞은 코드를 넣으시오

//		int num - 24;
//		int result = /* (1) */;
//		System.out.println("result : " + result);
		
		int num7 = 24;
		int result7 = (num7/10+1)*10 - num7;
//		num을 이용해 num보다 크면서 근접한 10의 배수를 만들어줘야한다.
//		num을 10으로 나눠 십의 자리를 취하고 1을 더한 후 10을 곱한다.
//		24를 예로 들면 24를 10으로 나누어 2로 만든다 (int형) 거기에 1을 더해 3으로 만들고 10을 곱해준다 그럼 30이된다
//		이 값에서 num인 24를 빼면 6이 나온다.
		System.out.println("3-7 " + (int)(num7/10*10+10) + "에서 " + num7 + "을 뺀 나머지: " + result7 + "\n");
		
		
//		3-8) 아래는 화씨를 섭씨로 변환하는 코드이다. 변환공식이 'C = 5/9*(F - 32)'라고할 때, (1)과 (2)에 알맞은 코드를 넣으시오.
//		단, 변환 결과 값은 소수점 셋째자리에서 반올림 해야한다.
		
//		int fahrenheit = 100; //화씨
//		float formula = (/* (1) */); // 변환공식 활용 
//		float celcius = (/* (2) */); // formula 소수점 셋째 자리에서 반올림
//		System.out.println("Fahrenheit:" + fahrenheit);
//		System.out.println("Celcius:" + celcius);
		
		int fahrenheit = 100;
		float formula = 5/9F*(fahrenheit - 32); 		//변환공식에 한쪽을 float로 캐스팅해 양 쪽을 float형으로 만들어준다. 37.777778
		float celcius = (int)(formula*100+0.5)/100F;	
//		formula에 100을 곱하면 3777.7778이 되는데 여기에 0.5를 더해주면 3778.2778이 된다.
//		0.5를 더했을 때 소수점 첫 번째 자리가 5 미만이라면 1의자리가 올라가지 않고, 5 이상이면 올라가기 때문에 반올림이 성립된다.
//		그 다음 값을 int타입으로 만들어 소수점 부분 0.2778을 없애준다.
//		3778에 다시 100을 곱해 원래의 자리수로 돌려놓는데 앞에서 int로 바뀐 변수타입을 float로 바꿔주어야하기 때문에 100을 곱할 때 F를 붙힌다.  
		
	}

}
