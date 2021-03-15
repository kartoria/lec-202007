package kr.or.ddit.myclassroom.task.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.myclassroom.task.vo.TaskVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ITaskDAO {

	int selectTaskCount(PagingVO<TaskVO> pagingVO);
	List<TaskVO> selectTaskList(PagingVO<TaskVO> pagingVO);
	int selectTaskSubmit(MemberVO authMember);
	int insertTask(TaskVO task);
	TaskVO selectTask(TaskVO task);
	int updateTask(TaskVO task);
	int deleteTask(TaskVO task);
	List<TaskVO> submitList(List<TaskVO> taskList);
	List<TaskVO> retrieveTaskSb(List<TaskVO> taskList);
	List<TaskVO> retrieveRecentTask(TaskVO task);
	List<TaskVO> retrieveNoSbList(TaskVO task);

}
