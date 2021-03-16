package review;

import java.util.Map;

public interface IReviewDao {

	/**
	 * 고른 영화의 리뷰를 보여주는 메소드
	 * @param movie_name
	 * @return 
	 */
	boolean selectMovieReview(String movie_name);

	boolean deleteMovieReview(int review_no);

	boolean printAllReview();

	boolean writeReview(Map<String, String> writeReviewMap);

	

}
