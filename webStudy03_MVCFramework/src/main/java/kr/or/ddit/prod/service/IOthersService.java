package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LProdVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public interface IOthersService {
	
	public List<BuyerVO> retrieveBuyerList();
	public List<Map<String, Object>> retrieveLProdList();
}
