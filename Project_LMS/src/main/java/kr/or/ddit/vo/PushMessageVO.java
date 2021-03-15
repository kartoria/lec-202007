package kr.or.ddit.vo;

import java.util.List;

import kr.or.ddit.enumpkg.PushMessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PushMessageVO {
	
	
	public PushMessageVO(PushMessageType messageType, String message) {
		this.messageType = messageType;
		this.message = message;
	}
	
	private PushMessageType messageType;
	private List<String> messageTargetList;
	private String message;
}
