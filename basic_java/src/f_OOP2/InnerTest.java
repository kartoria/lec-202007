package f_OOP2;


public class InnerTest {
	public static void main(String[] args) {
		Outer.Inner ot = new Outer().new Inner();
		ot.method2(3);
		
	}
}
class Outer{
	int value = 10;
	class Inner{
		int value = 20;
		
		void method2(int value){
			System.out.println(value); // 인자값
			System.out.println(this.value); //20
			System.out.println(Outer.this.value); //10
		}
	}
	
	static class Inner2{
		
	}
	void method(){
		
	}
}