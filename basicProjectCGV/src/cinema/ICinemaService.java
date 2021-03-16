package cinema;

import java.util.List;
import java.util.Map;

public interface ICinemaService {

	/**
	 * 현재 상영관 목록 리스트 반환
	 * @method 	readAllCinema
	 * @return 	List<CinemaVO>
	 * @author 	PC-NEW02
	 * @since  	2020. 9. 9.오후 12:25:52
	 */
	List<CinemaVO> readAllCinema();

	boolean addCinema(Map<String, Object> cinema);

	boolean checkID(int cinema_id);

	int deleteCinema(int cinema_id);

	int reviseCinema(Map<String, Object> cinema);

	int cinemaSales(int cinema_id);

	int allSales();
}
