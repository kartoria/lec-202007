package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.CustomException;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService{
	private IProdDao dao;
	private static ProdServiceImpl self;
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getInstance();
	}
	public static ProdServiceImpl getInstance() {
		if(self == null) self = new ProdServiceImpl();
		return self;
	}
	
	private File saveFolder;
	public void setSaveFolder(File saveFolder) {
		this.saveFolder = saveFolder;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public ServiceResult createProd(ProdVO prod){
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			if(dao.insertProd(prod, null) > 0) {
				prod.saveTo(saveFolder);
				sqlSession.commit();
				return ServiceResult.OK;
			}else { 
				return ServiceResult.FAILED;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO) {
		return dao.selectProdCount(pagingVO);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if(prod == null) throw new CustomException(prod_id+"에 해당하는 상품 없음.");
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		try {
			if(prod == null) throw new CustomException();
			if(dao.updateProd(prod) > 0) {
				prod.saveTo(saveFolder);
				return ServiceResult.OK;
			}else 
				return ServiceResult.FAILED;
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
