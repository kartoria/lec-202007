package kr.or.ddit.servlet04;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.BtsVO;

@WebServlet(urlPatterns="/bts", loadOnStartup=1)
public class BTSServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(this.getClass().getSimpleName()+"초기화됨.");
		Map<String, BtsVO> btsDB = new LinkedHashMap<>();
		getServletContext().setAttribute("btsDB", btsDB);
		btsDB.put("B001", BtsVO.getBuilder().code("B001").name("뷔").url("/bts/bui").build());
		btsDB.put("B002", BtsVO.getBuilder().code("B002").name("제이홉").url("/bts/jhop").build());
		btsDB.put("B003", BtsVO.getBuilder().code("B003").name("지민").url("/bts/jimin").build());
		btsDB.put("B004", BtsVO.getBuilder().code("B004").name("진").url("/bts/jin").build());
		btsDB.put("B005", BtsVO.getBuilder().code("B005").name("정국").url("/bts/jungkuk").build());
		btsDB.put("B006", BtsVO.getBuilder().code("B006").name("RM").url("/bts/rm").build());
		btsDB.put("B007", BtsVO.getBuilder().code("B007").name("슈가").url("/bts/suga").build());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/btsForm.tiles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String selected = req.getParameter("bts");
		
		Date now = new Date();
		req.setAttribute("now", now);
		
		if(StringUtils.isBlank(selected)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Map<String, BtsVO> btsDB = (Map) req.getServletContext().getAttribute("btsDB");
		if(!btsDB.containsKey(selected)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		BtsVO selectedMember = btsDB.get(selected);
		req.getSession().setAttribute("selected", selectedMember);
		String logicalView = selectedMember.getUrl();
//		req.getRequestDispatcher("/WEB-INF/views" + view).forward(req, resp);
		req.getRequestDispatcher(logicalView+".tiles").forward(req, resp);
		
	}
}













