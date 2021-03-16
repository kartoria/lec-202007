package z_exam;

public class TotalTest {
	// 프로그램이 실행되면 클래스가 메서드 영역에 로드되며 클래스 안에있는 클래스 변수, 클래스 메서드가 생성된다.
	// 해당 클래스 파일과 같은 이름의 클래스기때문에 가장 먼저 로드된다
	public static void main(String[] args) { 
		// TotalTest 클래스에는 main()이라는 클래스 메서드밖에 없기 때문에 main()만 생성된다. 콜스텍에 로드 
		
		TvMaker.color = "Blue";	// TvMaker 클래스의 클래스 변수인 color를 "Blue"로 초기화한다.
		
		TvMaker tm = new TvMaker(); // TvMaker 클래스의 인스턴스를 생성 후 인스턴스의 주소를 참조변수 tm에 저장한다.
		
		tm.age = 30; // heap영역에 있는 tm의 주소에 가서 TvMaker의 인스턴스 변수 age를 30으로 초기화한다.
		
		// 3. 메서드 호출
		// 3.1 
		System.out.println(TvControll.channel);
		// TvControll 클래스의 channel이라는 클래스변수를 불러와 출력시킨다. 15를 출력하게된다
		
		TvControll.volumeDown();
		// tvControll 클래스의 클래스 메서드 volumeDown()을 호출한다.
		
		System.out.println(TvControll.channel);
		// tvControll 클래스에서 수행된 후 반환받은 정수형 channel의 값을 출력한다. 16을 출력하게 된다.

		// 3.2
		TvControll tc; // TvControll클래스의 참조변수를 선언하고.
		
		tc = new TvControll(); //해당 클래스의 인스턴스를 생성 후 인스턴스의 주소를 참조변수에 저장한다.
		
		System.out.println(tc.volume);
		// heap영역에 있는 tc의 주소에 저장되어있는 인스턴스 변수 volume의 값을 출력한다. 출력값은 99
		
		tc.volumeUp();
		// tc의 인스턴스 메서드 volumeUp를 호출한다.
		
		System.out.println(tc.volume);
//		tc의 인스턴스 변수 volume의 값을 출력한다. 출력값은 100
		
		tc.volumeUp();
//		 tc의 인스턴스 메서드 volumeUp를 호출한다.
		
		System.out.println(tc.volume);
//		tc의 인스턴스 변수 volume의 값을 출력한다. 0을 출력한다
		
		Calc cc = new Calc();
//		Calc 클래스를 인스턴스화해서 참조변수 cc에 주소를 저장한다.
		
		System.out.println(cc.add(Integer.MAX_VALUE, 4));
		// 매개변수로 int형 2개를 갖고있는 cc의 인스턴스메서드 add를 호출하고 int형 정수의 최대값과 4라는 인자값을 매개변수로 넘겨준다.
		// add() 메서드에서 반환받은 int형 정수의 최대값과 4를 더한 수(int타입)를 출력하게 된다.
		
		System.out.println(cc.add(3L, Integer.MAX_VALUE));
		// 매개변수로 long, int를 1개씩 갖고있는 cc의 인스턴스 메서드 add를 호출하고 3L과 int형 정수의 최대값을 매개변수로 넘겨준다.
		// add() 메서드에서 반환받은 3L과 int형 정수의 최대값의 합(Long타입)을 출력하게 된다.
	}
}

class TvMaker {
	// 1. TvMaker라는 클래스가 메서드영역에 로드되면서 안에있는 클래스 변수와 클래스 메서드가 생성된다
	// 1.1 메서드 영역에 생성되는 클래스 변수로 String color, int inch가 있고 클래스메서드는 없다. 
	static String color;
	static int inch;
	
	// 1.2 밑의 변수들은 모두 static이 붙지 않은 인스턴스 변수로 클래스의 인스턴스가 생성될때 heap영역의 객체주소 생성된다. 
	String name = "";
	int age;

	// 2. 인스턴스가 생성될 때 호출되는 인스턴스 초기화 메서드인 생성자로 인스턴스 변수를 초기화할 목적으로 사용된다.
	TvMaker() {
		// 클래스 내에 String타입의 인스턴스변수 name를 "man"으로, int타입 인스턴스변수 age를 25로 초기화한다. 
		this("man", 25);
	}

	// 2.2 위의 생성자와 이름이 같지만 매개변수가 있기때문에 오버라이딩된다
	// 	매개변수인 name과 age을 TvMaker의 인스턴스변수 name이 갖고있는 값과 age의 값으로 초기화한다.
	TvMaker(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class TvControll {
	final int MAX_VOLUME = 100;			// 인스턴스 상수로 클래스가 인스턴스화할때 heap 영역의 객체주소에 저장된다.
	final int MIN_VOLUME = 0;			// 인스턴스 상수
	static final int MAX_CHANNEL = 50;  // TvControll이 메서드영역에 로드될때 생성되는 클래스 상수들이다.
	static final int MIN_CHANNEL = 1;	// 클래스 상수
	static int channel = 15;			// 클래스 변수
	int volume = 99; 					// 인스턴스 변수

	// 4. return문
	int volumeUp() {
		// 인스턴스변수 volume과 클래스상수 MAX_VOLUME을 비교 후 같으면
//		volume을 MIN_VOLUME으로 초기화하고 아닐경우 volume의 값을 1 올려준다
		if (volume == MAX_VOLUME) {
			volume = MIN_VOLUME;
		} else {
			volume++;
		}
		// 후에 volume의 값을 int 반환하고 메서드 수행을 종료한다.
		return volume;
	}

	static int volumeDown() {
		// main() 메서드에서 호출되어 tvControll의 클래스변수 channal을 클래스 상수인 MAX_CHANNEL과 비교하고
		// 값이 같으면 channel 변수를 클래스 상수인 MIN_CHANNEL로 초기화한다.
		// 값이 다를경우 channel의 값을 1 올려준다.	
		if (channel == MAX_CHANNEL) {
			channel = MIN_CHANNEL;
		} else {
			channel++;
		}
		
		// 그 후에 클래스 변수 channel에 들어있는 값을 정수형으로 반환하고 메서드 수행을 종료한다.
		return channel;
	}
}

class Calc {
	// 5.인스턴스 메서드들로 위에서 호출될때 매개변수에 인자값을 받아 수행된다.
	
	int add(int a, int b) {
		// 위에서 호출되어 int형 정수의 최대값과 4를 더한 값을 int형으로 반환한다.
		return a + b;
	}
	
	long add(long a, int b) {
		// 위에서 호출되어 3L과 int형 정수의 최대값을 더한 값을 long타입으로 반환한다.
		return a + b;
	}

	int minus(int a, int b) { 
		// 인스턴스화할때 저장은 되었지만 호출된 적 없는 메서드
		return a + b;
	}
}