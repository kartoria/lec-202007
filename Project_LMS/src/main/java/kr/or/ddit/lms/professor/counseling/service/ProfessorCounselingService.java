/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.lms.professor.counseling.service;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.lms.professor.counseling.dao.IProfessorCounselingDAO;
import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.lms.student.profile.dao.IProfileDAO;
import kr.or.ddit.lms.student.profile.service.ProfileService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Service
public class ProfessorCounselingService {
	private static final Logger logger = LoggerFactory.getLogger(ProfessorCounselingService.class);
	
	@Inject
	private WebApplicationContext container;
	
	@Inject
	IProfileDAO profileDao;
	
	@Value("#{appInfo['memberImages']}")
	private String imagePath;
	
	@Inject
	private IProfessorCounselingDAO dao;
	
	private File saveFolder;
	public void setSaveFolder(File saveFolder) {
		this.saveFolder = saveFolder;
	}
	
	@PostConstruct
	public void init() {
		saveFolder = new File( container.getServletContext().getRealPath(imagePath));
		if(saveFolder!=null && !saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		logger.info("{} ", saveFolder.getAbsolutePath());
	}
	
	public int retrieveCounselingApplyCount(PagingVO<CounselingVO> paging){
		return dao.selectCounselingApplyCount(paging);
	}
	
	public int retrieveCounselingDoneCount(PagingVO<CounselingVO> paging) {
		return dao.selectCounselingDoneCount(paging);
	}
	
	public List<CounselingVO> retrieveCounselingApplyList(PagingVO<CounselingVO> paging){
		return dao.selectCounselingApplyList(paging);
	}
	
	public int updateCounselingCstNote(CounselingVO coun) {
		return dao.updateCounselingCstNote(coun);
	}
	
	public List<CounselingVO> selectCounselingDoneList(PagingVO<CounselingVO> paging){
		return dao.selectCounselingDoneList(paging);
	}
	
	public CounselingVO selectDetailCounseling(CounselingVO coun) {
		return dao.selectDetailCounseling(coun);
	}
	
	public List<CounselingVO> selectChooseCounselingDate(CounselingVO coun){
		return dao.selectChooseCounselingDate(coun);
	}
	
	public int updateCounseling(CounselingVO coun) {
		return dao.updateCounseling(coun);
	}
	
	public CounselingVO chooseDateCounseling(CounselingVO coun) {
		return dao.chooseDateCounseling(coun);
	}
}
