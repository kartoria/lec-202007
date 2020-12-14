package kr.or.ddit.db.service;

import java.util.List;

import kr.or.ddit.dbprop.dao.DatabasePropertyDao;
import kr.or.ddit.vo.DatabasePropertyVO;

public class DatabasePropertyService implements IDatabasePropertyService {
	DatabasePropertyDao dao = new DatabasePropertyDao();

	@Override
	public List<DatabasePropertyVO> retriveDatabaseProperty(DatabasePropertyVO paramVO) {
		List<DatabasePropertyVO> list = dao.selectDBProperties(paramVO);
		for(DatabasePropertyVO tmp : list) {
			String tName = Thread.currentThread().getName();
			tmp.setProperty_value(String.format("%s[%s]", tmp.getProperty_value(), tName));
		}
		return list;
	}
}
