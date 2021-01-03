package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver implements IViewResolver {

	@Override
	public void viewResolve(String goPage, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		boolean redirect = goPage.startsWith("redirect:");
		boolean forward = goPage.startsWith("forward:");
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage.substring("redirect:".length()));
		}else if(forward){
			req.getRequestDispatcher(goPage.substring("forward:".length())).forward(req, resp);
		}else{	
			try {
				req.getRequestDispatcher("/"+goPage+".tiles").forward(req, resp);
			}catch (Exception e) {
				req.getRequestDispatcher("/WEB-INF/views/"+goPage+".jsp").forward(req, resp);
			}
		}

	}

}
