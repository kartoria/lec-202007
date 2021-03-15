package kr.or.ddit.admin.scholarship.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.scholarship.service.AdminScholarshipService;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
/**
 * @author 조예슬
 * @since 2021. 2. 01.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 01.     조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminScholarShipTypeController extends BaseController {
	@Inject
	private AdminScholarshipService service;
	/**장학 유형 조회**/
	@RequestMapping("/admin/scholarshipType.do")
	public String scholarshipType(Model model)  {
		
		List<ScholarshipVO> scholarList;
		try {
			scholarList = service.retrieveScholarTypeList();
			model.addAttribute("scholarship",scholarList);
			model.addAttribute("pageTitle", "장학 유형");
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return "admin/adminScholarship/scholarShipTypeList";
	}
	/**장학 유형 등록 페이지**/
	@RequestMapping("/admin/insertScholarTypeForm.do")
	public String insertTypeForm(ScholarshipVO schVO,Model model) {
		model.addAttribute("schVO", schVO);
		model.addAttribute("pageTitle", "장학 유형 등록");
		model.addAttribute("pageLink", "/admin/scholarshipType.do");
		return "admin/adminScholarship/scholarshipForm";
	}
	/**장학 유형 등록**/
	@RequestMapping(value="/admin/insertScholarType.do",method=RequestMethod.POST)
	public String insertType(@Validated(InsertGroup.class) @ModelAttribute("schVO")ScholarshipVO schVO,BindingResult errors,RedirectAttributes redirectModel) {
		String goPage = null;
		boolean valid = !errors.hasErrors();
		if(valid) {
			try {
				int cnt = service.createScholarType(schVO);
				redirectModel.addFlashAttribute("message", notyInsertSuccessMessage());
				goPage = "redirect:/admin/scholarshipType.do";
			} catch (DataAccessException e) {
				redirectModel.addFlashAttribute("message", notyErrorMessage());
				goPage = "admin/adminScholarship/scholarshipForm";
			}
		}else {
			goPage = "admin/adminScholarship/scholarshipForm";
		}
		return goPage;
	}
	/**장학 유형 수정 페이지**/
	@RequestMapping("/admin/updateScholarForm.do")
	public String updateForm(@RequestParam("schCode")String schCode,Model model ) {
		ScholarshipVO schVO = service.retrieveScholarType(schCode);
		model.addAttribute("schVO", schVO);
		model.addAttribute("TYPE", "MODIFY");
		model.addAttribute("pageTitle", "장학 유형 수정");
		model.addAttribute("pageLink", "/admin/scholarshipType.do");
		return "admin/adminScholarship/scholarshipForm";
	}
	/**장학 유형 수정**/
	@RequestMapping(value="/admin/updateScholarType.do",method=RequestMethod.POST)
	public String update(@Validated(UpdateGroup.class) @ModelAttribute("schVO")ScholarshipVO schVO,BindingResult errors,Model model,RedirectAttributes redirectModel ) {
		String goPage = null;
		boolean valid = !errors.hasErrors();
		if(valid) {
			try {
				int cnt = service.modifyScholarType(schVO);
				redirectModel.addFlashAttribute("message",NotyMessageVO.builder("수정완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
				goPage = "redirect:/admin/scholarshipType.do";
			} catch (DataAccessException e) {
				model.addAttribute("message",  NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				goPage = "admin/adminScholarship/scholarshipForm";
			}
		}else {
			model.addAttribute("msg",  NotyMessageVO.builder("형식에 맞게 입력해주세요.").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
			goPage = "admin/adminScholarship/scholarshipForm";
		}
		
		
		return goPage;
	}

	/**장학 유형 삭제(비활성)**/
	@RequestMapping("/admin/deleteScholarType.do")
	public String deleteType(@RequestParam("schCode")String schCode,Model model,RedirectAttributes redirectModel ) {
		String goPage = null;
			try {
				int result = service.removeScholarType(schCode);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("비활성화 성공").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
				goPage = "redirect:/admin/scholarshipType.do";
			} catch (DataAccessException e) {
				model.addAttribute("message",  NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				goPage = "admin/adminScholarship/scholarShipTypeList";

			}
		return goPage;
	}
	
	
}
