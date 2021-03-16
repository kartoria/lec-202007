package c_sentence;

/****************************
 * if, switch-case			*
 * @author PC-18			*
 * @since 2020.07.27		*
 ****************************/

public class sentence_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
//		1. 조건문(if문, switch문)
//		- 조건식과 문장을 포함하는 블럭{}으로 구서오디어 있다.
//		- 조건식의 연산결과에 따라 프로그램의 실행 흐름을 변경할 수 있다.
		
//		2. if문
//		- 구조
//		if(조건식){
//			조건식이 true일 때 수행될 문장
//		}
		
//		if(조건식){
//			조건이 true일 때
//		}else{
//			조건이 false일 때
//		}
		
//		if(조건식1){
//			조건식 1이 true일 때
//		}else if(조건식 2){
//			조건식 1이 false, 조건식2가 true일 때
//		}else{
//			조건식 1과 2가 모두 false일 때 
//		}
		
		boolean power = false;
//		power가 true면 "켜져있음" 출력
		if(power == true) {
			System.out.println("켜짐");
		}else{
			System.out.println("꺼짐");
		}
		
		int a1 = 10;
//		a1의 값이 0보다 크면 "양수"
//		a1의 값이 0보다 작으면 "음수"
//		a1의 값이 0이면 "0"을 출력
		if(a1>0){
			System.out.println("양수");
		}else if(a1<0){
			System.out.println("음수");
		}else{
			System.out.println("0");
		}
		/*
		int score = (int)(Math.random()*101);
		System.out.println("당신의 점수는 : " + score + "점");
		if(score >= 90){
			System.out.print("A");
			if(score%10 >= 5){
				System.out.println("+");	
			}else{
				System.out.println("-");
			}
		}else if(score >= 80){
			System.out.print("B");
			if(score%10 >= 5){
				System.out.println("+");	
			}
			else{
				System.out.println("-");
			}
		}else if(score >= 70){
			System.out.print("C");
			if(score%10 >= 5){
				System.out.println("+");	
			}else{
				System.out.println("-");
			}
		}else if(score >= 60){
			System.out.print("D");
			if(score%10 >= 5){
				System.out.println("+");	
			}else{
				System.out.println("-");
			}
		}else{
			System.out.println("F");
		}
		*/
//		3. switch-case문
//		- 조건의 경우의 수가 많을 때는 if문 보다는 switch문을 사용하는 것이 좋다.
//		- 조건의 결과 값으로 int형 범위의 정수 값을 허용한다.
//		- 구조

//		switch(조건식) {
//			case 값1 : // 조건식의 값 == 값1
//				조건식 == 값1일 때 수행
//				break;
//			case 값2 : // 조건식의 값 == 값2
//				조건식 == 값2일 때 수행될 문장
//				break;
//			default :
//				조건식과 만족하는 값이 없을 때 수행될 문장.
//		}
		
//		문제 2-17)
//		1. 변수 random을 선언하고 1이상 5이하의 임이의 정수로 초기화하라
		int random = (int)Math.random()*5+1;
//		2. random의 값이 1이면 "32평 아파트 당첨", 2이면 "자동차 당첨", 3이면 "노트북 당첨", 4이면 "자전거 당첨, 5이면 "다음 기회에"를 출력하여라
		switch(random) {
			case 1 : 
				System.out.println("32평 아파트 당첨");
				break;
			case 2 :
				System.out.println("자동차 당첨");
				break;
			case 3 :
				System.out.println("노트북 당첨");
				break;
			case 4 :
				System.out.println("자전거 당첨");
				break;
			case 5 :
				System.out.println("다음 기회에");
				break;
			default :
				System.out.println("먼저 추첨을 해주세요");
		}	
		
		int score2 = (int)(Math.random()*101);
		switch(score2/10){
			case 10 : 
			case 9 :
				System.out.println("당신의 점수는 : " + score2);
				System.out.println("A");
				break;
			case 8 :
				System.out.println("당신의 점수는 : " + score2);
				System.out.println("B");
				break;
			case 7 :
				System.out.println("당신의 점수는 : " + score2);
				System.out.println("C");
				break;
			case 6 :
				System.out.println("당신의 점수는 : " + score2);
				System.out.println("D");
				break;
			default :
				System.out.println("당신의 점수는 : " + score2);
				System.out.println("F");
				break;
		}
		
	}
}
