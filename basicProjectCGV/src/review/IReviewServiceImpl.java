package review;

import java.util.Map;
import java.util.Scanner;

import member.IMemberDaoImpl;

public class IReviewServiceImpl implements IReviewService{
	
	private static IReviewService reviewSv;
	private IReviewDao reviewDao;
	
	private IReviewServiceImpl(){
		reviewDao = IReviewDaoImpl.getInstance(); 
	}
	
	public static IReviewService getInstance(){
		if(reviewSv == null){
			reviewSv = new IReviewServiceImpl();
		}
		return reviewSv;
	}

	@Override
	public boolean selectMovieReview(String movie_name) {
		System.out.println("────────────────────────────────────");
		System.out.println("\t※" + movie_name +"의 리뷰");
		System.out.println("────────────────────────────────────");
		return reviewDao.selectMovieReview(movie_name);
	}

	public boolean printAllReview(){
		return reviewDao.printAllReview();
	}
	@Override
	public boolean deleteMovieReview(int review_no) {
		return reviewDao.deleteMovieReview(review_no);
	}

	@Override
	public boolean writeReview(Map<String, String> writeReviewMap) {
		return reviewDao.writeReview(writeReviewMap);
	}
	
	@Override
	public String writeReviewContent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("내용 : ");
		String write = sc.nextLine();
		return write;
	}
	
	@Override
	public String writeReviewTitle() {
		Scanner sc = new Scanner(System.in);
		System.out.println("제목 : ");
		String write = sc.nextLine();
		return write;
	}

	
	
	
}
