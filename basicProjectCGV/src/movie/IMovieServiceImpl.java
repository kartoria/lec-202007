package movie;

import genre.GenreVO;
import genre.IGenreService;
import genre.IGenreServieImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import notice.INoticeService;
import notice.INoticeServiceImpl;
import review.IReviewDao;
import review.IReviewDaoImpl;
import review.IReviewService;
import review.IReviewServiceImpl;
import cinema.ICinemaService;
import cinema.ICinemaServiceImpl;
import show.IShowService;
import show.IShowServiceImpl;
import reserve.IReserveService;
import reserve.IReserveServiceImpl;
import member.IMemberDaoImpl;
import member.IMemberService;
import member.IMemberServiceImpl;

public class IMovieServiceImpl implements IMovieService{
	
	private static IMovieService movieSv;
	private IMovieDao movieDao;
	private IMemberService memSv = IMemberServiceImpl.getInstance();
	private INoticeService noticeSv = INoticeServiceImpl.getInstance();
	private IReviewService reviewSv = IReviewServiceImpl.getInstance();
	private ICinemaService CinemaSv = ICinemaServiceImpl.getInstance();
	private IShowService showSv = IShowServiceImpl.getInstance();
	private IReserveService reserveSv = IReserveServiceImpl.getInstance();
	private IGenreService genSv = IGenreServieImpl.getInstance();
	private IMovieServiceImpl(){
		movieDao = IMovieDaoImpl.getInstance();
	}
	
	public static IMovieService getInstance(){
		if(movieSv == null){
			movieSv = new IMovieServiceImpl();
		}
		return movieSv;
	}

	// 영화 제목 출력하고 선택하는 메소드
	@Override
	public Map<Integer, String> printMovieName(){
		Map<Integer, String> movieNameList = movieDao.readMovieName(); // 영화제목 받아온 해시맵 저장
		
		System.out.println();
		for(int i = 1; i<movieNameList.size()+1; i++){
			System.out.println("┌――――――――――――――――――――――――――――――――┐");
			System.out.println("\t[ " +i + " ] " + movieNameList.get(i)); //영화 제목 출력
			System.out.println("└―――――――――━――――――――――――――――――――――┘");
		}
		return movieNameList;
	}

	// 해당 영화의 모든 정보를 출력
	@Override
	public void printMovieInfo(String movie_name) {
		Map<String, String> movieInfo = movieDao.readMovieInfo(movie_name);
		System.out.println();
		System.out.println("┌――――――――――――――――――――――――――――――――┐");
		System.out.print("제목 : " + movieInfo.get("movie_name"));
		System.out.println("\t등급 : " + movieInfo.get("grade_level"));
		System.out.print("장르 : " + movieInfo.get("genre_name"));
		System.out.println("\t\t\t러닝타임 : " + movieInfo.get("movie_runt") + "분");
		System.out.print("감독 : " + movieInfo.get("movie_director"));
		System.out.println("\t\t주연 : " + movieInfo.get("movie_actor"));
		System.out.println("개봉일 : " + movieInfo.get("movie_open"));
		System.out.println("└―――――――――━――――――――――――――――――――――┘");

	}
	
	public boolean printAllMovie(){
		return movieDao.printAllMovie();
	}

	@Override
	public boolean createMovie() {
		//name,director,runt,actor,open,grade,genre 입력받아야함
		MovieVO mv = new MovieVO();
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println("\t\t   영화 제목입력");
		System.out.println("└―――――――――━―――――――――┘");
		System.out.print("영화제목 : ");
		String movie_name = sc.nextLine();

		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println("\t\t   감독이름 입력");
		System.out.println("└―――――――――━―――――――――┘");
		System.out.print("감독이름 : ");
		String movie_director = sc.nextLine();
		
		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println("\t\t   러닝타임 입력");
		System.out.println("\t      <분 단위로 입력하세요>");
		System.out.println("└―――――――――━―――――――――┘");
		System.out.print("러닝타임 : ");
		String movie_runt = sc.next();
		sc.nextLine();
		
		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println("\t\t   배우이름 입력");
		System.out.println("└―――――――――━―――――――――┘");
		System.out.print("배우이름 : ");
		String movie_actor = sc.nextLine();

		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println("\t\t   개봉일자 입력");
		System.out.println("\t\t  <ex. 20201024>");
		System.out.println("└―――――――――━―――――――――┘");
		System.out.print("개봉일자 : ");
		String movie_open = sc.next();
		sc.nextLine();

		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println("\t\t   연령등급 선택");
		System.out.println("\t   <하단의 번호를 입력하세요>");
		System.out.println("└―――――――――━―――――――――┘");
		System.out.print("[ 1 ] 전체이용가\t");
		System.out.println("[ 2 ] 만 7세 이상");
		System.out.print("[ 3 ] 만 12세 이상\t");
		System.out.print("[ 4 ] 만 15세 이상\t");
		System.out.println("[ 5 ] 만 19세 이상");
		System.out.print("연령등급 선택 : ");
		String grade_id = sc.next();
		sc.nextLine();
		
		//장르리스트 출력하는 메소드
		readGenre();
		System.out.print("장르를 선택하세요 : ");
		String genre_id = sc.next();
		
		mv.setMovie_name(movie_name);
		mv.setMovie_director(movie_director);
		mv.setMovie_runt(movie_runt);
		mv.setMovie_actor(movie_actor);
		mv.setMovie_open(movie_open);
		mv.setGenre_id(genre_id);
		mv.setGrade_id(grade_id);
		
		
		if(movieDao.createMovie(mv) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	private void readGenre() {

		List<GenreVO> list = genSv.readGenre();

		System.out.println("\t\t■ 장르리스트 ■");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("┌――――――――――――――――――┐");
			System.out.println("│\t장르번호 : " + list.get(i).getGenre_id());
			System.out.println("│\t장르이름 : " + list.get(i).getGenre_name());
			System.out.println("└――――――――――――――――――┘");
		}
	}

	@Override
	public boolean deleteMovie(int movie_id) {
		return movieDao.deleteMovie(movie_id);
	}
	
	@Override
	public int movieSales(int movie_id) {
		// TODO Auto-generated method stub
		return movieDao.movieSales(movie_id);
	}
	
	@Override
	public boolean movieCheck(int movie_id) {
		// TODO Auto-generated method stub
		return movieDao.movieCheck(movie_id);
	}
	
	
	
}
