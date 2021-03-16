package z_exam;

import java.util.Scanner;

/**
 * 2-3 연습문제
 * @author PC-18
 * @since 2020-07-23
 */

public class Exam_03 {
	public static void main(String[] args) {
		
//		4-1) 다음의 문장들을 조건식으로 표현하라.
		
//		(1) int형 변수 x가 10보다 크고 20보다 작을 때 true인 조건식
		int x = 11;
		if(x > 10 && x < 20){ 						// x가 10보다 크고 20보다 작을 때
			System.out.println("4-1_1) 10<x<20 : " + true); 		// true 출력
		}

		
//		(2) char형 변수 ch가 공백이고 탭이 아닐 때 true인 조건식
		char ch = 'A';
		if(ch == ' ' && ch != '\t'){ 				// ch가 ' '이면서 '\t'가 아닐 때 
			System.out.println("4-1_2) ~(공백|탭) : " + true); 		// true 출력
		}
		
		
//		(3) char형 변수 ch가 'x' 또는 'X'일 때 true인 조건식
		if(ch == 'x' || ch == 'X'){ 				// ch가 'x'이거나 'X'일 때 
			System.out.println("4-1_3) x 또는 X : " + true);		// true 출력
		}

		
//		(4) char형 변수 ch가 숫자 ('0'~'9')일 때 true인 조건식
		if((int)ch >= 49 && (int)ch <= 57){ 		// '0'은 10진수로 49고 '9'는 57이라서 int형으로 캐스팅한 ch가 49보다 크거나 같고 57보다 작거나 같을 때로						
			System.out.println("4-1_4) 숫자 : " + true);		// 조건을 걸면 '0'부터 '9'사이의 문자는 전부 true로 출력할 수 있다.
		}
		

//		(5) char형 변수 ch가 영문자(대문자 또는 소문자) 일 때 true인 조건식
		if(ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z'){	// ch가 'A'보다 크거나 같고 'Z'보다 작거나 같을 때 (대문자일 때) 또는 ch가 'a'보다 크거나 같고 'z'보다 작거나 같을 때 (소문자일 때) 
			System.out.println("4-1_5) 영문자 : " + true); 		// true를 출력
		}

		
//		(6) boolean형 변수 powerOn가 false일 때 true인 조건식
		boolean powerOn = false;
		if(powerOn == false){						// powerOn이 false일 때
			System.out.println("4-1_6) 전원 꺼짐 : " + true);		// true 출력
		}
		
		
//		(7) 문자열 참조변수 str이 "yes"일 때 true인 조건식
		String str = "yes";
		if("yes".equals(str)){							// str이 "yes"일 때
			System.out.println("4-1_7) yes? : " + true);		// true 출력
		}
		
		
		
//		4-2) 1부터 20까지의 정수 중에서 2 또는 3의 배수가 아닌 수의 총합을 구하시오.
		int j = 0; 									// 합을 저장할 변수
		for(int i = 0; i < 21 ; i++) {				// i를 0부터 시작해서 21보다 작을 때까지 (20일 때까지) 1씩 증가시키며 반복한다.
			if((i % 2 != 0) && (i % 3 != 0)) { 		// 만약 i를 2로 나눈 나머지가 0이 아닐 때 (2의 배수가 아닐 때) 그리고 i를 3으로 나눈 나머지가 0이 아닐 때 (3의 배수가 아닐 때)
				j = j + i; 							// 합을 저장할 변수 j에 현재의 i를 더한다.
			}										// 1 + 5 + 7 + 11 + 13 + 17 + 19 = 73
		}										
		System.out.println("4-2) 1부터 20까지 중에 2또는 3의 배수가 아닌 수의 총합 : " + j); 
		
		
		
//		4-3) 다음의 for문을 while문으로 변경하시오.
//		for(int dan = 2; dan < 10; dan++){
//			for(int gob = 1; gob < 10; gob++){
//				System.out.println("for : " + dan + " * " + gob + " = " + dan*gob);
//			}
//		}
		
		int dan = 2; 			// for문은 초기화 > 조건식 > 내부수행 > 증감식 순으로 실행되지만 while문은 조건식밖에 들어있지 않다 그래서 선언과 초기화를 위에서 먼저 해준다.
		while(dan < 10){		// dan이 10 미만일 때 true로 반복 수행하다가 10이 되는 순간 조건식이 false가 되어 멈춘다.
			int gob = 1;		// 밑의 while문에서 사용할 선언과 초기화
			while(gob < 10){	// 위 while과 마찬가지로 반복수행하다가 gob이 10이되는 순간 동작을 멈추고 while문을 탈출한다.
				System.out.println("4-3) 구구단 : "+ dan + " * " + gob + " = " + dan*gob);
				gob++; 			// while문에서 한번 반복될때마다 1씩 값을 올린다.
			}
			dan++; 				// dan 값을 1개 증가시킨다.
		}
//		while문 내의 while문(2번째 while)의 print가 처음 동작될 때 dan의 초기값은 2, gob은 1로 출력된다. 2*1 = 2
//		print출력 후 gob의 값을 1 올리고 다시 print되는데 gob이 9가 될때 까지 반복하다가 10이되면 print로 돌아가지않고 while문을 탈출해 밑의 dan++이 실행된다.
//		dan을 하나 올려준 후 다시 첫 while의 int gob =1;이 실행되어 10이었던 gob의 값이 1로 초기화된다.
//		그럼 2번째 while문의 조건이 다시 true가 되어 2번째 while문이 동작한다.
//		그렇게 반복하다 dan이 10이 되는 순간 멈춘다. print의 마지막 출력값은 9*9 = 81
		
		
		
		
//		4-4) 두 개의 주사위를 던졌을 때, 눈의 합이 6이 되는 모든 경우의 수를 출력하는 프로그램을 작성하시오.
		
		for(int dice1 = 1; dice1<7; dice1++){		// 첫번째 주사위 1부터 시작 6이 될때까지 한번 실행할 때마다 1씩 증가 
			for(int dice2 = 1; dice2<7; dice2++){  // 두번째 주사위 1부터 시작 6이 될때까지 한번 실행할 때마다 1씩 증가 
				if(dice1 + dice2 == 6){				// 두 주사위의 합이 6일 때만
					System.out.println("4-4) 주사위 : " + dice1 + "+" + dice2 + "=" + (int)(dice1 + dice2) ); // 두 주사위의 합 출력
				}
			}
		}
		// 두개의 for문을 통해 주사위가 각각 1부터 6까지 순차적으로 증가한다. 이 때 내부의 if문을 통해 두 주사위의 값이 6일때만 출력하게하면 1,1부터 6,6까지 모든 경우의 수를 따져볼 수 있다.
		
		
		
//		4-5) 방정식 2x+4y=10의 모든 해를 구하시오. 단, x와 y는 정수이고 각각의 범위는 0<=x<=10, 0<=y<=10이다.
		for(int x5 = 1; x5<=10; x5++){		// 0<=x<=10일 때만 내부반복수행
			for(int y5 = 1; y5<=10; y5++){	// 0<=y<=10일 때만 내부반복수행
				if(2 * x5 + 4 * y5 == 10){		// 2x+4y=10일 때만 print실행
					System.out.println("4-5) 방정식 : " + "x: " + x5 + ", y: " + y5); // x와 y값 출력
				}
			}
		}
		

//		4-6) 사용자로부터 두 개의 정수(시작, 끝)를 입력받아 시작(포함)에서 끝(포함)까지의 곱을 출력하는 프로그램을 작성하시오.
		Scanner sc = new Scanner(System.in); 	// 스캐너를 사용하기 위해 객체를 만든다.
//		System.out.print("4-6) 첫 정수를 입력 : ");		
//		int firstInt = sc.nextInt();			// 첫 정수를 입력 받음
		int firstInt = 3;					// 입력하기 귀찮아서 임의로 정함
//		System.out.print("4-6) 끝 정수를 입력 : ");
//		int lastInt = sc.nextInt();				// 끝의 정수를 입력 받음
		int lastInt = 6;
		int result6 = firstInt;				// 두 정수의 처음부터 끝까지 곱한 값을 저장할 변수, 초기 값은 첫 정수로 주었다. 
		
		if (firstInt<lastInt) {												// 첫 정수가 끝 정수보다 작을 때
			for(int i = firstInt; i < lastInt; i++) {  						// 첫 정수의 초기 값은 자기자신. 첫 정수가 끝 정수보다 작을 때까지만 반복한다. 
				result6 = result6 * (i + 1);								// result(초기 값은 첫 정수)에 첫 정수에 +1을 한 수를 곱한다. 
			}	// 이렇게 하면 첫 정수에 첫 정수보다 1 큰 수를 곱함으로써 순차적으로 곱이 가능하다. 
				// 첫 정수에 +1을 해줌으로써 첫 정수에 첫 정수를 곱하지 않게 되고(중복x), 첫 정수가 끝 정수보단 작지만 끝 정수의 값까지 포함해서 곱할 수 있게 된다.												  
			System.out.println("4-6) 두 정수의 시작에서 끝까지 곱은 : " + result6); 	// 모두 곱한 결과를 출력한다.
		}else if(firstInt>lastInt) {										// 첫 정수가 끝 정수보다 클 때
			for(int i = firstInt; i > lastInt; i--) {  						// 첫 정수가 끝 정수보다 크므로 첫 정수가 끝 정수보다 작아지기 전까지 1씩 줄여준다.
				result6 = result6 * (i - 1); 								// result에 첫 정수에 -1을 한 수를 곱한다.
			}	// 첫 정수(result 초기값)에 첫 정수의 값이 끝 정수가 될 때까지 1씩 떨어뜨리면서 곱할 수 있다.
			System.out.println("4-6) 두 정수의 시작에서 끝까지 곱은 : " + result6);
		} else if(firstInt == lastInt) { 								// 두 정수가 같은 경우 
			System.out.println("4-6) 두 정수의 시작에서 끝까지 곱은 : " + firstInt);  // 첫 정수나 뒷 정수 하나만 출력
		}

		
//		4-7) 1 + (1+2) + (1+2+3) + (1+2+3+4) + ... + (1+2+3+...+10) 의 결과를 계산하시오.
		int y7 = 0, result7 = 0;					// 앞의 계산 값을 저장할 변수 선언 및 초기화
		for(int x7 = 0; x7 < 10; x7++) { // 총 10번 반복
			y7 = y7 + (x7+1);		// 첫 계산은 0+1=1이다. 그 결과 값이 y7에 저장되어 그 값에 계속 증가하는 x7+1을 반복해서 더한다.
			// y7이 순차적으로 1 > 3 > 6 > 10 > 15 > 21 > 28 > 36 > 45 > 55로 변경된다.
			result7 = result7 + y7; // y7을 반복해서 더하게 된다 그 값이 result7에 저장되어 y7을 전부 더한 총합이 된다.
		}
		System.out.println("4-7) 총 합 : " + result7); // 220
		
		
	
		
//		4-8) 1 + (-2) + 3 + (-4) + ...과 같은 식으로 계속 더해나갔을 때, 몇까지 더해야 총합이 100 이상이 되는지 구하시오.
		int x8 = 1, y8 = 0, result8 = 0;
		while(result8 < 100) {
			if(x8 % 2 != 0) { // 홀수번째로 더할 때 (양수)
				y8 = x8; // 1 3 5 7 ...
				result8 = result8 + y8; //
			}
			else if(x8 % 2 == 0) { // 짝수번째로 더할 때 (음수)
				y8 = -1*x8; // -2 -4 -6 -8 ...
				result8 = result8 + y8;
			}
			x8++;
		}
		System.out.println("4-8) 총 합 : " + result8);
		
		
//		4-9) 사용자로부터 입력받은 정수의 각 자리의 합을 더한 결과를 출력하는 프로그램을 작성하시오.
//		예를 들어 사용자가 53263을 입력하였다면 결과는 19가 되어야 한다.
		Scanner sc2 = new Scanner(System.in); 	// 스캐너를 사용하기 위해 객체를 만든다.
		System.out.print("4-9) 5자리 이하의 정수를 입력 : ");		
		int inputInt = sc2.nextInt();			// 정수를 입력 받음.
		int sumInt = 0;
		//2147483647
		if(inputInt >= 100000){
			System.out.println("5자리 이하의 정수로 입력해 주세요");
		}else if(inputInt < 0){
			System.out.println("양수나 0으로 입력해 주세요");
		}else{
			for(int i = 0; i < 5; i++){
				switch(i) {
				case 0 :
					sumInt += inputInt/10000;	// 만의 자리. ex) 54231/10000 = 5 를 sumInt에 더한다
					break;
				case 1 :
					sumInt += inputInt/1000%10;	// 천의 자리. ex) 54231/1000 = 54 => 54%10 = 4
					break;
				case 2 :
					sumInt += inputInt/100%10;	// 백의 자리. ex) 54231/100 = 542 => 542%10 = 2
					break;
				case 3 :
					sumInt += inputInt/10%10;	// 십의 자리. ex) 54231/10 = 5423 => 5423%10 = 3
					break;
				case 4 :
					sumInt += inputInt%10;		// 일의 자리. ex) 54231%10 = 1
					break;
				}
			}
			System.out.println("각 자리의 합 : " + sumInt); // 케이스에서 한자리씩 더한 총 합.
		}
		
//		4-10) 피보나치 수열은 앞을 두 수를 더해서 다음 수를 만들어 나가는 수열이다. 
//		예를 들어 앞의 두 수가 1과 1이라면 그 다음 수는 2가 되고 그 다음 수는 1과 2를 더해서 3이 되어서 1,1,2,3,5,8,13,21,...과 같은 식으로 진행된다.
//		1과 1부터 시작하는 피보나치수열의 10번째 수는 무엇인지 계산하는 프로그램을 작성하시오.
		int fibonnaci = 0;
		int odd = 1; 	// odd와 even을 1로주어 첫 계산을 1+1로 시작한다
		int even = 1;
		for(int i=1; i<9; i++){ 		// 10번째 수를 얻어야 하는데 이미 1과 1이 존재하니 8번 더 반복해야 10번째 수가 나온다
			if(i%2 != 0){				// 홀수번째 계산식
				fibonnaci = odd + even; // odd는 첫번째 수 even은 두번째 수
				odd = fibonnaci;		// a + b = c일때 다음 계산(짝수번째 계산)에서는 b + c = d가 되어야한다 여기서 odd가 항상 c에 해당되게해주어야 한다. even은 b에 해당된다.
				System.out.println((int)(i+2) +"번째 수 : " + fibonnaci);
			}else{						// 짝수번째 계산식
				fibonnaci = odd + even;	// b + c = d의 다음 계산(홀수번째 계산) c + d = e를 만들어 주려면 even을 d로 계속 만들어주어야 한다.
				even = fibonnaci;
				System.out.println((int)(i+2) +"번째 수 : " + fibonnaci);
			}
		}
		System.out.println("4-10) 피보나치 10번째 수 : " + fibonnaci); // odd와 even을 더하는걸 8번 반복하면 10번째 Fibonnaci 값이 나온다.
		
		
//		4-11) 다음은 주어진 문자열이 숫자인지를 판별하는 프로그램이다. (1)에 알맞은 코드를 넣어서 프로그램을 완성하시오.
		String value11 = "1234";
		char ch11 = ' ';
		boolean isNumber = true;
		
		for(int i=0; i < value11.length(); i++){
			// /* (1)
			ch11 = value11.charAt(i);				// 문자열 value11에서 한자리씩 추출하여 문자형으로 만든다.
			if (!(ch11 >= '0' && ch11 <= '9')){		// 추출한 문자형이 숫자가 아닐 경우
				isNumber = false;					// isNumber를 false로 만든다.
			}
		}
		
		if (isNumber){
			System.out.println("4-11) " + value11 + "는 숫자입니다.");
		}else{
			System.out.println("4-11) " + value11 + "는 숫자가 아닙니다.");
		}
		

		
	}
}
