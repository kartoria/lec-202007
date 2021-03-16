package reserve;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import common.ViewClass;
import show.IShowService;
import show.IShowServiceImpl;

public class IReserveServiceImpl implements IReserveService{
	
	private static IReserveService ReserveSv;
	private IReserveDao ReserveDao;
	private IShowService showSv = IShowServiceImpl.getInstance();
	
	private IReserveServiceImpl(){
		ReserveDao = IReserveDaoImpl.getInstance();
	}
	
	public static IReserveService getInstance(){
		if(ReserveSv == null){
			ReserveSv = new IReserveServiceImpl();
		}
		return ReserveSv; 
	}
	
	// 영화 시간 고르기
	@Override
	public void selectShow(String movie_name, String login_id) {
		while(true){
			try{
				Map<String, String> map = showSv.readMovieTimeList(movie_name);
				String seat_number = map.get("seat_number");
				
				System.out.println();
				System.out.println("┌――――――――――――――――――――――――┐");
				System.out.println("\t\t\t상영시간 선택");
				System.out.println("└――――――――――――――――――――――――┘");
				int count = Integer.parseInt(map.get("count"));
				for(int i = 1; i < count+1; i++){
					System.out.print("[ " + i +" ] "+ map.get("show_start_time" + i) + "\t\t");
					if(i%3 == 0) {
						System.out.println();
					}
				}
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println();
				System.out.println();
				System.out.print("상영시간 번호를 입력하세요 (뒤로가기는 0번 입니다) : ");
				int select = sc.nextInt();
				sc.nextLine();
				if(select == 0){
					return;
				}else if(select > 0 && select < count+1 ){
					Map<String, String> showInfoMap = new HashMap<>();
					showInfoMap.put("seat_number", seat_number);
					showInfoMap.put("show_start_time", map.get("show_start_time" + select));
					showInfoMap.put("cinema_name", map.get("cinema_name"));
					showInfoMap.put("cinema_id" , map.get("cinema_id"));
					showInfoMap.put("login_id", login_id);
					showInfoMap.put("show_id", map.get("show_id" + select));
					System.out.print("예약할 인원 수 를 입력해 주세요 : ");
					int Guest = sc.nextInt();
					for(int i = 0; i<Guest; i++){
						selectSeat(showInfoMap);
					}
					System.out.println("예매가 완료되었습니다");
					return;
				}else{
					System.out.println("제대로 입력하세요");
				}
			}catch(InputMismatchException e){
				System.out.println("제대로 입력하세요");
			}
		}
	}

	
	//영화 좌석 고르기
	private void selectSeat(Map<String, String> showInfoMap) {
		while(true){
			try{
				Scanner sc = new Scanner(System.in);

				System.out.println("┌――――――――――――――――――――――――――――┐");
				System.out.println("\t\t\t" + "    좌석 정보 (" + showInfoMap.get("show_start_time") + ")");
				System.out.println("└――――――――――――━―――――――――――――――┘");
				printSeat(showInfoMap);
				
				System.out.println();
				System.out.print("좌석을 선택하세요 (뒤로가기는 0번 입니다) : ");
				int select = sc.nextInt();
				int seat_number = Integer.parseInt(showInfoMap.get("seat_number"));
				String mem_id = showInfoMap.get("login_id");
				String show_id = showInfoMap.get("show_id");
				
				//회원이 좌석을 선택 -> seat_no랑 cinema_id를 Map으로 가지고 Dao로 감
				//-> 예약테이블에서 seat_no와 cinema_id가 일치하는 좌석이 있으면 -> 이미 예약된 테이블이다
				//-> 다시선택하게 만듬
				
				if(select == 0){
					return;
				}else if(select > 0 && seat_number >= select){//총좌석수
					Map<String, String> map = new HashMap<>();
					map.put("mem_id", mem_id);
					map.put("seat_no", Integer.toString(select));
					map.put("cinema_id", showInfoMap.get("cinema_id"));
					map.put("show_id", showInfoMap.get("show_id"));
					if(createReserve(map)){
						return;
					}
					System.out.println();
					System.out.println("이미 예약된 좌석입니다.");
				}
				
			}catch(InputMismatchException e){
				ViewClass.msg();
			}
		}
	}

	//예매하기
	private boolean createReserve(Map<String, String> map) {
		return ReserveDao.createReserve(map);
	}

	private void printSeat(Map<String, String> showInfoMap) {
		int seat_num = Integer.parseInt(showInfoMap.get("seat_number"));
		int cinema_id = Integer.parseInt(showInfoMap.get("cinema_id"));
		int show_id = Integer.parseInt(showInfoMap.get("show_id"));
		
		//cinema_id가 있어야함
		//cinema_id를 가지고 예약테이블을 조회해서 해당 cinema_id와 일치하는 좌석번호를 int[] 에 담아온다.
		int[] resSeat = readResSeat(cinema_id, show_id);
		boolean flag = true;
		
		for(int i = 1; i<seat_num+1; i++){
			if(flag) {
				for(int k=0; k<resSeat.length; k++) {
					if(i==resSeat[k]) {
						System.out.print("[■]\t");
						flag = false;
						break;
					}
				}
			}
			
			if(flag) {
				if(i < 10){
					System.out.print("[0"+ i + "]\t");
					
				}else{
					System.out.print("["+ i + "]\t");
				}
				
				if(i%10 == 0){
					System.out.println("");
				}
			}
			
			flag = true;
		}
	}

	//좌석예약정보 읽어옴
	private int[] readResSeat(int cinema_id, int show_id) {
		return ReserveDao.readResSeat(cinema_id, show_id);
	}

	@Override
	public Map<String, String> readMyReserve(String login_id) {
		return ReserveDao.readMyReserve(login_id);
	}

	@Override
	public String readResNo(Map<String, String> writeReviewMap) {
		return ReserveDao.readResNO(writeReviewMap);
	}

	@Override
	public List<ReserveVO> readAllReserve() {
		// TODO Auto-generated method stub
		return ReserveDao.readAllReserve();
	}
}
