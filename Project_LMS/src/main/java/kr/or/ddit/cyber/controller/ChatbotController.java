package kr.or.ddit.cyber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatbotController {
	
	/** 자주 묻는 질문 챗봇**/
	@RequestMapping("/cyber/chatbot.do")
	public String cyberChatbot() {
		return "cyber/qna/cyberchatbot";
	}
	
}
