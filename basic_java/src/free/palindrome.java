package free;
import java.util.Scanner;

/**
 * 팰린드롬 체크하기  
 * 처음 짜본 코드
 * @author PC-18
 * @since 2020.07.28
 */
public class palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력해 주세요 : ");
		int p = sc.nextInt();							// 체크할 정수
		String pStr = Integer.toString(p); 			// int를 str으로 바꾸고
		int pSize = pStr.length();					// 자릿수 구함
		int[] pArr = new int[pSize];				// 자릿수만큼 배열생성
		int pCh; 									// str로 바꾼 정수들을 하나씩 빼기위한 변수
		boolean check = true;  						// 수가 같은지 체크하기 위한 배열
		
		for(int i=0; i<pSize; i++){					// 자릿수만큼 반복
			pCh = pStr.charAt(i)-'0';				// 한 단어씩 빼서 ch로만듬
			pArr[i] = pCh;							// ch에 저장된걸 arr에 넣음
		}
		
		for(int j = 0; j<pSize/2; j++){			// 배열의 길이/2를 해주면 홀수는 가운데 수를 제외하고 비교할 수 있고 짝수는 가운데 두 수까지 비교가능함
			if(pArr[j] != pArr[pSize-1-j]){		// 앞의 수와 뒤의 수가 같을 때
				check = false;					// 하나라도 다르면 false시킴
			}
		}
		
		if(check == true){
			System.out.println("이 정수는 펠린드롬 입니다.");
		}else{
			System.out.println("이 정수는 펠린드롬이 아닙니다.");
		}
	}

}
