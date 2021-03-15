package kr.or.ddit.myclassroom.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.myclassroom.vo.MyclassVO;

@Repository
public interface MyclassDAO {

	public int myclassAuthorization(MyclassVO myclassVO);
	
	public int lectureCheck(MyclassVO myclassVO);
	
	public List<MyclassVO> getMylectureList(MyclassVO myclassVO);

	public MyclassVO getSubName(MyclassVO myclassVO);
}
