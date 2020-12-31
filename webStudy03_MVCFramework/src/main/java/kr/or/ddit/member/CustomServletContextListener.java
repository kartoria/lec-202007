package kr.or.ddit.member;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.tiles.web.startup.simple.SimpleTilesListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;

public class CustomServletContextListener extends SimpleTilesListener{
	Logger LOGGER = LoggerFactory.getLogger(CustomServletContextListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		super.contextInitialized(sce);
		ServletContext application = sce.getServletContext();
		sce.getServletContext().setAttribute("cPath", application.getContextPath());
		String saveFolderUrl = "/prodImages";
		ProdServiceImpl service = ProdServiceImpl.getInstance();
		File saveFolder = new File(sce.getServletContext().getRealPath(saveFolderUrl));
		service.setSaveFolder(saveFolder);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		LOGGER.info("{} 가 초기화 되면서 saveFolder를 ProdServiceImpl에 넣었음", getClass().getSimpleName());
	}
	
}
