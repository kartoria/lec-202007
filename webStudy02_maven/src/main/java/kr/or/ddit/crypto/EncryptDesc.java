package kr.or.ddit.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptDesc {
	public static void main(String[] args) throws Exception {
		String password = "Abc001";
		String encoded = encryptSha512(password);
		System.out.println(encoded);
		
		
//		String plain = "대덕인재개발원401호";
//		long start = System.currentTimeMillis();
//		encryptAESExample(plain);
//		long end = System.currentTimeMillis();
//		System.out.printf("AES 소요시간 %d ms\n", (end-start));
//		
//		start = System.currentTimeMillis();
//		encryptRSAExample(plain);
//		end = System.currentTimeMillis();
//		System.out.printf("RSA 소요시간 %d ms\n", (end-start));
	}
	
	public static String encryptSha512(String plain) throws NoSuchAlgorithmException {
		// 1. 단방향 (해시 함수) : 다양한 입력데이터를 일정 길이의 해시 코드를 생성할 때 사용
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(plain.getBytes());
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		return encoded;
	}
	
	public static void encryptAESExample(String plain) throws Exception{
//		String ivString = "아무거나";
//		MessageDigest md5 = MessageDigest.getInstance("MD5");
//		byte[] iv = md5.digest(ivString.getBytes());
		
		SecureRandom random = new SecureRandom();
		byte[] iv = new byte[128/8];
		random.nextBytes(iv);
		
		// 2. 양방향 -> 대칭키
		Cipher cp = Cipher.getInstance("AES/CBC/PKCS5Padding");
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(128);
		SecretKey sk =  kg.generateKey();
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cp.init(Cipher.ENCRYPT_MODE, sk, ivSpec);
		byte[] encrypted = cp.doFinal(plain.getBytes());
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		
		byte[] decoded = Base64.getDecoder().decode(encoded);
		cp.init(Cipher.DECRYPT_MODE, sk, ivSpec);
		byte[] decrypted = cp.doFinal(decoded);
	}
	
	public static void encryptRSAExample(String plain) throws Exception{
		// 3. 양방향 -> 비대칭키(공개키)
		Cipher cp = Cipher.getInstance("RSA");
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair pair = kpg.generateKeyPair();
		PrivateKey priKey = pair.getPrivate();
		PublicKey pubKey = pair.getPublic();
		cp.init(Cipher.ENCRYPT_MODE, priKey);
		byte[] enc = cp.doFinal(plain.getBytes());
//		String encoded = URLEncoder.encode(new String(enc), "UTF-8");
		String encoded = Base64.getEncoder().encodeToString(enc);
		
//		String decoded = URLDecoder.decode(encoded, "UTF-8");
		byte[] decoded = Base64.getDecoder().decode(encoded);
		
		cp.init(Cipher.DECRYPT_MODE, pubKey);
		byte[] dec = cp.doFinal(decoded);
	}
}
