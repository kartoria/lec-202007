package e_OOP;
/**
 * 메소드 연습
 * @author PC-18
 * @since 2020-08-17
 */
public class MyMathTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		8.
		MethodTest.add01();
		
//		9.
		int add02 = MethodTest.add02(1);
		System.out.println(add02);
		
//		10.	
		MethodTest mt = new MethodTest();
		int add03 = mt.add03(2);
		System.out.println(add03);
		
//		11.
		mt.add04();
		
	}

}

class MethodTest{

//	1.
	static int a = 10;
//	2.
	static int b = 20;
//	3.
	int c = 50;
//	4.
	static void add01(){
		System.out.println(a + b);
	}
//	5.
	static int add02(int c){
		int result = a + b + c;
		return result;
	}
//	6.
	int add03(int c){
		int result = this.c + c;
		return result;
	}
//	7.
	void add04(){
		System.out.println(c + (int)(Math.random()*101));
	}
	
}
