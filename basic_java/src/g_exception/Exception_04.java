package g_exception;

import java.sql.SQLException;

public class Exception_04 {

	public static void main(String[] args) {
		Controller.idCheck();
		
	}

}
class Controller{
	static void idCheck(){
		Service.idCheck();
	}
	
}

class Service{
	static void idCheck(){
		try {
			Dao.idCheck();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Dao{
	static void idCheck() throws SQLException{
		SQLException e1 = new SQLException("데이터 다 날아갔다");
		throw e1;
	}
}