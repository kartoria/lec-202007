package kr.or.ddit.dbprop.dao;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDataBasePropertyDAO {

	List<DataBasePropertyVO> selectDBProperties(DataBasePropertyVO paramVO);

}