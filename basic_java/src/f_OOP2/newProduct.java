package f_OOP2;

import java.util.Scanner;
import java.util.Vector;

public class newProduct {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View view = new View();
		view.start();
	}
}
class View{
	Scanner sc = new Scanner(System.in);
	Product pd = new Product();
	Member mb = new Member();
	void start(){
		mb.setBuyer();
		pd.setProduct();
		while(true){
			int input = 0;
			System.out.println("1. 고객관리");
			System.out.println("2. 재고관리");
			System.out.println("3. 상품조회");
			System.out.print("번호 입력해서 이동 : ");
			input = sc.nextInt();
			switch (input) {
			case 1:
				mb.managementBuyer();
				break;
			case 2:
				pd.managementStock();
				break;
			case 3:
				showItem();
				break;
			default:
				System.out.println("※다시 입력하세요");
			}
		}
	}

	void showItem(){
		pd.checkStock();
		System.out.println("1. 상품구입");
		System.out.println("2. 상품환불");
		System.out.println("3. 영수증출력");
		System.out.println("4. 뒤로가기");
		System.out.print("번호 입력해서 이동 : ");
		int move = sc.nextInt();
		switch (move) {
		case 1: // 상품구입
			buyItem();
			showItem();
			break;
		case 2: // 상품환불
			refundItem();
			showItem();
			break;
		case 3: //영수증출력
			outputSummary();
			showItem();
			break;
		case 4:
			break;
		default:
			System.out.println("※다시 입력하세요");
			showItem();
		}
	}
	void buyItem(){
		mb.checkBuyer();
		System.out.print("구매하시는 고객님의 번호를 선택 : ");
		int buyerNum = sc.nextInt();
		pd.checkStock();
		System.out.print("구입할 상품의 번호를 선택 : ");
		int itemNum = sc.nextInt();
		System.out.print("구입할 수량을 선택 : ");
		int buyNum = sc.nextInt();
		if(pd.item.get(itemNum) instanceof Product 
				&& mb.buyer.get(buyerNum) instanceof Buyer){
			Buyer b = (Buyer)mb.buyer.get(buyerNum);
			Product p = (Product)pd.item.get(itemNum);
			if(buyNum > p.stock){
				System.out.println("※오류 - 구입할 수량이 재고 수량보다 많습니다");
			}else{
				b.buy(p, buyNum);
			}
		}
	}
	void refundItem(){
		mb.checkBuyer();
		System.out.print("환불하시는 고객님의 번호를 선택 : ");
		int buyerNum = sc.nextInt();
		pd.checkStock();
		System.out.print("환불할 상품의 번호를 선택 : ");
		int itemNum = sc.nextInt();
		System.out.print("환불할 수량을 선택 : ");
		int refundNum = sc.nextInt();
		if(pd.item.get(itemNum) instanceof Product 
				&& mb.buyer.get(buyerNum) instanceof Buyer){
			Buyer b = (Buyer)mb.buyer.get(buyerNum);
			Product p = (Product)pd.item.get(itemNum);
			b.refund(p, refundNum);
		}
	}
	void outputSummary(){
		mb.checkBuyer();
		System.out.print("영수증을 출력할 고객님의 번호를 선택 : ");
		int buyerNum = sc.nextInt();
		if(mb.buyer.get(buyerNum) instanceof Buyer){
			Buyer b = (Buyer)mb.buyer.get(buyerNum);
		b.summary(b);
		}
	}
}

class Product{
	Scanner sc = new Scanner(System.in);
	String name;
	int price;
	int stock;
	int count;
	int mileage;
	Vector item = new Vector();
	Product(){
		
	}
	
	public Product(String name, int price, int stock){
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.mileage = price/10;
	}
	
