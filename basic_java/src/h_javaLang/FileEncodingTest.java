package h_javaLang;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class FileEncodingTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "가나";
		
		byte[] cpStr = str.getBytes("CP949");
		byte[] msStr = str.getBytes("MS949");
		byte[] utfStr = str.getBytes("UTF-8");
		
		System.out.println(Arrays.toString(cpStr));
		System.out.println(Arrays.toString(msStr));
		System.out.println(Arrays.toString(utfStr));
	}

}
