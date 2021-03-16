package e_OOP;

public class Qu3_8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyAdd ma = new MyAdd();
		
//		7. 3번의 add메서드를 호출하여라.
		int addI = MyAdd.add(5); 
		System.out.println(addI);
		
//		8. 4번의 add메서드를 호출하여라.
		int addII = ma.add(3,7);
		System.out.println(addII);
		
//		9. 5번의 add메서드를 호출하여라.
		long addIL = ma.add(3, 70000000000000000L);
		System.out.println(addIL);
		
//		10. 6번의 add메서드를 호출하여라.
		float addCF = ma.add('a', 3.141592f);
		System.out.println(addCF);
	}

}

class MyAdd{
//	1. 클래스 변수 a를 선언하고 20의 값으로 초기화 하여라.
	static int a = 20;
	
//	2. 인스턴스 변수 b를 선언하고 15의 값으로 초기화 하여라.
	int b = 15;
	
//	3. int타입의 매개변수가 하나이며 변수 a의 합을 반환하는 클래스메서드 add를 작성하여라
	static int add(int a){
		return MyAdd.a + a; 
	}
	
//	4. int타입의 매개변수가 두개이고 매개변수의 합을 반환하는 인스턴스메서드 add를 작성하여라.
	int add(int a, int b){
		return a+b;
	}
	
//	5. int타입, long타입 각 한 개의 매개변수, 매개변수의 합을 반환하는 인스턴스 메서드 add를 작성하여라.
	long add(int a, long b){
		return a+b;
	}
	
//	6. char타입, float타입 각 한개의 매개변수, 매개변수의 합을 반환하는 인스턴스 메서드 add를 작성 하여라.
	float add(char a, float b){
		return a+b;
	}
	
//	추가1. 인스턴스 메서드 add, 매개변수는 long타입 하나와 int 타입 하나이며 매개변수의 합을 반환하는 메서드
	long add(long a, int b){
		return a+b;	
	}
	
//	추가2. 인스턴스 메서드 add, 매개변수가 int타입 두개이며 두개의 합을 반환하는 메서드
//	long add(int a, int b){
//		return (long)a + b;
//	}
	
}
