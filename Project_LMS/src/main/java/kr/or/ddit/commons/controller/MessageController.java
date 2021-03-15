package kr.or.ddit.commons.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.service.MessageService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.MessageVO;

@Controller
public class MessageController {

	@Inject
	MessageService service;
	
	@RequestMapping(value="/message/messageCount.do" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> messageCount(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			){
		int result = service.messageCount(authMember);
		Map<String, Object> resultMap=new HashMap<>();
		if(result<0) {
			resultMap = Collections.singletonMap("result", "NO");
		}else {
			
			resultMap = Collections.singletonMap("result", result);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/message/selectMessage.do" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> selectMessage(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			){
		
		List<MessageVO> messageList = service.selectMessage(authMember);
		
		Map<String, Object> resultMap = Collections.singletonMap("messageList", messageList);
		return resultMap;
		
	}
	
	@RequestMapping(value="/message/deleteMessage.do" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> deleteMessage(
			@RequestParam("msgNum") int msgNum
			){
		
		
		MessageVO messageVO = new MessageVO();
		messageVO.setMsgNum(msgNum);
		
		int result = service.deleteMessage(messageVO);
		
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
		
	}
}