	void managementStock(){ //재고관리 페이지
		checkStock();
		System.out.println("1. 재고추가");
		System.out.println("2. 재고폐기");
		System.out.println("3. 뒤로가기");
		System.out.print("번호 입력해서 이동 : ");
		int move = sc.nextInt();
		switch (move) {
		case 1: // 재고추가
			addStock();
			managementStock();
			break;
		case 2: // 재고폐기
			removeStock();
			managementStock();
			break;
		case 3:
			break;
		default:
			System.out.println("※다시 입력하세요");
			managementStock();
		}
	}
	
	
	void addStock() { //재고 추가
		System.out.print("재고를 추가시킬 상품 번호 선택 :");
		int itemNum = sc.nextInt();
		System.out.print("추가할 수량 입력 :");
		int stockNum = sc.nextInt();
		if(item.get(itemNum) instanceof Product){
			Product pd = (Product)item.get(itemNum);
			pd.stock += stockNum;
		}else{
			System.out.println("※ 오류 : 추가할 수 없습니다.");
		}
	}

	void checkStock() { // 재고 확인
		if(item.isEmpty()){
			System.out.println("※등록된 상품이 없습니다");
			return;
		}
		System.out.println("\n 상품명\t\t\t가격\t\t보유재고");
		System.out.println("───────────────────────────────────────────────────");
		for(int i=0; i<item.size(); i++){
			if(item.get(i) instanceof Product){
				Product pd = (Product)item.get(i);
				System.out.print(i + ". ");
				System.out.print(pd.name+"\t\t");
				System.out.print(pd.price+"만원\t\t");
				System.out.println(pd.stock+"개");
			}
		}
		System.out.println("───────────────────────────────────────────────────");
	}
	void removeStock(){ // 재고 폐기
		System.out.print("재고를 폐기시킬 상품 번호 선택 :");
		int itemNum = sc.nextInt();
		System.out.print("폐기할 수량 입력 :");
		int stockNum = sc.nextInt();
		if(item.get(itemNum) instanceof Product){
			Product pd = (Product)item.get(itemNum);
			if(pd.stock < stockNum){
				System.out.println("※ 오류 : 폐기할 수량이 보유 재고보다 많습니다.");
			}else{
				pd.stock -= stockNum;
			}
		}else{
			System.out.println("※ 오류 : 추가할 수 없습니다.");
		}
	}

	void setProduct(){
		Product nb = new Notebook("노트북\t", 100, 3);
		Product st = new Styler("스타일러", 200, 1);
		Product ph = new Phone("스마트폰", 300, 5);
		item.add(nb);
		item.add(st);
		item.add(ph);
	}
}


class Member{
	Scanner sc = new Scanner(System.in);
	Vector buyer = new Vector();
	
	void managementBuyer(){ //고객관리 페이지
		checkBuyer();
		System.out.println("1. 고객추가");
		System.out.println("2. 고객삭제");
		System.out.println("3. 뒤로가기");
		System.out.print("번호 입력해서 이동 : ");
		int move = sc.nextInt();
		switch (move) {
		case 1: // 고객추가
			addBuyer();
			managementBuyer();
			break;
		case 2: // 고객삭제
			removeBuyer();
			managementBuyer();
			break;
		case 3:
			break;
		default:
			System.out.println("※다시 입력하세요");
			managementBuyer();
		}
	}
	void setBuyer(){ // 초기 고객
		Buyer lee = new Buyer("이재용", 2500);
		Buyer jung = new Buyer("정용진", 1500);
		buyer.add(lee);
		buyer.add(jung);
	}
	void addBuyer(){ // 고객추가
		System.out.print("고객이름 입력 : ");
		String inputName = sc.next();
		System.out.print("보유금액 입력 : ");
		int inputMoney = sc.nextInt();
		Buyer b = new Buyer(inputName, inputMoney);
		buyer.add(b);
		System.out.println(inputName + "고객이 추가되었습니다.");
	}
	
