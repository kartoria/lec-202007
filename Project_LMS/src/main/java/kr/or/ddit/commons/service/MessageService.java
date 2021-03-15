package kr.or.ddit.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.IMessageDAO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.MessageVO;

@Service
public class MessageService {

	@Inject
	IMessageDAO dao;
	//메세지 조회
	public List<MessageVO> selectMessage(MemberVO authMember) {
		return dao.selectMessage(authMember);
	}
	//메세지 카운트 조회
	public int messageCount(MemberVO authMember) {
		return dao.messageCount(authMember);
	}
	//메세지 지우기
	public int deleteMessage(MessageVO messageVO) {
		return dao.deleteMessage(messageVO);
	}
	public int insertMessage(MessageVO messageVO) {
		return dao.insertMessage(messageVO);
	}
	
}
