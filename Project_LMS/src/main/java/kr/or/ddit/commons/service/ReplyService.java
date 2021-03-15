package kr.or.ddit.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.dao.IReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;
import kr.or.ddit.vo.ReplyVO.ReplyVOBuilder;

/**
 * 댓글 서비스
 * @author 김선준
 * @since 2021. 2. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 2.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Service
public class ReplyService extends BaseController {
	@Inject
	IReplyDAO replyDAO;
	
	// 페이징용 데이터 수 체크
	public int selectReplyCount(PagingVO<ReplyVO> paging) throws Exception{
		return replyDAO.selectReplyCount(paging);
	}
	
	// 페이징 댓글 리스트 가져오기
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> paging) throws Exception{
		return replyDAO.selectReplyList(paging);
	}

	// 댓글 등록
	public void createReply(ReplyVO reply) throws Exception {
		replyDAO.insertReply(reply);
	}

	// 댓글 삭제
	public void removeReply(ReplyVO reply) throws Exception{
		replyDAO.deleteReply(reply);
	}

	// 페이징 없는 댓글리스트
	public List<ReplyVO> selectAllReply(ReplyVO replyVO) throws Exception{
		return replyDAO.selectReplyAll(replyVO);
	}
}
