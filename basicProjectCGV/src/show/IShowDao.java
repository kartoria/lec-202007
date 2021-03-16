package show;

import java.util.List;
import java.util.Map;

public interface IShowDao {
	
	/**
	 * 영화 상영시간 목록을 출력 메서드
	 * @author 이상헌
	 * @since 2020-09-09
	 * @return List<ShowVO>
	 * @see 
	 */
	List<ReadshowVO> readShow();
	
	/**
	 * 뷰 테이블 V_MEMBER_MOVIE_LIST 의 목록을 받아서 보여줌
	 * @author 이상헌
	 * @since 2020-09-09
	 * @return List<DayShowListVO> 상영관 영화 상영시간 테이블 조인한 값 반환 
	 */
	List<DayShowListVO> dayShowList();
	
	/**
	 * 상영중인 영화와 영화 ID를 출력해줌
	 * @author 이상헌
	 * @since 2020-09-10
	 * @return 상영중인 영화와 영화 ID 출력
	 */
	void screeningMovie();
	
	/**
	 * 
	 * 관리자가 영화를 선택하면  그 영화를 예매한 사람의 목록을 출력
	 * @param String movie_name 영화이름을 받음
	 * @author 이상헌
	 * @since 2020-09-09
	 * @return Map<Integer, String> movie_name의 값에 맞는 상영관이름, 상영시간, 상영날짜, 영화제목, 예매한 회원 ID
	 */
	Map<Integer, String> movieTimeList(String movie_name);
	
	
	
	
	/**
	 * 상영관에따라 상영시간이 나오게
	 * @author 이상헌
	 * @param time
	 * @return 리스트로줌 상영시간
	 */
	List<String> movieTIMEList(Map<String, Integer> time);
	
	
	
	/**
	 * 선택한 상영시간을 비활성화
	 * @author 이상헌
	 * @since 2020-09-09
	 * @param int show_id 상영시간 번호를 받는다
	 * @return 상영정보를 비활성화 하고 성공하면 true 실패하면 false를 반환
	 */
	boolean disabledShow(int show_id);
	
	
	/**
	 * 비활성화 되어있는 상영시간을 다시 활성화 시켜줌
	 * @author 이상헌
	 * @param show_id 상영시간 번호를 받는다
	 * @return 해당 상영시간을 활성화 시키면 true 실패하면 false를 반환
	 */
	boolean liveshow(int show_id);
	
	
	
	
	
	/**
	 * 상영시간을 입력할 때 입력한 시간이 상영시간에 있는지 확인하는 메서드
	 * 
	 * @author 이상헌
	 * @since 2020-09-09
	 * @param show_start_time 을 받아와서 비교함
	 * @return 값이 데이터베이스에 있으면 false 없으면 true를 반환
	 */
	boolean checkshow_sc(String show_start_time);
	
	
	
	/**
	 * 상영시간 추가하는 메서드
	 * @author 이상헌
	 * @since 2020-09-09
	 * @param AddshowVO addshow 의 타입에 담아서 가져와야함
	 * @return 없음
	 * @see 상영날짜 컬럼을 추가해서 메서드를 바꿨음
	 * String sql = "INSERT INTO SHOW_SC(SHOW_ID, SHOW_START_TIME, SHOW_DELETE, MOVIE_ID, CINEMA_ID, SHOW_DATD) " +
						  "VALUES(SEQ_SHOW_SC_ID.NEXTVAL, '" + addshow.getShow_start_time()  + 
						           "', '0'," + addshow.getMovie_id() +", " + addshow.getCinema_id() +
						           ", TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
	 */
	boolean add_Show_Sc(AddshowVO addshow);
	
	
	
	/**
	 * 영화id 번호를 받고 해당 id의 영화 이름을 문자열로 받아오는 메서드
	 * 
	 * @author 이상헌
	 * @since 2020-09-10
	 * @return string
	 */
	String selectMovieId_returnString(int movie_id);
	
	
	/**
	 * 선준
	 */
	Map<Integer, String> screeningMovieName();
	Map<String, String> readMovieTimeList(String movie_name);
}
