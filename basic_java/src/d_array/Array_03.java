package d_array;

import java.util.Arrays;

public class Array_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		1. 다차원 배열
//		- 자바에서는 1차원 배열뿐만 아니라 다차원 배열도 허용한다.
		
//		2. 이차원 배열의 선언
//		- 변수타입[][] 변수명;
//		- 변수타입 변수명[][];
//		- 변수타입[] 변수명[];
		
//		3. 이차원 배열의 생성
//		- 변수명 = new 변수타입[크기1][크기2];
		int arr[][] = new int[][]
		{
			{1,2,3},
			{4,5,6}
		};
//		arr.length : 2
//		System.out.println(arr.length);
//		System.out.println(arr[0].length);
//		System.out.println(arr[1][1]);
		
//		for(int raw = 0; raw<arr.length; raw++){
//			for(int col = 0; col<arr[raw].length; col++){
//				System.out.println(arr[raw][col]);
//			}
//		}
		
		int[][] arr2 = new int[][]{
									{10,20,30},
									{40,50,60}
								  };
		
		int[][] arr3 = new int[3][];
		arr3[0] = new int[2];
		arr3[1] = new int[3];
		arr3[2] = new int[4];
		
		
//		3. 변수 names를 선언하고 주변친구 6명의 이름으로 초기화 하여라.
		String[] names = new String[]{"김성준","박찬","전진원","김선준","이재형","서대철"};
		
//		4. subjects라는 변수를 선언하고 위에 국 영 수 사 과 자바 오라클을 만들고 선언
		String[] subjects = {"국어", "영어", "수학", "사회", "과학", "자바", "오라클"};
		
//		1. 6명의 7과목을 저장할 수 있는 배열 score를 선언 및 생성
		int score[][] = new int[names.length][subjects.length];
		
//		2. score 각 방을 0~100점 사이의 임의의 정수의 값을 저장하여라.
		for(int raw = 0; raw < score.length; raw++){
			for (int col = 0; col < score[raw].length; col++){
				score[raw][col] = (int)(Math.random()*101);
			}
		}
		
		
//		5. 사람별 합계
		int nameSum[] = new int[names.length];
		for(int j = 0; j < names.length; j++){
			for(int i = 0; i < subjects.length; i++){
				nameSum[j] += score[j][i];
			}
		}
		
		
		
//		6. 사람별 평균 소수점 3번째에서  반올림에서 소수점 2자리만
		float avg[] = new float[names.length];
		for(int i = 0; i < nameSum.length; i++){
			avg[i] = (int)((float)nameSum[i] / subjects.length * 100 + 0.5)/100f; 
		}
		
		
//		7. 과목별 합계
		int subSum[] = new int[subjects.length];
		for(int j = 0; j<subjects.length; j++){
			for(int i = 0; i<names.length; i++){
				subSum[j] += score[i][j];
			}
		}
		
//		8. 과목별 평균
		float subAvg[] = new float[subSum.length];
		for(int i = 0; i<subSum.length; i++){
			subAvg[i] = (int)((float)subSum[i] / names.length * 100 + 0.5)/100f;
		}
		
//		9. 사람별 석차
		int rankArr[] = new int [names.length];
		for(int j=0; j<names.length; j++){
			rankArr[j]++;
			for(int i=0; i<names.length; i++){
				if(nameSum[j] < nameSum[i]){
					rankArr[j]++;
				}
			}
		}
		
		
		

		
//		0. 아래와 같이 출력
		System.out.print("\t");
		
		for(int col = 0; col < subjects.length; col++){
			System.out.print(subjects[col] + "\t");
		}
		
		System.out.println("합계\t평균\t석차");
		
		for (int raw = 0; raw < score.length; raw++) {
			System.out.print(names[raw] + "\t");
			for (int col = 0; col < score[raw].length; col++) {
				System.out.print(score[raw][col] + "\t");
			}
			System.out.print(nameSum[raw] + "\t");
			System.out.print(avg[raw] + "\t");
			System.out.println(rankArr[raw]);
		}
		
		System.out.print("과목별합\t");
		for(int col = 0; col < subjects.length; col++){
			System.out.print(subSum[col] + "\t");
		}
		System.out.print("\n과목별평균\t");
		for(int col = 0; col < subSum.length; col++){
			System.out.print(subAvg[col] + "\t");
		}
		System.out.println();

		
		
		
		
		System.out.println();
		System.out.println();
		System.out.println("============================================ sort 후 ========================================");
		System.out.println();
		System.out.print("\t");
		
		int max = 0;
		int num = 0;
		for(int j = 0; j<nameSum.length-1; j++){
			max = nameSum[j];
			num = j;
			for(int i = j+1; i<nameSum.length; i++){
				if(max < nameSum[i]){
					max = nameSum[i];
					num = i;
				}
			}
			nameSum[num] = nameSum[j]; 		//합계소팅
			
			float avgTemp = avg[num];		//평균소팅
			avg[num] = avg[j];
			avg[j] = avgTemp;
			
			int rankTemp = rankArr[num];
			rankArr[num] = rankArr[j];
			rankArr[j] = rankTemp;
			
			String namesTemp = names[num];	//이름소팅
			names[num] = names[j];
			names[j] = namesTemp;
			
			for(int i = 0; i<subjects.length; i++){
				int scoreTemp = score[num][i];
				score[num][i] = score[j][i];
				score[j][i] = scoreTemp;
			}

			nameSum[j] = max;
			
		}
		
		
		
		
		
		
		
		
		for(int col = 0; col < subjects.length; col++){
			System.out.print(subjects[col] + "\t");
		}
		
		System.out.println("합계\t평균\t석차");
		
		for (int raw = 0; raw < score.length; raw++) {
			System.out.print(names[raw] + "\t");
			for (int col = 0; col < score[raw].length; col++) {
				System.out.print(score[raw][col] + "\t");
			}
			System.out.print(nameSum[raw] + "\t");
			System.out.print(avg[raw] + "\t");
			System.out.println(rankArr[raw]);
		}
		
		System.out.print("과목별합\t");
		for(int col = 0; col < subjects.length; col++){
			System.out.print(subSum[col] + "\t");
		}
		System.out.print("\n과목별평균\t");
		for(int col = 0; col < subSum.length; col++){
			System.out.print(subAvg[col] + "\t");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
