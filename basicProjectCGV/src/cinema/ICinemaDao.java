package cinema;

import java.util.List;
import java.util.Map;

public interface ICinemaDao {

	List<CinemaVO> readAllCinema();

	boolean addCinema(Map<String, Object> cinema);

	boolean checkID(int cinemaID);

	int deleteCinema(int cinema_id);

	int reviseCinema(Map<String, Object> cinema);

	int cinemaSales(int cinema_id);

	int allSales();
	
	}
