package kr.or.ddit.myclassroom.test.service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.myclassroom.test.dao.ITestDAO;
import kr.or.ddit.myclassroom.test.vo.ObjectiveVO;
import kr.or.ddit.myclassroom.test.vo.RightAnsVO;
import kr.or.ddit.myclassroom.test.vo.StuAnsVO;
import kr.or.ddit.myclassroom.test.vo.StudentAnswerVO;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.myclassroom.test.vo.SubjectiveVO;
import kr.or.ddit.myclassroom.test.vo.TestVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TakeLecVO;

@Service
public class TestService {
	
	@Inject
	ITestDAO dao;
	
	/** 시험 리스트 조회 **/
	public List<TestVO> testList(TestVO test) {
		return dao.testList(test);
	}

	public List<ObjectiveVO> obList(ObjectiveVO ob) {
		return dao.obList(ob);
	}

	public List<SubjectiveVO> sbList(SubjectiveVO sb) {
		return dao.sbList(sb);
	}

	public TestVO testView(TestVO test) {
		return dao.testView(test);
	}
	
	@Transactional
	public int answerInsert(StuAnsVO ansList, StudentScoreVO score) {
		//1. 문제 답안 불러오기
		// 객관식 
		List<RightAnsVO> obAnsList= dao.selectObAns(ansList.getAnsList().get(0).getTestNo());
		// 주관식 
		List<RightAnsVO> sbAnsList = dao.selectSbAns(ansList.getAnsList().get(0).getTestNo());
		List<RightAnsVO> totalList = ListUtils.union(obAnsList, sbAnsList);
		//2. 정답/학생답 비교하기
		int totalScore = 0;
		for(int i=0; i<ansList.getAnsList().size(); i++) {
			// 맞았을 때 
			if((totalList.get(i).getAns()).equals(ansList.getAnsList().get(i).getSansAns())) {
				// 득점
				ansList.getAnsList().get(i).setSansScore(totalList.get(i).getAnsScore());
				// 총점
				totalScore += totalList.get(i).getAnsScore();
			} else {
				ansList.getAnsList().get(i).setSansScore(0);
			}
		}
		//3. 수강 번호 조회
		score = tlecNoSelect(score);
		//4. 총점 insert
		// 중간인지 기말인지 판별
		if("M".equals(ansList.getTestType())) {
			score.setScrMiddle(Math.round((totalScore*0.35)*100)/100.0);
		}else{
			score.setScrFinal(Math.round((totalScore*0.35)*100)/100.0);
		}
		dao.totalScoreInsert(score);
		//5. 답안+점수 insert
		return dao.answerInsert(ansList);
	}

	public List<TestVO> retrieveProTestList(String lecCode) {
		return dao.retrieveProTestList(lecCode);
	}

	public List<StudentAnswerVO> retrieveAns(TestVO testVO) {
		return  dao.retrieveAns(testVO);
	}
	
	/** 수강 번호 조회 **/
	public StudentScoreVO tlecNoSelect(StudentScoreVO score) {
		return dao.tlecNoSelect(score);
	}

	public StudentScoreVO stuScore(StudentScoreVO score) {
		StudentScoreVO tlec = tlecNoSelect(score);
		if(tlec!=null) {
			score.setTlecNo(tlec.getTlecNo());
		}
		return dao.stuScoreSelect(score);
	}

	public List<StudentScoreVO> proStuScoreList(PagingVO<StudentScoreVO> pagingVO) {
		return dao.proStuScoreList(pagingVO);
	}

	public List<TestVO> retrieveRecentTest(TestVO test) {
		return dao.retrieveRecentTest(test);
	}

	public List<TestVO> retrieveNoTestList(TestVO test) {
		return dao.retrieveNoTestList(test);
	}
}
