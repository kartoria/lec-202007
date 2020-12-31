package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer 
 *
 */
public interface IProdDao {
	public int insertProd(ProdVO prod, SqlSession sqlSession);
	public int selectProdCount(PagingVO<ProdVO> pagingVO);
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	public ProdVO selectProd(String prod_id);
	public int updateProd(ProdVO prod);
}
