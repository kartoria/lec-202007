package kr.or.ddit.explorer;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ServerSideExplorerController{
	
	@RequestMapping("/server/explorer.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String base = req.getParameter("base");
		if(StringUtils.isBlank(base))
			base = "/";
 		
		List<FileWrapper> list =  makeData(base, req.getServletContext());
		
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
			return "others/explorer";
		}
		
	}

	private List<FileWrapper> makeData(String base, ServletContext application) {
		String realPath = application.getRealPath(base);
		Set<String> resourcePaths = application.getResourcePaths(base);
		List<FileWrapper> list = new ArrayList<>();
		if(resourcePaths!=null) {
			TreeSet<String> treeSet = new TreeSet<>(resourcePaths);
			for(String relativePath : treeSet) {
				String fileRealPath = application.getRealPath(relativePath);
				File child = new File(fileRealPath);
				list.add(new FileWrapper(child, relativePath));
			}
			Collections.sort(list);
		}
		return list;
	}
}
















