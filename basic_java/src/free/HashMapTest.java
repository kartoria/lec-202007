package free;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class HashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		1. Set 인터페이스 타입의 참조변수 set에 HashSet객체의 주소를 저장하여라.
		Set<Integer> set = new HashSet<>();
		
//		2. 참조변수 set에 중복되지 않는 1~45사이의 임의의 정수를 6개 저장하여라.
		for(int i = 0; i<6; i++){
			set.add((int)(Math.random()*45+1));
		}
		
//		3. 참조변수 set에 저장된 값을 출력하여라.
		System.out.println(set);
		
//		4. 참조변수 set에 저장된 정수들을 오름차순으로 정렬하여라.
		List<String> setList = new ArrayList(set);
		Collections.sort(setList);
		
//		5/ 정렬된 정수를 출력하여라.
		System.out.println(setList);
		
		
	}

}
