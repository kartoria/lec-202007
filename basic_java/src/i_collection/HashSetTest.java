package i_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetTest{
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		
		for(int i = 0; i < 10; i++){
			int random = (int)(Math.random()*35+1);
			boolean rs = set.add(random);
			System.out.println(rs);
		}
		
		List<Integer> list = new ArrayList<>(set); //리스트로만들어서
		Collections.sort(list);					   //소트시키고 
		System.out.println(list);
	}
	
}
