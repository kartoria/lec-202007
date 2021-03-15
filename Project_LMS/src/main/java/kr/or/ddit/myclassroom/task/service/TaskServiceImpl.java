package kr.or.ddit.myclassroom.task.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.task.dao.ITaskDAO;
import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.myclassroom.task.vo.TaskVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class TaskServiceImpl implements ITaskService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Inject
	private ITaskDAO dao;
	
	/** 과제 리스트 **/
	@Override
	public int retrieveTaskCount(PagingVO<TaskVO> pagingVO) {
		return dao.selectTaskCount(pagingVO);
	}

	/** 과제 리스트 **/
	@Override
	public List<TaskVO> retrieveTaskList(PagingVO<TaskVO> pagingVO) {
		return dao.selectTaskList(pagingVO);
	}
	
	/** 과제 등록 **/
	@Transactional
	@Override
	public int createTask(TaskVO task) {
		return dao.insertTask(task);
	}
	
	/** 과제 상세보기 **/
	@Override
	public TaskVO retrieveTask(TaskVO task) {
		TaskVO result = dao.selectTask(task);
		if(result ==null) {
			throw new CustomException();
		}
		return result;
	}

	/** 과제 게시글 수정 **/
	@Override
	public int modifiyTask(TaskVO task) {
		// 수정할 게시글이 있는지 판별
		TaskVO savedBoard = dao.selectTask(task);
		if(savedBoard == null) {
			throw new CustomException(task.getTaskNo() + "번 게시글이 존재하지 않습니다.");
		}
		return dao.updateTask(task);		
	}

	/** 과제 게시글 삭제 **/
	@Override
	public int removeTask(TaskVO task) {
		return dao.deleteTask(task);
	}
	
	/** 과제 제출 조회 **/
	@Override
	public int selectTaskSubmit(MemberVO authMember) {
		return 0;
	}

	/** 교수 과제 제출 여부 조회 **/
	@Override
	public List<TaskVO> submitList(List<TaskVO> taskList) {
		return dao.submitList(taskList);
	}

	@Override
	public List<TaskVO> retrieveTaskSb(List<TaskVO> taskList) {
		return dao.retrieveTaskSb(taskList);
	}

	@Override
	public List<TaskVO> retrieveRecentTask(TaskVO task) {
		return dao.retrieveRecentTask(task);
	}

	@Override
	public List<TaskVO> retrieveNoSbList(TaskVO task) {
		return  dao.retrieveNoSbList(task);
	}
}
