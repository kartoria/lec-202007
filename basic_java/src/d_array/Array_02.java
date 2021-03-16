package d_array;

import java.util.Arrays;

public class Array_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		1. 정렬(sort)
//		- 어떤 데이터를 빠르고 쉽게 찾을 목적으로 일정한 순서대로 데이터를 가지런히 나열하는 작업.
//		- 버블정렬, 선택정렬, 삽입정렬
		
//		2. 버블정렬(bubble sort)
//		- 인접한 데이터 간에 교환이 계속해서 일어난다.
//		- 한 회전이 끝나면 가장 큰 값이 뒤쪽에 확정된다.
//		- 개수보다 1번 적은 회전을 해야 순차적으로 작성됨.
//		- 0 1 인덱스 비교 => 큰 것 뒤로 => 1, 2 인덱스 비교 => 큰 것 뒤로 해서 마지막까지 한개 1회전
		
//		5 2 3 1 4
		
//		2 5 3 1 4
//		2 3 5 1 4
//		2 3 1 5 4
//		2 3 1 4 5
		
//		int bSort[] = new int[]{5,2,3,1,4};
//		for(int j = 0; j<bSort.length-1; j++){
//			System.out.println(j+1 + "회전");
//			for(int i = j; i<bSort.length; i++){
//				if(bSort[j] > bSort[i]){
//					int temp = bSort[j];
//					bSort[j] = bSort[i];
//					bSort[i] = temp;
//				}
//				for(int k = 0; k<bSort.length; k++){
//					System.out.print(bSort[k]);
//				}
//				System.out.println();
//					
//			}
//		}
		
//		3. 선택정렬 (select sort)
		
//		-- 1회전(기준 0번 index)
//		1. 최소값을 가지고 있는 index를 찾는다.
//		2. 기준 index와 최소값이 있는 index의 값을 바꿔준다.
//		1 2 3 5 4
		
//		-- 2회전(기준 1번 index)
//		1. 최소값을 가지고 있는 index를 찾는다. (1)
//		2. 기준 index와 최소값이 있는 index의 값을 바꿔준다.
//		1 2 3 5 4
		
//		-- 3회전(기준 2번 index)
//		1. 최소값을 가지고 있는 index를 찾는다. (2)
//		2. 기준 index와 최소값이 있는 index의 값을 바꿔준다.
//		1 2 3 5 4

//		-- 4회전(기준 3번 index)
//		1. 최소값을 가지고 있는 index를 찾는다.
//		2. 기준 index와 최소값이 있는 index의 값을 바꿔준다.
//		1 2 3 4 5

		int sSort[] = {5,2,3,1,4};
		int min = 0;								// 배열의 최소값
		int num = 0;								// 최소값이 있던 index
		for(int j = 0; j<sSort.length-1; j++){
			min = sSort[j];							// 초기값은 기준이 되는 index의 값
			num = j;								// 초기값은 기준이 되는 index
			for(int i = j+1; i<sSort.length; i++){  // 기준이 되는 index와 뒤의 index 계속 비교
				if(min > sSort[i]){					// 현재의 최소값과 뒤의 index를 비교해서 최소값이 더 크면
					min = sSort[i];					// 최소값을 그 index의 값으로 바꿈  
					num = i;						// 최소값이 된 index를 기억
				}
			}
			sSort[num] = sSort[j];					// 최소값이 있던자리에 기준이 되는 index의 값을 넣음
			sSort[j] = min;							// 기준이 되는 index 자리에 최소값을 넣음
			System.out.println(Arrays.toString(sSort));
		}

		
		
		
		
	}

}
