package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.sevice.BoardServiceImpl;
import kr.or.ddit.board.sevice.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class BoardRetrieveController {
	private IBoardService service = BoardServiceImpl.getInstance(); 
	
	@RequestMapping("/board/boardList.do")
	public String getBoardRetrieve(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
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
	
	@RequestMapping("/board/boardView.do")
	public String boardView(@RequestParam("bo_no") int bo_no, HttpServletRequest req) {
		BoardVO board = service.retrieveBoard(bo_no);
		req.setAttribute("board", board);
		return "board/boardView";
	}
	
	@RequestMapping("/board/recommend.do")
	public String recommend(@RequestParam(value="what", required=true) int bo_no
							, HttpServletRequest req 
							, HttpServletResponse resp) throws IOException, ServletException {
		
		ServiceResult result = null;
		ObjectMapper mapper = new ObjectMapper();
		CookieUtils utils = new CookieUtils(req);
		String value = utils.getCookieValue("boardCookie");
		int[] already = null;
		if(value==null) {
			already = new int[0];
		}else {
			already = mapper.readValue(value, int[].class);
		}
		Arrays.sort(already);
		if(Arrays.binarySearch(already, bo_no) < 0) {
			result = service.incrementRecCnt(bo_no);
		}
		
		if(ServiceResult.OK.equals(result)) {
			int[] boardNos = null;
			if(value==null) {
				boardNos = new int[1];
			}else {
				boardNos = new int[already.length+1];
				System.arraycopy(already, 0, boardNos, 0, already.length);
			}
			boardNos[boardNos.length-1] = bo_no;
			value = mapper.writeValueAsString(boardNos);
			Cookie cookie = CookieUtils.createCookie("boardCookie", value, req.getContextPath(), 60*60*24*3);
			resp.addCookie(cookie);
		}
		resp.setContentType("text/plain;charset=UTF-8");
		try(PrintWriter out = resp.getWriter()){
			out.println(result.name());
		}
		return null;
	}
}
