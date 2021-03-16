package reserve;

import java.util.List;
import java.util.Map;

public interface IReserveDao {

	/**
	 * 예약 좌석을 가져오는 메서드
	 * @param cinema_id 상영관아이디
	 * @author 김선준
	 * @param show_id 
	 * @return int형 배열에 담아서 가져온다.
	 */
	int[] readResSeat(int cinema_id, int show_id);

	boolean createReserve(Map<String, String> map);

	/**
	 * map에 내 예매정보를 다 담아옴
	 * @param login_id
	 * @return map (movie_id, movie_name)
	 */
	Map<String, String> readMyReserve(String login_id);

	String readResNO(Map<String, String> writeReviewMap);

	List<ReserveVO> readAllReserve();
}
