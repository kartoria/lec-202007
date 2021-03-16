package review;

import java.util.Map;

public interface IReviewService {

	/**
	 * 고른 영화의 리뷰를 보여주는 메소드
	 * @param movie_name
	 * @return 
	 * @author 김선준
	 */
	boolean selectMovieReview(String movie_name);

	/**
	 * 고른 영화의 리뷰를 삭제하는 메소드
	 * @param int review_no 고른 리뷰 번호
	 * @return boolean 삭제유무
	 * @author 김선준
	 */
	boolean deleteMovieReview(int review_no);
	
	/**
	 * 모든 영화 출력
	 * @return 출력유무
	 * @author 김선준
	 */
	boolean printAllReview();

	boolean writeReview(Map<String, String> writeReviewMap);

	/**
	 * 리뷰 제목 작성
	 * @author 김선준
	 * @return
	 */
	String writeReviewTitle();

	/**
	 * 리뷰 내용 작성
	 * @author 김선준
	 * @return
	 */
	String writeReviewContent();
}
