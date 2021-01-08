package kr.or.ddit.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.ISearchZipDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

@Service
public class SearchZipServiceImpl implements ISearchZipService {
	
	@Inject
	private ISearchZipDAO dao;
	
	@Override
	public int retrieveZipCount(PagingVO<ZipVO> pagingVO) {
		return dao.selectZipCount(pagingVO);
	}
	
	@Override
	public List<ZipVO> retrieveZipList(PagingVO<ZipVO> pagingVO) {
		return dao.selectZipList(pagingVO);
	}

}
