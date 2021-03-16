package e_OOP;

class Data{
	int x; // 10
}

public class Qu3_6 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. Data클래스의 객체를 생성하여라. 변수명 d1
		Data d1 = new Data();
//		2. 참조변수 d1의 x값을 10으로 변경하여라.
		d1.x = 10;
//		3. 참조변수 d1의 x값을 출력하여라.
		System.out.println(d1.x);
//		4. 인자값으로 참조변수 d1의 x값을 가지는 change메서드를 호출하여라.
		change(d1.x);
//		5. 참조변수 d1의 x값을 출력하여라.
		System.out.println(d1.x);
//		6. Data클래스의 객체를 생성하여라. 변수명 d2
		Data d2 = new Data();
//		7. 참조변수 d2의 x값을 10으로 변경하여라.
		d2.x = 10;
//		8. 참조변수 d2의 x값을 출력하여라.
		System.out.println(d2.x);
//		9. 인자 값으로 참조변수 d2를 가지는 change메서드를 호출하여라.
		change(d2);
//		10. 참조변수 d2의 x값을 출력하여라.
		System.out.println(d2.x);
	}
	static void change(int x){
		x = 1000;
		System.out.println("change() 기본형 : " + x);
	}
	static void change(Data d1){
		d1.x = 1000;
		System.out.println("change() 참조형 : " + d1.x);
	}
}


