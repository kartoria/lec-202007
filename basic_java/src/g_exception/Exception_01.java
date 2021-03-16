package g_exception;

public class Exception_01 {
	public static void main(String[] args) {
//		
//		1. 프로그램오류
//		- 프로그램이 실행중에 어떤 이유로 인해 오작동하거나 비정상적으로 종료되는 경우
//		- 컴파일 오류 : 컴파일 시에 발생하는 오류
//		- 런타임 오류 : 프로그램 실행중에 발생하는 오류
//		- 오류의 종류
//			: 에러 -> 프로그램 코드를 이용하여 수습할 수 없는 심각한 오류
//			: 예외 -> 프로그램 코드를 이용하여 수습될 수 있는 경미한 오류
//		
//		2. RuntimeException과 그 외 Exception
//		- 모든 예외의 최고 조상은 Exception이다.
//		- RuntimeException은 주로 프로그래머의 실수에 의해서 발생하는 예외
//			: IndexOutOfBoundsException, NullPointException...
//		- 그 외 Exception들은 주로 외부의 영향에 의해서 발생하는 에외
//			: ClassNotFoundException, FileNotFoundException...
		
//		3. try-catch
//		- 프로그램 실행 시  발생할 수 있는 에외에 대비한 코드를 작성하는 것.
//		- 작성방법
//			try{
//				//예외가 발생할 수 있는 코드
//			}catch(Exception e){
				//수행한 코드에서 익셉션이라는 예외가 발생했을 때 수행할 문장 
//			}
//		- 발생한 종류의 예외와 일치하는 하나의 catch만 수행된다.
//		
//		4. 예외발생시키기
//		- 연산자 new를 이용하여 발생시키고자 하는 예외의 객체를 만든다.
//			Exception e1 = new Exception();
//		- 예약어 throw를 이용하여 예외를 발생시킨다.
//			throw e1;
		
//		5. 예외던지기(throws)
//		- 메서드에 예외 선언하기
//			: 메서드의 선언부 마지막에 예약어 throws와 함께 예외 종류를 적어주기만 하면 된다.
//			: 예외의 처리는 해당 메서드를 호출한 쪽에서 처리하면 된다.
		
//		6. finally
//		- try-catch블럭에 상관없이 수행되어야할 코드를 포함한다.
//			try{
//				//예외가 발생할 수 있는 코드
//			}catch(Exception e){
//				//수행한 코드에서 예외가 발생했을 때 수행할 문장 
//			}finally{
//				//예외발생에 상관없이 수행되어야 할 코드
//			}
		
	}
}
