package g_exception;

public class Exception_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exception e1 = new Exception("이게 왜 오류냐?");
		
		try {
			throw e1;
		} catch (Exception e) {
			System.out.println("예외");
			e.printStackTrace(); // 예외시 로그를 찍어줌
			System.out.println(e.getMessage()); //이유만 찍어줌
		}
		
		RuntimeException e2 = new RuntimeException("이건 또 왜 오류?");
		try{
			throw e2;
		} catch(RuntimeException e){
			e.printStackTrace();
		}
		
//		컴파일러가 예외처리를 강제하지 않는 경우
//		- RuntimeException과 그 자손들
//		- Error
//		이 두가지를 'unChecked예외' 라고 부른다.
		
	}

}