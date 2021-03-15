package kr.or.ddit.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@Repository
public interface IReplyDAO {
	
	public int selectReplyCount(PagingVO<ReplyVO> paging) throws Exception;
	
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> paging) throws Exception;
	
	public int insertReply(ReplyVO replyVO) throws Exception;
	
	public int deleteReply(ReplyVO replyVO) throws Exception;
	
	public List<ReplyVO> selectReplyAll(ReplyVO replyVO) throws Exception;

}
