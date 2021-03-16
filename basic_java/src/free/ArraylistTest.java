package free;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 연습장
 **/
public class ArraylistTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(new Integer(5)); // 리스트에 정수를 추가
		list1.add(new Integer(4));
		list1.add(new Integer(3));
		list1.add(new Integer(2));
		list1.add(new Integer(1));
		
		ArrayList<Integer> list2 = new ArrayList<Integer>(list1.subList(2, 4));
		System.out.println("1 : "+ list1);
		System.out.println("1 : "+ list2);
		
		Collections.sort(list1); // 소트시키는 명령어
		Collections.sort(list2);
		System.out.println("2 : " + list1);
		System.out.println("2 : " + list2);
		
		System.out.println(list1.containsAll(list2)); // list1에 list2가 포함되므로 true
		System.out.println(list2.containsAll(list1)); // list2에 list1이 포함되지 않으므로 false
		System.out.println(list1.contains(3)); // 리스트1에 3이 있으므로
		System.out.println(list1.contains(6)); // 리스트1에 6이 없으므로
		
		
		list2.add(4);	// 리스트2에 4를 추가함 ( 2, 3 -> 2, 3, 4 )
		list2.add(7);	// 리스트2에 7을 추가함 (2, 3, 4 -> 2, 3, 4, 7)
		System.out.println("3 : " + list1);
		System.out.println("3 : " + list2);
		
		list2.add(2,11); // 리스트2의 2번 index에 11의 값을 삽입 (2 3 4 7 -> 2 3 11 4 7) 
		System.out.println("4 : " + list1);
		System.out.println("4 : " + list2);
		
		list2.set(2, 33); // 리스트2의 2번 index를 33으로 수정 (2 3 11 4 7 -> 2 3 33 4 7) 
		System.out.println("5 : " + list1);
		System.out.println("5 : " + list2);
		
		list1.addAll(list2); // 리스트1에 리스트2를 모두 더함
		System.out.println(list1);
		list1.removeAll(list2); //리스트1에서 리스트2에있는 값을 모두 삭제함
		System.out.println(list1);
		
		list1.clear(); // 리스트1의 값을 모두 삭제함
		System.out.println(list1.isEmpty()); // 리스트 1이 비었으면 true 출력
		
	}
}
