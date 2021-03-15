package kr.or.ddit.commons.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

@Repository
public interface IAttachDAO {
	int insertAttaches(BoardVO board);
	AttachVO selectAttach(int attNo);
	int deleteAttach(BoardVO board);
	
}
