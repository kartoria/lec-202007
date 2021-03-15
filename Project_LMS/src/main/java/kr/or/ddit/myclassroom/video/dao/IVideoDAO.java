package kr.or.ddit.myclassroom.video.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
import kr.or.ddit.myclassroom.video.vo.VideoAttendanceVO;
import kr.or.ddit.myclassroom.video.vo.VideoVO;
import kr.or.ddit.myclassroom.video.vo.ViewRecordVO;
import kr.or.ddit.vo.LectureVO;

@Repository
public interface IVideoDAO {
	/**강의조회**/
	LectureVO selectLec(String lecCode);
	/**영상리스트**/
	List<VideoVO> selectVideoList(VideoVO videoVO);
	/**영상 잔여 week 조회**/
	List<LecPlanVO> selectLecPlanList(String lecCode);
	/**영상등록**/
	int videoInsert(VideoVO video);
	/**영상조회**/
	VideoVO selectVideo(VideoVO video);
	/**시청기록등록/업데이트(merge)**/
	int insertViewRecord(ViewRecordVO viewRecordVO);
	/**시청기록조회**/
	ViewRecordVO selectViewRecord(ViewRecordVO viewRecordVO);
	/**출석체크**/
	int insertVideoAttendance(VideoAttendanceVO attendVO);
	/**영상수정**/
	int updateVideo(VideoVO video);
	/**출석체크여부확인**/
	VideoAttendanceVO selectCheckAttendance(ViewRecordVO viewRecordVO);
	/**강의영상삭제**/
	int deleteVideo(ViewRecordVO viewRecordVO);

}
