package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;

/**
 * Servlet implementation class ImageStreamingServlet
 */
@WebServlet("/imageView.do")
public class ImageStreamingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String COOKIENAME = "imageCookie";

	File folder;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String contentFolder = getServletContext().getInitParameter("contentFolder");
//		String contentFolder = config.getInitParameter("contentFolder");
		folder = new File(contentFolder);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(
				InputStream is = req.getInputStream();
			){
				ObjectMapper mapper = new ObjectMapper();
				String[] array = mapper.readValue(is, String[].class);
				String jsonValue = mapper.writeValueAsString(array);
				Cookie imageCookie =  CookieUtils.createCookie(COOKIENAME, jsonValue, 
						req.getContextPath(), TextType.PATH, 60*60*24*3);
				resp.addCookie(imageCookie);
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageName = request.getParameter("image");
		if(imageName==null || imageName.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		File imageFile = new File(folder, imageName);
		
		if(!imageFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		if(!getServletContext().getMimeType(imageName).startsWith("image")) {
			response.sendError(400);
			return;
		}
		
		// 파일의 제목을 가져와 MIME을 세팅해줌
		String mime = getServletContext().getMimeType(imageFile.getName());
		response.setContentType(mime);
		try(FileInputStream fis = new FileInputStream(imageFile);
				OutputStream os = response.getOutputStream();
		){
			byte[] buffer = new byte[1024];
			int cnt = -1;
			while((cnt = fis.read(buffer)) != -1) { //EOF 문자 : -1
				os.write(buffer, 0, cnt);
			}
		}catch(IOException e){
			
		}
	}

}
