package kr.or.ddit.listner;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class WebApplicationContextInitializeListner {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationContextInitializeListner.class);
	
	@EventListener(classes=ContextRefreshedEvent.class)
	public void initialize(ContextRefreshedEvent event) {
		WebApplicationContext container = (WebApplicationContext) event.getApplicationContext();
		ServletContext application =  container.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		LOGGER.info("{} 컨텍스트 초기화, Root 컨테이너 초기화", application.getContextPath());
	}
}
