package kr.or.ddit.admin.student.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.professor.service.AdminProfessorService;
import kr.or.ddit.admin.student.service.AdminStudentService;
import kr.or.ddit.admin.student.vo.AdminDepartmentInfoVO;
import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.admin.student.vo.SearchMemFormVO;
import kr.or.ddit.admin.student.vo.StuGradeCountVO;
import kr.or.ddit.admin.student.vo.StudentListVO;
import kr.or.ddit.admin.student.vo.TreeModelVO;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
/**
 * @author 조예슬
 * @since 2021. 2. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 15.    조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminStudentListController extends BaseController{
	@Inject
	private AdminStudentService service;
	@Inject
	private AdminProfessorService proService;
	/**zTree 사용하여 학부/학과 정보 조회 **/
	@RequestMapping("/admin/collegeTreeStructure.do")
	public String collegeTreeStructure(Model model)  {
		model.addAttribute("pageTitle", "학부 정보");
		return "admin/adminStudent/collegeTreeStructure";
	}
	// 학부/학과정보 ajax
	@RequestMapping(value="/admin/collegeTreeStructureAjax.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<TreeModelVO> ztreeForAjax(Model model)  {
		List<TreeModelVO> treeList = new ArrayList<>();
		List<TreeModelVO> colList = new ArrayList<>();
		colList = service.retreveCollegeHierarchy();
		for(int i =0 ; i<colList.size();i++) {
			
		}
		List<TreeModelVO> depList = service.retrieveDepartmentHierarchy();
		List<TreeModelVO> proList = service.retrieveProfessorHierarchy();
		treeList.addAll(colList);
		treeList.addAll(depList);
		treeList.addAll(proList);
		return treeList;
	}
	
	// 트리구조에서 학과 선택시 학과정보 가져오기
	@RequestMapping(value="/admin/getCollegeAndState.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> selectDepartmentInfo(@ModelAttribute("depVO")DepartmentVO depVO,Model model) {
		AdminDepartmentInfoVO adminDepVO = new AdminDepartmentInfoVO();
		List<CodeVO> roomList = new ArrayList<>();
		adminDepVO = service.retrieveDepartmentInfo(depVO);
		roomList = service.retrieveLectureRoomInfo(depVO);
		Map<String, Object> map = new HashMap<>();
		map.put("adminDepVO", adminDepVO);
		map.put("roomList", roomList);
		return map;
	}
	@RequestMapping("/admin/selectProfessorDetail.do")
	public String selectProfessorDetail(@RequestParam("memId")String memId,Model model){
		AdminMemVO memVO = proService.retrieveProfessor(AdminMemVO.builder().memId(memId).build());
		model.addAttribute("memVO",memVO);
		return "admin/adminStudent/modal/treeStructureProfessorModal";
	}
	
	/**학생관리 페이지**/
	@RequestMapping("/admin/studentList.do")
	public String studentList(Model model)  {
		List<CodeVO> collegeList = service.retrieveCollegeList();
		List<CodeVO> stateList = service.retrieveStateList();
		model.addAttribute("pageTitle", "학생 관리");
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("stateList", stateList);
		return "admin/adminStudent/studentList";
	}
	
	/**학생/교수관리 단과대정보 ajax**/
	@RequestMapping(value="/admin/getCollegeAndState.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, List<?>> studentListForAjax()  {
		Map<String, List<?>> map = new HashMap<>();
		List<CodeVO> collegeList = service.retrieveCollegeList();
		List<CodeVO> stateList = service.retrieveStateList();
		map.put("collegeList", collegeList);
		map.put("stateList", stateList);
		return map;
	}
	
	/**학생관리 페이지**/
	@RequestMapping(value="/admin/studentList.do",method=RequestMethod.POST)
	public String studentListPost(SearchMemFormVO searchStuVO,Model model)  {
		List<CodeVO> collegeList = service.retrieveCollegeList();
		List<CodeVO> stateList = service.retrieveStateList();
		model.addAttribute("pageTitle", "학생 관리");
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("searchVO", searchStuVO);
		
		return "admin/adminStudent/studentList";
	}
	
	/**학생/교수관리 학과정보 ajax**/
	@RequestMapping(value="/admin/getDepartment.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DepartmentVO> scheduleView(@RequestParam("college")String college,
								Model model) {
		CodeVO colVO = new CodeVO();
		colVO.setCode(college);
		DepartmentVO depVO = new DepartmentVO();
		List<DepartmentVO> depList = service.retrieveDepartmentList(colVO);
		return depList;
	}
	/**학생관리조회**/
	@RequestMapping(value="/admin/studentSearchList.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, List<?>> studentSearchList(@ModelAttribute("searchStuVO")SearchMemFormVO searchStuVO,Model model) {
		searchStuVO.setMemType("ROLE_STUDENT");
		List<AdminMemVO> memList = service.selectMemberList(searchStuVO);
		
		List<StuGradeCountVO> gradeTotalList = getStudentCountForGrade(memList,model);
		
		Map<String, List<?>> map = new HashMap<>();
		map.put("gradeTotalList", gradeTotalList);
		map.put("memList", memList);
		model.addAttribute("searchVO", searchStuVO);
		model.addAttribute("gradeTotalList", gradeTotalList);
		model.addAttribute("memList", memList);
		
		return map;
	}

	
	/**학적정보 수정**/
	@RequestMapping(value="/admin/updateStudentState.do",method=RequestMethod.POST)
	public String updateStudentState(@ModelAttribute("stuList")StudentListVO stuList,Model model) {
		LOGGER.info("{}", stuList);
		try {
			int cnt = service.modifyStudentState(stuList);
			//ajax
			model.addAttribute("msg", "변경완료되었습니다.");
		} catch (DataAccessException e) {
			//ajax
			model.addAttribute("msg", "서버오류");
		}
		return "jsonView";
	}
	/**학생상세조회**/
	@RequestMapping(value="/admin/studentView.do")
	public String studentView(@RequestParam("memId")String memId,SearchMemFormVO searchVO,Model model) {
		try {
			AdminMemVO stuVO = new AdminMemVO();
		
			stuVO.setMemId(memId);
			stuVO = service.retrieveStudent(stuVO);
			model.addAttribute("stuVO", stuVO);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("pageTitle", "학생 상세 정보");
			model.addAttribute("pageLink", "/admin/studentList.do");
		} catch (DataAccessException e) {
			model.addAttribute("msg", "서버오류");
		}
		
		return "admin/adminStudent/studentView";
	}
	
	/**학생리스트 엑셀 다운로드**/
	@RequestMapping("/admin/downloadStudentListExcel.do")
	public void downExcel(SearchMemFormVO searchVO,HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/msexcel");
		response.setHeader("Content-Disposition", "attachment;filename=\"studentList.xlsx\"");
		
		try {
			InputStream io = new ClassPathResource("kr/or/ddit/jxlsTemplate/excel/studentList.xlsx").getInputStream();
			OutputStream os = response.getOutputStream();
			searchVO.setMemType("ROLE_STUDENT");
			List<AdminMemVO> dataList = service.selectMemberList(searchVO);
			Context context = new Context();
			context.putVar("dataList", dataList);
			
			JxlsHelper.getInstance().processTemplate(io, os, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**학생정보수정 폼**/
	@RequestMapping("/admin/updateStudentForm.do")
	public String updateStudentForm(@RequestParam("memId")String memId,Model model) {
		AdminMemVO stuVO = new AdminMemVO();
		stuVO.setMemId(memId);
		stuVO = service.retrieveStudent(stuVO);
		model.addAttribute("stuVO", stuVO);
		model.addAttribute("pageTitle", "학생 정보 수정");
		model.addAttribute("pageLink", "/admin/studentList.do");
		return "admin/adminStudent/studentUpdateForm";
	}
	
	/**학생정보 수정**/
	@RequestMapping(value="/admin/updateStudent.do",method=RequestMethod.POST)
	public String updateStudent(@Validated(UpdateGroup.class) @ModelAttribute("stuVO")AdminMemVO stuVO,BindingResult errors,Model model,RedirectAttributes redirectModel) {
		boolean valid = !errors.hasErrors();
		if(valid) {
			try {
				int cnt = service.modifyStudent(stuVO);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("수정 완료").type(NotyType.success).build());
				return "redirect:/admin/studentView.do?memId="+stuVO.getMemId();
			} catch (Exception e) {
				model.addAttribute("message",NotyMessageVO.builder("서버오류").build());
				return "admin/adminStudent/studentUpdateForm";
			}
			
		}else {
			model.addAttribute("message",NotyMessageVO.builder("입력형식에 맞게 입력하세요.").build());
			return "admin/adminStudent/studentUpdateForm";
		}
		
	}
	
	
	
	// 학년별 재학/휴학/자퇴/졸업 수 구하는 메서드
	private List<StuGradeCountVO> getStudentCountForGrade(List<AdminMemVO> memList, Model model) {
		StuGradeCountVO gradeVO1 = new StuGradeCountVO();
		StuGradeCountVO gradeVO2 = new StuGradeCountVO();
		StuGradeCountVO gradeVO3 = new StuGradeCountVO();
		StuGradeCountVO gradeVO4 = new StuGradeCountVO();
		StuGradeCountVO gradeVO5 = new StuGradeCountVO();
		// 1학년
		int firstIng=0;
		int firstRest=0;
		int firstLeft=0;
		int firstEnd=0;
		int firstTotal=0;
		// 2 학년
		int SecondIng=0;
		int SecondRest=0;
		int SecondLeft=0;
		int SecondEnd=0;
		int SecondTotal=0;
		// 3 학년
		int ThirdIng=0;
		int ThirdRest=0;
		int ThirdLeft=0;
		int ThirdEnd=0;
		int ThirdTotal=0;
		// 4 학년
		int FourthIng=0;
		int FourthRest=0;
		int FourthLeft=0;
		int FourthEnd=0;
		int FourthTotal=0;
		// 총합
		int IngCnt=0;
		int RestCnt=0;
		int LeftCnt=0;
		int EndCnt=0;
		int TotalCnt=memList.size();
		for(int idx = 0 ; idx < memList.size() ; idx++) {
			String memState = memList.get(idx).getMemState();
			String memGrd = memList.get(idx).getMemGrd();
			if(memState.equals("ING")) {
				IngCnt += 1; 
				if(memGrd.equals("1")||memGrd.equals("2")) {firstIng +=1;firstTotal+=1;}
				if(memGrd.equals("3")||memGrd.equals("4")) {SecondIng +=1;SecondTotal+=1;}
				if(memGrd.equals("5")||memGrd.equals("6")) {ThirdIng +=1;ThirdTotal+=1;}
				if(memGrd.equals("7")||memGrd.equals("8")) {FourthIng +=1;FourthTotal+=1;}
			}else if(memState.equals("REST")) {
				RestCnt +=1;
				if(memGrd.equals("1")||memGrd.equals("2")) {firstRest +=1;firstTotal+=1;}
				if(memGrd.equals("3")||memGrd.equals("4")) {SecondRest +=1;SecondTotal+=1;}
				if(memGrd.equals("5")||memGrd.equals("6")) {ThirdRest +=1;ThirdTotal+=1;}
				if(memGrd.equals("7")||memGrd.equals("8")) {FourthRest +=1;FourthTotal+=1;}
			}else if(memState.equals("LEFT")) {
				LeftCnt +=1;
				if(memGrd.equals("1")||memGrd.equals("2")) {firstLeft +=1;firstTotal+=1;}
				if(memGrd.equals("3")||memGrd.equals("4")) {SecondLeft +=1;SecondTotal+=1;}
				if(memGrd.equals("5")||memGrd.equals("6")) {ThirdLeft +=1;ThirdTotal+=1;}
				if(memGrd.equals("7")||memGrd.equals("8")) {FourthLeft +=1;FourthTotal+=1;}
			}else if(memState.equals("END")) {
				EndCnt +=1; 
				if(memGrd.equals("1")||memGrd.equals("2")) {firstEnd +=1;firstTotal+=1;}
				if(memGrd.equals("3")||memGrd.equals("4")) {SecondEnd +=1;SecondTotal+=1;}
				if(memGrd.equals("5")||memGrd.equals("6")) {ThirdEnd +=1;ThirdTotal+=1;}
				if(memGrd.equals("7")||memGrd.equals("8")||memGrd.equals("0")) {FourthEnd +=1;FourthTotal+=1;}
			}
			
		}
		// 리스트 Vo 에 담기
		List<StuGradeCountVO> gradeTotalList = new ArrayList<>();
		gradeVO1.setIngCnt(firstIng);
		gradeVO1.setRestCnt(firstRest);
		gradeVO1.setLeftCnt(firstLeft);
		gradeVO1.setEndCnt(firstEnd);
		gradeVO1.setTotalCnt(firstTotal);
		gradeTotalList.add(gradeVO1);
		gradeVO2.setIngCnt(SecondIng);      
		gradeVO2.setRestCnt(SecondRest);    
		gradeVO2.setLeftCnt(SecondLeft);    
		gradeVO2.setEndCnt(SecondEnd);      
		gradeVO2.setTotalCnt(SecondTotal);  
		gradeTotalList.add(gradeVO2);
		gradeVO3.setIngCnt(ThirdIng);      
		gradeVO3.setRestCnt(ThirdRest);    
		gradeVO3.setLeftCnt(ThirdLeft);    
		gradeVO3.setEndCnt(ThirdEnd);      
		gradeVO3.setTotalCnt(ThirdTotal);  
		gradeTotalList.add(gradeVO3);
		gradeVO4.setIngCnt(FourthIng);      
		gradeVO4.setRestCnt(FourthRest);    
		gradeVO4.setLeftCnt(FourthLeft);    
		gradeVO4.setEndCnt(FourthEnd);      
		gradeVO4.setTotalCnt(FourthTotal);  
		gradeTotalList.add(gradeVO4);
		gradeVO5.setIngCnt(IngCnt);      
		gradeVO5.setRestCnt(RestCnt);    
		gradeVO5.setLeftCnt(LeftCnt);    
		gradeVO5.setEndCnt(EndCnt);      
		gradeVO5.setTotalCnt(TotalCnt);  
		gradeTotalList.add(gradeVO5);
		
		
		return gradeTotalList;
		
	}
	
	
}
