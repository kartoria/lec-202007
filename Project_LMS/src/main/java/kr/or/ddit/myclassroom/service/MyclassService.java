package kr.or.ddit.myclassroom.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.myclassroom.dao.MyclassDAO;
import kr.or.ddit.myclassroom.vo.MyclassVO;

/**
 * @author 김선준
 * @since 2021. 3. 2.
 */
@Service
public class MyclassService extends BaseController{
	@Inject
	MyclassDAO myclassDAO;
	
	/** 마이클래스 입장할때 교수는 본인이 맡은 강의만 들어가고 학생은 수강중인 강의만 들어갈수 있게 체크 **/
	public boolean myclassAuthorization(MyclassVO myclassVO){
		String memType = myclassVO.getMemberVO().getMemType();
		if("ROLE_ADMIN".equals(memType) || myclassDAO.myclassAuthorization(myclassVO) > 0) 
			return true;
		return false;
	}
	
	/** 해당 강의가 존재하는지 체크 **/
	public boolean lectureCheck(MyclassVO myclassVO) {
		if(myclassDAO.lectureCheck(myclassVO) > 0)
			return true;
		return false;
	}
	
	public List<MyclassVO> getMylectureList(MyclassVO myclassVO){
		return myclassDAO.getMylectureList(myclassVO);
	}
	
	public MyclassVO getSubName(MyclassVO myclassVO) {
		printInfo("subNameVO", myclassVO);
		return myclassDAO.getSubName(myclassVO);
	}
}
