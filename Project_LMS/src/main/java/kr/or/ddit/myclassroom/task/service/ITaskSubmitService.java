package kr.or.ddit.myclassroom.task.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.vo.PagingVO;

public interface ITaskSubmitService {

	List<TaskSubmitVO> retrieveTaskSbList(PagingVO<TaskSubmitVO> pagingVO) throws SQLException;

	int retrieveTaskSbCount(PagingVO<TaskSubmitVO> pagingVO) throws SQLException;

	int createTask(TaskSubmitVO taskSb) throws SQLException;

	int deleteTaskSb(TaskSubmitVO taskSb) throws SQLException;

	TaskSubmitVO download(TaskSubmitVO taskSb);

	int taskScrInsert(TaskSubmitVO taskSb);

	int taskSelect(TaskSubmitVO taskSb);

}
