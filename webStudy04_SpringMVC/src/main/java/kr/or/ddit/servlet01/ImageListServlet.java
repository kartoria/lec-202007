package kr.or.ddit.servlet01;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class ImageListServlet
 */
//@WebServlet("/01/imageList.tmpl")
public class ImageListServlet extends UseTemplateServlet {
	File folder;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String contentFolder = getServletContext().getInitParameter("contentFolder");
//		String contentFolder = config.getInitParameter("contentFolder");
		folder = new File(contentFolder);
	}

	@Override
	public String getMimeType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	public void getDataMap(Map<String, Object> dataMap, HttpServletRequest req) {
		String[] imageList =  folder.list((dir, name)->{
			String mime = getServletContext().getMimeType(name);
			return mime!=null && mime.startsWith("image");
		});
		String pattern = "<option value='%1$s'>%1$s</option>";
		StringBuffer options = new StringBuffer("");
		for(String filename : imageList) {
			options.append( String.format(pattern, filename) );
		}
		
		dataMap.put("title", "이미지 목록");
		dataMap.put("optionData", options);
		dataMap.put("today", new Date());
		
	}

}












