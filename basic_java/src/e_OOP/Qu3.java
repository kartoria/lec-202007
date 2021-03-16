package e_OOP;
/**
 * 3장 문제 - 카드 만들기
 * @author PC-18
 * @since 2020-08-14
 */
public class Qu3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		1.
		System.out.println(Card.width);
//		2.
//		
		System.out.println(Card.height);
//		3.
//		클래스명 변수명 = new 클래스명()
		Card cd1 = new Card();
//		4.
//		참조변수명.인스턴스변수명 = 바꿀내용;
		cd1.name = "Daniel";
//		5.
		cd1.number = 19961210;
//		6.
		Card cd2 = new Card();
//		7.
		cd2.name = "nayeon";
//		8.
		cd2.number = 19950922;
//		9.
		System.out.println("1번 카드의 이름은 " + cd1.name + " 직원번호 " + cd1.number + " 폭은 " + Card.width + " 높이는 " + Card.height + "이다.");
		System.out.println("2번 카드의 이름은 " + cd2.name + " 직원번호 " + cd2.number + " 폭은 " + Card.width + " 높이는 " + Card.height + "이다.");
//		10.
		Card.width = 70;
//		11.
		Card.height = 100;
//		12.
		System.out.println("1번 카드의 이름은 " + cd1.name + " 직원번호 " + cd1.number + " 폭은 " + Card.width + " 높이는 " + Card.height + "이다.");
		System.out.println("2번 카드의 이름은 " + cd2.name + " 직원번호 " + cd2.number + " 폭은 " + Card.width + " 높이는 " + Card.height + "이다.");
	}
}	
class Card {
	String name;
	int number;
	static int width = 100;
	static int height = 250;
	
}

