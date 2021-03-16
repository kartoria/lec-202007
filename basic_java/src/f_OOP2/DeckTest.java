package f_OOP2;

import java.util.Arrays;

public class DeckTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Card cd1 = new Card();
		System.out.println(cd1); //SPADE,A
		
		Card cd2 = new Card(2,5);
		System.out.println(cd2); // DIAMOND,5
		
		// 8. 카드 한 벌의 객체를 생성
		Deck d = new Deck();
		System.out.println(Arrays.toString(d.c));//52장 다출력하면 성공
		
		// 9. 0번 index의 카드 한 장을 뽑기
		System.out.println("0번 index : " + d.pick(0));
		
		// 10. 임의의 index번째 카드 한 장을 뽑기
		System.out.println("임의 index : " + d.pick());
		
		// 11. 자동 섞기
		d.shuffle();
//		System.out.println("섞은 후 : " + Arrays.toString(d.c));
		// 12. 0번 index의 카드 한 장을 뽑기
		System.out.println("섞은 후 0번 index : " + d.pick(0));
		
		// 12. 카드 1000번 섞기
		d.shuffle(1000);
//		System.out.println("천번 섞은 후 : " +Arrays.toString(d.c));
		
		// 13. 0번 index의 카드 한 장을 뽑기
		System.out.println("천번 섞은 후 0번 index : " + d.pick(0));
		
	}

	
}

class Card{
	static final int KIND_MAX = 4;
	static final int NUM_MAX = 13;
	static final int SPADE = 1;
	static final int DIAMOND = 2;
	static final int HEART = 3;
	static final int CLOVER = 4;
	static int width = 100;
	static int height = 250;
	int kind;
	int num;
	
	Card(int kind, int num){
		this.kind = kind;
		this.num = num;
	}
	
	Card(){
		this(SPADE,1);
	}
	
	public String toString(){
		String kind = "";
		switch (this.kind){
			case 1:
				kind = "♠";
				break;
			case 2:
				kind = "◆";
				break;
			case 3:
				kind = "♥";
				break;
			default :
				kind = "♣";
				break;
		}
		
		String num = "";
		switch (this.num){
			case 1:
				num = "A";
				break;
			case 11:
				num = "J";
				break;
			case 12:
				num = "Q";
				break;
			case 13:
				num = "K";
				break;
			default :
				num += this.num; //Integer.toString(this.num);
				break;
		}
		
		return "["+ kind + "," + num + "]";
	}
}

class Deck{
	// 1. 카드의 수량을 저장할 수 있는 변수 CARD_NUM을 선언 후 card 클래스의 변수를 사용하여 초기화
	
	static final int CARD_NUM = Card.NUM_MAX*Card.KIND_MAX;
	
	// 2. 카드 52장을 저장할 수 있는 변수 c를 선언 및 생성
	Card c[] = new Card[CARD_NUM];
	
	
	// 3. 기본생성자를 만들어라 c의 방들의 값을 (1,1)~(4,13)까지의 카드로 초기화하여라.
	Deck(){
			for(int i = 0; i<CARD_NUM; i++){
				c[i] = new Card(i/Card.NUM_MAX+1,i%Card.NUM_MAX+1);
			}
	}
	// 4. c에서 임의의 index번째 카드 한 장을 반환하는 메서드(pick)를 작성하여라.
	Card pick(){
		return c[(int)(Math.random()*c.length)];
	}
	
	// 5. 사용자로부터 입력받은 index번째 카드 한 장을 반환하는 메서드(pick)를 작성하여라
	Card pick(int inputNum){
		int returnNum = 0;
		if(0 <= inputNum && inputNum < 52){
			returnNum = inputNum;
		}else{
			returnNum = (int)(Math.random()*c.length);
		}
		return c[returnNum];
	}
	
	// 6. c의 카드를 섞는 메서드를 작성하여라
	void shuffle(){
		for(int i = 0; i<c.length; i++){
			int random = (int)(Math.random()*c.length);
			Card temp = c[i];
			c[i] = c[random];
			c[random] = temp;
		}
	}
	
	// 7. 사용자로부터 입력받은 횟수만큼 c의 카드를 섞는 메서드(shuffle)을 작성하여라.
	void shuffle(int inputNum){
		for(int i = 0; i<inputNum; i++){
			int a = (int)(Math.random()*c.length);
			int b = (int)(Math.random()*c.length);
			Card temp = c[a];
			c[a] = c[b];
			c[b] = temp;
		}
	}
	
}
