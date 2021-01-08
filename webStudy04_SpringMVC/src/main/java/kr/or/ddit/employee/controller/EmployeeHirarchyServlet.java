package kr.or.ddit.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.employee.dao.IEmployeeDAO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class EmployeeHirarchyServlet extends HttpServlet{
	IEmployeeDAO dao;
	
//	private  ServletContext application;
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		application = getServletContext();
//	}
	
	@RequestMapping("/employees/hirarchy.do")
	public String doGet(@RequestParam(value="base", required=false) String manager_id, 
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<EmployeeVO> list = dao.selectEmployeeHierarchy(manager_id);
		
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = resp.getWriter();	
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, list);
			}
			return null;
		}else {
			req.setAttribute("children", list);
			return "employee/hirarchy";
		}
		
	}
}
















