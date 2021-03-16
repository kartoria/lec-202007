package g_exception;

public class Exception_02 {

	public static void main(String[] args) {
		
		int number = 100;
		int result = 0;
		
		for(int i =0; i< 10; i++){
			int random = (int)(Math.random()*5);
			try{
				result = number/random;
				System.out.println(result);
			}catch(ArithmeticException e){
				System.out.println(0);
			}
		}
		System.out.println("종료");
	}

}
