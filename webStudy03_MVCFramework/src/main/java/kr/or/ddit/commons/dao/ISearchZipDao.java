package kr.or.ddit.commons.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public interface ISearchZipDao {
	public List<ZipVO> selectZipList(PagingVO<ZipVO> pagingVO);

	public int selectZipCount(PagingVO<ZipVO> pagingVO);
}
