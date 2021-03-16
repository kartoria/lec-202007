package e_OOP;
/**
 * 메서드 연습
 * @author PC-18
 * @since 2020-08-17
 */
public class MyMath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		12. add1 메서드 호출
		MathTest.add1();
		
//		13. add2 메서드 호출
		MathTest mt = new MathTest();
		int add2 = mt.add2();
		System.out.println(add2);
		
//		14. add3 메서드 호출
		MathTest.add3(10, 20);
		
//		15. add4 메서드 호출
		int add4 = mt.add4(20, 30);
		System.out.println(add4);
		
//		16. add5 메서드 호출
		long add5 = mt.add5(30, 40L);
		System.out.println(add5);
		
//		17. add6 메서드 호출
		long add6 = mt.add6(40L, 50, 60);
		System.out.println(add6);
		
//		18. add7 메서드 호출
		String add7 = mt.add7("숫자");
		System.out.println(add7);
	}

}

class MathTest{
//	1. 클래스 변수 a를 선언하고 10의 값으로 초기화 하여라.
	static int a = 10;
//	2. 클래스 변수 b를 선언하고 20의 값으로 초기화 하여라.
	static int b = 20;
//	3. 인스턴스 변수 c를 선언하고 30의 값으로 초기화 하여라.
	int c = 30;
//	4. 인스턴스 변수 d를 선언하고 40의 값으로 초기화 하여라.
	int d = 40;
//	5. 클래스 메서드 add1, 클래스 변수 a,b의 합을 출력하는 메서드
	static void add1() {
		System.out.println(a+b);
	}
//	6. 인스턴스 메서드 add2, 인스턴스 변수 c,d의 합을 반환하는 메서드
	int add2(){
		int result = c+d;
		return result;
	}
//	7. 클래스 메서드 add3, 매개변수: int타입 두개, 매개변수의 합을 출력하는 메서드 
	static void add3(int a, int b){
		System.out.println(a+b);
	}
//	8. 인스턴스 메서드 add4, 매개변수: int타입 두개, 매개변수의 합을 반환하는 메서드
	int add4(int a, int b){
		int result = a+b;
		return result;
	}
//	9. 인스턴스 메서드 add5, 매개변수: int타입 하나, long타입 하나, 매개변수의 합을 반환하는 메서드
	long add5(int a, long b){
		long result = a + b;
		return result;
	}
//	10. 인스턴스 메서드 add6, 매개변수: long타입 하나, int타입 두개, 매개변수의 합을 반환하는 메서드
	long add6(long a, int b, int c){
		long result = a + b + c;
		return result;
	}
//	11. 인스턴스 메서드 add7, 매개변수: 문자열 하나, 매개변수에 48~94중 임의의 값과 문자열의 합을 반환하는 메서드
	String add7(String a){
		String result = a + (int)(Math.random()*47+48);
		return result;
	}
	
	
	
	
}