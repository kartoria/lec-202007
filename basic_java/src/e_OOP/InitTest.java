package e_OOP;

public class InitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Init ii = new Init(30);
//		1. action = 100
		System.out.println(ii.action); // 명시적 초기화 -> 초기화블럭
		
		System.out.println(ii.action2);
	}

}

class Init{	
	static int action = 100; // 명시적 초기화
	int action2;
	Init(int action2){
		this.action2 = action2;
	}
	
	static {
		action = 10;
	}
	
	{
		action2 = 70; // 명시적초기화 -> 초기화블럭 -> 생성자
	}
}
