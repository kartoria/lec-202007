package kr.or.ddit.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.commons.vo.LeftMenuVO;

@Repository
public interface ILeftMenuDAO {

	public List<LeftMenuVO> getMenuList(LeftMenuVO menuVO);
}
