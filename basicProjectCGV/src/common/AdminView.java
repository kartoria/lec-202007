package common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.xml.soap.Detail;

import cinema.*;
import member.*;
import movie.*;
import notice.*;
import reserve.*;
import review.*;
import show.*;
import genre.*;
import grade.*;

public class AdminView {

	private IMemberService memSv = IMemberServiceImpl.getInstance();
	private IMovieService movieSv = IMovieServiceImpl.getInstance();
	private INoticeService noticeSv = INoticeServiceImpl.getInstance();
	private IReviewService reviewSv = IReviewServiceImpl.getInstance();
	private ICinemaService cinemaSv = ICinemaServiceImpl.getInstance();
	private IShowService showSv = IShowServiceImpl.getInstance();
	private IReserveService reserveSv = IReserveServiceImpl.getInstance();
	private IGenreService genreSv = IGenreServieImpl.getInstance();
	private RegExClass reg = new RegExClass();
	private String login_id;

	/*
	 * System.out.println("┌――――――[사용자 메뉴]―――――――┐");
	 * System.out.println("｜\t\t[ 1 ] 게시물 수정하기");
	 * System.out.println("｜\t\t[ 2 ] 게시물 삭제하기");
	 * System.out.println("｜\t\t[ 3 ] 뒤로가기");
	 * System.out.println("└―――――――――――――――――――┘");
	 */

	public void adminList() {
		do {
			Scanner sc = new Scanner(System.in);
			int input = 0;
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t      관리자 메뉴");
			System.out.println("└――――――――――――――――――――――――――┘");
			System.out.print("[ 1 ] 영화 관리");//
			System.out.print("\t[ 2 ] 상영 관리");//
			System.out.println("\t[ 3 ] 상영관 관리");//
			System.out.print("[ 4 ] 예매 현황");//
			System.out.print("\t[ 5 ] 회원 관리");//
			System.out.println("\t[ 6 ] 공지 관리");//
			System.out.print("[ 7 ] 리뷰 관리");//
			System.out.print("\t[ 8 ] 매출 관리");//
			System.out.println("\t[ 0 ] 로그아웃");
			System.out.println();

			System.out.print("메뉴번호를 입력하세요 : ");
			
			try {
				input = sc.nextInt();
				sc.nextLine();
			} catch (IndexOutOfBoundsException e) {
				msg();
				return;
			} catch (InputMismatchException e) {
				msg();
				return;
			}
			switch (input) {
			case 1: // 영화 관리
				System.out.println();
				managerMovie();
				break;
			case 2: // 상영 관리
				System.out.println();
				managerShow(); // DB수정 후 다시 만들어야 함.
				break;
			case 3: // 상영관 관리
				managerCinema();
				break;
			case 4: // 예매 현황
				System.out.println();
				reserveList();
				break;
			case 5: // 회원 관리 메서드() : 이상헌
				System.out.println();
				worklist();
				break;
			case 6: // 공지 사항() : 이상헌
				System.out.println();
				selectNoticeWork();
				break;
			case 7: // 리뷰 관리
				System.out.println();
				managerReview();
				break;
			case 8: // 매출 관리
				System.out.println();
				managerSales();
				break;
			case 0: // 나가기 -> adminList() 메서드 종료
				System.out.println();
				System.out.println("┌―――――――――――――――――┐");
				System.out.println("\t  이용해주셔서 감사합니다.");
				System.out.println("└―――――――――――――――――┘");
				return; // 나가기
			default:
				System.out.println();
				msg();
				break;
			}

		} while (true);
	}

	/*
	 * ===================================================
	 * 						1. 영화 관리
	 * ===================================================
	 */

	public void msg() {
		System.out.println();
		System.out.println("┌―――――――― WARNING!! ――――――――┐");
		System.out.println("\t\t    정확히 입력해주세요.");
		System.out.println("└――――――――――――――――━―――――┘");
	}

	/**
	 * 영화 관리 뷰
	 * 
	 * @method managerMovie
	 * @return void
	 * @author JSB
	 * @since 2020. 9. 3
	 */
	private void managerMovie() {
		do {
			Scanner sc = new Scanner(System.in);
			int input = 0;
			System.out.println("┌――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t      영화 관리");
			System.out.println("└――――――――――――――――――――――――――┘");
			System.out.print("[ 1 ] 영화 조회");
			System.out.print("\t[ 2 ] 영화 추가");
			System.out.println("\t[ 3 ] 영화 삭제");
			System.out.print("[ 4 ] 장르리스트");
			System.out.println("\t[ 0 ] 나가기");
			System.out.println();

			System.out.print("메뉴번호를 입력하세요 : ");
			try {
				input = sc.nextInt();
				sc.nextLine();
			} catch (IndexOutOfBoundsException e) {
				msg();
				return;
			} catch (InputMismatchException e) {
				msg();
				return;
			}

			switch (input) {
			case 1: // 영화 조회
				System.out.println();
				movieList();
				break;
				
			case 2: // 영화 추가
				System.out.println();
				System.out.println("┌―――――――――――――――――――――┐");
				System.out.println("\t\t영화 추가를 진행합니다.");
				System.out.println("└―――――――――――━―――――――――┘");
				addMovie();
				break;
				
			case 3: // 영화 삭제
				System.out.println();
				System.out.println("┌―――――――――――――――――――――┐");
				System.out.println("\t\t영화 삭제를 진행합니다.");
				System.out.println("└―――――――――――━―――――――――┘");
				deleteMovie();
				break;

			case 4:
				readGenre();
				break;
				
			case 0: 
				return;
				
			default:
				msg();
				break;
			}
		} while (true);
	}

