package genre;

import java.util.List;

public class IGenreServieImpl implements IGenreService{
	
	private static IGenreService genreSv;
	private IGenreDao genreDao;
	
	private IGenreServieImpl(){
		genreDao = IGenreDaoImpl.getInstance();
	}
	
	public static IGenreService getInstance(){
		if(genreSv == null){
			genreSv = new IGenreServieImpl();
		}
		return genreSv;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<GenreVO> readGenre() {
		return genreDao.readGenre();
	}
	
}
