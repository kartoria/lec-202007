package x_Qu;

import java.util.Arrays;

/**
 * 2-1 문제
 * @author PC-18
 * @since 2020-07-21
 */
public class Qu2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//2-1
		int a, b;
		a=3; b=5;
		System.out.println("*2-1");
		System.out.print(a + "+" + b + "=");
		System.out.println(a + b + "\n");
		
		//2-2
		int value;
		value = 30;
		value = 100;
		int value2 = 7;
		System.out.println("**2-2");
		System.out.println("value: " + value + "\n" + "value2: " + value2 + "\n");
		
		//2-3
		boolean power = false;
		long lotto = 2537850000L;
		float result;
		
		//2-4
		String year = "2020";
		String month = "2";
		String day = "14";
		System.out.println("****2-4");
		System.out.println("오늘날짜: " + year + "년 " + month + "월 " + day + "일" + "\n");
		
		//2-4 (책 오류)
		int A = 8;
		int B = 3;
		int rEsult = A/B;
		float rEsult2 = (float)A/B;
		System.out.println("****2-4(2)");
		System.out.println("rEsult: " + rEsult);
		System.out.println("rEsult2: " + rEsult2 + "\n");

		//2-5
		System.out.println("*****2-5");
		byte byte01 = 33;
		System.out.println("byte01: " + byte01);
		long long01 = 888;
		System.out.println("long01: " + long01);
		char char01 = 'A';
		System.out.println("char01: " + char01);
		float float01 = 3.141592f;
		System.out.println("float01: " + float01);
		int intger01 = (int)long01;
		System.out.println("intger01: " + long01);
		short short01 = (short)char01;
		System.out.println("short01: " + short01);
		int integer01 = (int)float01;
		System.out.println("interger01: " + integer01);
		int integer02 = byte01;
		System.out.println("interger02: " + integer02);
		int integer03 = char01;
		System.out.println("interger03: " + integer03);
		
		//2-25
		int score[] = new int[5];
		for(int i = 0; i<5; i++){
			score[i] = 10*i;
			System.out.println("score [" +i+"] : "  + score[i]);
		}
		
		
		//2-26
		int scoreArray[] = new int[7];
		int sum = 0;
		System.out.print("score 값 : " + "{"); 
		for(int i = 0; i<scoreArray.length; i++){
			scoreArray[i] = (int)(Math.random()*101);	//임의의 정수 저장
			sum += scoreArray[i];						//저장된 요소들의 합계
			if(i<scoreArray.length-1){
				System.out.print(scoreArray[i] + ", ");
			}else{
				System.out.print(scoreArray[i]);
			}
			
		}
		System.out.println("}");
		float avg = (float)sum/scoreArray.length; 		//저장된 요소들의 평균
		System.out.println("합계 : " + sum);
		System.out.println("평균 : " + (int)(avg*100+0.5)/100f);	//소수점 세번째 자리에서 반올림
		
		int max = scoreArray[0];
		int min = scoreArray[0];
		
		for(int i = 0; i<scoreArray.length; i++){
			if(max < scoreArray[i]){
				max = scoreArray[i];
			}
			if(min > scoreArray[i]){
				min = scoreArray[i];
			}
		}
		
		System.out.println("최대값 : " + max);
		System.out.println("최소값 : " + min);
		
		
		
//		2-27
		int bSort[] = new int[]{5,2,3,1,4};
		for(int j = 1; j<bSort.length; j++){
			System.out.println(j + "회전");
			for(int i = 1; i<bSort.length; i++){
				if(bSort[i]<bSort[i-1]){
					int temp = bSort[i-1];
					bSort[i-1] = bSort[i];
					bSort[i] = temp;
				}
//				System.out.println(Arrays.toString(bSort));
			}
		}
		
//		2-28
		int sSort[] = new int[] { 5, 2, 3, 1, 4 };
		System.out.println(Arrays.toString(sSort));
		int sMin = sSort[0];
		for (int k = 1; k < sSort.length; k++) {
			for (int i = 1; i < sSort.length; i++) {
				if (sMin > sSort[i]) {
					sMin = sSort[i];
				}
			}
			for (int j = 1; j < sSort.length; j++) {
				if (sMin == sSort[j]) {
					int temp = sSort[j];
					sSort[j] = sSort[0];
					sSort[0] = temp;
				}
			}
			sMin = sSort[0+k];
			System.out.println(k + "회전");
			System.out.println(Arrays.toString(sSort));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
