package movie;

import java.util.Map;

public interface IMovieDao {
	
	/**
	 * 영화 전체의 제목을 해쉬맵에 담아서 가져오는 메소드
	 * @author 김선준
	 */
	public Map<Integer, String> readMovieName();
	
	/**
	 * 영화 제목을 가져가서 그 영화의 모든 정보를 해쉬맵에 담아서 가져오는 메소드
	 * @param movie_name 영화 제목 
	 * @author 김선준
	 */
	public Map<String, String> readMovieInfo(String movie_name);

	/**
	 * 영화 추가
	 * @return int
	 */
	public int createMovie(MovieVO mv);

	/**
	 * 모든 영화를 출력만 하는 메소드
	 * @return 출력유무
	 */
	public boolean printAllMovie();

	/**
	 * 영화 삭제
	 * @param movie_id 영화기본키
	 * @return 삭제유무
	 */
	public boolean deleteMovie(int movie_id);
	

	public boolean movieCheck(int movie_id);

	public int movieSales(int movie_id);
}
