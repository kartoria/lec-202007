package h_javaLang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.nio.cs.ext.MacHebrew;

public class regEx1 {
	public static void main(String[] args) {
//		String regEx1 = "[a-z]{2,3}";
//		System.out.println(Pattern.matches(regEx1, "ssss"));
		
//		1. 텍스트가 영문자가 3회 반복되고 이후에 숫자가 하나이상으로 구성
		Pattern p = Pattern.compile("[a-zA-Z]{3}[0-9]+");
//		Pattern p = Pattern.compile("[a-zA-Z]{3}\\d+"); //같은거
		Matcher m = p.matcher("aaa7");
		System.out.println(m.matches());
		
//		2. 텍스트가 핸드폰 번호 형태인 '숫자3개-숫자4개-숫자4개'로 구성
		String regEx2 = "[0-9]{3}(-)[0-9]{4}(-)[0-9]{4}";
		System.out.println(Pattern.matches(regEx2, "010-2018-0766"));
		
//		3. 텍스트가 핸드폰 번호로 구성
		// 01 다음 0,1,7,8,9 - 0을 제외한 숫자, 숫자3개 - 숫자4개
		String regEx3 = "^(01)[0-9](-)[1-9][0-9]{3}(-)[0-9]{4}";
		System.out.println(Pattern.matches(regEx3, "010-2018-0766"));
		
//		4. 텍스트가 주민등록번호로 구성
		// 년도 월 일 - 1~4 숫자 6개
		String regEx4 = "^[0-9]{2}[0-1][0-9][0-3][0-9]";
		System.out.println(Pattern.matches(regEx4, "961232"));
		
	}

}
