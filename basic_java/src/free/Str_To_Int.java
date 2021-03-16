package free;

/**
 * 프로그래머스 코딩테스트
 * 문자열의 숫자를 int로 바꿔주기
 * @author PC-18
 * @since 2020.07.28
 */

public class Str_To_Int {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
				String s = "-1234";
				int answer = 0;
				int size = s.length();
				char ch = ' ';
				int num = 0;
				boolean sign = true; //true는 양수 false는 음수
				
				for(int i = 0; i<size; i++){
					ch = s.charAt(i); // 0 1 2 3 4
					num = ch-'0';
					if (ch >= '0' && ch <= '9') {
						if (i == size - 5 && size == 5) { // 0번째 자리일때
							answer += num * 10000;
						} else if (i == size - 4 && size == 5) { // 1번째 자리일때
							answer += num * 1000;
						} else if (i == size - 3 && size == 5) { // 2번째 자리일때
							answer += num * 100;
						} else if (i == size - 2 && size == 5) { // 3번째 자리일때
							answer += num * 10;
						} else if (i == size - 1 && size == 5) { // 4번째 자리일때
							answer += num;
						}
						else if (i == size - 4 && size == 4) { // 0번째 자리일때
							answer += num * 1000;
						} else if (i == size - 3 && size == 4) { // 1번째 자리일때
							answer += num * 100;
						} else if (i == size - 2 && size == 4) { // 2번째 자리일때
							answer += num * 10;
						} else if (i == size - 1 && size == 4) { // 3번째 자리일때
							answer += num;
						}
						else if (i == size - 3 && size == 3) { // 0번째 자리일때
							answer += num * 100;
						} else if (i == size - 2 && size == 3) { // 1번째 자리일때
							answer += num * 10;
						} else if (i == size - 1 && size == 3) { // 2번째 자리일때
							answer += num;
						} else if (i == size - 2 && size == 2) { // 0번째 자리일때
							answer += num * 10;
						} else if (i == size - 1 && size == 2) { // 1번째 자리일때
							answer += num;
						} else if (i == size - 1 && size == 1) { // 0번째 자리일때
							answer += num;
						}
					}else if(ch == '-'){
						sign = false;
					}
				}
				answer = sign == false ? answer*(-1) : answer;
				System.out.println(answer);
	}
}
