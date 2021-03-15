package kr.or.ddit.myclassroom.task.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.CustomException;
import kr.or.ddit.myclassroom.task.dao.ITaskSubmitDAO;
import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.myclassroom.test.dao.ITestDAO;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class TaskSubmitServiceImpl implements ITaskSubmitService{
	
	@Inject
	private ITaskSubmitDAO dao;
	@Inject
	ITestDAO testDao;
	
	// properties 파일을 읽어옴
	@Value("#{appInfo['boardFiles']}")
	private File saveFolder;
	
	@Override
	public int retrieveTaskSbCount(PagingVO<TaskSubmitVO> pagingVO) throws SQLException{
		return dao.selectTaskSbCount(pagingVO);
	}
	@Override
	public List<TaskSubmitVO> retrieveTaskSbList(PagingVO<TaskSubmitVO> pagingVO) throws SQLException{
		return dao.selectTaskSbList(pagingVO);
	}

	/** 과제 제출 **/
	@Transactional
	@Override
	public int createTask(TaskSubmitVO taskSb) throws SQLException{
		MultipartFile taskAtt = taskSb.getRealFile();
		int cnt=0;
		if(taskAtt !=null) {
			cnt += dao.insertAttaches(taskSb);
			try {
				taskSb.saveTo(saveFolder);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cnt;
	}
	
	/** 제출 과제 삭제 **/
	@Transactional
	@Override
	public int deleteTaskSb(TaskSubmitVO taskSb) throws SQLException {
		int cnt = 0;
		MultipartFile taskAtt = taskSb.getRealFile();
		if(taskSb.getTasksubmitNo()==null) {
			throw new CustomException(taskSb.getTaskNo()+"번 해당 댓글이 존재하지 않습니다.");
		}
		String saveName = null;
		if(taskAtt!=null) {
			saveName = taskSb.getAttSavename();
		}
		cnt = dao.deleteTaskSb(taskSb);
		if(saveName!=null) {
			FileUtils.deleteQuietly(new File(saveFolder, saveName));
		}
		return cnt;
	}
	
	/** 제출 과제 다운로드 **/
	@Override
	public TaskSubmitVO download(TaskSubmitVO taskSb) {
		taskSb = dao.selectTaskSb(taskSb);
		if(taskSb ==null) {
			throw new CustomException(taskSb.getTasksubmitNo() + "파일이 존재하지 않습니다.");
		}
		return taskSb;
	}
	
	/** 제출 과제 배점 등록 / task_submit과 student_score 테이블 **/
	@Transactional
	@Override
	public int taskScrInsert(TaskSubmitVO taskSb) {
		int taskScr = taskSb.getTaskScr();
		int cnt = 0;
		if(taskSb.getTasksubmitNo() ==null) {
			throw new CustomException(taskSb.getTasksubmitNo() + "배점 등록할 과제가 존재하지 않습니다.");
		}
		cnt = dao.insertTaskScr(taskSb);
		if(cnt>0) {
			StudentScoreVO score = new StudentScoreVO();
			score.setLecCode(taskSb.getLecCode());
			score.setMemId(taskSb.getMemId());
			// 수강번호 조회
			score = testDao.tlecNoSelect(score);
			score.setScrTask(taskScr);
			// 학생성적 테이블에 과제점수 등록
			
			cnt = dao.insertTaskScoreToTotalScore(score);
		}
		
		
		return cnt;
	}
	
	/** 제출 과제 여부 확인 **/
	@Override
	public int taskSelect(TaskSubmitVO taskSb) {
		return dao.selectTask(taskSb);
	}
}
