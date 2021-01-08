package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyService {
	public ServiceResult createReply(ReplyVO reply);
	public int readReplyCount(PagingVO<ReplyVO> pagingVO);
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVO);
	public ServiceResult modifyReply(ReplyVO reply);
	public ServiceResult removeReply(ReplyVO reply);
}
