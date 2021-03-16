package c_sentence;

import java.util.Scanner;

/****************************
 * do while, scanner		*
 * @author PC-18			*
 * @since 2020.07.28		*
 ****************************/

public class sentence_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		1. 사용자로부터 값을 입력받기
//		-Scanner
		Scanner sc = new Scanner(System.in);
//		System.out.println("문자열을 입력해주세요");
//		String input = sc.nextLine();
//		System.out.println(input);
		
//		System.out.println("숫자를 입력해주세요");
//		int inputInt = sc.nextInt();
//		System.out.println("입력하신 숫자는 "+inputInt+"입니다.");
//		System.out.println(inputInt);
		/*
//		1. 사용자로부터 숫자 두 개를 받아서 두 숫자 사이의 합을 출력해주세요
		System.out.print("숫자1을 입력 : ");
		int inputInt = sc.nextInt();
		System.out.print("숫자 2를 입력 : ");
		int inputInt2 = sc.nextInt();
		int sum = 0;
		if(inputInt<inputInt2){
			for(int i=inputInt; i<inputInt2+1; i++){
				sum += i;
			}
		}else if(inputInt == inputInt2){
			sum = inputInt;
		}else{
			for(int i=inputInt2; i<inputInt+1; i++){
				sum += i;
			}
		}
		System.out.println(sum);
		*/
		
//		2. do-while
//		- while문의 변형으로 기본구조는 while문과 비슷하다.
//		하지만 최소 1회는 블럭{}을 수행하게 된다.
//		- 기본구조
//		do{
//			수행될 문장
//		}while();
		
//		사용자로부터 입력받은 문자열을 출력하는 프로그램을 만든다.
//		단, 사용자가 "exit"를 입력할 때까지 무한 반복
//		String input3;
//		do{
//			System.out.print("문자열을 입력해 주세요 : ");
//			input3 = sc.next();
//			System.out.println(input3);
//		}while(!("exit".equals(input3)));

		
//		1. 사용자로부터 숫자를 입력받는다.
//		2. 사용자로부터 부호를 입력받는다.
//		3. 사용자로부터 숫자를 입력받는다.
//		4. 연산을 수행하여 결과를 출력한다.
//		5. 1~4를 무한 반복해준다.
//		단 사용자가 입력한 부호가 사칙연산자가 아니면 종료하여라.
		
		int inputInt, inputInt2;
		String inputSign;
		
		do{
			System.out.print("숫자1을 입력하세요 : ");
			inputInt = sc.nextInt();
			System.out.print("부호를 입력하세요 : ");
			inputSign = sc.next();
			System.out.print("숫자2를 입력하세요 : ");
			inputInt2 = sc.nextInt();
	
			if("+".equals(inputSign)){			// 부호가 +일때
				System.out.println("더한 값은 : " + (int)(inputInt + inputInt2));
			}else if("-".equals(inputSign)){ 	// 부호가 -일때
				System.out.println("뺀 값은 : " + (int)(inputInt - inputInt2));
			}else if("*".equals(inputSign)){	// 부호가 *일때
				System.out.println("곱한 값은 : " + (int)(inputInt * inputInt2));
			}else if("/".equals(inputSign)){	// 부호가 /일때
				System.out.println("나눈 값은 : " + ((float)inputInt / inputInt2));
			}
		}while(("+".equals(inputSign) || "-".equals(inputSign) 
				|| "*".equals(inputSign) || "/".equals(inputSign))); // 부호가 +-*/ 중에 하나일 때만 반복하라
		System.out.println("계산기 종료");
		
		
		
		
		
	}

}
