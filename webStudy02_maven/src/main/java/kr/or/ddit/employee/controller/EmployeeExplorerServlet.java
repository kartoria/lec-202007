package kr.or.ddit.employee.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.employee.service.EmployeeServiceImpl;
import kr.or.ddit.employee.service.IEmployeeService;
import kr.or.ddit.explorer.FileWrapper;
import kr.or.ddit.vo.EmployeeVO;

@WebServlet("/server/Employee.do")
public class EmployeeExplorerServlet extends HttpServlet {
	private IEmployeeService empService = new EmployeeServiceImpl();
	private ServletContext application;

	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String base = req.getParameter("base");
//		if (StringUtils.isBlank(base))
//			base = "/";
		List<String> list = makeData();
		String accept = req.getHeader("Accept");
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			try(PrintWriter out = resp.getWriter()){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, list);
			}
		} else {
			req.setAttribute("children", list);
			String logicalView = "others/employee";
			req.getRequestDispatcher("/" + logicalView + ".tiles").forward(req, resp);
		}
	}

	private List<String> makeData() {
		Set<EmployeeVO> empSet = empService.selectEmployee();
		List<String> empNameList = new ArrayList<>();
		if(empSet.size() > 0) {
			for (EmployeeVO empVO : empSet) {
				String empName = empVO.getEmp_name();
				empNameList.add(empName);
			}
			Collections.sort(empNameList);
		}
		return empNameList;
	}
}
