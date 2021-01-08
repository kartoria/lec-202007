package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	@Inject
	private IBoardService service;
	@Inject
	private WebApplicationContext container;
	
	private ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
//	RequestToViewNameTranslator
	@RequestMapping("boardList.do")
	public void list(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO")  SearchVO searchVO , Model model) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<BoardVO> BoardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(BoardList);
		
		model.addAttribute("pagingVO", pagingVO);
		
//		return "board/boardList";
	}
	
	@RequestMapping("{what}")
	public String boardView(@PathVariable(value="what", required=true) int bo_no
		, Model model) {
		
		BoardVO board = service.retrieveBoard(bo_no);
		model.addAttribute("board", board);
		return "board/boardView";
	}
	
	@RequestMapping(value="recommend.do", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String recommend(
		@RequestParam(value="what", required=true) int bo_no
		, @CookieValue(required=false, value="boardCookie") String value, HttpServletResponse resp
	) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		int[] already = null;
		if(value==null) {
			already = new int[0];
		}else {
			already = mapper.readValue(value, int[].class);
		}
		Arrays.sort(already);
		ServiceResult result = ServiceResult.FAILED;
		if(Arrays.binarySearch(already, bo_no) < 0) {
			result = service.incrementRecCnt(bo_no);
		}
		
		if(ServiceResult.OK.equals(result)) {
			int[] boardNos = new int[already.length+1];
			System.arraycopy(already, 0, boardNos, 0, already.length);
			boardNos[boardNos.length-1] = bo_no;
			value = mapper.writeValueAsString(boardNos);
			Cookie cookie = CookieUtils.createCookie("boardCookie", value, 
											application.getContextPath(), 60*60*24*3);
			resp.addCookie(cookie);			
		}
		
		return result.name();
	}
}





























