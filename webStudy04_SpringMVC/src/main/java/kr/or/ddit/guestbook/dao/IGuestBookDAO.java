package kr.or.ddit.guestbook.dao;

import org.springframework.stereotype.Repository;

/**
 * 방명록 관리를 위한 persystence layer 
 * 작성,수정,삭제,조회
 */
@Repository
public interface IGuestBookDAO {
	
	public int InsertBook();
}
