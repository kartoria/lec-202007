package kr.or.ddit.dbprop.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDataBasePropertyService {
	public List<DataBasePropertyVO> retrieveDataBaseProperty(DataBasePropertyVO paramVO);
}
