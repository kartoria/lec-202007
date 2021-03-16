package movie;

import java.util.Map;

public interface IMovieService {
	
	/**
	 * 영화 전체의 제목을 출력하고 그 영화를 선택해서 
	 * @author 김선준
	 * @return 
	 */
	Map<Integer, String> printMovieName();
	
	/**
	 * 영화 제목을 가져가서 그 영화의 전체 정보를 출력하는 메소드
	 * @param movie_name 영화 제목 
	 * @author 김선준
	 */
	void printMovieInfo(String movie_name);

	/**
	 * 영화 추가
	 * @return
	 */
	boolean createMovie();
	
	boolean printAllMovie();
	
	boolean deleteMovie(int movie_id);
	
	boolean movieCheck(int input);
	
	int movieSales(int movie_id);
	
	
}
