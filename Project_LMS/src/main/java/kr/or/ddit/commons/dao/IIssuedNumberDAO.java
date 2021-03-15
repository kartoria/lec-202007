package kr.or.ddit.commons.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.commons.vo.IssuedVO;

@Repository
public interface IIssuedNumberDAO {

	public IssuedVO selectIssuedNumber(IssuedVO issuedVO) ;

	public int selectIssuedMaxNumber();
	

}
