package f_OOP2;

public class SingleToneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1);
		System.out.println(s2);
	}
}
final class Singleton{
	private static Singleton s;
	private Singleton(){
		
	}
	static Singleton getInstance(){
		if(s == null){
			s = new Singleton();
		}
		return s;
	}
}