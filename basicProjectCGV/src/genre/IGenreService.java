package genre;

import java.util.List;

public interface IGenreService {

	/**
	 * 모든 장르이름, 장르번호를 리스트로 가져오는 메서드
	 * @author 신광진
	 * @return List<GenreVO>
	 * @since 2020-09-09
	 * @see
	 * 
	 */
	public List<GenreVO> readGenre();
}
