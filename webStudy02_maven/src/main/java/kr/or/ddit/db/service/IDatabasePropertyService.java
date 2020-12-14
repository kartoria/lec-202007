package kr.or.ddit.db.service;

import java.util.List;

import kr.or.ddit.vo.DatabasePropertyVO;

public interface IDatabasePropertyService {
	public List<DatabasePropertyVO> retriveDatabaseProperty(DatabasePropertyVO paramVO);
}
