package free;
/**
 * 연습문제 4-7 변형
 * 식 출력하기
 * @author PC-18
 * @since 2020.07.24
 */
public class Exam4_7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		4-7) 1 + (1+2) + (1+2+3) + (1+2+3+4) + ... + (1+2+3+...+10)를 똑같이 출력시키는 반복문
//		System.out.println(i); //j = 1
//		j++
//		System.out.println(i + i+i); //j = 2 j의 수만큼 i를 더하는것을 반복하라
//		j++
//		System.out.println(i + i+i + i+i+i); //j = 3
//		j++
		int i = 1;
		int j = 1;
		
		for (j = 1; j < 11; j++) {
			if (j == 1) {							// j가 1일때는 맨 처음 출력할 때를 말한다.
				System.out.print(i + "+"); 
			} else {								// 처음 출력 이후부터
				System.out.print("(");				// ( 출력
				for (i = 1; i <= j; i++) {			// i를 j만큼 반복해서 출력하라 
					if (i == j) {
						System.out.print(i);		// i가 j와 같을경우(괄호 안의 끝 수를 출력할 경우) 뒤에 +를 출력하지 않음
					}else{
						System.out.print(i + "+");	// 괄호의 첫 수부터 마지막의 수를 제외한 수까지 뒤에 + 출력
					}
				}
				if (j == 10) { 
					System.out.println(")");
				}else{
					System.out.print(")+");
				}
			}
		}			
			
			
			
			
	}
}
