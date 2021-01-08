package kr.or.ddit.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@RestController
@RequestMapping(value="/reply", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReplyController {
	@Inject
	private IReplyService service;
	
	@PostMapping
	public Map<String, Object> insert(@ModelAttribute("reply") ReplyVO reply){
		ServiceResult result = service.createReply(reply);
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
	@PutMapping
	public Map<String, Object> update(@ModelAttribute("reply") ReplyVO reply){
		ServiceResult result = service.modifyReply(reply);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(ServiceResult.INVALIDPASSWORD.equals(result)) {
			resultMap.put("message", "비밀번호 오류");
		}
		return resultMap;
	}
	
	@DeleteMapping
	public Map<String, Object> delete(@ModelAttribute("reply") ReplyVO reply){
		ServiceResult result = service.removeReply(reply);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(ServiceResult.INVALIDPASSWORD.equals(result)) {
			resultMap.put("message", "비밀번호 오류");
		}
		return resultMap;
	}
	
	@GetMapping
	public PagingVO<ReplyVO> list(
			@RequestParam(value="what", required=true) int bo_no, 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage){
//		====검색, 특정글의 댓글만 조회
		ReplyVO searchDetail = new ReplyVO();
		searchDetail.setBo_no(bo_no);
		
		PagingVO<ReplyVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setSearchDetail(searchDetail);
//		========
		int totalRecord = service.readReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord); // totalPage
		pagingVO.setCurrentPage(currentPage); // startRow, endRow, startPage, endPage
		
		List<ReplyVO> ReplyList = service.readReplyList(pagingVO);
		pagingVO.setDataList(ReplyList);
		return pagingVO;
	}
}
