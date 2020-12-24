package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.prod.dao.IOthersDao;
import kr.or.ddit.prod.dao.OthersDaoImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ProdRetrieveController{
	private static final Logger logger = LoggerFactory.getLogger(ProdRetrieveController.class);
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDao dao = OthersDaoImpl.getInstance();
	
	@RequestMapping("/prod/prodList.do")
	public String prodList(
			@RequestParam(value="page", requried=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchDetail") ProdVO searchDetail, HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(3, 2);
		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchDetail(searchDetail);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			JsonResponseUtils.toJsonResponse(req, resp);
			return null;
		}else {
			return "prod/prodList";
		}
	}
	
	@RequestMapping("/prod/prodView.do")
	public String prodView(@RequestParam("prod_id") String prod_id, HttpServletRequest req) throws IOException {
		ProdVO prod = service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		return "prod/prodView";
	}
	
	@RequestMapping("/prod/getOthers.do")
	public String getOthers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// json 데이터로 비동기 요청에 대한 응답의 형태로 buyerList/lprodList 제공
		List<BuyerVO> buyerList = dao.selectBuyerList();
		List<Map<String, Object>> lprodList = dao.selectLProdList();
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
		
		JsonResponseUtils.toJsonResponse(req, resp);
		return null;
	}
	
	
	
}









