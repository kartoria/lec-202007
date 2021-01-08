package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BuyerVO;

/**
 * option UI 생성용 persistence Layer
 *
 */
@Repository
public interface IOthersDAO {
	public List<Map<String, Object>> selectLprodList();
	public List<BuyerVO> selectBuyerList();
}


















