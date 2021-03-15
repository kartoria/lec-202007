package kr.or.ddit.interceptor;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.CustomException;
import kr.or.ddit.commons.vo.LeftMenuVO;
import kr.or.ddit.cyber.service.CyberService;
import kr.or.ddit.lms.student.grade.vo.GradeVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.MemberWrapper;
import kr.or.ddit.myclassroom.service.MyclassService;
import kr.or.ddit.myclassroom.vo.MyclassVO;
import kr.or.ddit.vo.LectureVO;

public class MyClassInterceptor extends HandlerInterceptorAdapter{
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Inject 
	MyclassService myclassService;
	
	@Inject
	WebApplicationContext container;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String lecCode = null;
		// 경로변수를 담은 Map에서 lecCode를 꺼낸다
		MemberVO memberVO = ((MemberWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		
		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(MapUtils.isNotEmpty(pathVariables)) {
			lecCode = pathVariables.get("lecCode");
			LOGGER.info("pathVariables" + lecCode);
			
			if(StringUtils.isNotBlank(lecCode)) {
				boolean lectureCheck = myclassService.lectureCheck(MyclassVO.builder().lecCode(lecCode).build());
				if(!lectureCheck) {
					response.sendError(404, "존재하지 않는 강의실입니다.");
					return false;
				}
				boolean authorizationCheck = myclassService.myclassAuthorization(MyclassVO.builder().memberVO(memberVO).lecCode(lecCode).build());
				if(!authorizationCheck) throw new AccessDeniedException("권한이 없습니다.");
			}
			MyclassVO myclassVO = myclassService.getSubName(MyclassVO.builder().lecCode(lecCode).build());
			request.setAttribute("subName", myclassVO.getSubName());
			request.setAttribute("professorId", myclassVO.getMemId());
		}
		
		List<MyclassVO> myLectureList =  myclassService.getMylectureList(MyclassVO.builder().memberVO(memberVO).build());
		
		ServletContext application = container.getServletContext();
		
		List<LeftMenuVO> perentMenuList = (List<LeftMenuVO>) application.getAttribute("MyClassPerentMenuList");
		List<LeftMenuVO> childMenuList = (List<LeftMenuVO>) application.getAttribute("MyClassChildMenuList");
		
		request.setAttribute("myLectureList", myLectureList);
		request.setAttribute("perentMenuList", perentMenuList);
		request.setAttribute("childMenuList", childMenuList);
		request.setAttribute("main", "myclass/"+lecCode+"/dashList.do");
		
		return super.preHandle(request, response, handler);
	}
}