	void removeBuyer(){ // 고객삭제
		if(buyer.isEmpty()){
			System.out.println("※등록된 고객이 없습니다");
			return;
		}
		checkBuyer();
		System.out.print("삭제할 고객의 번호를 입력 : ");
		int inputNum = sc.nextInt();
		if(inputNum > buyer.size()){
			removeBuyer();
		}else{
			buyer.remove(inputNum);
			System.out.println(inputNum + "번 고객님이 삭제되었습니다.");
			return;
		}
	}
	
	void checkBuyer(){ // 고객리스트
		if(buyer.isEmpty()){
			System.out.println("※등록된 고객이 없습니다");
			return;
		}
		System.out.println("\n 고객명\t보유금액\t보유마일리지");
		System.out.println("──────────────────────────");
		for(int i=0; i<buyer.size(); i++){
			if(buyer.get(i) instanceof Buyer){
				Buyer b = (Buyer)buyer.get(i);
				System.out.print(i + ". ");
				System.out.print(b.name+"\t");
				System.out.print(b.money+"만원\t");
				System.out.println(b.mileage+"만포인트");
			}
		}
		System.out.println("──────────────────────────");
	}
}

class Notebook extends Product{
	public Notebook(String name, int price, int stock){
		super(name, price, stock);
	}
}

class Styler extends Product{
	public Styler(String name, int price, int stock){
		super(name, price, stock);
	}
}
class Phone extends Product{
	public Phone(String name, int price, int stock){
		super(name, price, stock);
	}
}

class Buyer{
	String name;
	int money;
	int sumPrice;
	int mileage;
	Vector buyItem = new Vector();
	
	public Buyer(){
		
	}
	public Buyer(String name, int money){
		this.name = name;
		this.money = money;
	}
	
	void buy(Product pd, int count){
		boolean itemCheck = true; // 벡터에 해당 물건이 없는지 체크용
		for(int i = 0; i<buyItem.size(); i++){
			if(pd.equals(buyItem.get(i))){
				itemCheck = false;
			}
		}
		pd.count += count;
		money -= count*pd.price;
		mileage += count*pd.mileage;
		pd.stock -= count;
		System.out.println("※" + name +"고객님께서 "+ pd.name +"를" + count +"개 구매하셨습니다.");
		if(itemCheck == true){
			buyItem.add(pd);
		}
	}
	
	void refund(Product pd, int count){
		for(int i = 0; i<buyItem.size(); i++){
			if(buyItem.get(i) == pd){
				if(count > pd.count){
					System.out.println("※환불 수량이 구매하신 수량보다 "+ (count-pd.count) +"개 더 많아 환불이 불가능 합니다.");
					return;
				}else if(count == pd.count){
					buyItem.remove(pd);
				}
				pd.count -= count;
				money += count*pd.price;
				mileage -= count*pd.mileage;
				pd.stock += count;
				System.out.println("※" + name + "고객님께서 " + pd.name + "을(를) "+ count + "개 환불하셨습니다.");
				return;
			}
		}
		System.out.println("※" + name + "고객님은 " + pd.name + "을(를) 구매한 적이 없으십니다.");
		return;		
	}
	
	void summary(Buyer b){
		System.out.println("--------------------------------");
		System.out.println(" \t영\t수\t증\n");
		System.out.println(" 구매목록");
		int sum = 0;
		for(int i = 0; i<buyItem.size(); i++){
			if(b.buyItem.get(i) instanceof Product){ // item에 들어있는건 모두 Object타입
				Product p = (Product)b.buyItem.get(i); // 다운캐스팅
				System.out.print("\t"+p.name);
				System.out.print("\t" + p.count + "개");
				System.out.println("\t" + p.count*p.price + "만원");
				sum += p.count*p.price;
			}
		}
		System.out.println("\n\t총합" + "\t\t" + sum + "만원\n");
		System.out.println("※" + b.name + " 고객님의 남은 돈은 " + b.money + "만원,\n" + " 보유 마일리지는 " + b.mileage + "만 포인트입니다.");
		System.out.println("--------------------------------");
	}
	
}
