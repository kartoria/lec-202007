package kr.or.ddit.dbprop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.service.DatabasePropertyService;
import kr.or.ddit.db.service.IDatabasePropertyService;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.DatabasePropertyVO;

@WebServlet("/08/jdbcDesc.do")
public class DatabasePropertyServlet extends HttpServlet {
	
	IDatabasePropertyService service = new DatabasePropertyService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String property_name = req.getParameter("property_name");
		String property_value = req.getParameter("property_value");
		String description = req.getParameter("description");
		DatabasePropertyVO paramVO = DatabasePropertyVO.builder()
									.property_name(property_name)
									.property_value(property_value)
									.description(description)
									.build();
		List<DatabasePropertyVO> list = service.retriveDatabaseProperty(paramVO);
		
		req.setAttribute("dbProps", list);
		
		String accept = req.getHeader("Accept");
		if(accept.contains("json")) {
			JsonResponseUtils.toJsonResponse(req, resp);
		}else {
			String logicalView = "08/jdbcDesc";
			req.getRequestDispatcher("/WEB-INF/views/" + logicalView + ".jsp").forward(req, resp);
		}
	}
}
