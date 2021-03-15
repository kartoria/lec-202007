package kr.or.ddit.admin.scholarship.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.lecture.service.AdminLectureService;
import kr.or.ddit.admin.scholarship.service.AdminScholarshipService;
import kr.or.ddit.admin.scholarship.vo.ScholarshipFundListVO;
import kr.or.ddit.admin.scholarship.vo.ScoreFormVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.ScholarshipFundVO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.SemesterScoreVO;
/**
 * @author 조예슬
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.    조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminScholarshipSelectionController {
	@Inject
	private AdminScholarshipService service;
	@Inject
	private AdminLectureService lecService;
	// 현재 학기 조회 메서드
	private CodeVO getCurrentSmst() {
		CodeVO currentSmst = new CodeVO();
		currentSmst.setGroupCode("A00");
		currentSmst = lecService.retrieveCurrentSmst(currentSmst);
		return currentSmst;
	}
	/** 성적 산출페이지 **/
	@RequestMapping("/admin/scholarshipSelectByGrade.do")
	public String  ChooseByGrade(Model model){
		model.addAttribute("pageTitle", "성적 장학생 선발");
		return "admin/adminScholarship/selectByGrade";
	}
	/**성적산출페이지 단과대 정보 가져오는 ajax**/
	@RequestMapping(value="/admin/getScholarshipCollege.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, List<?>> ChooseByGradeForAjax(Model model){
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		List<CodeVO> collegeList = service.retrieveCollegeList();
		// 성적 선발할 학기조회 
		List<SemesterScoreVO> smstList = service.retrieveScoreSemseter();
		map.put("collegeList", collegeList);
		map.put("smstList", smstList);
		return map;
	}
	/**단과대 선택 후 학과 정보 가져오기**/
	@RequestMapping(value="/admin/selectCollege.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String  selectColleage(@RequestParam("college")int college,Model model)  {
		List<DepartmentVO> depList = service.retrieveDepartmentList(college);
		model.addAttribute("department", depList);
		return "jsonView";
	}
	/** 성적 산출 **/
	@RequestMapping(value="/admin/produceScholarshipByGrade.do",method=RequestMethod.POST)
	public String  ChooseByGradePost(@ModelAttribute("ScoreFormVO")ScoreFormVO scoreFormVO,Model model) {
		// 현재  학기 (장학금 테이블에선 현재학기로조회해야함)
		CodeVO codeVO = getCurrentSmst();
		String currentSmst = codeVO.getCodeName();
		// 장학금 성적 조회시에는 검색한 학기로 조회하고
		// 선발 여부 확인시는 선발 및 지급 되었을 시점인 다음학기로 조회해야 함
		// 검색한 성적 학기에서 ex 202002 학기 => (뒤에서1을더해서3이면 연도1늘리고 뒤에는 01 학기로)
		
		// 검색한 학기 (성적테이블에서 조회할 학기)
		String searchSmst = scoreFormVO.getScrSmst();
		// 비교할 임시학기 01 => 1학기  02 => 2학기 
		int tmpSmst = Integer.parseInt(searchSmst.substring(4))+1;
		// 년도
		String year = "";
		// 최종적으로 검색할 학기
		String resultSmst = "";
		if(tmpSmst==3) {
			year = (Integer.parseInt((searchSmst.substring(0, 4)))+1)+"";
			resultSmst = year+"01";
		}else {
			resultSmst = (Integer.parseInt(searchSmst)+1)+"";
		}
		scoreFormVO.setCurrentSmst(resultSmst);
		// 선발전 기존 장학금 테입블에 선발 여부가 있는지 확인 
		List<SemesterScoreVO> schAccptCheckList= service.selectScoreScholarCheck(scoreFormVO);
		// 선발 대상인 학생을 성적테이블에서 조회 
		List<SemesterScoreVO> semeScoreList = service.retrieveSemesterScoreList(scoreFormVO);
		// 성적 장학금 유형 조회
		List<ScholarshipVO> scoreScholarshipList = service.retrieveScoreScholarshipList();
		// 선발된 내역이 있다면 기존 정보를 VO 에 담기 
		if(!schAccptCheckList.isEmpty()) {
			for(int a = 0 ; a <semeScoreList.size() ; a++) {
				for(int b =0 ; b < schAccptCheckList.size() ; b++) {
						// 학기 성적 순위가 0 이 아니고 (장학생대상자)
					if(semeScoreList.get(a).getScrNo()!=0&&schAccptCheckList.get(b).getScrNo()!=0) {
							// 이미 선발된 학생 순위와 같으면 
						if(semeScoreList.get(a).getScrNo()==schAccptCheckList.get(b).getScrNo()) {
							 // 선발자 내역에 있으면 
							if(!ObjectUtils.isEmpty(schAccptCheckList.get(b).getSchoFundVO())) {
								// 선발결과를  (접수/지급 등)
								String codeResult = schAccptCheckList.get(b).getSchoFundVO().getCodeResult();
								// jsp 가져갈 VO 에 담는다.
								semeScoreList.get(a).getSchoFundVO().setCodeResult(codeResult);
							}
						}
					}
				}
			}
		}
		
		String memGrd = null;
		int grd = 0;
		// 학년이 1~8 까지 학기별로 나눠져있는것을 1,2,3,4,학년으로 구함
		for(int i = 0 ; i<semeScoreList.size(); i++) {
			memGrd = semeScoreList.get(i).getMemberVO().getMemGrd();
			grd = Integer.parseInt(memGrd);
			if(grd%2!=0) {
				grd = grd/2+1;
			}else {
				grd = grd/2;
			}
			memGrd = grd + "";
			semeScoreList.get(i).getMemberVO().setMemGrd(memGrd);;
		}
		// 성적장학 1유형
		model.addAttribute("scoreScholar1Type", scoreScholarshipList.get(0));
		// 성적장학 2유형
		model.addAttribute("scoreScholar2Type", scoreScholarshipList.get(1));
		// 조회조건 
		model.addAttribute("scoreFormVO", scoreFormVO);
		// 성적 리스트
		model.addAttribute("semeScoreList", semeScoreList);
		// 현재  학기
		model.addAttribute("currentSmst", currentSmst);
		return "admin/adminScholarship/selectByGrade";
	}
	
	/**성적장학생 선발 **/
	@RequestMapping(value="/admin/insertScholarByGrade.do",method=RequestMethod.POST)
	public String insertScholarByGrade(@ModelAttribute("applyListVO") ScholarshipFundListVO applyList,Model model) {
		
		try {
			int cnt	= service.createScholarFund(applyList);
			// ajax
			model.addAttribute("message", "접수 되었습니다.");
		} catch (DataAccessException e) {
			// ajax
			model.addAttribute("message", "접수 실패하였습니다.");
		}
		
		return "jsonView";
	}
	
	/** 성적장학생 선발취소**/
	@RequestMapping(value="/admin/cancelScholarScoreList.do",method=RequestMethod.POST)
	public String cancelScholarScoreList(@ModelAttribute("applyListVO") ScholarshipFundListVO applyList,Model model) {
		try {
			int cnt = service.removeScholarScoreRecord(applyList);
			// ajax
			model.addAttribute("message", "취소 완료되었습니다.");
		} catch (DataAccessException e) {
			// ajax
			model.addAttribute("message", "취소에 실패되었습니다.");
		}
		
		return "jsonView";
	}
	
	/** 신청 장학생 선발 기본페이지 **/
	@RequestMapping("/admin/scholarshipSelectByApply.do")
	public String  ChooseByApply(Model model){
		ScholarshipFundVO emptyVO = new ScholarshipFundVO();
		//신청장학금에대한 학기 조회
		List<ScholarshipFundVO> smstList = service.retrieveScholarSmstList();
		model.addAttribute("smstList", smstList);
		model.addAttribute("pageTitle", "교내 장학생 선발");
		return "admin/adminScholarship/selectByApply";
	}
	
	/** 신청장학생 과목필터 **/
	@RequestMapping("/admin/chooseByScholarType.do")
	public String  get(Model model) {
		List<ScholarshipVO> schList = service.retrieveScholarApplyType();
		model.addAttribute("scholarship", schList);
		return "jsonView";
	}
	/** 신청장학생 필터 검색 **/
	@RequestMapping(value="/admin/scholarshipSearchSelectByApply.do",method=RequestMethod.POST)
	public String  ChooseByScholarType(@ModelAttribute("searchForm")ScholarshipFundVO searchForm,Model model) {
		List<ScholarshipFundVO> scholarApplyList = service.retrieveScholarApplyList(searchForm);
		model.addAttribute("scholarApplyList", scholarApplyList);
		return "jsonView";
	}
	/** 신청장학생 선발 **/
	@RequestMapping(value="/admin/updateScholarApplyList.do",method=RequestMethod.POST)
	public String  updateApplyScholarshipList(@ModelAttribute("applyListVO") ScholarshipFundListVO applyList,Model model) {
		try {
			int cnt = service.modifyScholarFund(applyList);
			model.addAttribute("message", "접수 되었습니다.");
		} catch (DataAccessException e) {
			model.addAttribute("message", "접수에 실패하였습니다.");
		}
		return "jsonView";
	}
	/** 신청장학생 선발취소**/
	@RequestMapping(value="/admin/cancelScholarApplyList.do",method=RequestMethod.POST)
	public String  cancelApplyScholarshipList(@ModelAttribute("applyListVO") ScholarshipFundListVO applyList,Model model) {
		try {
			int cnt = service.removeScholarApplyRecord(applyList);
			model.addAttribute("message", "처리 되었습니다.");
		} catch (DataAccessException e) {
			model.addAttribute("message", "취소에 실패되었습니다.");
		}
		return "jsonView";
	}
	
	/** 신청장학생 상세조회**/
	@RequestMapping("/admin/applyScholarView.do")
	public String  applyScholarView(@RequestParam("sfundNo")int sfundNo,Model model) {
		ScholarshipFundVO schFundVO = service.retrieveScholarApply(sfundNo);
		model.addAttribute("schFund",schFundVO);
		model.addAttribute("pageTitle", "장학금 신청내역 상세조회");
		model.addAttribute("pageLink", "/admin/scholarshipSelectByApply.do");
		return "admin/adminScholarship/selectByApplyView";
	}
	
	/**개별 신청내역 접수/접수취소/반려**/
	@RequestMapping(value="/admin/updateScholarApply.do",method=RequestMethod.POST)
	public String updateScholarApply(@ModelAttribute("schVO")ScholarshipFundVO schVO,Model model) {
		try {
			int cnt = service.updateScholarApply(schVO);
			if(cnt>0) {
				ScholarshipFundVO schFundVO = service.retrieveScholarApply(schVO.getSfundNo());
				model.addAttribute("schFundVO", schFundVO);
			}
			model.addAttribute("message", "처리 되었습니다.");
		} catch (DataAccessException e) {
			model.addAttribute("message", "처리 실패하였습니다.");
		}
		return "jsonView";
	}
	
	
}
