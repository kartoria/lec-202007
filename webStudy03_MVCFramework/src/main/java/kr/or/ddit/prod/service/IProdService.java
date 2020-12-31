package kr.or.ddit.prod.service;

import java.io.IOException;
import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Business Logic Layer 
 */
public interface IProdService {
	public ServiceResult createProd(ProdVO prod) throws IOException;
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO);
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO);
	
	/**
	 * 상품 상세 조회
	 * @param prod_id
	 * @return 존재하지 안흔ㄴ다면, custom execption 발생
	 */
	public ProdVO retrieveProd(String prod_id);
	
	/**
	 * 상품 정보 수정
	 * @param prod
	 * @return 존재하지 않는다면, custom exception 발생, OK, FAILED
	 */
	public ServiceResult modifyProd(ProdVO prod);
}
