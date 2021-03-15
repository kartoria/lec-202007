package kr.or.ddit.websocket;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class PushMessageWebSocketHandler extends TextWebSocketHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(PushMessageWebSocketHandler.class);
	
	@Resource(name="wsSessionSet")
	private Set<WebSocketSession> wsSessionSet;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		wsSessionSet.add(session);
		Authentication authentication = (Authentication) session.getPrincipal();
		String username = authentication.getName();
		LOGGER.info("{} 연결 수립", username);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		wsSessionSet.remove(session);
		Authentication authentication = (Authentication) session.getPrincipal();
		String username = authentication.getName();
		LOGGER.info("{} 연결 종료", username);
	}
}	















