package free;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stack st = new Stack();
		st.push("0"); // 스택에 삽입
		st.push("1");
		st.push("2");
		
		System.out.println("===stack===");
		while(!st.empty()){ // 스택이 비지 않았으면
			System.out.println(st.pop()); // 하나 꺼낸다
		}
		
		Queue q = new LinkedList();
		q.offer("0");
		q.offer("1");
		q.offer("2");
		System.out.println("===Queue===");
		while(!q.isEmpty()){
			System.out.println(q.poll());
		}
	}

}
