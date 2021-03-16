package e_OOP;
/**
 * 팩토리얼
 * @author PC-18
 * @since 2020-08-18
 */
public class RecursiveTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = factorial(4);
		System.out.println(num);
	}
	
	static int factorial(int num){
		int result = 0;
		if(num == 1){
			result = 1;
		}else{
			result = num * factorial(num-1);
		}
		return result;
	}
}
