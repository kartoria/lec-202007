package free;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		list.add("123"); // 리스트에 123 추가
		list.add("456");	
		list.add("789");
		
		List<String> list2 = new ArrayList<>(list);
		// Iterator : 단방향 이동만 된다.
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()){
			System.out.println("list : " + it.next());
			it.remove();
		}
		
		ListIterator<String> li = list2.listIterator();
		System.out.println("list2 : " + li.hasNext());
		System.out.println("list2 : " + li.next());
//		void remove();
		System.out.println("list2 : " + li.next());
		System.out.println("list2 : " + li.next());
		System.out.println("list2 : " + li.hasNext());
		System.out.println("list2 : " + li.hasPrevious());
		System.out.println("list2 : " + li.previous());
		System.out.println("list2 : " + li.previous());
		System.out.println("list2 : " + li.previous());
		System.out.println("list2 : " + li.hasPrevious());
		
	}

}
