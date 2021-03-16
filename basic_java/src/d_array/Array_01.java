package d_array;

/****************************
 * 배열						*
 * @author PC-18			*
 * @since 2020.07.28		*
 ****************************/

public class Array_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		1. 배열(array)란?
//		- 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것.
		
//		2. 배열의 선언
//		- 원하는 타입의 변수를 선언하고 변수 또는 타입에 배열임을 의미하는 []를 붙이면 된다.
//		변수타입[] 변수명; int[] arr;
//		변수타입 변수명[]; int arr[];
		
//		3. 배열의 생성
//		- 배열을 선언한 다음에는 배열을 생성해야 한다.
//		- 배열을 생성하기 위해서는 연산자 'new'와 함께 배열의 타입과 크기를 지정해 주면된다.
//		ex) 변수명 = new 변수타입[3];
//			변수명 = new 변수타입[] {10,20,30,40};
		
//		4. 배열의 초기화
//		- 배열은 생성과 동시에 자동적으로 자신의 타입에 해당하는 기본 값으로 초기화 된다.
		
//		1) int형 값을 4개 저장할 수 있는 변수 arr을 선언 및 생성해주세요.
		int[] arr = new int[4]; // {0,0,0,0}
//		
//		for(int i=0; i<arr.length;i++){
//			System.out.println(arr[i]);
//		}
//		
//		2) 0번 index에 10, 1번 index에 20, 2번 index에 30, 3번 index에 40을 넣어주세요
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 10 * (i + 1);
			System.out.println(arr[i]);
		}
		
		int[] arr2 = new int[]{1,3,5,7,9};
		int[] arr3 = {1,3,5,7,9};
		
//		3) arr3의 모든 방의 합계를 구하여라.
//		int sum = 0;
//		sum += arr3[0];
//		sum += arr3[1];
//		sum += arr3[2];
//		sum += arr3[3];
//		sum += arr3[4];
//		System.out.println(sum);
		
		int sum = 0;
		for(int i=0;i<arr3.length;i++){
			sum += arr3[i];
		}
		System.out.println(sum);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
