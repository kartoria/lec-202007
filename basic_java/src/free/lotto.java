package free;
/**
 * 로또 추첨기
 * 심심할 때 하려고 만든거
 * @author PC-18
 * @since 2020.07.24
 */
public class lotto {
	public static void main(String[] args) {
		
		int lotto [] = new int[6];
		
		System.out.print("Lotto 당첨 숫자 : ");
		
		for(int i =0; i < lotto.length; i++){
			lotto[i] = (int)(Math.random()*45)+1;
		}
		
		for(int i=0; i<lotto.length; i++){
			System.out.print(lotto[i] + " ");
		}
		System.out.println("입니다.");	
	}
}

	
