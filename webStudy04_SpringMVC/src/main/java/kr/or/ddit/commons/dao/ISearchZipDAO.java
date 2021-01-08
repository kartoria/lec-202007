package kr.or.ddit.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

@Repository
public interface ISearchZipDAO {
	public int selectZipCount(PagingVO<ZipVO> pagingVO);
	public List<ZipVO> selectZipList(PagingVO<ZipVO> pagingVO);
}
