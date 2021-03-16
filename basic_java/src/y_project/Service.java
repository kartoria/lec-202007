package y_project;


public class Service {
	
	public boolean addProduct(String name, int price) {
		DBClass db = new DBClass();
		boolean result = db.addProduct(name, price);
		return result;
	}
	
}
