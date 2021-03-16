package show;

import java.util.List;
import java.util.Map;

public class IShowServiceImpl implements IShowService{

	private static IShowService showSv;
	private IShowDao showDao;
	
	private IShowServiceImpl(){
		showDao = IShowDaoImpl.getInstance();
	}
	
	public static IShowService getInstance(){
		if(showSv == null){
			showSv = new IShowServiceImpl();
		}
		return showSv;
	}

	@Override
	public List<ReadshowVO> readShow() {
		showDao = IShowDaoImpl.getInstance(); 
		return showDao.readShow();
	}

	@Override
	public boolean addShow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteShow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DayShowListVO> dayShowList() {
		showDao = IShowDaoImpl.getInstance();
		return showDao.dayShowList();
	}

	@Override
	public void screeningMovie() {
		showDao = IShowDaoImpl.getInstance();
		showDao.screeningMovie();
		
	}

	@Override
	public Map<Integer, String> movieTimeList(String movie_name) {
		showDao = IShowDaoImpl.getInstance();
		return showDao.movieTimeList(movie_name);
	}

	@Override
	public boolean disabledShow(int show_id) {
		showDao = IShowDaoImpl.getInstance();
		return showDao.disabledShow(show_id);
	}

	@Override
	public boolean liveshow(int show_id) {
		showDao = IShowDaoImpl.getInstance();
		return showDao.liveshow(show_id);
	}

	@Override
	public boolean checkshow_sc(String show_start_time) {
		showDao = IShowDaoImpl.getInstance();
		return showDao.checkshow_sc(show_start_time);
	}

	@Override
	public boolean add_Show_Sc(AddshowVO addshow) {
		showDao = IShowDaoImpl.getInstance();
		return showDao.add_Show_Sc(addshow);
	}

	@Override
	public List<String> movieTIMEList(Map<String, Integer> time) {
		showDao = IShowDaoImpl.getInstance();
		return showDao.movieTIMEList(time);
	}

	@Override
	public String selectMovieId_returnString(int movie_id) {
		showDao = IShowDaoImpl.getInstance();
		return showDao.selectMovieId_returnString(movie_id);
	}
	
	public Map<Integer, String> screeningMovieName(){
		return showDao.screeningMovieName();
	}
	
	public Map<String, String> readMovieTimeList(String movie_name){
		return showDao.readMovieTimeList(movie_name);
	}

	
	
	
}
