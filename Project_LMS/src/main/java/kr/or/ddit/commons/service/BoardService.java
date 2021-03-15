package kr.or.ddit.commons.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.commons.dao.IAttachDAO;
import kr.or.ddit.commons.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.dash.vo.AttChartVO;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class BoardService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardService.class);
	@Inject
	private IAttachDAO attDao;
	@Inject
	private IBoardDAO dao;
	
	// properties 파일을 읽어옴
	@Value("#{appInfo['boardFiles']}")
	private File saveFolder;
	
	// @PostConstruct는 의존성 주입이 이루어진 후 초기화를 수행하는 메서드
	@PostConstruct
	public void init(){
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		LOGGER.info("{}", saveFolder.getAbsolutePath());
	}
	
	/** 페이징 cnt **/
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	/** 페이징 List **/
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		return dao.selectBoardList(pagingVO);
	}

	/** 등록 **/
	@Transactional
	public void createBoard(BoardVO board) throws Exception{
		int cnt = dao.insertBoard(board);
		if(cnt > 0) {
			cnt += processAttaches(board);
		}
	}
	
	/** 상세보기 **/
	public BoardVO retrieveBoard(BoardVO board) {
		BoardVO result = dao.selectBoard(board);
		if(result==null) {
			// 상세 보기 할 게시글이 없다. exception 발생
			throw new CustomException();
		}
		// 조회수 
		dao.incrementHit(board);
		return result;
	}

	/** 첨부파일 **/
	private int processAttaches(BoardVO board) {
		List<AttachVO> attachList = board.getAttachList();
		int cnt=0;
		if(attachList !=null && !attachList.isEmpty()) {
			cnt += attDao.insertAttaches(board);
			// attachList에서 하나씩 꺼내서 attachVO안에 넣어주기
			for(AttachVO attach : attachList) {
				try {
					attach.saveTo(saveFolder);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return cnt;
	}
	
	/** 파일 다운로드 **/
	public AttachVO download(int attNo) {
		AttachVO attach = attDao.selectAttach(attNo);
		if(attach ==null) {
			throw new CustomException(attNo + "파일이 존재하지 않습니다.");
		}
		return attach;
	}

	/** 수정 **/
	@Transactional
	public ServiceResult modifiyBoard(BoardVO board) {
		// 수정할 게시글이 있는지 판별
		BoardVO savedBoard = dao.selectBoard(board);
		if(savedBoard == null) {
			throw new CustomException(board.getBoNo() + "번 게시글이 존재하지 않습니다.");
		}
		int cnt = dao.updateBoard(board);
		
		ServiceResult result = null;
		if(cnt > 0) {
			processAttaches(board);
			deleteAttaches(board);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	/** 파일 삭제 **/
	private int deleteAttaches(BoardVO board) {
		int cnt = 0;
		// 삭제할 파일 번호의 배열
		int[] delAttNos = board.getDelAttNos();
		if(delAttNos !=null && delAttNos.length>0) {
			String[] saveNames = new String[delAttNos.length];
			// 하나씩 꺼내서 조회해온 뒤 저장명을 가져와서 배열에 저장한다.
			for(int i=0; i<delAttNos.length;i++) {
				saveNames[i] = attDao.selectAttach(delAttNos[i]).getAttSavename();
			}
			// 첨부파일 삭제
			cnt = attDao.deleteAttach(board);
			// 저장된 원본 폴더의 갯수랑 삭제에 성공한 cnt가 같으면
			if(cnt == saveNames.length) {
				// 하나씩 꺼내서 saveFolder에서도 삭제해준다.
				for(String savename : saveNames) {
					FileUtils.deleteQuietly(new File(saveFolder, savename));
				}
			}
		}
		return cnt;
	}
	
	/** 글 삭제 **/
	@Transactional
	public ServiceResult removeBoard(BoardVO board) {
		// 게시글 존재 여부 확인
		BoardVO savedBoard = dao.selectBoard(board);
		ServiceResult result = ServiceResult.FAILED;
		if(savedBoard == null) {
			throw new CustomException(board.getBoNo()+"번 해당 글이 존재하지 않습니다.");
		} else {
			// 1. 첨부파일의 메타데이터 삭제                       
			List<AttachVO> attachList = savedBoard.getAttachList();
			// 저장명을 저장할 String 배열
			String[] saveNames = null;
			int cnt = 0;
			// attachList가 null이 아니면
			if(attachList !=null && attachList.size()>0) {
				// 저장된 파일만큼 배열을 생성
				saveNames = new String[attachList.size()];
				// 하나씩 꺼내서 배열에 저장한다.
				for(int i=0; i<saveNames.length; i++) {
					saveNames[i] = attachList.get(i).getAttSavename();
				}
				cnt = attDao.deleteAttach(board);
			}
			// 2. 게시글의 삭제
			cnt += dao.deleteBoard(board);
			// 3. 첨부파일의 이진데이터 삭제
			if(saveNames!=null) {
				// 하나씩 꺼내서 삭제해준다.
				for(String savename : saveNames) {
					FileUtils.deleteQuietly(new File(saveFolder, savename));
				}
			}
			if(cnt >0) {
				result = ServiceResult.OK;
			}
		}
		return result;
	}
	
	/** 페이징 없는 리스트 **/
	public List<BoardVO> retrieveDataList(BoardVO board) {
		return dao.selectDataList(board);
	}

	/** Q&A 등록 **/
	public ServiceResult createQna(BoardVO board) {
		int cnt = dao.insertQna(board);
		ServiceResult result = null;
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	/** Q&A 상세 조회 **/
	public BoardVO retrieveQna(BoardVO board) {
		BoardVO result = dao.selectQna(board);
		if(result==null) {
			throw new CustomException();
		}
		dao.incrementHit(board);
		return result;
	}

	/** Q&A 리스트 전체 조회 **/
	public List<BoardVO> selectQnaList(PagingVO<BoardVO> pagingVO) {
		return dao.selectQnaList(pagingVO);
	}
	
	/** Q&A 페이지 **/
	public int selectQnaCount(PagingVO<BoardVO> pagingVO) {
		return dao.selectQnaCount(pagingVO);
	}

	/** Q&A 상세 조회 시, 비번 체크 **/
	public MemberVO passCheck(MemberVO memberVO) {
		return dao.passCheck(memberVO);
	}
	
	/** Q&A 등록글 수정 **/
	public ServiceResult modifyQna(BoardVO board) {
		int cnt = dao.updateQna(board);
		ServiceResult result = null;
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	/** Q&A 등록글 삭제 **/
	public ServiceResult removeQna(BoardVO board) {
		int cnt = dao.deleteQna(board);
		ServiceResult result = null;
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	public BoardVO retrieveRep(BoardVO board) {
		return dao.retrieveRep(board);
	}

	public List<BoardVO> retrieveRepList(List<BoardVO> boardList) {
		return dao.retrieveRepList(boardList);
	}

	public List<BoardVO> retrieveRecentNoty(BoardVO board) {
		return dao.retrieveRecentNoty(board);
	}

	public List<BoardVO> retrieveRecentDis(BoardVO board) {
		return dao.retrieveRecentNoty(board);
	}

	public AttChartVO retrieveAttChart() {
		return dao.retrieveAttChart();
	}
	// qna 이전글
	public BoardVO previousQna(BoardVO realBoard) {
		return dao.previousQna(realBoard);
	}
	// qna 다음글
	public BoardVO nextQna(BoardVO realBoard) {
		return dao.nextQna(realBoard);
	}

	public List<BoardVO> retrieveQnaList(BoardVO board) {
		return dao.retrieveQnaList(board);
	}
}
