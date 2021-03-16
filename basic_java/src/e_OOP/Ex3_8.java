package e_OOP;

public class Ex3_8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExTest01 et01 = new ExTest01();
		
		ExTest02 et02 = new ExTest02(3);
	}
}

class ExTest01{
	
	ExTest01() {
		
	}
	int value;
}

class ExTest02{
	int value;
	ExTest02(int value){
		this.value = value;
	}
}