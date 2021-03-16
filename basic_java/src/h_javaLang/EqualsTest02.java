package h_javaLang;

import java.io.Serializable;

public class EqualsTest02 {
	public static void main(String[] args) {
		Person p1 = new Person(9612231402020L);
		Person p2 = new Person(9612231402020L);
		
		System.out.println(p1 == p2);
		System.out.println(p1.equals(p2));
		
		System.out.println(p1.toString());
		System.out.println(p1.hashCode());
	}
}

class Person implements Serializable{
	long regNo;
	
	Person(long regNo){
		this.regNo = regNo;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Person && obj != null){
			Person p2 = (Person)obj;
			result = this.regNo == p2.regNo;
		}
		return result;
	}

//	@Override
//	public String toString() {
//		return "Person [regNo=" + regNo + "]";
//	}
	
	
}