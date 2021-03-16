package cinema;

import java.util.List;
import java.util.Map;

public class ICinemaServiceImpl implements ICinemaService{
	
	private static ICinemaService cinemaSv;
	private ICinemaDao cinemaDao;
	
	private ICinemaServiceImpl(){
		cinemaDao = ICinemaDaoImpl.getInstance();
	}
	
	public static ICinemaService getInstance(){
		if(cinemaSv == null){
			cinemaSv = new ICinemaServiceImpl();
		}
		return cinemaSv;
	}

	@Override
	public List<CinemaVO> readAllCinema() {
		// TODO Auto-generated method stub
		return cinemaDao.readAllCinema();
	}

	@Override
	public boolean addCinema(Map<String, Object> cinema) {
		// TODO Auto-generated method stub
		return cinemaDao.addCinema(cinema);
	}

	@Override
	public boolean checkID(int cinema_id) {
		return cinemaDao.checkID(cinema_id);
	}

	@Override
	public int deleteCinema(int cinema_id) {
		// TODO Auto-generated method stub
		return cinemaDao.deleteCinema(cinema_id);
	}

	@Override
	public int reviseCinema(Map<String, Object> cinema) {
		// TODO Auto-generated method stub
		return cinemaDao.reviseCinema(cinema);
	}

	@Override
	public int cinemaSales(int cinema_id) {
		// TODO Auto-generated method stub
		return cinemaDao.cinemaSales(cinema_id);
	}

	@Override
	public int allSales() {
		// TODO Auto-generated method stub
		return cinemaDao.allSales();
	}
}
