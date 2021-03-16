package y_project;

import java.util.Scanner;

public class View {

	public void startMethod() {
		System.out.println("어서오세요 영만마트에요");
		System.out.println("원하시는 메뉴를 선택해주세요");
		System.out.println("1. 물품추가");
		System.out.println("2. 로그인");
		System.out.println("3. 종료");
		Scanner sc = new Scanner(System.in);
		int select = sc.nextInt();
		
		switch(select){
		case 1:
			addProduct();
			break;
		case 2:
//			logIn();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("1,2,3중에 골라주세요");
		}
	}

	private void addProduct() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		// 이름, 가격
		System.out.println("물건의 이름을 입력해주세요");
		String name = sc.next();
		System.out.println("물건의 가격을 설정해주세요");
		int price = sc.nextInt();
		
		Service sv = new Service();  
		boolean result = sv.addProduct(name,price);
		if(result){
			System.out.println("물품 등록에 성공하였습니다.");
		}
	}

}
