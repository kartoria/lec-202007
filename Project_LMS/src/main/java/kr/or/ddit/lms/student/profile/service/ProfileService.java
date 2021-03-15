package kr.or.ddit.lms.student.profile.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.lms.student.profile.dao.IProfileDAO;
import kr.or.ddit.member.vo.MemberVO;

@Service
public class ProfileService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);
	@Inject
	private WebApplicationContext container;
	
	@Inject
	IProfileDAO profileDao;
	
	@Value("#{appInfo['memberImages']}")
	private String imagePath;
	
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
	
	
	
	//학생 프로필 조회
	public ProfileVO profileView(MemberVO authMember) {
		return profileDao.profileView(authMember);
	}
	
	//학생 프로필 수정 시 정보 받아오기
	public ProfileVO profileUpdateView(MemberVO authMember) {
		return profileDao.profileUpdateView(authMember);
	}
	
	
	
	//프로필 업데이트
	@Transactional
	public boolean profileUpdate(ProfileVO profileVO) {
		int rownum = profileDao.profileUpdate(profileVO);
		boolean result = false; 
		if(rownum>0) {
			try {
				profileVO.saveTo(saveFolder);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			result = true;
		}
		return result;
	}


}
