package kr.or.ddit.admin.professor.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
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
import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.admin.student.vo.SearchMemFormVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
/**
 * 
 * @author 조예슬
 * 교수 조회/등록/수정/삭제 Controller
 * @since 2021. 2. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 22.     조예슬      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminProfessorController {
	@Inject
	private AdminProfessorService service;
	@Inject
	private AdminStudentService stuService;
	/**교수관리 초기페이지**/
	@RequestMapping("/admin/professorList.do")
	public String professorList(Model model) {
		model.addAttribute("pageTitle", "교수 관리");
		return "admin/adminStudent/professorList";
	}
	// 기본 VO 세팅
	@ModelAttribute("memVO")
	public AdminMemVO membervo() {
		return new AdminMemVO();
	}
	
	/**교수관리 검색조회**/
	@RequestMapping(value="/admin/professorSearchList.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<AdminMemVO> professorList(@ModelAttribute("searchMemVO")SearchMemFormVO searchMemVO,Model model) {
		searchMemVO.setMemType("ROLE_PROFESSOR");
		List<AdminMemVO> memList = stuService.selectMemberList(searchMemVO);
		model.addAttribute("memList", memList);
		return memList;
	}
	/**교수등록form**/
	@RequestMapping("/admin/insertProfessorForm.do")
	public String insertProfessorForm(Model model) {
		model.addAttribute("TYPE", "INSERT");
		model.addAttribute("pageTitle", "교수 등록");
		model.addAttribute("pageLink", "/admin/professorList.do");
		return "admin/adminStudent/professorForm";
	}
	/**교수등록**/
	@RequestMapping(value="/admin/insertProfessor.do",method=RequestMethod.POST)
	public String insertProfessor(@Validated(InsertGroup.class) AdminMemVO memVO,BindingResult errors,Model model,RedirectAttributes redirectModel) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		memVO.setMemAdmission(year+"");
		boolean valid = !errors.hasErrors();
		if(valid) {
			try {
				String memId = service.createProfessor(memVO);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("등록성공").type(NotyType.success).build());
				return "redirect:/admin/professorView.do?memId="+memId;
			} catch (Exception e) {
				model.addAttribute("TYPE", "INSERT");
				model.addAttribute("message", NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				return "admin/adminStudent/professorForm";
			}
			
		}else {
			model.addAttribute("message", NotyMessageVO.builder("입력형식에 맞게 입력해주세요.").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
			return "admin/adminStudent/professorForm";
		}
				
	}
	/**교수상세조회**/
	@RequestMapping("/admin/professorView.do")
	public String professorView(@RequestParam("memId")String memId,AdminMemVO memVO,Model model) {
		memVO = service.retrieveProfessor(AdminMemVO.builder().memId(memId).build());
		model.addAttribute("memVO",memVO);
		model.addAttribute("pageTitle", "교수 상세 조회");
		model.addAttribute("pageLink", "/admin/professorList.do");
		return "admin/adminStudent/professorView";
	}
	
	/**교수 개인정보 수정페이지**/
	@RequestMapping(value="/admin/updateProfessorForm.do",method=RequestMethod.POST)
	public String updateProfessorForm(AdminMemVO memVO,Model model) {
			memVO = service.retrieveProfessor(memVO);
			addModify(model);
			model.addAttribute("memVO", memVO);
			model.addAttribute("pageTitle", "교수 정보 수정");
			model.addAttribute("pageLink", "/admin/professorList.do");
		return "admin/adminStudent/professorForm";
	}
	
	/**교수 개인정보 수정**/
	@RequestMapping(value="/admin/updateProfessor.do",method=RequestMethod.POST)
	public String updateProfessor(@Validated(UpdateGroup.class) AdminMemVO memVO,BindingResult errors,Model model,RedirectAttributes redirectModel) {
		boolean valid = !errors.hasErrors();
		if(valid) {
			//교수 수정시 퇴사or퇴직 으로 변경할경우 퇴사날짜입력
			String memState = memVO.getMemState();
			if(memState=="END"||memState=="LEFT") {
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				memVO.setMemGraduation(year+"");
			}
			try {
				int cnt = service.modifyProfessor(memVO);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("수정성공").type(NotyType.success).build());
				return "redirect:/admin/professorView.do?memId="+memVO.getMemId();
				
			} catch (Exception e) {
				addModify(model);
				model.addAttribute("message", NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				return "admin/adminStudent/professorForm";
			}
			
		}else {
			addModify(model);
			model.addAttribute("memVO", memVO);
			model.addAttribute("message", NotyMessageVO.builder("입력형식에 맞게 입력해주세요.").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
			return "admin/adminStudent/professorForm";
		}
		
	}
	
	/**교수리스트 엑셀 다운로드**/
	@RequestMapping(value="/admin/downloadProfessorListExcel.do",method=RequestMethod.POST)
	public void downExcel(SearchMemFormVO searchVO,HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/msexcel");
		response.setHeader("Content-Disposition", "attachment;filename=\"professorList.xlsx\"");
		try {
			InputStream io = new ClassPathResource("kr/or/ddit/jxlsTemplate/excel/professorList.xlsx").getInputStream();
			OutputStream os = response.getOutputStream();
			searchVO.setMemType("ROLE_PROFESSOR");
			List<AdminMemVO> dataList = stuService.selectMemberList(searchVO);
			Context context = new Context();
			context.putVar("dataList", dataList);
			
			JxlsHelper.getInstance().processTemplate(io, os, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// 수정/등록 폼 에서 수정 명시
	private void addModify(Model model) {
		model.addAttribute("TYPE", "MODIFY");
		
	}
	
}
