/*package f_OOP2;

import java.util.Vector;

public class ProductTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Notebook nb = new Notebook("삼성 갤럭시북", 300);
		Styler st = new Styler("삼성 에어드레서", 200);
		Refrigerator rf = new Refrigerator("삼성 셰프컬렉션", 500);
		
		Buyer b = new Buyer("이재용", 1500);
		b.buy(nb);
		b.buy(st);
		b.buy(rf);
		b.buy(nb);
		b.summary(b);
		b.refund(b, nb);
	}
}

class Product{
	String name; //제품이름
	int price; //가격 
	int mileage; //마일리지
	
	Product(String name, int price){
		this.name = name;
		this.price = price;
		mileage = price/10;
	}
}

class Notebook extends Product{
	
	Notebook(String name, int price) {
		super(name, price);
	}
	
	@Override
	public String toString() {
		return name;
	}
}

class Styler extends Product{
	
	Styler(String name, int price) {
		super(name, price);
	}
	
	@Override
	public String toString() {
		return name;
	}
}

class Refrigerator extends Product{
	
	Refrigerator(String name, int price) {
		super(name, price);
	}
	
	@Override
	public String toString() {
		return name;
	}
}

class Buyer{
	String buy_name;
	int buy_money;
	int buy_mileage;
	int sumPrice = 0;
	Vector item = new Vector();
	
	public Buyer(String name, int money) {
		buy_name = name;
		buy_money = money;
	}
	
	void buy(Product pd){
		if(buy_money < pd.price){
			System.out.println("돈가져와");
			return;
		}
		buy_money -= pd.price;
		buy_mileage += pd.mileage;
		item.add(pd);
		System.out.println(buy_name+" 고객님 "+pd + "을 구매해주셔서 감사합니다.");
	}
	
	void summary(Buyer b){
		System.out.println("-----------------------------------");
		System.out.println(" 영\t\t수\t\t증 \n");
		System.out.println(" 구매목록");
		int sum = 0;
		for(int i = 0; i<item.size(); i++){
			if(item.get(i) instanceof Product){ // item에 들어있는건 모두 Object타입
				Product p = (Product)item.get(i); // 다운캐스팅
				System.out.print("\t"+p.name);
				System.out.println("\t\t   " + p.price + "만원");
				sum += p.price;
			}
			
		}
		System.out.println("\t총합" + "\t\t  " + sum + "만원");
		System.out.println(" "+b.buy_name + " 고객님의 남은 돈은 " + b.buy_money + "만원이고\n" + " 마일리지는 " + b.buy_mileage + "만입니다.");
		System.out.println(" 오늘도 좋은하루 보내십쇼 호갱님~!");
		System.out.println("-----------------------------------");
	}
	
	void refund(Buyer b, Product pd){
		if(item.isEmpty()){
			System.out.println("물건을 산 내역이 없습니다.");
			return;
		}
		item.remove(pd);
		buy_money += pd.price;
		buy_mileage -= pd.mileage;
		summary(b);
	}
}
*/
//	1. summary
//	영			수			증
//	구매목록
//		NoteBook	300만원
//		Styler		200만원
//		총합			500만원
//	xxx 고객님의 남은돈은  xxx만원이고 
//	마일리지는 xxx입니다.
//	오늘도 좋은하루 보내십쇼 호갱님~!
	
//	2. refund
//		1.고려사항
//		- 물건을 산 내역이 없을 때 
//		- 내가 산 물건만 반품
		
//	3. 물품의 수량을 관리


// 	4. 고객의 목록을 관리해주세요

