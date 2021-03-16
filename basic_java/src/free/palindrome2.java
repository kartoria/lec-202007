package free;

import java.util.Scanner;
/**
 * 팰린드롬 체크
 * 단순화  + 정수외의 문자 체크 추가
 * @author PC-18
 * @since 2020.07.28
 */
public class palindrome2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("문자를 입력해 주세요 : ");
		String pStr = sc.next();					// 체크할 문자
		int pSize = pStr.length();					// 자릿수 구함
		int pCh; 									// 문자들을 하나씩 빼기위한 변수
		boolean check = true;  						// 수가 같은지 체크하기 위한 배열
		
		for(int i=0; i<pSize/2; i++){				// 자릿수/2만큼 반복
			if(pStr.charAt(i) != pStr.charAt(pSize-1-i)){
				check = false;
			}
		}

		if(check == true){
			System.out.println("이 문자는 펠린드롬 입니다.");
		}else{
			System.out.println("이 문자는 펠린드롬이 아닙니다.");
		}
	}

}
