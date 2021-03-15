package kr.or.ddit.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.MessageVO;

@Repository
public interface IMessageDAO {

	List<MessageVO> selectMessage(MemberVO authMember);

	int messageCount(MemberVO authMember);

	int deleteMessage(MessageVO messageVO);

	int insertMessage(MessageVO messageVO);

}