	private void readGenre() {

		List<GenreVO> list = genreSv.readGenre();

		System.out.println();
		System.out.println("\t   ■ 장르리스트 ■");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("┌――――――――――――――┐");
			System.out.println("\t장르번호 : " + list.get(i).getGenre_id());
			System.out.println("\t장르이름 : " + list.get(i).getGenre_name());
			System.out.println("└――――――――――――――┘");
		}
	}

	/**
	 * 상영중인 영화 목록 출력 메서드
	 * 
	 * @method movieList
	 * @param
	 * @return void
	 * @author JSB
	 * @since 2020. 9. 3
	 */
	private void movieList() { // 등록된 영화 리스트
		if (!movieSv.printAllMovie()) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t\t상영중인 영화가 없습니다.");
			System.out.println("└―――――――――――━――――――――――――┘");
			return;
		}
	}

	/**
	 * 상영 영화 추가 메서드 영화 추가 실패시 오류 문구 출력 후 뒤로 가기
	 * 
	 * @method addMovie
	 * @return void
	 * @author 김선준
	 * @since 2020. 9. 10
	 */
	private void addMovie() {
		while (true) {
			if (movieSv.createMovie()) {
				System.out.println();
				System.out.println("┌―――――――――――――――――――――┐");
				System.out.println("\t\t영화가 추가되었습니다.");
				System.out.println("└―――――――――――━―――――――――┘");
				return;
			} else {
				System.out.println();
				System.out.println("┌――――――――――――――――――――――――┐");
				System.out.println("\t\t영화추가를 실패하였습니다.");
				System.out.println("└―――――――――――━――――――――――――┘");
			}
		}
	}

	/**
	 * 영화 삭제 메서드 영화 삭제 실패시 오류 문구 출력 후 뒤로 가기
	 * 
	 * @method deleteMovie
	 * @return void
	 * @author JSB
	 * @since 2020. 9. 3
	 */
	public void deleteMovie() {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println();
				System.out.println("--------------------------------------");

				if (!movieSv.printAllMovie()) { // 영화출력함 안되면 밑에문구출력
					System.out.println();
					System.out.println("┌――――――――――――――――――――――――┐");
					System.out.println("\t\t상영중인 영화가 없습니다.");
					System.out.println("└―――――――――――━――――――――――――┘");
					return;
				}

				System.out.println("0. 뒤로가기");
				System.out.print("삭제할 영화를 선택해 주세요 : ");
				int movie_id = sc.nextInt();
				sc.nextLine();
				if (movie_id == 0) {
					return;
				} else if (!movieSv.deleteMovie(movie_id)) {
					System.out.println();
					System.out.println("┌――――――――――――――――――――――――┐");
					System.out.println("\t\t영화삭제를 실패하였습니다.");
					System.out.println("└―――――――――――━――――――――――――┘");
				} else {
					System.out.println();
					System.out.println("┌――――――――――――――――――――――――┐");
					System.out.println("\t\t" + movie_id + "번 영화가 삭제되었습니다.");
					System.out.println("└―――――――――――━――――――――――――┘");
					return;
				}
			} catch (InputMismatchException e) {
				msg();
			}
		}

	}

	/*
	 * =================================================== 
	 * 						2. 상영 관리
	 * ===================================================
	 */

	/**
	 * 상영 관리 메서드 만들예정
	 * 
	 * @method managerShow
	 * @return void
	 * @author PC-NEW02
	 * @since 2020. 9. 8.오후 4:51:31
	 */
	private void managerShow() {
		do {
			Scanner sc = new Scanner(System.in);
			int input = 0;

			System.out.println();
			System.out.println("┌――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t      상영 관리");
			System.out.println("└――――――――――――――――――――――――――┘");
			System.out.print("[ 1 ] 상영시간 조회");
			System.out.println("\t\t\t[ 2 ] 상영시간 추가");
			System.out.print("[ 3 ] 상영시간 삭제");
			System.out.println("\t\t\t[ 0 ] 뒤로가기");
			try {
				input = sc.nextInt();
				sc.nextLine();
			} catch (IndexOutOfBoundsException e) {
				msg();
				return;
			} catch (InputMismatchException e) {
				msg();
				return;
			}
			switch (input) {
			case 1:
				showTimeList();
				break;
			case 2:
				addShowTime();
				break;
			case 3:
				deleteShowTime();
				break;
			case 0:
				return;
			default:
				break;
			}
		} while (true);
	}
	
	private void showTimeList() {
		do {
			Scanner sc = new Scanner(System.in);
			int input = 0;
			showSv.screeningMovie();
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t\t   영화를 선택해주세요.");
			System.out.println("\t\t (뒤로가기는 0번 입니다.)");
			System.out.println("└―――――――――――━――――――――――――┘");
			System.out.print("상영시간을 조회하실 영화ID를 입력해주세요 : ");
			
			try {
				input = sc.nextInt();
				sc.nextLine();
				if(input != 0){
					System.out.println();
					System.out.println("┌―――――――――――――――――――――――――――┐");
					System.out.println("\t\t\t상영관 종류 : " + showSv.movieTimeList(showSv.selectMovieId_returnString(input)).get(0));
					System.out.println("\t\t\t최대 좌석수 : " + showSv.movieTimeList(showSv.selectMovieId_returnString(input)).get(1) + "석");
					

					System.out.println("\t\t\t   【상영 시간】");
					for (int i = 2; i < showSv.movieTimeList(showSv.selectMovieId_returnString(input)).size(); i++) {
						System.out.println("\t\t\t【 [ "+ (i-1) + " ] " + showSv.movieTimeList(showSv.selectMovieId_returnString(input)).get(i) + " 】");	
					}
					System.out.println("└――――――――――――――━――――――――――――┘");
				} else {
					break;
				}
				
			} catch (IndexOutOfBoundsException e) {
				msg();
				return;
			} catch (InputMismatchException e) {
				msg();
				return;
			}
			
		} while (true);
		
		// for (showVO show : ???) {
		// }
	}
	
	private void addShowTime() {
		do {
			Scanner sc = new Scanner(System.in);
			AddshowVO addshowvo = new AddshowVO();
			
			movieSv.printMovieName();// 전체 영화 목록
			System.out.println();
			System.out.print("영화를 선택하세요(뒤로가기는 0번 입니다) : ");
			int movieselectnum = sc.nextInt();
			sc.nextLine();
			if(movieselectnum == 0){
				break;
			}
			
			//영화 선택
			//상영관 선택
			// checkMovie();
			// time.put("MOVIE_ID", sc.nextInt());
			// sc.nextLine();
			
			System.out.println();
			System.out.println("【현재 "+showSv.selectMovieId_returnString(movieselectnum) + "을 상영중인 상영관】");
			for (int i = 0; i < cinemaSv.readAllCinema().size(); i++) {
				System.out.println("┌―――――――――――――――――――――┐");
				System.out.println("\t상영관 번호 : " + cinemaSv.readAllCinema().get(i).getCinema_id());
				System.out.println("\t상영관 이름 : " + cinemaSv.readAllCinema().get(i).getCinema_name());
				System.out.println("\t상영관 좌석수 : " + cinemaSv.readAllCinema().get(i).getSeat_number());
				System.out.println("\t상영관 가격 : " + cinemaSv.readAllCinema().get(i).getCinema_price());
				if(cinemaSv.readAllCinema().get(i).getIsDelete() == 0){
					System.out.println("\t상영관 사용 여부 : 사용가능");				
				}else{
					System.out.println("\t상영관 사용 여부 : 불가");
				}
				System.out.println("└―――――――――――――――――――――┘");
				
			}
			System.out.println();
			System.out.println("상영관 번호를 입력하세요 (뒤로가기는 0번 입니다) : ");
			int cinemaselectnum = sc.nextInt();
			sc.nextLine();
			if(cinemaselectnum == 0){
				break;
			}
			
			
//		cinemaList();
			
			// time.put("CINEMA_ID", sc.nextInt());
			// sc.nextLine();
			
			System.out.println();
			System.out.println("┌―――――――――――――――――――――――――┐");
			System.out.println("\t\t\t※ 주의사항 ※");
			System.out.println("\t\t시간(HH)은 0 ~ 23 사이로 입력");
			System.out.println("\t\t분(MM)은 0 ~ 59 사이로 입력");
			System.out.println("\t\t입력예시 : 23:40");
			System.out.println("└―――――――――――――――――――――――――┘");

			System.out.println("추가할 상영 시간을 입력해주세요.(HH:MM)"); // 정규식 ㄱㄱ!
			String showtime = sc.nextLine();
			
			addshowvo.setMovie_id(movieselectnum);
			addshowvo.setCinema_id(cinemaselectnum);
			addshowvo.setShow_start_time(showtime);
			
			if(showSv.add_Show_Sc(addshowvo) == true){
				System.out.println("상영시간 추가 성공");
				return;
			}else{
				System.out.println("상영시간 추가 실패 다시 제대로 입력해주세요.");
				continue;
			}
			
			// sc.nextLine();
			// if(//상영 추가 메서드 : 반환타입 boolean){
			// System.out.println("");
			// } else {
			//
			// }
			
		}while(true);
	}
	

	private void deleteShowTime() {
		do{
			Scanner sc = new Scanner(System.in);
			List<ReadshowVO> readShow = showSv.readShow();
			System.out.println("\t   ┌―――――――――――――――――――――――――┐");
			System.out.println("\t\t\t   현재 상영중인 시간대 목록");
			System.out.println("\t   └―――――――――――――――――――――――――┘");

			for (int i = 0; i < showSv.readShow().size(); i++) {
				ReadshowVO rv = readShow.get(i);
				System.out.println("┌――――――――――――――――――――――――――――――――――┐");
				System.out.println("\t상영ID : " + rv.getShow_id());
				System.out.println("\t상영시간 : " + rv.getShow_start_time());
				if(rv.getShow_delete().equals("0")){
					System.out.println("\t상태 : 활성화");
				}else{
					System.out.println("\t상태 : 비활성화");					
				}
				System.out.println("\t상영관 이름 : " + rv.getCinema_name());
				System.out.println("\t영화이름 : " + rv.getMovie_name());
				System.out.println("\t등록날짜 : " + rv.getShow_date());
				System.out.println("└―――――――――━――――――――――――――――――――――――┘");
			}
			System.out.println("┌―――――――――――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t\t       ※ 주의사항 ※");
			System.out.println("\t비활성화 되어있는 상영시간 ID를 선택하시면 활성화상태로 전환");
			System.out.println("\t활성화 되어있는 상영시간 ID를 선택하시면 비활성화로 전환됩니다.");
			System.out.println("└――――――――――――━――――――――――――――――――――――┘");
			

			System.out.println();
			System.out.print("삭제할 상영시간 ID를 입력해주세요. (뒤로가기는 0번 입니다) : ");
			int movieselectnum = sc.nextInt();
			sc.nextLine();
			if(movieselectnum == 0){
				break;
			}else{
				if(readShow.get(movieselectnum-1).getShow_delete().equals("0")){
					showSv.disabledShow(movieselectnum);
					System.out.println("비활성화 완료");
				}else{
					showSv.liveshow(movieselectnum);
					System.out.println("활성화 완료");
				}
			}
			
		}while(true);
	}

	/*
	 * =================================================== 
	 * 						2. 상영관 관리
	 * ===================================================
	 */

	/**
	 * 상영관 출력 메서드
	 * 
	 * @method cinemaList
	 * @return void
	 * @author JSB
	 * @since 2020. 9. 6오후 2:21:141
	 */
	private void cinemaList() {
		int cinema_num = 0;
		for (CinemaVO cinema : cinemaSv.readAllCinema()) {
			if (cinema.getIsDelete() == 0) {
				System.out.println("┌――――――――――――――――――――――――┐");
				System.out.println("\t\tNo." + ++cinema_num);
				System.out.println("\t\t상여관 이름 : " + cinema.getCinema_name());
				System.out.println("\t\t잔여석 : " + cinema.getSeat_number());
				System.out.println("\t\t입장가격 : " + cinema.getCinema_price());
				System.out.println("└―――――――――――━――――――――――――┘");
			}
		}
	}

	/**
	 * 상영관 관리 메서드
	 * 
	 * @method managerShow
	 * @return void
	 * @author JSB
	 * @since 2020. 9. 6오후 2:20:40
	 */
	private void managerCinema() {
		do {
			Scanner sc = new Scanner(System.in);
			int input = 0;
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t      상영 관리");
			System.out.println("└――――――――――――――――――――――――――┘");
			System.out.print("[ 1 ] 상영관 조회");
			System.out.println("\t\t\t[ 2 ] 상영관 추가");
			System.out.print("[ 3 ] 상영관 삭제");
			System.out.println("\t\t\t[ 0 ] 뒤로가기");

			try {
				input = sc.nextInt();
				sc.hasNextLine();
			} catch (IndexOutOfBoundsException e) {
				msg();
				return;
			} catch (InputMismatchException e) {
				msg();
				return;
			}
			switch (input) {
			case 1: // 상영 조회
				cinemaList();
				break;
			case 2: // 상영 추가
				addCinema();
				break;
			case 3: // 상영 삭제
				deleteCinema();
				break;
			case 0: // 나가기
				return;
			default:
				msg();
				break;
			}
		} while (true);
	}

	private void addCinema() {
		Scanner sc = new Scanner(System.in);
		String[] cinema_name = {"2D", "3D", "4D", "IMAX"};
		int[] cinema_price = {2000, 3000, 4000, 5000};
		int input = 0;
		Map<String, Object> cinema = new HashMap<>();

		try{
			System.out.println();
			System.out.println("┌―――――――――――――――――――――――┐");
			System.out.println("\t\t상영관 추가를 진행합니다.");
			System.out.println("└―――――――――――――━―――――――――┘");
			System.out.println();
			
			System.out.println();
			System.out.println("┌―――――――――――――――――――――┐");
			System.out.println("\t\t    상영관 번호 입력");
			System.out.println("└―――――――――――━―――――――――┘");
			System.out.print("새로운 상영관 번호를 입력하세요 : ");
			
			input = sc.nextInt();
			sc.nextLine();
			if (cinemaSv.checkID(input)) {
				System.out.println("이미 존재하는 상영관입니다.");
				return;
			}
			cinema.put("CINEMA_ID", input);
			
			System.out.println();
			System.out.println();
			System.out.println("┌―――――――――――――――――――――┐");
			System.out.println("\t\t    상영관 종류입력");
			System.out.println("└―――――――――――━―――――――――┘");
			for (int i = 0; i < cinema_name.length; i++) {
				System.out.print( "[ " + (i + 1) + " ] "+ "" + cinema_name[i] + "\t");
			}
			System.out.println();
			System.out.println();
			System.out.print("상영관 종류를 선택하세요 : ");
			input = sc.nextInt() - 1;
			sc.nextLine();
			cinema.put("CINEMA_NAME", cinema_name[input]);
			
			System.out.println();
			System.out.println("┌―――――――――――――――――――――┐");
			System.out.println("\t\t  상영관 좌석 수 입력");
			System.out.println("└―――――――――――━―――――――――┘");
			System.out.print("상영관의 총 좌석을 입력하세요 : ");
			cinema.put("SEAT_NUMBER", sc.nextInt());
			sc.nextLine();
			System.out.println();
			
			System.out.println();
			System.out.println("┌―――――――――――――――――――――┐");
			System.out.println("\t\t  상영관 입장 가격입력");
			System.out.println("└―――――――――――━―――――――――┘");
			for (int i = 0; i < cinema_price.length; i++) {
				System.out.print(" [ " + (i + 1) + " ] " + cinema_price[i] + "\t");
			}
			System.out.println();
			System.out.println();
			System.out.print("상영관 입장 가격을 선택하세요 : ");
			input = sc.nextInt() - 1;
			sc.nextLine();
			cinema.put("CINEMA_PRICE", cinema_price[input]);
			
		} catch (IndexOutOfBoundsException e) {
			ViewClass.msg();
			return;
		} catch (InputMismatchException e) {
			ViewClass.msg();
			return;
		}
		if (cinemaSv.addCinema(cinema)) {
			System.out.println("상영관이 추가되었습니다!");
		} else {
			System.out.println("상영관 추가에 실패하였습니다!");
		}
	}

	private void deleteCinema() {
		Scanner sc = new Scanner(System.in);
		cinemaList();

		System.out.println();
		System.out.println("┌――――――――――――――――――――――――┐");
		System.out.println("\t\t상영관 번호를 입력해주세요.");
		System.out.println("└―――――――――――━――――――――――――┘");

		int cinema_id = sc.nextInt();
		sc.nextLine();
		if (!cinemaSv.checkID(cinema_id)) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t\t존재하지 않는 상영관입니다.");
			System.out.println("└―――――――――――━――――――――――――┘");
			return;
		}
		if (cinemaSv.deleteCinema(cinema_id) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t\t상영관이 비활성화 되었습니다.");
			System.out.println("└―――――――――――━――――――――――――┘");
		} else {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t\t상영관 비활성화를 실패하였습니다.");
			System.out.println("└―――――――――――━――――――――――――┘");
		}
	}


	/*
	 * =================================================== 
	 * 										7. 영화 리뷰 관리
	 * ===================================================
	 */

	/**
	 * 영화 리뷰 관리 뷰
	 * 
	 * @method managerReview
	 * @return void
	 * @author JSB
	 * @since 2020. 9. 3
	 */
	private void managerReview() {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				if (!reviewSv.printAllReview()) {
					System.out.println();
					System.out.println("┌――――――――――――――――――――――――┐");
					System.out.println("\t\t리뷰가 존재하지 않습니다.");
					System.out.println("└―――――――――――━――――――――――――┘");
					return;
				}
				System.out.println();
				System.out.println("┌――――――――――――――――――――――――┐");
				System.out.println("\t\t삭제할 리뷰 번호를 입력하세요.");
				System.out.println("\t\t(뒤로가기는 0번 입니다.");
				System.out.println("└―――――――――――━――――――――――――┘");
				System.out.println();

				System.out.print("번호 입력 : ");
				int select = sc.nextInt();

				if (select == 0) {
					return;
				} else {
					if (reviewSv.deleteMovieReview(select)) {
						System.out.println();
						System.out.println("┌――――――――――――――――――――――――┐");
						System.out.println("\t\t리뷰가 삭제되었습니다");
						System.out.println("└―――――――――――━――――――――――――┘");
						return;
					} else {
						System.out.println();
						System.out.println("┌――――――――――――――――――――――――┐");
						System.out.println("\t\t리뷰 삭제를 실패했습니다.");
						System.out.println("└―――――――――――━――――――――――――┘");
					}
				}

			} catch (InputMismatchException e) {
				msg();
			}
		}
	}
	/*
	 * =================================================== 
	 * 						6. 영화 예매 현황
	 * ===================================================
	 */
	
	private void reserveList(){
		for(ReserveVO res : reserveSv.readAllReserve()){
			System.out.println("================================");
			System.out.println("예매번호 : " + res.getRes_no());
			System.out.println(res.getCinema_name() + "\tNo." + res.getCinema_id() + "관");
			System.out.println("상영 종류 : " + res.getMem_id());
			System.out.println("회원명 : " + res.getSeat_no());
			System.out.println("좌석 번호 : " + res.getSeat_no());
			System.out.println("입장 가격 : " + res.getCinema_price());
			System.out.println("================================");
			System.out.println();
		}
	}

	/*
	 * ============================================================= 
	 * 								매출 관리
	 * =============================================================
	 */
	private void managerSales(){
		do{
			Scanner sc = new Scanner(System.in);
			int input = 0;
			System.out.println("1. 영화관 총 매출");
			System.out.println("2. 상영관별 매출");
			System.out.println("3. 영화별 매출");
			System.out.println("0. 나가기");
			try{
				input = sc.nextInt();
			} catch (IndexOutOfBoundsException e) {
				System.out.println("올바른 입력 형태가 아닙니다.!");
				System.out.println("다시 시도해주세요!");
				return;
			} catch (InputMismatchException e) {
				System.out.println("올바른 입력 형태가 아닙니다.!");
				System.out.println("다시 시도해주세요!");
				return;
			}
			switch(input){
			case 1:
				allSales();
				break;
			case 2:
				cinemaSales();
				break;
			case 3:
				movieSales();
				break;
			case 0:
				return;
			default:
				System.out.println("해당 목록은 존재하지 않습니다!");
				break;
			}
			
		} while(true);
	}
	
	private void movieSales(){
		Scanner sc = new Scanner(System.in);
		int input = 0;
		movieList();
		System.out.println("매출을 조회할 영화를 선택해 주세요.");
		try{
			input = sc.nextInt();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("올바른 입력 형태가 아닙니다.!");
			System.out.println("다시 시도해주세요!");
			return;
		} catch (InputMismatchException e) {
			System.out.println("올바른 입력 형태가 아닙니다.!");
			System.out.println("다시 시도해주세요!");
			return;
		}
		if(!movieSv.movieCheck(input)){
			System.out.println("해당 영화는 존재하지 않습니다.");
			return;
		}
		System.out.println("선택한 영화 총 매출  : ");
		System.out.println(movieSv.movieSales(input));
	}
	
	private void cinemaSales(){
		Scanner sc = new Scanner(System.in);
		int input = 0;
		cinemaList();
		System.out.println("매출을 조회할 상영관을 선택해 주세요.");
		try{
			input = sc.nextInt();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("올바른 입력 형태가 아닙니다.!");
			System.out.println("다시 시도해주세요!");
			return;
		} catch (InputMismatchException e) {
			System.out.println("올바른 입력 형태가 아닙니다.!");
			System.out.println("다시 시도해주세요!");
			return;
		}
		if(!cinemaSv.checkID(input)){
			System.out.println("존재하지 않는 상영관입니다!");
			return;
		}
		System.out.println("선택하신 상영관의 총 매출  : ");
		System.out.println(cinemaSv.cinemaSales(input));
	}

	private void allSales(){
		System.out.println("영화관의 총 매출  : ");
		System.out.println(cinemaSv.allSales());
	}

	/*
	 * ============================================================= 
	 * 							이상헌
	 * =============================================================
	 */

	/*
	 * 회원관리 managerMember() 들어오면 저장되어있는 회원의 이름이 번호가 부여되서 출력되고 해당 이름의 번호를 입력시 회원의
	 * 정보를 출력해주고 등급변경과 회원정보삭제 로 넘어감
	 * 
	 * 받아와야 되는 것 회원리스트및 정보
	 */
	/**
	 * 회원관리 작업매뉴 선택 View 메소드
	 * 
	 * @return
	 * 
	 */
	private void worklist() {
		do {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t      회원 관리");
			System.out.println("└――――――――――――――――――――――――――┘");
			System.out.print("[ 1 ] 회원정보 조회\t          ");
			System.out.println("[ 2 ] 뒤로가기");
			System.out.println();

			System.out.print("메뉴 번호를 입력하세요 : ");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch (input) {
			case 1:
				selectMember();
				break;
			case 2:
				// memberActivestate();
				return;
			default:
				ViewClass.msg();;
			}
		} while (true);
	}

	/**
	 * 정보를 조회할 회원을 고르는 메소드 회원목록을 불러오고 번호를 선택할 수 있다.
	 * 
	 * @param 없음
	 * @author 이상헌
	 * @return void
	 */
	private void selectMember() {
		List<MemberVO> memberlist = memSv.readAllMember();
		System.out.println("【회원 계정 목록】");
		for (int i = 0; i < memberlist.size(); i++) {
			MemberVO mem = memberlist.get(i);
			System.out.println("┌―――――――――――――――――――――――――┐");
			System.out.println("\t\t회원 ID : " + mem.getMem_id());
			System.out.println("\t\t이름 : " + mem.getMem_name());
			System.out.println("\t\t비밀번호 : " + mem.getMem_pass());
			System.out.println("\t\t생년월일 : " + mem.getMem_regno1());
			System.out.println("\t\t주소 : " + mem.getMem_add1());
			System.out.println("\t\t상세주소 : " + mem.getMem_add2());
			System.out.println("\t\t이메일 : " + mem.getMem_mail());
			System.out.println("\t\t핸드폰 번호 : " + mem.getMem_hp());
			System.out.println("\t\t마일리지 : " + mem.getMem_mileage() + " Point");
			if(mem.getMem_delete().equals("0")){
				System.out.println("\t\t계정 상태 : 활성화");
			} else {
				System.out.println("\t\t계정 상태 : 활성화");								
			}
			System.out.println("└―――――――――━―――――――――――――――┘");

		}
		
	}
	
	/**
	 * 회원계정 작업 View 메소드
	 * 
	 * @author 이상헌
	 * @since 2020-09-03
	 */
	/*
	 * 회원 정보 수정 및 삭제 cgran_Delete_Member(int num) 매개변수 int num은 회원관리 메인
	 * 메소드(managerMember())에서 입력받은 숫자를 기반으로 회원정보를 표시해준다.
	 * 
	 * 1. 회원 등급 변경 2. 회원 정보 삭제 3. 뒤로가기
	 * 
	 * 받아와야 하는 리스트 : readMember():관리자용 회원조회, readAllRes():관리자용 예약조회 메서드 db에서
	 * 받아와야하는 메소드 deleteMember(MemberVO member):회원삭제
	 */
	private void memberActivestate(){
		do{
			System.out.println("회원 계정 작업 목록");
			System.out.println("1. 회원 활성상태 변경 ");
			System.out.println("2. 뒤로가기");
			System.out.print("번호를 선택해서 이동 : ");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch (input) {
			case 1:
				changestmember();
				break;
			default:
				return;
			}
			
		}while(true);
	}
	
	private void changestmember(){
		do{
			Scanner sc = new Scanner(System.in);
			System.out.println("회원 정보");
			List<MemberVO> memberlist = memSv.readAllMember();
			for (int i = 0; i < memberlist.size(); i++) {
				MemberVO mem = memberlist.get(i);
				System.out.println((i+1) + "번 회원 ID : " + mem.getMem_id());
				System.out.println("이름 :" + mem.getMem_name());
				if(mem.getMem_delete().equals("0")){
					System.out.println("계정 상태 : 활성화");
				} else {
					System.out.println("계정 상태 : 비활성화");								
				}
				System.out.println("---------------------------------");
			}
			System.out.println();
			System.out.println("경고 : 비활성화 되어있는 회원 ID를 선택하시면 활성화가되고");
			System.out.println("경고 : 활성화 되어있는 회원 ID를 선택하시면 비활성화가됩니다.");
			System.out.println();
			System.out.println("계정 활성 상태를 변경할 회원의 번호를 고르세요.");
			System.out.println("0. 뒤로가기");
			int selectmember = sc.nextInt();
			sc.nextLine();
			if(selectmember == 0){
				break;
			}else{
				String deleteid = memberlist.get(selectmember-1).getMem_id();
				memSv.deleteMember(deleteid);
				System.out.println(deleteid + "님의 아이디의 상태를 변경합니다");
				return;
			}
			
			
		}while(true);
	}

	/**
	 * 공지사항 작업 선텍 메소드
	 * 
	 * @author 이상헌
	 * @since 2020-09-03
	 */
	private void selectNoticeWork() {
		do {
			System.out.println();
			System.out.println("┌―――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t      공지사항 관리");
			System.out.println("└―――――――――――――――――――――――――――┘");
			System.out.print("[ 1 ] 공지사항 조회\t     ");
			System.out.print("[ 2 ] 공지사항 추가\t   ");
			System.out.println("[ 3 ] 뒤로가기");
			System.out.println();

			System.out.print("메뉴 번호를 입력하세요 : ");

			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();

			switch (input) {
			case 1:
				readNotice(); // 공지사항 조회하기
				break;

			case 2:
				createNotice(); // 공지사항 작성하기
				break;

			case 3:
				return;

			default:
				msg();
			}
		} while (true);
	}

	/**
	 * 공지사항 추가 메서드
	 * 
	 * @author 신광진
	 */
	private void createNotice() {

		String notice_title = inputNoticeTitle();
		String notice_comment = inputNoticeComment();
		Map<String, String> info = new HashMap<>();
		info.put("notice_title", notice_title);
		info.put("notice_comment", notice_comment);

		if (noticeSv.createNotice(info) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t\t게시물을 추가하였습니다.");
			System.out.println("└―――――――――――━――――――――――――┘");
			return;
		}
		System.out.println();
		System.out.println("┌――――――――――――――――――――――――┐");
		System.out.println("\t\t게시물 추가를 실패하였습니다.");
		System.out.println("└―――――――――――━――――――――――――┘");
		return;
	}

	/**
	 * 공지사항 조회 메서드
	 * 
	 * @author 신광진
	 */
	private void readNotice() {

		while (true) {
			List<NoticeVO> list = noticeSv.readNotice();

			if (list.isEmpty()) {
				System.out.println();
				System.out.println("┌――――――――――――――――――――――――┐");
				System.out.println("\t\t공지사항이 존재하지 않습니다.");
				System.out.println("└―――――――――――━――――――――――――┘");
				return;
			}

			System.out.println();
			simplePrintNotice(list);
			System.out.print("[ 1 ] 공지사항 상세보기\t\t");
			System.out.println("[ 2 ] 뒤로가기\t     ");
			System.out.println();

			System.out.print("메뉴번호를 입력하세요 : ");
			Scanner sc = new Scanner(System.in);
			int cmd = -1;

			try {
				cmd = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			} catch (Exception e) {
				msg();
				continue;
			}

			switch (cmd) {
			case 1:
				noticeDetail(list);
				break;

			case 2:
				return;

			default:
				msg();
			}
		}

	}

	private void simplePrintNotice(List<NoticeVO> list) {

		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("┌――――――――――――――――――――――――――┐");
			System.out.println("\t게시글 번호 : No. " + (i + 1));
			System.out.println("\t게시글 제목 : " + list.get(i).getNotice_title());
			System.out.println("└――――――――――――――――――――――――――┘");
		}

	}

	private void detailPrintNotice() {

		List<NoticeVO> list = noticeSv.readNotice();

		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("┌―――――――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t게시글 번호 : No. " + (i + 1));
			System.out.println("\t\t\t게시글 제목 : " + list.get(i).getNotice_title());
			System.out
					.println("\t\t\t게시글 내용 : " + list.get(i).getNotice_commit());
			System.out.println("\t\t\t게시글 작성자 : " + list.get(i).getAdmin_id());
			System.out.println("└―――――――――――――――――――――――――――――――┘");
		}
	}

	private void noticeDetail(List<NoticeVO> list) {

		while (true) {
			detailPrintNotice();

			System.out.print("[ 1 ] 공지사항 수정\t\t");
			System.out.print("[ 2 ] 공지사항 삭제\t       ");
			System.out.println("[ 3 ] 뒤로가기");
			System.out.println();

			System.out.print("메뉴 번호를 입력하세요 : ");

			Scanner sc = new Scanner(System.in);
			int cmd = -1;

			try {
				cmd = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			} catch (Exception e) {
				msg();
				continue;
			}

			switch (cmd) {
			case 1:
				updateNotice(list);
				break;

			case 2:
				deleteNotice(list);
				return;

			case 3:
				return;

			default:
				msg();
			}

		}
	}

	/**
	 * 게시물 수정 메뉴
	 * 
	 * @author 신광진
	 * @param notice_no
	 */
	private void updateNotice(List<NoticeVO> list) {

		while (true) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――――┐");
			System.out.println("\t\t\t      공지사항 관리");
			System.out.println("└――――――――――――――――――――――――――┘");
			System.out.print("[ 1 ] 제목수정\t ");
			System.out.print("[ 2 ] 내용수정\t   ");
			System.out.println("[ 3 ] 뒤로가기");
			System.out.println();

			System.out.print("메뉴 번호를 입력하세요 : ");
			Scanner sc = new Scanner(System.in);
			int cmd = -1;

			try {
				cmd = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
			} catch (Exception e) {
				msg();
			}

			switch (cmd) {
			case 1:
				updateNoticeTitle(list);
				return;
			case 2:
				updateNoticeComment(list);
				return;

			case 3:
				return;

			default:
				msg();

			}
		}

	}

	private void deleteNotice(List<NoticeVO> list) {

		String notice_no = selectNotice(list);

		if (noticeSv.deleteNotice(notice_no) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t\t게시물을 삭제하였습니다.");
			System.out.println("└―――――――――――━――――――――――――┘");

			return;
		}

		System.out.println();
		System.out.println("┌――――――――――――――――――――――――┐");
		System.out.println("\t\t게시물 삭제를 실패하였습니다.");
		System.out.println("└―――――――――――━――――――――――――┘");
		return;
	}

	/**
	 * 게시물 내용을 수정하기 위한 메서드
	 * 
	 * @author 신광진
	 * @param notice_no
	 */
	private void updateNoticeComment(List<NoticeVO> list) {

		String notice_no = selectNotice(list);
		String notice_comment = inputNoticeComment();

		Map<String, String> info = new HashMap<>();
		info.put("notice_no", notice_no);
		info.put("notice_comment", notice_comment);

		if (noticeSv.updateNoticeComment(info) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t게시물의 내용이 변경되었습니다.");
			System.out.println("└―――――――――――━――――――――――――┘");
			return;
		}

		System.out.println();
		System.out.println("┌――――――――――――――――――――――――┐");
		System.out.println("\t게시물 내용 수정을 실패하였습니다.");
		System.out.println("└―――――――――――━――――――――――――┘");
		return;

	}

	/**
	 * 게시물 내용을 입력받는 메서드
	 * 
	 * @author 신광진
	 */
	private String inputNoticeComment() {

		System.out.println();
		System.out.println("게시물의 내용을 입력하세요 : ");

		Scanner sc = new Scanner(System.in);
		String notice_comment = sc.nextLine();

		return notice_comment;
	}

	/**
	 * 게시물 제목을 변경하기 위한 메서드
	 * 
	 * @author 신광진
	 * @param notice_no
	 */
	private void updateNoticeTitle(List<NoticeVO> list) {

		String notice_no = selectNotice(list);
		String notice_title = inputNoticeTitle();

		Map<String, String> info = new HashMap<>();
		info.put("notice_no", notice_no);
		info.put("notice_title", notice_title);

		if (noticeSv.updateNoticeTitle(info) != 0) {
			System.out.println();
			System.out.println("┌―――――――――――――――――――――┐");
			System.out.println("\t공지사항의 제목이 변경되었습니다.");
			System.out.println("└―――――――――――━―――――――――┘");
			return;
		}
		System.out.println();
		System.out.println("┌――――――――――――――――――――――――┐");
		System.out.println("\t공지사항 제목 수정을 실패하였습니다.");
		System.out.println("└―――――――――――━――――――――――――┘");
		return;

	}

	/**
	 * 게시물 제목을 입력받는 메서드
	 * 
	 * @author 신광진
	 */
	private String inputNoticeTitle() {

		System.out.println();
		System.out.println("공지사항 제목을 입력하세요 : ");

		Scanner sc = new Scanner(System.in);
		String notice_title = sc.nextLine();

		return notice_title;
	}

	/**
	 * 게시물 수정, 삭제의 대상이 되는 게시물을 선택하기 위한 메서드
	 * 
	 * @author 신광진
	 * @param list
	 * @return notice_no
	 */
	private String selectNotice(List<NoticeVO> list) {

		String notice_no = null;

		while (true) {
			detailPrintNotice();
			System.out.println();
			System.out.print("공지사항 번호를 입력하세요 : ");

			Scanner sc = new Scanner(System.in);
			int cmd = -1;

			try {
				cmd = sc.nextInt();
				sc.nextLine();

				notice_no = list.get(cmd - 1).getNotice_no();
			} catch (InputMismatchException e) {
				msg();
				continue;
			} catch (IndexOutOfBoundsException e) {
				msg();
				continue;
			} catch (Exception e) {
				msg();
				continue;
			}

			break;
		}

		return notice_no;

	}
}