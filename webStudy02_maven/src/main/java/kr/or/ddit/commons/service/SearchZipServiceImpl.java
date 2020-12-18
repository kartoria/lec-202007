package kr.or.ddit.commons.service;

import java.util.List;

import kr.or.ddit.commons.dao.ISearchZipDao;
import kr.or.ddit.commons.dao.SearchZipDaoImpl;
import kr.or.ddit.vo.ZipVO;

public class SearchZipServiceImpl implements ISearchZipService{
	private ISearchZipDao zipDao = SearchZipDaoImpl.getInstance();
	private static ISearchZipService zipService;
	private SearchZipServiceImpl() {
		
	}
	
	public static ISearchZipService getInstance() {
		if(zipService == null) zipService = new SearchZipServiceImpl();
		return zipService;
	}



	@Override
	public List<ZipVO> retrieveZipList(String keyword) {
		return zipDao.selectZipList(keyword);
	}

}
