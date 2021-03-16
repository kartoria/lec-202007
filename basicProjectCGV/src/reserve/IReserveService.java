package reserve;

import java.util.List;
import java.util.Map;

public interface IReserveService {

	/**
	 * 상영관, 시간 고르는 메소드
	 * @param movie_name
	 * @param login_id 
	 */
	void selectShow(String movie_name, String login_id);

	/**
	 * 내 예매정보를 가져오는 메소드
	 * @param login_id
	 * @return Map
	 */
	Map<String, String> readMyReserve(String login_id);

	/**
	 * 선택한 영화의 예매번호를 가져오는 메소드
	 * @param writeReviewMap
	 * @return String res_no
	 */
	String readResNo(Map<String, String> writeReviewMap);

	List<ReserveVO> readAllReserve();
}
