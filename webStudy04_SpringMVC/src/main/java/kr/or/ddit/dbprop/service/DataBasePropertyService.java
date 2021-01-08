package kr.or.ddit.dbprop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.dbprop.dao.IDataBasePropertyDAO;
import kr.or.ddit.vo.DataBasePropertyVO;
@Service
public class DataBasePropertyService implements IDataBasePropertyService{
	private IDataBasePropertyDAO dao; 

	@Override
	public List<DataBasePropertyVO> retrieveDataBaseProperty(DataBasePropertyVO paramVO) {
		List<DataBasePropertyVO> list = dao.selectDBProperties(paramVO);
		
		for(DataBasePropertyVO tmp : list) {
			String tName = Thread.currentThread().getName();
			tmp.setProperty_value(String.format("%s[%s]", tmp.getProperty_value(), tName));
			System.out.println("메일 발송");
		}
		
		return list;
	}
	
}













