package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.servlet01.ImageStreamingServlet;
import kr.or.ddit.utils.CookieUtils;

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
		preProcessWithCookie(req);
		String[] children = folder.list((dir, name)->{
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image");
		});
		
		String title = "이미지 목록";
		req.setAttribute("title", title);
		req.setAttribute("today", new Date());
		req.setAttribute("imageFiles", children);
		req.getRequestDispatcher("/imageList.tiles").forward(req, resp);
	}

	private void preProcessWithCookie(HttpServletRequest req) throws IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies == null) return;
		CookieUtils cookieUtils = new CookieUtils(req);
		String jsonValue = cookieUtils.getCookieValue(ImageStreamingServlet.COOKIENAME);
		if (jsonValue!=null) {
			// 언마샬링
			String[] array = new ObjectMapper().readValue(jsonValue, String[].class);
			req.setAttribute("array", array);
		}
	}
	
}
