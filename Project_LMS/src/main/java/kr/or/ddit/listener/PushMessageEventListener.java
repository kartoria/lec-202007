package kr.or.ddit.listener;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.MemberWrapper;
import kr.or.ddit.vo.PushMessageVO;
import kr.or.ddit.websocket.event.PushMessageEvent;

@Component
public class PushMessageEventListener extends BaseController{
	
	@Resource(name="wsSessionSet")
	private Set<WebSocketSession> wsSessionSet;
	
	@EventListener(PushMessageEvent.class)
	@Async
	public void eventHandler(PushMessageEvent event) throws IOException{
		PushMessageVO messageVO = event.getMessageVO();		
		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writeValueAsString(messageVO);		
		
		List<String> messageTargetList = messageVO.getMessageTargetList();
		
//		if(messageTargetList.isEmpty()) {
			for(WebSocketSession wsSession : wsSessionSet) {
				wsSession.sendMessage(new TextMessage(payload));
			}
//		}else{
//			for(WebSocketSession wsSession : wsSessionSet) {
//				Authentication authentication = (Authentication) wsSession.getPrincipal();
//				MemberWrapper memberWrapper = (MemberWrapper) authentication.getPrincipal();
//				MemberVO authMember = memberWrapper.getRealMember();
//				String memId = authMember.getMemId();
//				
//				if(messageTargetList.contains(memId)) {
//					printInfo("메세지 발송", memId);
//					wsSession.sendMessage(new TextMessage(payload));
//				}
//			}
//		}
		
	}
}










