package kr.or.ddit.websocket.event;

import org.springframework.context.ApplicationEvent;

import kr.or.ddit.vo.PushMessageVO;

public class PushMessageEvent extends ApplicationEvent{

	private PushMessageVO messageVO;
	
	public PushMessageEvent(PushMessageVO source) {
		super(source);
		this.messageVO = source;		
	}
	
	public PushMessageVO getMessageVO() {
		return messageVO;
	}
}
