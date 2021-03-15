package kr.or.ddit.security;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.datasource.DataSourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.or.ddit.commons.dao.IScheduleDAO;
import kr.or.ddit.commons.vo.SPlanVO;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.MemberWrapper;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	@Inject
	IMemberDAO memberDAO;
	
	@Inject
	IScheduleDAO scheDAO;
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
	/***************** 아이디 저장 쿠키 만들기 ************************/
		String saveId = request.getParameter("saveId");
		String memId = authentication.getName();
		Cookie idCookie = new Cookie("idCookie", memId);
		idCookie.setPath(request.getContextPath());
		int maxAge = 0;
		if(StringUtils.isNotBlank(saveId)) {
			maxAge = 60*60*24*7;
		}
		idCookie.setMaxAge(maxAge);
		response.addCookie(idCookie);
	/*********************************************************/
		try{
			getSchedule(session); // 스케줄 세션에다 담아줌
		}catch(DataSourceException e) {
			LOGGER.error("학사일정 세션에 담는중에 오류 발생 >>> {}", e);
		}
		
		// 이전 페이지로 돌아가기
        if (session != null) {
        	String redirectUrl = (String) session.getAttribute("preUrl");
        	if(redirectUrl != null) {
        		session.removeAttribute("preUrl");
        		response.sendRedirect(redirectUrl);
        	}
        }
        MemberWrapper memberWrapper = (MemberWrapper) authentication.getPrincipal();
        MemberVO authMember = memberWrapper.getRealMember();
        if("ROLE_STUDENT".equals(authMember.getMemType())) session.setAttribute("memType", "학생");
        if("ROLE_PROFESSOR".equals(authMember.getMemType())) session.setAttribute("memType", "교수");
       
        if(authMember.getMemImg() != null) session.setAttribute("imagePath", request.getContextPath()+"/memberImages/"+authMember.getMemImg());
        else session.setAttribute("imagePath", request.getContextPath()+"/images/"+"defaultImg.jpg");
        
		super.handle(request, response, authentication);
	}
	
	/**
	 * 지금 시간이랑 db의 일정 비교해서 접근가능한 카테고리들만 세션 어트리뷰트로 담아줌
	 * 만약 수강신청일이 아니면 수강신청 세션값이 없는거고 
	 * 수강신청페이지에서 해당 세션 어트리뷰트가 없으면 못들어가게 만들면됨
	 * <<세션 키값>>
	 * INTER	관심등록기간
	 * TLEC		수강신청기간
	 * IGRADE	성적등록기간
	 * SGRADE	성적조회기간
	 * PAY		등록금 납부기간
	 * DIV1		분할납부 1차
	 * DIV2		분할납부 2차
	 * DIV3		분할납부 3차
	 */
	private void getSchedule(HttpSession session) throws DataSourceException{
		List<SPlanVO> scheduleList = scheDAO.getSchedule();
		
		Date nowDate = new Date();
		for(SPlanVO splan : scheduleList) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
			LOGGER.info("{} >>>>>>>>>>>>> {} ~ {}", splan.getSplanName(), dateFormat.format(splan.getSplanStart()), dateFormat.format(splan.getSplanEnd()));
			
			Date startDate = splan.getSplanStart();
			Date endDate = splan.getSplanEnd();
			
			if (nowDate.compareTo(startDate) >= 0 && nowDate.compareTo(endDate) <= 0) {
				session.setAttribute(splan.getSplanCode(), "OK");
			}
		}
	}
}





















