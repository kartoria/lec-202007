package z_exam;

import java.util.Arrays;
import java.util.Scanner;
/**
 * 2-4 연습문제
 * @author PC-18
 * @since 2020-08-12
 */
public class Exam_04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
//		5-1) 잘 못된 것을 고르고 그 이유를 설명하시오
//		1. int[] arr[];
//		2. int[] arr = {1,2,3,};
//		3. int[] arr = new int[5];
//		4. int[] arr = new int[5]{1,2,3,4,5}; << 배열에 값을 넣을땐 자동으로 크기를 지정해주기때문에
//		5. int arr[5];						  << 선언시에 크기를 지정할 수 없다.
//		6. int[] arr[] = new int[3][];
		
		
//		5-2) 다음과 같은 배열이 있을 때,
//		int[][] arr = {
//				{5,5,5,5,5},
//				{10,10,10},
//				{20,20,20,20},
//				{30,30}
//		};
//		(1) arr[3].length의 값은 얼마인가?
//		-> 3행의 길이 = 2  
		
//		(2) arr.length의 값은 얼마인가?
//		-> 총 행의 개수 4
		
//		(3) System.out.println(arr[4][1])의 출력 결과는 얼마인가?
//		-> 행이 3까지밖에 없기때문에 오류가 난다
		
//		5-3) 배열 arr에 담긴 모든 값을 더하는 프로그램을 완성하시오.
		int[] arr3 = {10,20,30,40,50};
		int sum3 = 0;
		for(int i=0; i<arr3.length; i++){ // 배열의 길이만큼 반복
			sum3 += arr3[i];				 // 배열의 값을 더함
		}
		System.out.println("Sum = " + sum3);
		
//		5-4) 2차원 배열 arr에 담긴 모든 값의 총합과 평균을 구하는 프로그램을 완성하시오.
		int[][] arr4 = {
				{5, 8, 13, 5, 2},
				{22, 13, 28},
				{2,18,23,62}
		};
		int total = 0;		// 합계를 저장하기 위한 변수
		float average = 0;  // 평균을 저장하기 위한 변수
		int count = 0;
		
		for(int i = 0; i<arr4.length; i++){
			for(int j = 0; j<arr4[i].length; j++){
				total += arr4[i][j];				// 값을 더해서 합계를 만듬
				count++;							// 배열에 들어있는 값 개수 체크용
			}
		}
		average = (int)((float)total/count*100+0.5)/100f; // 평균구하는식
		
		System.out.println("total = " + total);
		System.out.println("Average = " + average);
		
//		5-5) 거스름돈을 몇 개의 동전으로 지불할 수 있는지를 계산하는 문제이다. 변수 money의 금액을 동전으로
//		바꾸었을 때 각각 몇 개의 동전이 필요한지 계산해서 출력하라. 단, 가능한 한 적은 수의 동전으로 거슬러 주어야 한다.
//		(1)에 알맞은 코드를 넣어서 프로그램을 완성하시오.
		
		int[] coinUnit = {500,100,50,10};
		int money = 2790;
		int[] coinCount = new int[coinUnit.length];
		for(int i = 0; i<coinUnit.length; i++){
			coinCount[i] = money/coinUnit[i];		//돈을 동전단위로 나눠서 필요 동전 개수체크
			money -= coinUnit[i]*coinCount[i];		//동전단위에 동전개수를 곱해서 돈에서 빼줌
			System.out.println(coinUnit[i] + "원" + ":" + coinCount[i] + "개");
		}
		
//		5-6) 다음은 1과 9사이의 중복되지 않은 숫자로 이루어진 3자리 숫자를 만들어내는 프로그램이다.
		
		int[] ballArr = {1,2,3,4,5,6,7,8,9};
		int[] ball3 = new int[3];
		
//		ballArr의 index 순서대로 index의 요소와 임의의 요소를 골라서 값을 바꾼다.
		for(int i = 0; i<ballArr.length; i++){
			int random = (int)(Math.random()*9);	//랜덤 0~8로해서 배열에 있는 값 하나를 뽑음
			int temp = ballArr[i];					//현재 인덱스의 값을 저장
			ballArr[i] = ballArr[random];			//현재 인덱스에 랜덤인덱스의 값을 넣음
			ballArr[random] = temp;					//랜덤인덱스에 현재인덱스의 값을 넣음
		}
		System.out.println(Arrays.toString(ballArr));
		
//		ballArr의 앞에서 3개를 ball3로 복사한다.
		for(int i=0; i<3; i++){
			ball3[i] = ballArr[i];
		}
		
//		ball3의 값을 출력한다.
		for(int i=0; i<3; i++){
			System.out.print(ball3[i]);
		}
		System.out.println();
		
