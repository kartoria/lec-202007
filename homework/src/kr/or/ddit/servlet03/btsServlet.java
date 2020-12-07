package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import kr.or.ddit.vo.BtsVO;

@WebServlet(urlPatterns="/bts", loadOnStartup=1)
public class btsServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println(this.getClass().getSimpleName()+"초기화됨.");
		Map<String, BtsVO> btsDB = new LinkedHashMap<>();
		getServletContext().setAttribute("btsDB", btsDB);
		btsDB.put("B001", BtsVO.getBuilder().code("B001").name("부이").url("/bts/bui.jsp").build());
		btsDB.put("B002", BtsVO.getBuilder().code("B002").name("즈홉").url("/bts/jhop.jsp").build());
		btsDB.put("B003", BtsVO.getBuilder().code("B003").name("지민").url("/bts/jimin.jsp").build());
		btsDB.put("B004", BtsVO.getBuilder().code("B004").name("진").url("/bts/jin.jsp").build());
		btsDB.put("B005", BtsVO.getBuilder().code("B005").name("정국").url("/bts/jungkuk.jsp").build());
		btsDB.put("B006", BtsVO.getBuilder().code("B006").name("RM").url("/bts/rm.jsp").build());
		btsDB.put("B007", BtsVO.getBuilder().code("B007").name("수가").url("/bts/suga.jsp").build());
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String path = "D:\\A_TeachingMaterial\\10.gitRepository\\lec-202007\\webStudy01\\WebContent\\WEB-INF\\views\\bts";
//		File dir = new File(path);
//		String[] fileList = dir.list();
//		List<String> btsList = new ArrayList<>();
//		for(String filename : fileList) {
//			if("jsp".equals(FilenameUtils.getExtension(filename))) {
//				filename = filename.replace(".jsp", "");
//				btsList.add(filename);
//			};
//		}
//		req.setAttribute("btsList", btsList);
		req.getRequestDispatcher("/WEB-INF/views/btsForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String selected = (String)req.getParameter("bts");
		
		Date now = new Date();
		req.setAttribute("now", now);
		
		Map<String, BtsVO> btsDB = (Map) req.getServletContext().getAttribute("btsDB");
		if(!btsDB.containsKey(selected)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		BtsVO selectedMember = btsDB.get(selected);
		req.getSession().setAttribute("selected", selectedMember);
		String view = selectedMember.getUrl();
		req.getRequestDispatcher("/WEB-INF/views" + view).forward(req, resp);
	}
}
