package kr.or.ddit.myclassroom.test.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.test.service.TestService;
import kr.or.ddit.myclassroom.test.vo.ObjectiveVO;
import kr.or.ddit.myclassroom.test.vo.StuAnsVO;
import kr.or.ddit.myclassroom.test.vo.StudentAnswerVO;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.myclassroom.test.vo.SubjectiveVO;
import kr.or.ddit.myclassroom.test.vo.TestVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class TestController extends BaseController{
	
	@Inject
	private TestService service;
	
	@ModelAttribute("test")
	public TestVO test() {
		return new TestVO();
	}
	
	/** 시험 리스트 조회 **/
	@RequestMapping("/myclass/{lecCode}/testList.do")
	public String testList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@PathVariable(value ="lecCode", required=true) String lecCode,
						@RequestAttribute("subName") String subName,
						Model model, TestVO test) {
		test.setLecCode(lecCode);
		List<TestVO> testList = service.testList(test);
		Map<String, TestVO> testMap = new HashMap<>();
		for(int i=0; i<testList.size();i++) {
			testMap.put(testList.get(i).getTestNo()+"", testList.get(i));
		}
		List<TestVO> resultList = new ArrayList<TestVO>();
        resultList = testList.stream().distinct().collect(Collectors.toList());
        
        TestVO testVO = TestVO.builder().memId(authMember.getMemId()).resultList(resultList).build();
        
        model.addAttribute("testMap", testMap);
		String goPage ="";
		if("ROLE_STUDENT".equals(authMember.getMemType())) {
			if(!testMap.isEmpty()) {
				List<StudentAnswerVO> stuAnsList = service.retrieveAns(testVO);
				stuAnsList = stuAnsList.stream().distinct().collect(Collectors.toList());
				model.addAttribute("stuAnsList",stuAnsList);
			}
			goPage ="myClassRoom/test/myTestList";
		} else {
			model.addAttribute("test", test);
			goPage ="myClassRoom/test/proTestList";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "시험"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/testList.do");
		return goPage;
	}
	
	/** 시험 학생 보기 **/
	@RequestMapping("/myclass/{lecCode}/testView.do")
	public String testView(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@PathVariable(value ="lecCode", required=true) String lecCode, Model model, TestVO test,
			@RequestAttribute("subName") String subName) {
		try {
			test = TestVO.builder().testNo(test.getTestNo()).lecCode(lecCode).build();
			// 객관
			ObjectiveVO ob = ObjectiveVO.builder().testNo(test.getTestNo()).build();
			List<ObjectiveVO> obList = service.obList(ob);
			// 주관
			SubjectiveVO sb = SubjectiveVO.builder().testNo(test.getTestNo()).build();
			List<SubjectiveVO> sbList = service.sbList(sb);
			// 시험
			List<TestVO> testList = service.testList(test);
			Map<String, TestVO> testMap = new HashMap<>();
			for(int i=0; i<testList.size();i++) {
				testMap.put(testList.get(i).getTestNo()+"", testList.get(i));
			}
			model.addAttribute("testMap",testMap);
			model.addAttribute("obList",obList);
			model.addAttribute("sbList",sbList);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
			return "redirect:/myclass/{lecCode}/testList.do";
		}
		String goPage = null;
		if("ROLE_PROFESSOR".equals(authMember.getMemType())) {
			goPage = "myClassRoom/test/proTestView";
		}else if("ROLE_STUDENT".equals(authMember.getMemType())){
			goPage = "myClassRoom/test/myTestStu";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "시험"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/testView.do");
		return goPage;
	}
	
	/** 학생 답안지 등록 **/
	@RequestMapping(value="/myclass/{lecCode}/answerInsert.do",method=RequestMethod.POST)
	public String answerInsert(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
				@PathVariable(value ="lecCode", required=true) String lecCode, Model model, @ModelAttribute("ansList") StuAnsVO ansList,
				RedirectAttributes redirectAttributes,@RequestAttribute("subName") String subName) {
		StudentScoreVO score = StudentScoreVO.builder().memId(authMember.getMemId()).lecCode(lecCode).build();
		try {
			service.answerInsert(ansList, score);
			redirectAttributes.addFlashAttribute("message",notyInsertSuccessMessage());
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "시험"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/answerInsert.do");
		return "redirect:/myclass/"+lecCode+"/testList.do";
	}
	
	/** 교수 시험 리스트 조회 (Ajax) **/
	@RequestMapping(value="/myclass/{lecCode}/proTestList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, TestVO> proTestList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
										@PathVariable(value ="lecCode", required=true) String lecCode,@RequestAttribute("subName") String subName)  {
		Map<String, TestVO> map = new HashMap<>();
		List<TestVO> proTestList = service.retrieveProTestList(lecCode);
		for(int i=0; i<proTestList.size();i++) {
			map.put(proTestList.get(i).getTestNo()+"", proTestList.get(i));
		}
		return map;
	}
	
	/** 답안-점수 조회 (Ajax) **/
	@RequestMapping(value="/myclass/{lecCode}/stuScoreList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String stuScoreList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
							@PathVariable(value ="lecCode", required=true) String lecCode, StudentScoreVO score, Model model){
		try {
			StudentScoreVO ansScore = service.stuScore(score);
			model.addAttribute("ansScore", ansScore);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	
	/** 교수님 학생 점수 보기 **/
	@RequestMapping(value="/myclass/{lecCode}/proStuScoreList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String proStuScoreList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@PathVariable(value ="lecCode", required=true) String lecCode, StudentScoreVO score, Model model,@RequestAttribute("professorId") String professorId,
			@ModelAttribute("searchVO") SearchVO searchVO) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		try {
			PagingVO<StudentScoreVO> pagingVO = new PagingVO<>();
			pagingVO.setBoGroupCode(lecCode);
			pagingVO.setSearchVO(searchVO);
			
			List<StudentScoreVO> scoreList = service.proStuScoreList(pagingVO);
			pagingVO.setDataList(scoreList);
			model.addAttribute("pagingVO", pagingVO);
		}catch (Exception e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	
	/** 교수님 성적 excel 다운로드 **/
	@RequestMapping(value="/myClass/{lecCode}/downloadTestExcel.do")
	public void downloadTestExcel(StudentScoreVO scoreVO, HttpServletRequest request, HttpServletResponse response, 
					@PathVariable(value ="lecCode", required=true) String lecCode) {
		response.setContentType("application/msexcel");
		response.setHeader("Content-Disposition", "attachment;filename=\"proTestList.xlsx\"");
		try {
			InputStream io = new ClassPathResource("kr/or/ddit/jxlsTemplate/excel/proTestList.xlsx").getInputStream();
			OutputStream os = response.getOutputStream();
			PagingVO<StudentScoreVO> pagingVO = new PagingVO<>();
			pagingVO.setBoGroupCode(lecCode);
			
			List<StudentScoreVO> dataList = service.proStuScoreList(pagingVO);
			Context context = new Context();
			context.putVar("dataList", dataList);
			
			JxlsHelper.getInstance().processTemplate(io, os, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
