package kr.or.ddit.crypto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/** 
 * 부호화 (encode / decode) : %23 (Percent Encoding / URL encoding), Base64
 * 	: 데이터를 전송하거나 저장하기 위해 시스템이 인지할 수 있는 데이터 표현방식으로 바꾸는 작업
 *  
 * 암호화 (encrypt / descrypt) 
 * 	: 허가되지 않은 유저가 데이터를 읽을 수 없도록 데이터 표현방식을 바꾸는 작업 
 *  단방향 암호화 : decrypt 불가능, MD5 -> SHA-512
 *  양방향 암호화 : decrypt 가능
 *  	1) 대칭키(비밀키) : 암복호화에 동일한 비밀키 사용 : AES
 *  	2) 비대칭키(공개키) : 공개키와 개인키로 구성된 한쌍의 키로 암복호화 수행 : RSA
 */
public class EncodeDesc {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String plain = "대덕인재개발원405호";
		// 뷁
		String encoded = URLEncoder.encode(plain, "UTF-8");
		System.out.println(encoded);
		String decoded = URLDecoder.decode(encoded, "UTF-8");
		System.out.println(decoded);
		
		encoded = Base64.getEncoder().encodeToString(plain.getBytes());
		System.out.println(encoded);
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
		System.out.println(new String(decodedBytes));
		
	}
}
