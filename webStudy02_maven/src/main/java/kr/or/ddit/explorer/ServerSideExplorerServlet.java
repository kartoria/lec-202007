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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/server/explorer.do")
public class ServerSideExplorerServlet extends HttpServlet {

	private ServletContext application;

	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String base = req.getParameter("base");
		if (StringUtils.isBlank(base))
			base = "/";
		List<FileWrapper> list = makeData(base);
		String accept = req.getHeader("Accept");
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			try(PrintWriter out = resp.getWriter()){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, list);
			}
		} else {
			req.setAttribute("children", list);
			String logicalView = "others/explorer";
			req.getRequestDispatcher("/" + logicalView + ".tiles").forward(req, resp);
		}
	}

	private List<FileWrapper> makeData(String base) {
		String realPath = application.getRealPath(base);
		Set<String> resourcePaths = application.getResourcePaths(base);
		List<FileWrapper> list = new ArrayList<>();
		if(resourcePaths != null) {
			TreeSet<String> treeSet = new TreeSet<>(resourcePaths);
			for (String realativePath : treeSet) {
				String fileRealPath = application.getRealPath(realativePath);
				File child = new File(fileRealPath);
				list.add(new FileWrapper(child, realativePath));
			}
			Collections.sort(list);
		}
		return list;
	}
}
