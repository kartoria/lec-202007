package kr.or.ddit.commons.controller;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author 전진원
 * @since 2021. 3. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 3. 5.    전진원	     	 property 암호화 하기위해 jasypt 적용
 * Copyright (c) 2021 by DDIT All right reserved
 */
public class Encryptor {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("java");
        
        String url = encryptor.encrypt("jdbc:log4jdbc:oracle:thin:@112.220.114.130:1521:xe");
        String user = encryptor.encrypt("TEAM3_202007F");
        String password = encryptor.encrypt("java");
        
        System.out.println("url=" + url);
        System.out.println("user" + user);
        System.out.println("password=" + password);
    }
}