package grade;

public class IGradeDaoImpl implements IGradeDao{

	private static IGradeDao gradeDao;
	
	private IGradeDaoImpl(){
		
	}
	
	public static IGradeDao getInstance() {
		if(gradeDao == null){
			gradeDao = new IGradeDaoImpl();
		}
		return gradeDao;
	}

}
