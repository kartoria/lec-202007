package f_OOP2;

public class PolyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. SmartTv 3대를 저장해 주세요
		SmartTv[] st = new SmartTv[3];
		for(int i = 0; i<st.length; i++){
			st[i] = new SmartTv(); 			
		}
		
//		2. AfreecaTv 2대를 저장해주세요
		AfreecaTv[] at = new AfreecaTv[2];
		for(int i = 0; i<at.length; i++){
			at[i] = new AfreecaTv(); 			
		}
		
//		3. DMBTv 2대를 저장해주세요
		DMBTv[] dt = new DMBTv[2];
		for(int i = 0; i<dt.length; i++){
			dt[i] = new DMBTv(); 			
		}
		
		Tv[] t = new Tv[]{st[0],st[1],st[2],at[0],at[1],dt[0],dt[1]};
		t[2] = (Tv)st[2]; // up-casting
		Tv t3 = t[3];
		
		SmartTv st2 = (SmartTv)t3; //down-casting
//		SmartTv tt = (SmartTv)new Tv(); // 업캐스팅했다가 다운캐스팅은 가능하지만 바로 다운캐스팅은 안됨
		
	}
}

class Tv{
	int volume;
	String color;
	void changeColor(String color){
		this.color = color;
	}
}

class SmartTv extends Tv{
	void internet(){
		
	}
}

class AfreecaTv extends Tv{
	void starBalloon(){
		
	}
}

class DMBTv extends Tv{
	void antenna(){
		
	}
}