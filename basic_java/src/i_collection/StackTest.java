package i_collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> s = new Stack<>();
		
		s.push("0");
		s.push("1");
		s.push("2");
		
		System.out.println("===Stack===");
		while(!s.empty()){
			System.out.println(s.pop());
		}
		
		Queue<String> q = new LinkedList<>();
		
		q.offer("0");
		q.offer("1");
		q.offer("2");
		
		System.out.println("\n===Queue===");
		
		while(!q.isEmpty()){
			System.out.println(q.poll());
		}
	}
}
