package kr.or.ddit.commons.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.ZipVO;

public class SearchZipDaoImplTest {
	private ISearchZipDao dao = SearchZipDaoImpl.getInstance();

	@Test
	public void testSelectZipList() {
		List<ZipVO> zipList = dao.selectZipList(null);
		assertNotEquals(0, zipList.size());
	}

}
