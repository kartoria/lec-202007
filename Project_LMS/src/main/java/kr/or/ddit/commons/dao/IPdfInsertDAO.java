package kr.or.ddit.commons.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.commons.vo.IssuedVO;

@Repository
public interface IPdfInsertDAO {

	int insertPdfIssued(IssuedVO issuedVO);

}
