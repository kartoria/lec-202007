package c_sentence;

/****************************
 * for, while				*
 * @author PC-18			*
 * @since 2020.07.27		*
 ****************************/

public class sentence_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		1. 반복문(for, while, do-while)
//		- 어떤 작업이 반복적으로 수행되도록 할 때 사용된다.
//		- 반복문은 주어진 조건이 만족하는 동안 문장을 반복 수행한다.
//		- for문의 경우 반복 횟수를 알고 있을 때 사용한다.
//		- while문의 경우 반복 횟수를 모를 때 사용한다.
		
		
//		2. for문
//		- 기본구조
//			for(초기화; 조건식; 증감식){
//				조건식이 만족할 때 수행될 문장.
//			}
		
//		1. 5~ 16까지의 합을 구하여라.
		int result = 0;
		for(int i = 5; i<17; i++){
			result += i;
		}
		System.out.println(result);
		
//		1. 0 이상 5 이하에서 정수를 결과와 같이 출력 하여라.
		for(int i = 0; i<6; i++){
			System.out.print(i + " ");
		}
		System.out.println("");
		
//		2. 0 이상 5 이하에서 정수를 결과와 같이 출력 하여라.
		for(int i = 5; i>-1; i--){
			System.out.print(i + " ");
		}
		System.out.println("");
		
//		3. 2 이상 10 이하에서 정수의 합계를 구하여라
		int result3 = 0;
		for(int i = 1; i<11; i++){
			result3 += i;
		}
		System.out.println(result3);
		
//		4. 5이상 15미만에서 정수의 곱을 구하여라.
		long result4 = 1L;
		for(int i = 5; i<15; i++){
			result4 *= (long)i;
		}
		System.out.println(result4);
		
//		5. 3이상 4462이하에서 짝수인 정수의 합을 구하여라.
		int result5 = 1;
		for(int i = 3; i<4463; i++){
			if(i%2 == 0){
				result5 += i;
			}
		}
		System.out.println(result5);
		
//		6. 7초과 57미만에서 2 또는 3의 배수인 정수의 합을 구하여라.
		int result6 = 1;
		for(int i = 8; i<57; i++){
			if(i%2 == 0 || i%3 == 0){
				result6 += i;
			}
		}
		System.out.println(result6);
		
		
		int j = 1;
		for(int i = 1; i<10; i++){
				System.out.print(j + "*" + i + "=" + i*j + " ");
				if(j<9 && i == 9){
					System.out.println("\n");
					j++;
					i = 0;
				}
		}
		System.out.println("");
		
//		3. while문
//		- 반복횟수를 알 수 없을 때 많이 사용
//		- 조건식과 수행해야할 블럭{}만으로 구성되어 있다.
//		- 기본구조
//		while(조건식){
//			조건식이 true일 때 수행될 문장.
//		}
		
	
//		1~10까지 출력
		int i = 1;
		while(i<11){
			System.out.println(i);
			i++;
		}
		
//		1. 3~15까지의 합계를 구하라
		
		int num = 3, sum = 0;
		
		while(num<16){
			sum += num;
			num++;
		}
		System.out.println(sum);
		
		int qued = 1;
		int quedSum = 0;
		while(qued < 101){
			if(qued % 4 == 0){
				quedSum += qued;
			}
			qued++;
		}
		System.out.println(quedSum);
		
//		3. 구구단 while문으로 만들자
		
		int dan = 2;
		while(dan<10){
			int gob = 1;
			while(gob<10){
				System.out.println(dan + "*" + gob + "=" + dan*gob );
				gob++;
			}
			dan++;
			
		}
		
//		4. 5~? 합계를 구하였을때 합계가 100이상이 되는 ?의 값
		
		int num4 = 5, sum4 = 0;
		while(sum4 < 100){
			sum4 += num4;
			if(sum4 < 100){
				num4++;
			}
		}
		System.out.println(num4);
		
		int num5 = 5, sum5 = 0;
		while (true) {
			sum5 += num5;
			if(sum5 >= 100){
				break;
			}
			num5++;
		}
		System.out.println(num5);
		
		int num6 = 0;
		while(num6 < 10){
			if(num6%2==0){
				System.out.println(num6);
			}
			num6++;
		}
		
		int num7 = 0;
		while(num7 < 10){
			if(num7%2!=0){
				continue;
			}
			System.out.println(num7);
			num7++;
		}
		
		for(int i2 = 0; i2 < 10; i2++){
			if(i2%2 != 0){
				continue;
			}
			System.out.println(i2);
		}
		
		
		
		
	}
}
