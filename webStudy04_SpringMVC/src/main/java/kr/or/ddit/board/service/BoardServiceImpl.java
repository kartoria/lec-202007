package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.CustomException;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class BoardServiceImpl implements IBoardService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class); 
	@Inject
	private IBoardDAO boardDAO;
	@Inject
	private IAttatchDAO attDAO;
	
	@Value("#{appInfo['boardFiles']}")
	private File saveFolder;
	@PostConstruct
	public void init() {
		LOGGER.info("boardService saveFolder 경로** : {}", saveFolder.getAbsolutePath());
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
	}
	
	private void encodePassword(BoardVO board) {
		String encoded = SecurityUtils.encryptSha512(board.getBo_pass());
		board.setBo_pass(encoded);
	}

	@Transactional
	@Override
	public ServiceResult createBoard(BoardVO board) {
		encodePassword(board);
		int cnt = boardDAO.insertBoard(board);
		if(cnt > 0) {
			cnt += processAttatches(board);
		}
		ServiceResult result = null;
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	private int processAttatches(BoardVO board) {
		List<AttatchVO> attatchList = board.getAttatchList();
		int cnt = 0;
		if(attatchList != null && !attatchList.isEmpty() ) {
			cnt += attDAO.insertAttaches(board);
			try {
				for(AttatchVO attatch : attatchList) {
					attatch.saveTo(saveFolder);
				}	
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cnt;
	}

	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> paging) {
		return boardDAO.selectBoardCount(paging);
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> paging) {
		return boardDAO.selectBoardList(paging);
	}

	@Override
	public BoardVO retrieveBoard(int bo_no) {
		BoardVO board = boardDAO.selectBoard(bo_no);
		if(board==null) throw new CustomException(bo_no+"번 글이 없음.");
		boardDAO.incrementHit(bo_no);
		return board;
	}

	@Override
	public ServiceResult incrementRecCnt(int bo_no) {
		int cnt = boardDAO.incrementRecCnt(bo_no);
		return cnt>0 ? ServiceResult.OK : ServiceResult.FAILED;
	}
	
	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		BoardVO savedBoard = boardDAO.selectBoard(board.getBo_no());
		if(savedBoard==null)  throw new CustomException(board.getBo_no()+"번 글이 없음.");

		encodePassword(board);
		String inputPass = board.getBo_pass();
		String savedPass = savedBoard.getBo_pass();
		
		ServiceResult result = ServiceResult.FAILED;
		if(savedPass.equals(inputPass)) {
			int cnt = boardDAO.updateBoard(board);
			if(cnt > 0) {
				// 신규 등록 첨부 파일
				processAttatches(board);
				processDeleteAttatch(board);
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}		
		
		return result;
	}

	private int processDeleteAttatch(BoardVO board) {
		int cnt = 0;
		int[] delAttNos = board.getDelAttNos();
		if(delAttNos!=null && delAttNos.length>0) {
			String[] saveNames = new String[delAttNos.length];
			for(int i = 0; i<delAttNos.length; i++) {
				saveNames[i] = attDAO.selectAttach(delAttNos[i]).getAtt_savename();
			}
			cnt = attDAO.deleteAttatches(board);
			if(cnt == saveNames.length) {
				for(String savename : saveNames) {
					FileUtils.deleteQuietly(new File(saveFolder, savename));
				}
			}
		}
		return cnt;
	}

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		// 게시글 존재 여부 확인
		BoardVO savedBoard = boardDAO.selectBoard(board.getBo_no());
		if(savedBoard==null)  throw new CustomException(board.getBo_no()+"번 글이 없음.");
		// 비밀번호 암호화 후 인증
		encodePassword(board);
		String inputPass = board.getBo_pass();
		String savedPass = savedBoard.getBo_pass();
		ServiceResult result = ServiceResult.FAILED;
		if(savedPass.equals(inputPass)) {
			// 인증 성공시 삭제
			// 1. 첨부파일 메타 삭제
			List<AttatchVO> attatchList = savedBoard.getAttatchList();
			String[] saveNames = null;
			int cnt = 0;
			if(attatchList!=null && attatchList.size()>0) {
				saveNames = new String[attatchList.size()];
				for(int i=0; i<saveNames.length; i++) {
					saveNames[i] = attatchList.get(i).getAtt_savename();
				}
				cnt = attDAO.deleteAttatches(board);
			}
			// 2. 게시글 삭제
			cnt += boardDAO.deleteBoard(board.getBo_no());
			// 3. 첨부파일 2진 데이터 삭제
			if(saveNames!=null) {
				for(String savename : saveNames) {
					FileUtils.deleteQuietly(new File(saveFolder, savename));
				}
			}
			if(cnt > 0) result = ServiceResult.OK;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public AttatchVO download(int att_no) {
		AttatchVO attatch = attDAO.selectAttach(att_no);
		if(attatch==null) throw new CustomException(att_no+" 파일이 없음.");
		return attatch;
	}

}
























