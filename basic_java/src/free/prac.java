package free;

import java.util.Arrays;



public class prac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution so = new Solution();
//		System.out.println(Math.sqrt(25)%10);
		System.out.println(so.solution(16));
	}
}
class Solution {
	 public long solution(long n) {
	        long answer = 0;
	        int x = 0;
	        if(Math.sqrt(n) == (int)Math.sqrt(n)){
	        	x = (int)Math.sqrt(n);
	        	answer = (x+1)*(x+1);
	        }else{
	        	answer = -1;
	        }
	        return answer;
	    }
}
