package grade;

public class IGradeServiceImpl implements IGradeService{
	
	private static IGradeService gradeSv;
	private IGradeDao gradeDao;
	
	private IGradeServiceImpl(){
		gradeDao = IGradeDaoImpl.getInstance();
	}

	public static IGradeService getInstance(){
		if(gradeSv == null){
			gradeSv = new IGradeServiceImpl();
		}
		return gradeSv;
	}
}
