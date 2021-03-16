package free;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailTest {
	
//	javax.mail jar파일을 라이브러리로 등록하고
//	네이버 메일에 들어가 SMTP(송신),POP3(수신) 옵션을 켠다
	
//	1. 발신자 메일의 계정과 비밀번호를 설정
	static final String USER = "gavethesun@naver.com";
	static final String PASSWORD = "1q2w3e4r&";
	
	public static void main(String[] args) {
		mailSend();
	}
	
	public static void mailSend() {

		
		//받는사람, 메일제목, 내용
		String mem_mail = "ha961223@naver.com";
		String subject = "javamailtest";
		String text = "text";
		
		
//		2. Property에 SMTP 서버 정보를 설정
//		(Property는 HashMap과 비슷한 개념인데 파일 입출력이 가능하다고함)
		Properties prop = new Properties();
		String host = "smtp.naver.com";
		int port = 465; 
		
		// SMTP 서버명
		prop.put("mail.smtp.host", host);
		
		// SMTP 포트명
		prop.put("mail.smtp.port", port);
		
		// 권한 설정, SSL 연결
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", host);
		
		// 세션 인스턴스화
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASSWORD);
			}
		});
		session.setDebug(true);
		
		try {
			if(!(mem_mail.equals(null) || subject.equals(null) || text.equals(null))){
				MimeMessage message = new MimeMessage(session);
				
				// 발신자
				message.setFrom(new InternetAddress(USER));
				
				// 수신자 메일주소
				message.addRecipient(Message.RecipientType.TO,  new InternetAddress(mem_mail));
				
				// subject (메일 제목)
				message.setSubject(subject);
				
				// text (메일 내용)
				message.setText(text);
				
				
				// 메일 전송
				Transport.send(message);
				System.out.println("메세지 전송 완료");
			}else{
				System.out.println("오류 - 메일 제목 또는 내용을 받아올 수 없습니다");
				return;
			}
		}catch(AddressException e){
			e.printStackTrace();
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
}