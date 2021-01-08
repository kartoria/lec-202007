package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.CustomException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
@Service
public class ProdServiceImpl implements IProdService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdServiceImpl.class);
	@Inject
	private WebApplicationContext container;
	@Inject
	private IProdDAO dao;

	@Value("#{appInfo['prodImages']}")
	private String imagePath;
	private File saveFolder;
	
	// 위에서 세이브폴더 파일을 주입한 다음 메소드를 실행하기 위한 어노테이션
	@PostConstruct
	public void init() {
		saveFolder = new File(container.getServletContext().getRealPath(imagePath));
		if(saveFolder!=null && !saveFolder.exists()) saveFolder.mkdirs(); 
		LOGGER.info("{}", saveFolder.getAbsolutePath());
	}

	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		ServiceResult result = ServiceResult.FAILED;
		if(rowcnt>0) {
			try {
				prod.saveTo(saveFolder);
				result = ServiceResult.OK;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
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
		if(prod == null) throw new CustomException(prod_id+"에 해당하는 상품이 없음.");
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		try {
			int rowcnt = dao.updateProd(prod);
			ServiceResult result = ServiceResult.FAILED;
			if(rowcnt>0) {
				prod.saveTo(saveFolder);
				result = ServiceResult.OK;
			}
			return result;
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}














