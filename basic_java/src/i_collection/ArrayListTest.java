package i_collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ArrayListTest {
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>();
		list1.add(new Integer(5));
		list1.add(2);
		list1.add(3);
		list1.add(1);
		list1.add(4);
		
		List<Integer> list2 = new ArrayList<>(list1.subList(1, 4));
		
		System.out.println(list1);
		System.out.println(list2);
		
		//Read
		int a = list1.get(2);
		System.out.println(a);
		
		//Delete
		list1.remove(2);			//5 2 3 1 4
		System.out.println(list1);	// 5 2 1 4
		
		//Update
		int after = new Integer(10);
		int before = list1.set(1, after); //5 2 1 4
		
		System.out.println("바뀌기 전 : " + before);
		System.out.println("바뀐 후 : " + after);
		System.out.println(list1); // 5 10 1 4
		
		Collections.sort(list1);
		System.out.println(list1);
		
	}

}
