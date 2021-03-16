package f_OOP2;

public class ChildTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Child c = new Child();
		c.volume = 10;
		c.volumeUp();
		System.out.println(c.volume);
		c.channel = 15;
		c.channelUp();
		System.out.println(c.channel);
	}

}


class Parent {
	int channel;
	void channelUp(){
		channel++;
	}
}

class Child extends Parent{
	int volume;
	int channel;
	void volumeUp(){
		volume++;
	}
}