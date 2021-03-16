package project;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class View {
	private IService sv = new IServiceImpl();
	private String logIn_id;
	
	public void startMethod(){
		while(true){
			try{
				System.out.println("----- 어서오세요 -----");
				System.out.println("1. 회원가입");
				System.out.println("2. 로그인");
				System.out.println("3. 끝내기");
				
				//고르기
				Scanner sc = new Scanner(System.in);
				System.out.print("입력 : ");
				int input = sc.nextInt();
				
				//메소드 이동
				switch(input){
				case 1:
					//회원가입
					insertMember();
					break;
				case 2:
					//로그인
					logIn();
					break;
				case 3:
					//종료
					System.out.println("시스템을 종료합니다");
					System.exit(0);
					break;
				default:
					System.out.println("제대로 입력하세요");
					break;
				}
			}catch(InputMismatchException e){
				System.out.println("숫자로 입력하세요");
			}
		}
	}
	
	private void insertMember() {
		System.out.println("----- 회원가입 -----");
		System.out.println("아이디를 입력해주세요");
		String mem_id = inputString();
		System.out.println("비밀번호를 입력해주세요");
		String mem_pass = inputString();
		System.out.println("이름을 입력해주세요");
		String mem_name = inputString();
		System.out.println("주민번호 앞자리를 입력해주세요");
		String mem_regno1 = inputString();
		System.out.println("주민번호 뒷자리를 입력해주세요");
		String mem_regno2 = inputString();
		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		params.put("mem_name", mem_name);		
		params.put("mem_regno1", mem_regno1);
		params.put("mem_regno2", mem_regno2);
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(mem_id);
		mv.setMem_pass(mem_pass);
		mv.setMem_name(mem_name);
		mv.setMem_regno1(mem_regno1);
		mv.setMem_regno2(mem_regno2);
		
		
		if(sv.insertMember(mv)){
			System.out.println("회원가입에 성공하였습니다.");
		}else{
			System.out.println("오류 : 회원가입에 실패하였습니다.");
		}
		return;
	}

	private String inputString() {
		while(true){
			try{
				Scanner sc = new Scanner(System.in);
				String text = sc.next();
				return text;
			}catch(InputMismatchException e){
				System.out.println("다시 입력해주세요");
			}
		}
	}

	private void logIn() {
		//id, pw
		Scanner sc = new Scanner(System.in);
		System.out.println("----- 로그인 -----");
		System.out.println("아이디를 입력해주세요");
		String mem_id = sc.next();
		System.out.println("비밀번호를 입력해주세요");
		String mem_pass = sc.next();
		
		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		logIn_id = sv.logIn(params);
		
		if(logIn_id == null){
			System.out.println("그런사람 없다");
		}else{
			System.out.println(logIn_id + "회원님 어서오세요");
			showMemList();
		}
	}

	private void showMemList() {
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				Map<Integer, String> params = sv.showMemList();
				if(params.isEmpty()){
					System.out.println("회원이 없습니다.");
					return;
				}
				System.out.println("회원을 선택해주세요");
				int select = sc.nextInt();
				String mem_id = params.get(select);
				
				configMember(mem_id);
			}catch(InputMismatchException e){
				e.printStackTrace();
				System.out.println("다시 입력해주세요");
			}catch(NullPointerException e){
				e.printStackTrace();
				System.out.println("다시 입력해주세요");
			}
		}
	}
 
	private void configMember(String mem_id) {
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				if(sv.infoMember(mem_id)){
					System.out.println("-----" + mem_id +" 회원님의 정보 -----");
					System.out.println("1. 정보수정");
					System.out.println("2. 회원탈퇴");
					System.out.println("3. 뒤로가기");
					
					System.out.print("입력 : ");
					int select = sc.nextInt();
					
					switch(select){
					case 1:
						updateMember(mem_id);
						break;
					case 2:
						deleteMember(mem_id);
						break;
					case 3:
						return;
					}
				}else{
					System.out.println("회원정보를 불러올 수 없습니다.");
					return;
				}
			}catch(InputMismatchException e){
				e.printStackTrace();
				System.out.println("다시 입력해주세요");
			}
		}
	}

	private void updateMember(String mem_id) {
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				System.out.println("----- " + mem_id + " 회원님의 정보 수정 -----");
				System.out.println("1. 비밀번호 수정하기");
				System.out.println("2. 이름 수정하기");
				System.out.println("3. 주민번호 수정하기");
				System.out.println("4. 뒤로가기");
				
				int select = sc.nextInt();
				switch(select){
				case 1:
					changePass(mem_id);
					break;
				case 2:
					changeName(mem_id);
					break;
				case 3:
					ChangeRegno(mem_id);
					break;
				case 4:
					return;
				default:
					System.out.println("다시 입력해주세요");
				}
			}catch(InputMismatchException e){
				System.out.println("다시 입력해 주세요");
			}
		}
	}

	private void ChangeRegno(String mem_id) {
		Scanner sc = new Scanner(System.in);
		Map<String, String> params = new HashMap<>();
		while(true){
			try{
				System.out.println("변경할 주민번호 앞자리 : ");
				String mem_regno1 = sc.next();
				System.out.println("변경할 주민번호 앞자리 : ");
				String mem_regno2 = sc.next();
				
				params.put("mem_id", mem_id);
				params.put("mem_regno1", mem_regno1);
				params.put("mem_regno2", mem_regno2);
				if(sv.changePass(params)){
					System.out.println("주민번호가 변경되었습니다.");
					return;
				}else{
					System.out.println("오류 : 변경에 실패하였습니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("다시 입력해 주세요");
			}
		}
	}

	private void changeName(String mem_id) {
		Scanner sc = new Scanner(System.in);
		Map<String, String> params = new HashMap<>();
		while(true){
			try{
				System.out.println("변경할 이름 : ");
				String mem_name = sc.next();
				params.put("mem_id", mem_id);
				params.put("mem_name", mem_name);
				if(sv.changeName(params)){
					System.out.println("이름이 변경되었습니다.");
					return;
				}else{
					System.out.println("오류 : 변경에 실패하였습니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("다시 입력해 주세요");
			}
		}
	}

	private void changePass(String mem_id) {
		Scanner sc = new Scanner(System.in);
		Map<String, String> params = new HashMap<>();
		while(true){
			try{
				System.out.println("변경할 비밀번호 : ");
				String mem_pass = sc.next();
				params.put("mem_id", mem_id);
				params.put("mem_pass", mem_pass);
				if(sv.changePass(params)){
					System.out.println("비밀번호가 변경되었습니다.");
					return;
				}else{
					System.out.println("오류 : 변경에 실패하였습니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("다시 입력해 주세요");
			}
		}
	}
	
	private void changeRegno(String mem_id) {
		Scanner sc = new Scanner(System.in);
		Map<String, String> params = new HashMap<>();
		while(true){
			try{
				System.out.println("변경할 주민번호 앞자리 : ");
				String mem_regno1 = sc.next();
				String mem_regno2 = sc.next();
				params.put("mem_id", mem_id);
				params.put("mem_regno1", mem_regno1);
				params.put("mem_regno2", mem_regno2);
				if(sv.changeRegno(params)){
					System.out.println("주민번호가 변경되었습니다.");
					return;
				}else{
					System.out.println("오류 : 변경에 실패하였습니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("다시 입력해 주세요");
			}
		}
	}

	private void deleteMember(String mem_id) {
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				System.out.println("정말 회원탈퇴하시겠습니까?");
				System.out.println("1. 네");
				System.out.println("2. 아니오");
				System.out.print("입력 : ");
				
				int select = sc.nextInt();
				if(select == 1){
					if(sv.deleteMember(mem_id)){
						System.out.println("탈퇴에 성공하셨습니다.");
					}else{
						System.out.println("오류 : 탈퇴에 실패하였습니다.");
					}
					return;
				}else if(select == 2){
					return;
				}else{
					System.out.println("다시 입력해 주세요");
				}
			}catch(InputMismatchException e){
				System.out.println("다시 입력해 주세요");
			}
		}
		
	}
	
	
}
