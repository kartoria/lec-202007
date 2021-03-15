package kr.or.ddit.admin.lecture.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.lecture.service.AdminLectureService;
import kr.or.ddit.admin.lecture.vo.AdminLectureVO;
import kr.or.ddit.admin.lecture.vo.LecSearchVO;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
/**
 * @author 조예슬
 * @since 2021. 2. 01.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 01.    조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminLectureController extends BaseController {
	@Inject
	private AdminLectureService service;
	
	/**관리자 강의목록조회**/
	@RequestMapping("/admin/lectureList.do")
	public String lectureListPage(Model model) {
		model.addAttribute("pageTitle", "강의 관리");
		return "admin/adminLecture/lectureList";
	}
	
	/**검색조건중 학부코드**/
	@RequestMapping(value="/admin/lecCollegeList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, List<?>> collegeListForAjax() {
		Map<String, List<?>> map = new HashMap<>();
		List<CodeVO> colList = service.retrieveCollegeList();
		List<LectureVO> smstList = service.retriveLecSmstList();
		map.put("colList", colList);
		map.put("smstList", smstList);
		return map;
	}
	/**검색조건중 학과코드가져오기**/
	@RequestMapping(value="/admin/lecSelectCollege.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DepartmentVO> departmentListForAjax(@RequestParam("college") String college,
												Model model){
		List<DepartmentVO> depList = service.retrieveDepartmentList(college);
		return depList;
	}
	/**검색조건중 교수코드가져오기**/
	@RequestMapping(value="/admin/lecSelectDepartment.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<MemberVO> professorListForAjax(@RequestParam("depNo") String depNo,
											Model model){
		List<MemberVO> proList = service.retrieveProfessorList(depNo);
		return proList;
	}
	
	
	/**관리자 강의목록조회 검색 ajax**/
	@RequestMapping(value="/admin/lectureSearchList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String lectureListForAjax(@RequestParam(value="page", required=false,defaultValue="1" ) int currentPage,
								@ModelAttribute("searchVO") LecSearchVO lecsearchVO,
								Model model)  { 
		try {
			PagingVO<AdminLectureVO> pagingVO = new PagingVO<>(15,5);
			SearchVO searchVO = new SearchVO();
			if(lecsearchVO.getSearchType()!=null &&lecsearchVO.getSearchWord()!=null) {
				searchVO.setSearchType(lecsearchVO.getSearchType());
				searchVO.setSearchWord(lecsearchVO.getSearchWord());
			}
			AdminLectureVO lecVO = new AdminLectureVO();
			if(StringUtils.isNotBlank(lecsearchVO.getCollege())) {
				lecVO.setCode(lecsearchVO.getCollege());
			}
			if(lecsearchVO.getDepartment()!=null) {
				lecVO.setDepNo(lecsearchVO.getDepartment());
			}
			if(StringUtils.isNotBlank(lecsearchVO.getProfessor())) {
				lecVO.setMemId(lecsearchVO.getProfessor());
			}
			if(StringUtils.isNotBlank(lecsearchVO.getSmst())) {
				lecVO.setLecSmst(lecsearchVO.getSmst());
			}
			if(StringUtils.isNotBlank(lecsearchVO.getLecDelete())) {
				lecVO.setLecDelete(lecsearchVO.getLecDelete());
			}
			pagingVO.setSearchVO(searchVO);
			pagingVO.setSearchDetail(lecVO);
			int totalRecord = service.retrieveLectureCount(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			
			pagingVO.setCurrentPage(currentPage);
			List<AdminLectureVO> lecList = service.retrieveLectureList(pagingVO);
			pagingVO.setDataList(lecList);
			model.addAttribute("pagingVO", pagingVO);
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	/**관리자 강의 상세조회**/
	@RequestMapping("/admin/lectureView.do")
	public String lectureView(@RequestParam("lecCode") String lecCode,Model model){
		
		try {
			LectureVO lectureVO = new LectureVO();
			lectureVO.setLecCode(lecCode);
			lectureVO = service.retrieveLecture(lectureVO);
			List<LectureTimeVO> timeList = service.retrieveLectureTimeList(lectureVO);
			model.addAttribute("lecVO", lectureVO);
			model.addAttribute("timeList", timeList);
			model.addAttribute("pageTitle", "강의 상세조회");
			model.addAttribute("pageLink", "/admin/lectureList.do");
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "admin/adminLecture/lectureView";
	}
	
	/**관리자 강의 수정폼**/
	@RequestMapping("/admin/updateLectureForm.do")
	public String updateform(@ModelAttribute("lecVO")LectureVO lecVO,Model model) {
		try {
			LectureVO lectureVO;
			lectureVO = service.retrieveLecture(lecVO);
			List<LectureTimeVO> timeList = service.retrieveLectureTimeList(lecVO);
			model.addAttribute("lecVO", lectureVO);
			model.addAttribute("timeList", timeList);
			model.addAttribute("pageTitle", "강의 수정");
			model.addAttribute("pageLink", "/admin/lectureList.do");
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "admin/adminLecture/lectureUpdateForm";
	}
	
	/**관리자 수정**/
	@RequestMapping(value="/admin/updateLecture.do",method=RequestMethod.POST)
	public String updateLecture(@ModelAttribute("lecVO")LectureVO lecVO, Model model) {
		String goPage = null;
		try {
			int cnt = service.modifyLecture(lecVO);
			model.addAttribute("msg", NotyMessageVO.builder("수정완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
			goPage = "redirect:/admin/lectureView.do?lecCode="+lecVO.getLecCode();
		} catch (Exception e) {
			model.addAttribute("msg",  NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
			goPage = "admin/adminLecture/lectureUpdateForm";
		}
		return goPage;
	}
	
	/**관리자 강의 삭제**/
	@RequestMapping("/admin/deleteLecture.do")
	public String deleteLecture(@ModelAttribute("lecVO")LectureVO lecVO,Model model,RedirectAttributes redirectAttribute) {
		String goPage = null;
		try {
			int cnt = service.deleteLecture(lecVO);
			redirectAttribute.addFlashAttribute("msg", NotyMessageVO.builder("폐강완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
			goPage = "redirect:/admin/lectureList.do";
		} catch (DataAccessException e) {
			redirectAttribute.addFlashAttribute("msg",  NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
			redirectAttribute.addFlashAttribute("lecVO", lecVO);
			goPage = "redirect:/admin/lectureView.do?lecCode="+lecVO.getLecCode();
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return goPage;
	}
	
	
}
