package kr.or.ddit.myclassroom.video.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.myclassroom.task.vo.TaskVO;
import kr.or.ddit.myclassroom.video.dao.IVideoDAO;
import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
import kr.or.ddit.myclassroom.video.vo.VideoAttendanceVO;
import kr.or.ddit.myclassroom.video.vo.VideoVO;
import kr.or.ddit.myclassroom.video.vo.ViewRecordVO;
import kr.or.ddit.vo.LectureVO;

@Service
public class VideoService{
	
	@Inject
	IVideoDAO dao;
	
	/** 강의 소개 조회 **/
	/** 강의 소개 조회 **/
	public LectureVO retrieveLecIntro(String lecCode) {
		LectureVO lec = dao.selectLec(lecCode);
		if(lec == null) throw new CustomException(lecCode+"해당 강의가 존재하지 않습니다.");
		return lec;
	}
	
	/** 영상 강의 리스트 **/
	public List<VideoVO> retrieveVideoList(VideoVO videoVO) {
		return dao.selectVideoList(videoVO);
	}
	/**영상 잔여 week 조회*/
	public List<LecPlanVO> retrieveLecplanList(String lecCode) {
		return dao.selectLecPlanList(lecCode);
	}
	/** 영상 등록 **/
	public int videoInsert(VideoVO video) {
		return dao.videoInsert(video);
	}

	public VideoVO retrieveVideo(VideoVO video) {
		return dao.selectVideo(video);
	}

	public int insertViewRecord(ViewRecordVO viewRecordVO) {
		return dao.insertViewRecord(viewRecordVO);
	}

	public ViewRecordVO retrieveViewRocord(ViewRecordVO viewRecordVO) {
		return dao.selectViewRecord(viewRecordVO);
	}
	/**출석체크**/
	public int insertVideoAttendance(VideoAttendanceVO attendVO) {
		return dao.insertVideoAttendance(attendVO);
	}
	/**영상수정**/
	public int modifyVideo(VideoVO video) {
		return dao.updateVideo(video);
	}
	/**출석기록여부확인**/
	public VideoAttendanceVO retrieveCheckAttendant(ViewRecordVO viewRecordVO) {
		return dao.selectCheckAttendance(viewRecordVO);
	}
	/**강의영상삭제**/
	public int removeVideo(ViewRecordVO viewRecordVO) {
		return dao.deleteVideo(viewRecordVO);
	}

	
}
