package kr.or.ddit.listener;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.commons.service.LeftMenuService;
import kr.or.ddit.commons.vo.LeftMenuVO;

@Component
public class WebApplicationContextInitializeListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationContextInitializeListener.class);
	
	@Inject
	private LeftMenuService leftMenuService;
	
	
	@EventListener(classes=ContextRefreshedEvent.class)
	public void initialize(ContextRefreshedEvent event) {
		WebApplicationContext container =  (WebApplicationContext) event.getApplicationContext();
		ServletContext application = container.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		LOGGER.info("{} 컨텍스트  초기화, Root 컨테이너 초기화.", application.getContextPath());
		
		List<LeftMenuVO> lmsStudentPerentMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/lms/student").build());
		List<LeftMenuVO> lmsStudentChildMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/lms/student").menuPerent(0).build());
		
		List<LeftMenuVO> lmsProfessorPerentMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/lms/professor").build());
		List<LeftMenuVO> lmsProfessorChildMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/lms/professor").menuPerent(0).build());
		
		List<LeftMenuVO> cyberPerentMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/cyber").build());
		
		List<LeftMenuVO> adminPerentMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/admin").build());
		List<LeftMenuVO> adminChildMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/admin").menuPerent(0).build());
		
		List<LeftMenuVO> myclassPerentMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/myclass").build());
		List<LeftMenuVO> myClassChildMenuList = leftMenuService.getMenuList(LeftMenuVO.builder().menuUri("/myclass").menuPerent(0).build());
		
		application.setAttribute("lmsStudentPerentMenuList", lmsStudentPerentMenuList);
		application.setAttribute("lmsStudentChildMenuList", lmsStudentChildMenuList);
		
		application.setAttribute("lmsProfessorPerentMenuList", lmsProfessorPerentMenuList);
		application.setAttribute("lmsProfessorChildMenuList", lmsProfessorChildMenuList);
		
		application.setAttribute("cyberPerentMenuList", cyberPerentMenuList);
		
		application.setAttribute("adminPerentMenuList", adminPerentMenuList);
		application.setAttribute("adminChildMenuList", adminChildMenuList);
		
		application.setAttribute("MyClassPerentMenuList", myclassPerentMenuList);
		application.setAttribute("MyClassChildMenuList", myClassChildMenuList);
		
		
	}
}




















