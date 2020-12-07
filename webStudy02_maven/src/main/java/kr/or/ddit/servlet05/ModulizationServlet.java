package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceKind;

@WebServlet("/module.do")
public class ModulizationServlet extends HttpServlet{
   
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   String service = req.getParameter("service");
	   int status = HttpServletResponse.SC_OK;
	   if(StringUtils.isNotBlank(service)) {
		   try {
			   ServiceKind serviceKind = ServiceKind.valueOf(service);
			   String includePath = serviceKind.getMenu().getMenuPath();
			   req.setAttribute("includePath", includePath);
		   }catch(Exception e) {
			   status = HttpServletResponse.SC_NOT_FOUND;
		   }
	   }
	   if(status==HttpServletResponse.SC_OK) {
		   req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
	   }else {
		   resp.sendError(status);
	   }
	   
   }
   
}