package e_OOP;
/**
 * 3장 예제 - Tv
 * @author PC-18
 * @since 2020-08-14
 */
public class TvTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(Tv.color);
		Tv.color = "black";
		System.out.println(Tv.color);
		Tv.changeColor();
		System.out.println(Tv.color);
		
		Tv t = new Tv();
		System.out.println(t.channel);
		t.channel = 10;
		System.out.println(t.channel);
		t.channelUp();
		System.out.println(t.channel);
		t.volume = 15;
		t.volumeUp();
		System.out.println(t.volume);
	}
}

class Tv{
	// 전역변수 (멤버변수)
	// 클래스변수
	static String color;
	
	// 인스턴스 변수
	int channel;
	int volume;
	
	// 클래스 메서드
	static void changeColor(){
		color = "yellow";
	}
	// 인스턴스 메서드
	void channelUp(){
		channel++;
	}
	
	void volumeUp(){
		volume++;
	}
}
