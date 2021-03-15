package kr.or.ddit.myclassroom.task.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.myclassroom.task.vo.TaskVO;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ITaskSubmitDAO {

	int selectTaskSbCount(PagingVO<TaskSubmitVO> pagingVO);
	List<TaskSubmitVO> selectTaskSbList(PagingVO<TaskSubmitVO> pagingVO);
	int insertTaskSb(TaskSubmitVO taskSb);
	int insertAttaches(TaskSubmitVO taskSb);
	int deleteTaskSb(TaskSubmitVO taskSb);
	TaskSubmitVO selectTaskSb(TaskSubmitVO taskSb);
	int insertTaskScr(TaskSubmitVO taskSb);
	int selectTask(TaskSubmitVO taskSb);
	int insertTaskScoreToTotalScore(StudentScoreVO score);

}
