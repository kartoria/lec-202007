package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mime.do")
public class MimeDescriptionServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		MIME : Multipurposed Internet Mail Extension 
//		형식    : main_type/sub_type;charset=encoding
//		예시    : text/html, text/javascript, text/plain, image/jpeg, video/mp4 ...
		String mime = "text/plain; charset=UTF-8";
		resp.setContentType(mime);
		String data = "안녕 서블릿";
		String html = "<html>"
					+ "<body>"
					+ "<h1>"+ data +"</h1>"
					+ "</body>"
					+ "</html>";
		
//		PrintWriter out = null;
//		try {
//			out = resp.getWriter();
//			out.println(html);
//		}catch (Exception e) {
//		}finally {
//			if(out != null) out.close();
//		}
		
		try(		
			//Closable 객체 생성 코드
			PrintWriter out = resp.getWriter()
		){
			out.println(html);
		}catch(Exception e) {
			// TODO: handle exception
		}
	}
}
