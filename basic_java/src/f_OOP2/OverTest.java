package f_OOP2;

public class OverTest {
	public static void main(String[] args) {
		OverGo og = new OverGo();
		System.out.println(og.overMethod());
		
		System.out.println(og.getoverUpValue());
	}
}
class OverUp{
	int value = 10;
	String overMethod(){
		return "OverUp Method";
	}
	
}

class OverGo extends OverUp{
	int value = 20;
	
	
	@Override
	String overMethod(){
		return "OverGo Method";
	}
	
	String overMethod(int k){
		return k+"OverGo Method";
	}
	
	int getoverUpValue(){
		return super.value;
	}
	
}
