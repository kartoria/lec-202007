package kr.or.ddit.myclassroom.task.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.myclassroom.task.vo.TaskVO;
import kr.or.ddit.vo.PagingVO;

public interface ITaskService {

	int retrieveTaskCount(PagingVO<TaskVO> pagingVO);
	List<TaskVO> retrieveTaskList(PagingVO<TaskVO> pagingVO);
	int selectTaskSubmit(MemberVO authMember);
	int createTask(TaskVO task);
	TaskVO retrieveTask(TaskVO task);
	int modifiyTask(TaskVO task);
	int removeTask(TaskVO task);
	List<TaskVO> submitList(List<TaskVO> taskList);
	List<TaskVO> retrieveTaskSb(List<TaskVO> taskList);
	List<TaskVO> retrieveRecentTask(TaskVO task);
	List<TaskVO> retrieveNoSbList(TaskVO task);

}
