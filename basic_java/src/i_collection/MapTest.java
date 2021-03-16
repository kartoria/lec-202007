package i_collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String , Integer> param = new HashMap<>(); //Object, Object 

		param.put("이현무", 50);
		param.put("김근호", 60);
		param.put("이경륜", 10);
		param.put("이운주", 20); //insert겸 update라 update가 따로없다.
		
		//Read
		System.out.println(param.get("이현무"));
		
		//Delete
		System.out.println(param);
		System.out.println(param.remove("김근호")); //삭제됐던 키의 값이 출력
		System.out.println(param);
		
		Map<String, String> member = new HashMap<>();
		member.put("mem_id", "a001");
		member.put("mem_pw", "asdfasdf");
		System.out.println(member.get("mem_id"));
		System.out.println(member.get("mem_pw"));
		
		
		
		
		
		
		
	}
}
