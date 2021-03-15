package kr.or.ddit.myclassroom.live.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.myclassroom.live.vo.LiveVO;

@Repository
public interface LiveDAO {
	
	public void insertBroadcast(LiveVO liveVO);

	public LiveVO selectTotalWeek(LiveVO liveVO);
	
	public List<LiveVO> selectLiveList(LiveVO liveVO);
	
	public LiveVO selectLive(LiveVO liveVO);

	public void deleteLive(LiveVO liveVO);
	
	public List<LiveVO> selectStudentAttendanceList(LiveVO liveVO);
	
	public LiveVO selectStudentAttendance(LiveVO liveVO);

	public void insertAttendance(LiveVO liveVO);
	
	public List<LiveVO> selectAttendanceCodeList();
	
	public void updateAttendance(LiveVO liveVO);
	
	
}
