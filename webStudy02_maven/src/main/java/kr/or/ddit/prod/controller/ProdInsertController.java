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

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertController extends HttpServlet{
	private IProdService service = ProdServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logicalView = "prod/prodForm";
		req.getRequestDispatcher("/"+logicalView+".tiles").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(prod, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = validator.validate(prod, errors);
		String goPage = "";
		if(valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				goPage =  "redirect:/prod/prodList.do";
				break;
			default:
				//메세지 전달
				goPage = "prod/prodForm";
				break;
			}
		}else {
			goPage = "prod/prodForm";
		}
		req.setAttribute("errors", errors);
		
		boolean redirect = goPage.startsWith("redirect:");
		boolean forward = goPage.startsWith("forward:");
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage.substring("redirect:".length()));
		}else if(forward){
			req.getRequestDispatcher(goPage.substring("forward:".length())).forward(req, resp);
		}else{	
			req.getRequestDispatcher("/"+goPage+".tiles").forward(req, resp);
		}
	}
}
