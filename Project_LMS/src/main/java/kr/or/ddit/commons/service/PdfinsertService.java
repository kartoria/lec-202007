package kr.or.ddit.commons.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.IPdfInsertDAO;
import kr.or.ddit.commons.vo.IssuedVO;

@Service
public class PdfinsertService {

	@Inject
	IPdfInsertDAO dao;
	
	//발급 기록 남기기
	public int insertPdfIssued(IssuedVO issuedVO) {
		return dao.insertPdfIssued(issuedVO);
	}
	
	
}
