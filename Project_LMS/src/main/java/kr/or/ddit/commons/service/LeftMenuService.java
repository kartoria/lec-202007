package kr.or.ddit.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.ILeftMenuDAO;
import kr.or.ddit.commons.vo.LeftMenuVO;

@Service
public class LeftMenuService {

	@Inject
	private ILeftMenuDAO leftMenuDAO;
	
	public List<LeftMenuVO> getMenuList(LeftMenuVO menuVO){
		return leftMenuDAO.getMenuList(menuVO);
	}
}
