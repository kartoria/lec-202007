package kr.or.ddit.admin.professor.service;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.admin.professor.dao.IAdminProfessorDAO;
import kr.or.ddit.admin.student.vo.AdminMemVO;

@Service
public class AdminProfessorService {
	@Inject
	private WebApplicationContext container;
	@Inject
	private IAdminProfessorDAO proDAO;
	
	@Value("#{appInfo.memberImages}")
	private String imagePath;
	
	private File saveFolder;
	public void setSaveFolder(File saveFolder) {
		this.saveFolder = saveFolder;
	}
	// 인젝션이 끝난후 폴더를생성해야함
	@PostConstruct
	public void init() {
		saveFolder = new File(container.getServletContext().getRealPath(imagePath));
		if(saveFolder==null && !saveFolder.exists()) saveFolder.mkdirs();
	}
	
	
	//교수등록
	@Transactional
	public String createProfessor(AdminMemVO memVO) {
		int cnt = 0;
		String memId=null;
		cnt = proDAO.insertProfessor(memVO);
		if(cnt>0) {
			try {
				memVO.saveTo(saveFolder);
				memId=memVO.getMemId();
			} catch (Exception e) {
				cnt = 0;
				throw new RuntimeException(e);
			}
			
		}
		return memId;
	}

	public AdminMemVO retrieveProfessor(AdminMemVO memVO) {
		return proDAO.selectProfessor(memVO);
	}

	public int modifyProfessor(AdminMemVO memVO) {
		int cnt = 0;
		cnt = proDAO.updateProfessor(memVO);
		if(cnt>0) {
			try {
				memVO.saveTo(saveFolder);
			} catch (Exception e) {
				cnt = 0;
				throw new RuntimeException(e);
			}
			
		}
		return cnt;
	}
}
