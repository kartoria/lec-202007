package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LProdVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public interface IOthersDao {
	public List<BuyerVO> selectBuyerList();
	public List<Map<String, Object>> selectLProdList();
}
