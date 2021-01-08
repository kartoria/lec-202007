package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements IReplyService {
	
	@Inject
	private IReplyDAO replyDAO;

	private void encryptPassword(ReplyVO reply) {
	 	String inputPass = reply.getRep_pass();
	 	if(StringUtils.isNotBlank(inputPass)) {
	 		reply.setRep_pass(SecurityUtils.encryptSha512(inputPass));
	 	}
	}
	
	@Override
	public ServiceResult createReply(ReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = replyDAO.insertReply(reply);
		ServiceResult result = ServiceResult.FAILED;
		if(rowcnt>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public int readReplyCount(PagingVO<ReplyVO> pagingVO) {
		return replyDAO.selectReplyCount(pagingVO);
	}

	@Override
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult modifyReply(ReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = replyDAO.updateReply(reply);
		ServiceResult result = ServiceResult.INVALIDPASSWORD;
		if(rowcnt>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public ServiceResult removeReply(ReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = replyDAO.deleteReply(reply);
		ServiceResult result = ServiceResult.INVALIDPASSWORD;
		if(rowcnt>0) result = ServiceResult.OK;
		return result;
	}
	
}
