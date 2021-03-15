package kr.or.ddit.myclassroom.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.myclassroom.test.vo.ObjectiveVO;
import kr.or.ddit.myclassroom.test.vo.RightAnsVO;
import kr.or.ddit.myclassroom.test.vo.StuAnsVO;
import kr.or.ddit.myclassroom.test.vo.StudentAnswerVO;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.myclassroom.test.vo.SubjectiveVO;
import kr.or.ddit.myclassroom.test.vo.TestVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ITestDAO {

	List<TestVO> testList(TestVO test);

	List<ObjectiveVO> obList(ObjectiveVO ob);

	List<SubjectiveVO> sbList(SubjectiveVO sb);

	TestVO testView(TestVO test);

	int answerInsert(StuAnsVO ansList);

	List<TestVO> selectAnswer(TestVO test);

	List<TestVO> retrieveProTestList(String lecCode);

	List<RightAnsVO> selectObAns(int testNo);

	List<RightAnsVO> selectSbAns(int testNo);

	List<StudentAnswerVO> retrieveAns(TestVO testVO);

	StudentScoreVO tlecNoSelect(StudentScoreVO score);

	int totalScoreInsert(StudentScoreVO score);

	StudentScoreVO stuScoreSelect(StudentScoreVO score);

	List<StudentScoreVO> proStuScoreList(PagingVO<StudentScoreVO> pagingVO);

	List<TestVO> retrieveRecentTest(TestVO test);

	List<TestVO> retrieveNoTestList(TestVO test);

}
