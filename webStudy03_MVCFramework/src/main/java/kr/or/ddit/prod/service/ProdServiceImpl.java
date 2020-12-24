package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.CustomException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService{
	private IProdDao dao;
	private static IProdService self;
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getInstance();
	}
	public static IProdService getInstance() {
		if(self == null) self = new ProdServiceImpl();
		return self;
	}
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		if(dao.insertProd(prod) == 0) 
			return ServiceResult.FAILED;
		else 
			return ServiceResult.OK;
	}

	@Override
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO) {
		return dao.selectProdCount(pagingVO);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if(prod == null)
			throw new CustomException();
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		return null;
	}

}
