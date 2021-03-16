package notice;

import java.util.List;
import java.util.Map;

public interface INoticeService {

	/**
	 * 공지게시판 게시물을 모두 조회하기 위한 메서드
	 * @author 신광진 
	 * @return List<NoticeVO>
	 * @since 2020-09-08
	 * @see
	 * 
	 */
	List<NoticeVO> readNotice();

	/**
	 * 기존 게시물의 제목을 변경하는 메서드
	 * @author 신광진
	 * @param info (notice_title, notice_no)
	 * @return int
	 * @since 2020-09-08
	 * @see
	 * 
	 */
	int updateNoticeTitle(Map<String, String> info);

	/**
	 * 기존 게시물의 내용을 변경하는 메서드
	 * @author 신광진
	 * @param info
	 * @return int
	 * @since 2020-09-08
	 * @see
	 * 
	 */
	int updateNoticeComment(Map<String, String> info);

	/**
	 * 기존 게시물을 삭제하는 메서드
	 * @author 신광진
	 * @param notice_no
	 * @return int
	 * @since 2020-09-08
	 * @see
	 * 
	 */
	int deleteNotice(String notice_no);

	/**
	 * 게시물 추가를 위한 메서드
	 * @author 신광진
	 * @param info
	 * @return int
	 * @since 2020-09-08
	 * @see
	 * 
	 */
	int createNotice(Map<String, String> info);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
