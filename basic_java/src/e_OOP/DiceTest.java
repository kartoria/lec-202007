package e_OOP;
/**
 * 모두의 마블 주사위 굴리기
 * @author PC-18
 * @since 2020-08-18
 */
public class DiceTest {
	public static void main(String[] args) {
		DoubleDice dd = new DoubleDice();
		System.out.println("총 이동 거리 : " + dd.throwDice());
	}
}

class DoubleDice{
//	인스턴스 메서드 throwDice
//	주사위 2개를 던진다.
//	던진 주사위의 두개의 합을 반환한다.
//	단. 주사위의 눈이 같으면 한번 더 던진다. (재귀호출)
	int move = 0;
	
	int throwDice(){
		int dice1 = (int)(Math.random()*6+1);
		System.out.println("주사위1 : " + dice1);
		int dice2 = (int)(Math.random()*6+1);
		System.out.println("주사위2 : " + dice2);
		System.out.println();
		if(dice1 == dice2){
			move += throwDice();
		}
		move += dice1 + dice2;
		return move;
	}
}