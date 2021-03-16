package e_OOP;


public class CarTest {
	public static void main(String[] args) {
		Car cr1 = new Car();
		System.out.println(cr1.color);
		System.out.println(cr1.gearType);
		System.out.println(cr1.door);
		System.out.println();
		
		Car cr2 = new Car("red", "auto", 2);
		System.out.println(cr2.color);
		System.out.println(cr2.gearType);
		System.out.println(cr2.door);
		System.out.println();
		
		Car cr3 = new Car("yello");
		System.out.println(cr3.color);
		System.out.println(cr3.gearType);
		System.out.println(cr3.door);
		System.out.println();

	}
}
class Car{
	static String color;
	String gearType;
	int door;
	
	Car(){
		color = "black";
		gearType = "stick";
		door = 4;
	}
	
	Car(String color, String gearType, int door){
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
	
	Car(String color){
		this();
		this.color = color;
	}
	
	
	
}