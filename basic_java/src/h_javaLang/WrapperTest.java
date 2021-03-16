package h_javaLang;

public class WrapperTest {
	public static void main(String[] args) {
		Integer i1 = new Integer(10);
		Integer i2 = new Integer(10); 
		
		System.out.println(i1 +", "+ i2); //wrapper는 모두 toString이 오버라이드 되어있다.
		System.out.println(i1 == i2);
		System.out.println(i1.equals(i2)); //wrapper는 모두 equals이 오버라이드 되어있다.
		
		Integer[] intArr = new Integer[3];
		intArr[0] = 5;	// 5 에서 new Integer(5)로 자동으로 변환 - 오토박싱
		intArr[1] = 10;
		intArr[2] = 40;
		
		int a = intArr[0];	// 참조형에서 기본형으로 자동변환 - 언박싱
		System.out.println(a); 
		
//		int a = new Integer(5); // 이건 안됨
		
	}

}
