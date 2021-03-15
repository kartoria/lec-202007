package kr.or.ddit.websocket;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class EchoTestWebSocketHandler extends TextWebSocketHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoTestWebSocketHandler.class);
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		Map<String, Object> attributes = session.getAttributes();
//		Authentication authentication = (Authentication) session.getPrincipal();
//		MemberWrapper wrapper = (MemberWrapper) authentication.getPrincipal();
//		String name = wrapper.getRealMember().getMem_name();
//		String address = session.getRemoteAddress().toString();
//		LOGGER.info("{}[{}] 연결 수립", name, address);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		 String payload = message.getPayload();
		 session.sendMessage(new TextMessage(payload));
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		LOGGER.error("웹소켓 핸들러 예외 발생 ", exception);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String address = session.getRemoteAddress().toString();
		LOGGER.info("{} 연결 종료", address);
	}
	
}











