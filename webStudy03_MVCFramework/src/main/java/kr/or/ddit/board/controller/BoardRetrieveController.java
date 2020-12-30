package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.board.sevice.BoardServiceImpl;
import kr.or.ddit.board.sevice.IBoardService;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class BoardRetrieveController {
	
	@RequestMapping("/board/boardList.do")
	public String getBoardRetrieve(
			@RequestParam(value="page", requried=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		IBoardService service = BoardServiceImpl.getInstance(); 
		PagingVO<BoardVO> pagingVO = new PagingVO<>(5, 5);
		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<BoardVO> memberList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
}
