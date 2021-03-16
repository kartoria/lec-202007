package notice;

import java.util.List;
import java.util.Map;

import member.IMemberDao;
import member.IMemberDaoImpl;

public class INoticeServiceImpl implements INoticeService{
	
	private static INoticeService noticeSv; 
	private INoticeDao noticeDao;
	
	private INoticeServiceImpl(){
		noticeDao = INoticeDaoImpl.getInstance();
	}

	public static INoticeService getInstance(){
		if(noticeSv == null){
			noticeSv = new INoticeServiceImpl();
		}
		return noticeSv;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<NoticeVO> readNotice() {
		return noticeDao.readNotice();
	}

	@Override
	public int updateNoticeTitle(Map<String, String> info) {
		return noticeDao.updateNoticeTitle(info);
	}

	@Override
	public int updateNoticeComment(Map<String, String> info) {
		return noticeDao.updateNoticeComment(info);
	}

	@Override
	public int deleteNotice(String notice_no) {
		return noticeDao.deleteNotice(notice_no);
	}

	@Override
	public int createNotice(Map<String, String> info) {
		return noticeDao.createNotice(info);	
	}
}
