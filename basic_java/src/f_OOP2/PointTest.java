package f_OOP2;

public class PointTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Point2{
	int x;
	int y;
	Point2(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Point3D extends Point2{
	int z;
	Point3D(int x, int y, int z){
		super(x, y);
		this.z = z;
	}
	
}
