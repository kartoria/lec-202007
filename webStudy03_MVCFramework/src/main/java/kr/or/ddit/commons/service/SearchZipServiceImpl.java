package kr.or.ddit.commons.service;

import java.util.List;

import kr.or.ddit.commons.dao.ISearchZipDao;
import kr.or.ddit.commons.dao.SearchZipDaoImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public class SearchZipServiceImpl implements ISearchZipService{
	private ISearchZipDao zipDao;
	private static ISearchZipService zipService;
	private SearchZipServiceImpl() {
		zipDao = SearchZipDaoImpl.getInstance();
	}
	public static ISearchZipService getInstance() {
		if(zipService == null) zipService = new SearchZipServiceImpl();
		return zipService;
	}
	@Override
	public int retrieveZipCount(PagingVO<ZipVO> pagingVO) {
		return zipDao.selectZipCount(pagingVO);
	}
	@Override
	public List<ZipVO> retrieveZipList(PagingVO<ZipVO> pagingVO) {
		return zipDao.selectZipList(pagingVO);
	}


}
