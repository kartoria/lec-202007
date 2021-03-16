package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import member.*;
import movie.*;
import notice.*;
import reserve.*;
import review.*;
import cinema.*;
import show.*;

public class ViewClass {

	private IMemberService memSv = IMemberServiceImpl.getInstance();
	private IMovieService movieSv = IMovieServiceImpl.getInstance();
	private INoticeService noticeSv = INoticeServiceImpl.getInstance();
	private IReviewService reviewSv = IReviewServiceImpl.getInstance();
	private ICinemaService screenSv = ICinemaServiceImpl.getInstance();
	private IShowService showSv = IShowServiceImpl.getInstance();
	private IReserveService reserveSv = IReserveServiceImpl.getInstance();
	private RegExClass reg = new RegExClass();
	private AdminView av = new AdminView();

	private String login_id;

	void run() {
		while (true) {
			System.out.println("┌――――――――――――――――――――――┐");
			System.out.println("  \t\tCGV에 오신걸 환영합니다!!     ");
			System.out.println("└――――――――――――――――――――━―┘");
			System.out.print("[ 1 ] 회원가입\t");
			System.out.print("[ 2 ] 로그인\t     ");
			System.out.println("[ 3 ] 나가기");
			System.out.println();
			
			System.out.print("메뉴 번호를 입력하세요 : ");
			Scanner sc = new Scanner(System.in);
			int select = sc.nextInt();

			switch (select) {
			case 1:
				createMember(); // 회원가입
				break;
			case 2:
				logIn(); // 로그인
				break;
			case 3:
				System.out.println();
				System.out.println("┌―――――――――――――――――┐");
				System.out.println("\t  이용해주셔서 감사합니다.");
				System.out.println("└―――――――――――――――――┘");
				; // 나가기
				return;
			default:
				msg();
				break;
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 신광진 //

	public static void msg() {
		System.out.println();
		System.out.println("┌―――――――― WARNING!! ――――――――┐");
		System.out.println("\t\t    정확히 입력해주세요.");
		System.out.println("└――――――――――――――――━―――――┘");
	}
	
	/**
	 * 회원가입 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-09
	 * @see
	 */
	private void createMember() {

		MemberVO newMember = new MemberVO();

		// mem_name
		String mem_name = inputName();
		newMember.setMem_name(mem_name);

		// mem_id
		String mem_id = inputId();
		newMember.setMem_id(mem_id);

		// mem_pass
		String mem_pass = inputPass();
		newMember.setMem_pass(mem_pass);

		// mem_add1
		String mem_add1 = inputAdd1();
		newMember.setMem_add1(mem_add1);

		// mem_add2
		String mem_add2 = inputAdd2();
		newMember.setMem_add2(mem_add2);

		// mem_regno1
		String mem_regno1 = inputRegno1();
		newMember.setMem_regno1(mem_regno1);

		// mem_regno2
		String mem_regno2 = inputRegno2();
		newMember.setMem_regno2(mem_regno2);

		// mem_hp
		String mem_hp = inputHp();
		newMember.setMem_hp(mem_hp);

		// mem_mail
		String mem_mail = inputMail();
		newMember.setMem_mail(mem_mail);

		// default
		newMember.setMem_mileage("0");
		newMember.setMem_delete("0");

		
		SimpleDateFormat format = new SimpleDateFormat ("yyyy년 MM월dd일");
		Date time = new Date();
		String time2 = format.format(time);
		
		
		Map<String, String> map = new HashMap<>();
		map.put("mail", mem_mail);
		map.put("name", mem_name);
		map.put("subject", mem_name + "님의 GGV 회원가입 소식을 알려드립니다.");
		map.put("text", "회원님의 가입 일시는 " + time2 + "입니다. 가입해주셔서 감사합니다.");
		
		if (memSv.createMember(newMember) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――――――┐");
			System.out.println("\t  "+mem_name + " 고객님 회원가입을 축하드립니다.");
			System.out.println("└――――――――――――――――━―――――――┘");
			CraftEmail.mailSend(map);
			return;
		}
		System.out.println();
		System.out.println("┌――――――――――――――――――――┐");
		System.out.println("\t회원가입을 실패하였습니다.");
		System.out.println("└―――――――――━――――――――――┘");

	}

	/**
	 * 회원의 이름을 입력하는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_name
	 */
	private String inputName() {
		System.out.println();
		
		String mem_name = null;
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("┌――――――――――――――┐");
			System.out.println("\t이름을 입력해주세요");
			System.out.println("└―――――――――━――――┘");
			System.out.print("이름(한글) : ");

			try {
				mem_name = sc.next();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			if (reg.checkName(mem_name)) {
				break;
			} else {
				msg();
			}
		}
		return mem_name;
	}

	/**
	 * 회원의 이메일을 입력하는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_mail
	 */
	private String inputMail() {

		String mem_mail = null;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("┌――――――――――――――┐");
			System.out.println("\t이메일을 입력해주세요");
			System.out.println("└―――――――――━――――┘");
			System.out.print("이메일 : ");
			
			try {
				mem_mail = sc.next();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			if (reg.checkEmail(mem_mail)) {
				break;
			} else {
				msg();
			}

		}

		return mem_mail;
	}

	/**
	 * 회원의 핸드폰 번호를 입력하는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_hp
	 */
	private String inputHp() {

		String mem_hp = null;
		Scanner sc = new Scanner(System.in);
		while (true) {

			try {
				System.out.println();
				System.out.println("┌――――――――――――――――┐");
				System.out.println("\t핸드폰 번호를 입력해주세요");
				System.out.println("\tex)010-XXXX-XXXX");
				System.out.println("└――――――――――――――――┘");
				System.out.print("전화번호 : ");
				
				mem_hp = sc.next();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			if (reg.checkHp(mem_hp)) {
				break;
			} else {
				msg();
			}
		}
		return mem_hp;
	}

	/**
	 * 회원의 주민등록번호 뒷자리를 입력하는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_regno2
	 */
	private String inputRegno2() {

		String mem_regno2 = null;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――┐");
			System.out.println("\t주민등록번호 앞자리를 입력해주세요");
			System.out.println("└―――――――――━――――――――――┘");
			System.out.print("주민등록번호 앞자리 : ");
			
			try {
				mem_regno2 = sc.next();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			if (reg.checkRegno2(mem_regno2)) {
				break;
			} else {
				msg();
			}
		}

		return mem_regno2;
	}

	/**
	 * 회원의 주민등록번호 앞자리를 입력하는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_regno1
	 */
	private String inputRegno1() {

		String mem_regno1 = null;
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――┐");
			System.out.println("\t주민등록번호 앞자리를 입력해주세요");
			System.out.println("└―――――――――━――――――――――┘");
			System.out.print("주민등록번호 앞자리 : ");

			try {
				mem_regno1 = sc.next();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			if (reg.checkRegno1(mem_regno1)) {
				break;
			} else {
				msg();
			}
		}

		return mem_regno1;
	}

	/**
	 * 회원의 상세주소를 입력받는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_add2
	 */
	private String inputAdd2() {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("┌――――――――――――――――┐");
		System.out.println("\t상세주소를 입력해주세요");
		System.out.println("└―――――――――━――――――┘");
		System.out.print("상세주소 : ");

		String mem_add2 = sc.nextLine();

		return mem_add2;
	}

	/**
	 * 회원의 주소를 입력받는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_add1
	 */
	private String inputAdd1() {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println("\t주소(시, 군, 구)를 입력해주세요");
		System.out.println("└―――――――――━―――――――――┘");
		System.out.print("주소 : ");

		String mem_add1 = sc.nextLine();

		return mem_add1;
	}

	/**
	 * 회원의 비밀번호를 입력하는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_pass
	 */
	private String inputPass() {

		Scanner sc = new Scanner(System.in);
		String mem_pass = null;

		while (true) {
			try {
				System.out.println();
				System.out.println("┌――――――――――――――――――――――――――┐");
				System.out.println("\t\t    비밀번호를 입력해주세요.");
				System.out.println("\t  <영어만 가능하며 특수문자 포함 4~11글자>");
				System.out.println("└―――――――――――――――━――――――――――┘");
				System.out.print("비밀번호 : ");

				mem_pass = sc.next();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			if (reg.checkPw(mem_pass)) {
				break;
			} else {
				msg();
			}

		}
		return mem_pass;
	}

	/**
	 * 회원의 아이디를 입력받는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 * @return mem_id
	 */
	private String inputId() {

		Scanner sc = new Scanner(System.in);
		String mem_id = null;

		while (true) {

			try {
				System.out.println();
				System.out.println("┌――――――――――――――――――――――――――┐");
				System.out.println("\t\t       아이디를 입력해주세요.");
				System.out.println("\t  <영어만 가능하며 특수문자 포함 5~15글자>");
				System.out.println("└―――――――――――――――━――――――――――┘");
				System.out.print("아이디 : ");

				mem_id = sc.next();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			if (checkDupId(mem_id) != null) {
				System.out.println();
				System.out.println("┌――――――――――――――――――――┐");
				System.out.println("\t이미 사용중인 아이디입니다.");
				System.out.println("└―――――――――━――――――――――┘");
				continue;
			}

			if (reg.checkId(mem_id)) {
				break;
			} else {
				msg();
			}

		}
		return mem_id;
	}

	/**
	 * 회원가입시 중복되는 아이디가 있는지 조회하는 메서드
	 * 
	 * @author 신광진
	 * @param mem_id
	 * @since 2020-09-08
	 * @return
	 */
	private String checkDupId(String mem_id) {

		return memSv.checkDupId(mem_id);
	}

	/**
	 * 모든회원의 정보를 조회하는 메서드
	 * 
	 * @author 신광진
	 * @since 2020-09-08
	 */
	private void printAllMember() {

		List<MemberVO> memberList = memSv.readAllMember();

		for (MemberVO mem : memberList) {
			System.out.print(mem.getMem_add1() + mem.getMem_add2());
			System.out.println();
		}
	}

	/**
	 * 회원 로그인 메서드
	 * @author 신광진
	 */
	private void logIn() {

		while (true) {

			System.out.println();
			System.out.println("┌―――――――――――――――――――――┐");
			System.out.println("  \t\t로그인 선택화면입니다.     ");
			System.out.println("└―――――――――――――――――――━―┘");
			System.out.print("[ 1 ] 관리자\t\t");
			System.out.print("[ 2 ] 회원\t     ");
			System.out.println("[ 3 ] 뒤로가기");
			System.out.println();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("메뉴 번호를 선택하세요 : ");
			
			int cmd = -1;

			try {
				cmd = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				msg();
				continue;
			}

			switch (cmd) {

			case 1:
				adminLogin();
				break;

			case 2:
				memberLogin();
				break;

			default:
				return;
			}
		}
	}


	private void adminLogin() {
		av.adminList();
	}

	private void memberLogin() {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("┌―――――――――――――――┐");
		System.out.println("\t아이디를 입력해주세요.");
		System.out.println("└―――――――――━―――――┘");
		System.out.print("아이디 : ");
		String mem_id = sc.next();

		System.out.println();
		System.out.println("┌――――――――――――――――┐");
		System.out.println("\t비밀번호를 입력해주세요.");
		System.out.println("└―――――――――━――――――┘");
		System.out.print("비밀번호 : ");
		String mem_pass = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);

		login_id = memSv.logIn(params);

		if (login_id == null) {
			System.out.println();
			System.out.println("┌―――――――――――――――――――――┐");
			System.out.println("\t\t로그인을 실패하였습니다.");
			System.out.println("└―――――――――――――――――――━―┘");
		} else {
			pageMember();
		}

	}

	void pageMember() {
		while (true) {
			try {
				System.out.println();
				System.out.println("┌―――――――――――――――――――――――――┐");
				System.out.println("\t\t" + login_id + "회원님 환영합니다.");
				System.out.println("└―――――――――━―――――――――――――――┘");
				System.out.print("[ 1 ] 영화 예매하기");
				System.out.print("\t   [ 2 ] 내 정보 조회하기\t");
				System.out.println("   [ 3 ] 영화 리뷰");
				System.out.print("[ 4 ] 회원 탈퇴하기\t");
				System.out.println("   [ 5 ] 로그아웃");

				System.out.println();
				System.out.print("메뉴 번호를 입력하세요 : ");
				Scanner sc = new Scanner(System.in);
				int select = sc.nextInt();

				switch (select) {
				case 1:
					// 1. 영화 예매하기
					pageReserve();
					break;
				case 2:
					// 3. 내 정보 조회하기
					pageMyInfo();
					break;
				case 3:
					// 4. 영화 리뷰보기
					pageReview();
					break;

				case 4:
					deleteMember();
					return;
				case 5:
					return; // 로그아웃
				default:
					msg();
					break;
				}
			} catch (InputMismatchException e) {
				msg();
			}
		}
	}

	private void pageReserve() {
		while (true) {
			try {
				// 1. 상영영화 출력메소드
				Map<Integer, String> map = showSv.screeningMovieName();

				System.out.print("예매할 영화번호를 입력하세요 (뒤로가기는 0번 입니다) : ");
				Scanner sc = new Scanner(System.in);
				int select = sc.nextInt();

				if (select == 0) {
					return;
				}else if(select < map.size()+1 && select > 0){
					String movie_name = map.get(select);
					reserveSv.selectShow(movie_name, login_id);
				}else{
					msg();
				}
			} catch (InputMismatchException e) {
				msg();
			}
		}
	}

	private void pageMyInfo() {

		while (true) {

			try {

				MemberVO member = memSv.readMemInfo(login_id);
				int cmd = -1;
				
				System.out.println();
				System.out.println("┌―――――――――――――――――――――――――┐");
				System.out.println("\t\t회원이름 : " + member.getMem_name() + "( "+ login_id + " )");
				System.out.println("\t\t핸드폰 번호 : " + member.getMem_hp());
				System.out.println("\t\t주민등록번호 : " + member.getMem_regno1() + "-"
						+ member.getMem_regno2());
				System.out.println("\t\t주소지 : " + member.getMem_add1());
				System.out.println("\t\t상세주소 : " + member.getMem_add2());
				System.out.println("\t\t이메일 : " + member.getMem_mail());
				System.out.println("\t\t마일리지 : " + member.getMem_mileage()
						+ " Point");
				System.out.println("└―――――――――━―――――――――――――――┘");
				
				System.out.print("[ 1 ] 내 정보 수정      ");
				System.out.print("\t[ 2 ] 예약조회\t     ");
				System.out.println("[ 3 ] 뒤로가기");
				System.out.println();


				System.out.print("번호를 입력해 이동 : ");

				Scanner sc = new Scanner(System.in);
				int select = sc.nextInt();
				sc.nextLine();

				switch (select) {
				case 1:
					updateMeminfo();
					break;
				case 2:
					// 예약조회
				default:
					return; // 뒤로가기
				}
			} catch (InputMismatchException e) {
				Scanner sc = new Scanner(System.in);
				System.out.println();
				msg();
			}
		}
	}

	private void deleteMember() {
		
		System.out.println("┌――――――――――――――――――――┐");
		System.out.println("\t정말 회원탈퇴를 하시겠습니까? (Y/N) ");
		System.out.println("└―――――――――━――――――――――┘");
		System.out.print("입력 : ");
		
		Scanner sc = new Scanner(System.in);
		String ans = sc.next();
		sc.nextLine();

		if (ans.toUpperCase().equals("Y")) {
			if (memSv.deleteMember(login_id) != 0) {
				System.out.println("┌―――――――――――――――――――――――┐");
				System.out.println("\t\t회원탈퇴가 완료되었습니다.");
				System.out.println("└――――――――――――━――――――――――┘");

				return;
			}
		} else {
			System.out.println("┌―――――――――――――――――――――――┐");
			System.out.println("\t\t항상 최선을 다하겠습니다!");
			System.out.println("└――――――――――――━――――――――――┘");
			return;
		}
	}

	private void updateMeminfo() {

		int cmd = -1;

		while (true) {
			System.out.println("┌―――――――――――――――――――┐");
			System.out.println("\t\t개인정보 수정하기");
			System.out.println("└―――――――――━―――――――――┘");
			System.out.print("[ 1 ] 핸드폰 번호");
			System.out.print("\t[ 2 ] 주소");
			System.out.println("\t[ 3 ] 이메일");
			System.out.print("[ 4 ] 비밀번호");
			System.out.print("\t[ 5 ] 이름");
			System.out.println("\t[ 6 ] 뒤로가기");
			System.out.println();
			
			System.out.print("메뉴 번호를 입력하세요 : ");
			Scanner sc = new Scanner(System.in);
			cmd = sc.nextInt();
			sc.nextLine();

			switch (cmd) {
			case 1:
				updateMemHp();
				break;

			case 2:
				updateMemAdd();
				break;

			case 3:
				updateMemMail();
				break;

			case 4:
				updateMemPass();
				break;
				
			case 5:
				updateMemName();
				break;
				
			case 6:
				return;
				
			default:
				msg();
			}
		}

	}

	/**
	 * 회원의 이름을 변경하는 메서드
	 * @author 신광진
	 */
	private void updateMemName() {

		String mem_name = inputName();
		
		Map<String, String> info = new HashMap<>();
		info.put("mem_id", login_id);
		info.put("mem_name", mem_name);
		
		if( memSv.updateMemName(info) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――┐");
			System.out.println("\t회원님의 이름이 변경되었습니다.");
			System.out.println("└―――――――――━――――――――――┘");

			return;
		}
		System.out.println();
		System.out.println("┌――――――――――――――――――――┐");
		System.out.println("\t이름 변경에 실패하였습니다.");
		System.out.println("└―――――――――━――――――――――┘");

		return;
	}

	/**
	 * 회원의 비밀번호를 변경하는 메서드
	 * @author 신광진
	 */
	private void updateMemPass() {
		
		String mem_pass = inputPass();
		
		Map<String, String> info = new HashMap<>();
		info.put("mem_id", login_id);
		info.put("mem_pass", mem_pass);
		
		if( memSv.updateMemPw(info) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――┐");
			System.out.println("\t회원님의 비밀번호가 변경되었습니다.");
			System.out.println("└―――――――――━――――――――――┘");
			return;
		} 
		System.out.println();
		System.out.println("┌――――――――――――――――――――┐");
		System.out.println("\t비밀번호 변경에 실패하였습니다.");
		System.out.println("└―――――――――━――――――――――┘");		
		return;
		
	}

	private void updateMemMail() {

		String new_mail = inputMail();

		Map<String, String> info = new HashMap<>();
		info.put("mem_id", login_id);
		info.put("new_mail", new_mail);

		if (memSv.updateMemMail(info) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――┐");
			System.out.println("\t회원님의 메일이 변경되었습니다.");
			System.out.println("└―――――――――━――――――――――┘");
			return;
		}
		
		System.out.println();
		System.out.println("┌――――――――――――――――――――┐");
		System.out.println("\t이메일 변경에 실패하였습니다.");
		System.out.println("└―――――――――━――――――――――┘");	
		return;
	}

	/**
	 * 회원의 주소를 수정하는 메서드
	 * 
	 * @author 신광진
	 */
	private void updateMemAdd() {

		String new_add1 = inputAdd1();
		String new_add2 = inputAdd2();

		Map<String, String> info = new HashMap<>();
		info.put("new_add1", new_add1);
		info.put("new_add2", new_add2);
		info.put("mem_id", login_id);

		if (memSv.updateMemAdd(info) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――┐");
			System.out.println("\t회원님의 주소가 변경되었습니다.");
			System.out.println("└―――――――――━――――――――――┘");
			return;
		}
		
		System.out.println();
		System.out.println("┌――――――――――――――――――――┐");
		System.out.println("\t주소변경에 실패하였습니다.");
		System.out.println("└―――――――――━――――――――――┘");	
		return;
	}

	/**
	 * 회원의 전화번호를 수정하는 메서드
	 * 
	 * @param newHp
	 */
	private void updateMemHp() {

		String new_hp = inputHp();

		Map<String, String> info = new HashMap<>();
		info.put("mem_id", login_id);
		info.put("new_hp", new_hp);

		if (memSv.updateMemHp(info) != 0) {
			System.out.println();
			System.out.println("┌――――――――――――――――――――┐");
			System.out.println("\t회원님의 전화번호가 변경되었습니다.");
			System.out.println("└―――――――――━――――――――――┘");
			return;
		}

		System.out.println();
		System.out.println("┌――――――――――――――――――――┐");
		System.out.println("\t전화번호 변경에 실패하였습니다.");
		System.out.println("└―――――――――━――――――――――┘");	
		return;

	}
	///////////////////////////////신광진 끝부분
	
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------김------------------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------선----------------------------------------------------------------------------------------------------------------------------------------------------
	// -----------------------------준--------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// 리뷰페이지
	private void pageReview() {
		while (true) {
			try {
				System.out.println();
				System.out.println();
				System.out.println("┌―――――――――――――――――――――――――┐");
				System.out.println("  \t\t\t  회원 리뷰페이지    ");
				System.out.println("└―――――――――――――――――――――――━―┘");
				System.out.print("[ 1 ] 영화 리뷰 보기\t");
				System.out.print("[ 2 ] 리뷰 작성\t  ");
				System.out.println("[ 3 ] 뒤로가기");
				System.out.println();

				System.out.print("메뉴 번호를 입력하세요 : ");
				Scanner sc = new Scanner(System.in);
				int select = sc.nextInt();

				switch (select) {
				case 1:
					// 리뷰 보는 페이지
					pageSeeReview();
					break;
				case 2:
					// 리뷰 작성하는 페이지
					pageWriteReview();
					break;
				default:
					return; // 뒤로가기
				}
			} catch (InputMismatchException e) {
				Scanner sc = new Scanner(System.in);
				msg();
			}
		}
	}

	// 리뷰 작성하는 페이지
	private void pageWriteReview() {
		while (true) {
			try {
				System.out.println("내가 예매했던 영화 목록");
				Map<String, String> map = reserveSv.readMyReserve(login_id);
				int count = Integer.parseInt(map.get("count"));
				for(int i = 1 ; i<count+1; i++){
					System.out.print(map.get("movie_id" + Integer.toString(i)) + ". ");
					System.out.println(map.get("movie_name" + Integer.toString(i)));
				}
				System.out.println("0. 나가기");

				Scanner sc = new Scanner(System.in);
				System.out.print("영화를 선택해 리뷰를 작성할 수 있습니다.");
				int select = sc.nextInt();

				if (select == 0) {
					return;
				}else if(select > 0){
					Map<String, String> writeReviewMap = new HashMap<>();
					writeReviewMap.put("movie_id", map.get("movie_id"+select));
					writeReviewMap.put("movie_name", map.get("movie_name"+select)); 
					writeReviewMap.put("mem_id", login_id);
					String res_no = reserveSv.readResNo(writeReviewMap); //예약번호
					writeReviewMap.put("res_no", res_no);
					String reviewTitle  = reviewSv.writeReviewTitle();
					writeReviewMap.put("title", reviewTitle);
					String reviewContent = reviewSv.writeReviewContent();
					writeReviewMap.put("content", reviewContent);
					
					if(reviewSv.writeReview(writeReviewMap)){
						System.out.println("리뷰가 작성되었습니다.");
						return;
					}
				}else{
					System.out.println("제대로 입력하세요");
				}
			} catch (InputMismatchException e) {
				Scanner sc = new Scanner(System.in);
				System.out.println("제대로 입력하세요");
			}
		}
	}

	private void pageSeeReview() {
		while (true) {
			try {
				Map<Integer, String> movieNameList = movieSv.printMovieName();

				Scanner sc = new Scanner(System.in);
				System.out.println();
				System.out.println("┌――――――――――――――――――――┐");
				System.out.println("\t\t리뷰 페이지");
				System.out.println("\t\t뒤로가기는 0번 입니다.");
				System.out.println("└―――――――――━――――――――――┘");

				System.out.print("리뷰를 볼 영화를 선택하세요 : ");
				int select = sc.nextInt();

				if (select > 0 && select < movieNameList.size() + 1) {
					if (reviewSv.selectMovieReview(movieNameList.get(select)) == false) { // 해당
																							// 영화의
																							// 리뷰를
																							// 출력
						System.out.println("리뷰를 불러올 수 없습니다.");
					}
				} else if (select == 0) {
					return;
				} else {
					msg();
				}
			} catch (InputMismatchException e) {
				Scanner sc = new Scanner(System.in);
				msg();
			}

		}
	}
}
