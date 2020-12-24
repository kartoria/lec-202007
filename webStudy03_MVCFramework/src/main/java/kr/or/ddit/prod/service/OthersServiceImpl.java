package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.dao.IOthersDao;
import kr.or.ddit.prod.dao.OthersDaoImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LProdVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class OthersServiceImpl implements IOthersService{
	private static IOthersService self;
	private IOthersDao dao;
	private OthersServiceImpl() {
		dao = OthersDaoImpl.getInstance();
	}
	
	public static IOthersService getInstance(){
		if(self == null) self = new OthersServiceImpl();
		return self;
	}
	
	@Override
	public List<BuyerVO> retrieveBuyerList() {
		return dao.selectBuyerList();
	}

	@Override
	public List<Map<String, Object>> retrieveLProdList() {
		return dao.selectLProdList();
	}

}
