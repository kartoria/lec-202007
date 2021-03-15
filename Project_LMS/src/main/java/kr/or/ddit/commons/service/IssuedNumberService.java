package kr.or.ddit.commons.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.IIssuedNumberDAO;
import kr.or.ddit.commons.vo.IssuedVO;

@Service
public class IssuedNumberService {

	@Inject IIssuedNumberDAO dao;

	public IssuedVO selectIssuedNumber(IssuedVO issuedVO) {
		return dao.selectIssuedNumber(issuedVO);
	}

	public int selectIssuedMaxNumber() {
		return dao.selectIssuedMaxNumber();
	}
	
}
