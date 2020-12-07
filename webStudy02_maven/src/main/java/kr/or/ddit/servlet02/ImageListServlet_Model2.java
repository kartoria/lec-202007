package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageList.do")
public class ImageListServlet_Model2 extends HttpServlet {
	ServletContext application;
	File folder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String contentFolder = application.getInitParameter("contentFolder");
		folder = new File(contentFolder);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] children = folder.list((dir, name)->{
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image");
		});
		String title = "이미지 목록";
		req.setAttribute("title", new Date());
		req.setAttribute("imageFiles", children);
		req.getRequestDispatcher("/WEB-INF/views/imageList.jsp").forward(req, resp);
	}
}