//		5-7) 다음은 배열 answer에 담긴 데이터를 읽고 각 숫자의 개수를 세어서 개수만큼 '*'을 찍어서 그래프를 그리는 프로그램이다.
		int[] answer = {1,4,3,2,1,2,3,2,1,4};
		int[] counter = new int[4];
		
		//answer의 요소들 중 1이면 counter의 0번방을 증가, 2면 1번방 3이면 2번방 4면 3번방 증가
		for(int i = 0; i<answer.length; i++){
			switch(answer[i]) {		// index의 담긴 값이
				case 1:				// 1일때
					counter[0]++;	// counter의 0번방에 1을 더함
					break;				
				case 2:				// 2일때                         
					counter[1]++;
					break;
				case 3:				// 3일때
					counter[2]++;
					break;
				case 4:				// 4일때
					counter[3]++;
					break;
			}
		}
		
		//counter에 저장된 요소를 결과와 같이 출력 하여라.
		for(int i=0; i<counter.length; i++){
			System.out.print(i+1 +" : "+ counter[i] + "개 ");
			for(int j=0; j<counter[i]; j++){
				System.out.print('*');
			}
			System.out.println();
		}
		
//		5-8) 문제 5-5에 동전의 개수를 추가한 프로그램이다 커맨드라인으로부터 거슬러 줄 금액을 입력받아 계산한다.
//		보유한 동전의 개수로 거스름돈을 지불할 수 없으면, '거스름돈이 부족합니다'라고 출력하고 종료한다.
//		지불할 돈이 있으면, 거스름돈을 지불한 만큼 가진 돈에서 빼고 남은 동전의 개수를 화면에 출력한다.
//		(1)에 알맞은 코드를 넣어서 프로그램을 완성하시오.
		
		if(args.length!=1){
			System.out.println("한개의 숫자만 입력해 주세요.");
			
			System.exit(0);
		}
//		문자열을 숫자로 변환한다. 입력한 값이 숫자가 아닐 경우 예외가 발생한다.
		int money8 = Integer.parseInt(args[0]);
		System.out.println("money=" + money8);
		int[] coinUnit8 = { 500, 100, 50, 10}; // 동전의 단위
		int[] coin = {5,5,5,5}; //단위별 동전의 개수
		
		for(int i = 0; i<coinUnit8.length; i++){
			int coinNum = 0;
			
//			1.금액(money)을 동전단위로 나눠서 필요한 동전의 개수(coinNum)를 구한다.
			coinNum = money8/coinUnit8[i];
			
//			2.배열 coin에서 coinNum만큼의 동전을 뺀다.
//			(만일 충분한 동전이 없다면 배열 coin에 있는만큼만 뺀다.)
			if(coin[i] < coinNum){	// 필요한 동전보다 갖고있는 동전이 적으면
				coinNum = coin[i];	// 필요한 동전을 갖고있는 동전 수로 바꿈 (5개)
				coin[i] = 0;		// 갖고있는 동전 수를 0개로 만듬
			}else{
				coin[i] -= coinNum;	// 아닐땐 갖고있는 동전 수에서 필요한 동전 수를 뺌
			}
			
//			3.금액에서 동전의 개수(coinNum)와 동전단위를 곱한 값을 뺀다.
			money8 -= coinNum*coinUnit[i];
			
			System.out.println(coinUnit8[i] + "원: " + coinNum);
		}
		
		if(money8 > 0){
			System.out.println("거스름돈이 부족합니다.");
			System.exit(0); //프로그램 종료
		}
		
		System.out.println("=남은 동전의 개수=");
		for(int i=0;i<coinUnit.length;i++){
			System.out.println(coinUnit[i]+"원: "+coin[i]);
		}
		
		
		
//		5-9) 주어진 배열을 시계방향으로 90도 회전시켜서 출력하는 프로그램을 완성 하시오.
		char[][] star = {
				{'*','*',' ',' ',' '},
				{'*','*',' ',' ',' '},
				{'*','*','*','*','*'},
				{'*','*','*','*','*'}
		};
		char[][] result = new char[star[0].length][star.length];
		
		for(int i=0; i< star.length;i++){
			for (int j = 0; j < star[i].length; j++) {
				System.out.print(star[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
//		좌표 이동
//		star -> result
//		00 -> 03  밑의 반복문에서 i는 행의 길이 (4) 
//		01 -> 13 j는 열의 길이 (5)   
//		02 -> 23 star의 앞자리는 i, 뒷자리는 j
//		03 -> 33 result의 앞자리는 j 뒷자리는 star.length-1-i
//		04 -> 43 
//				 
//		10 -> 02
//		11 -> 12
//		12 -> 22
//		13 -> 32
//		14 -> 42
//		...
//		30 -> 00
//		31 -> 10
//		32 -> 20
//		33 -> 30
//		34 -> 40

		for (int i = 0; i < star.length; i++) { //4번반복
			for (int j = 0; j < star[i].length; j++) {// 5번반복
//				(1) 알맞은 코드를 넣어 완성하시오.
				result[j][star.length-1-i] = star[i][j];
			}
		}
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
		
		
	}

}
