package kr.or.ddit.admin.lecture.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author 조예슬
 * @since 2021. 2. 23.
 * 과목 조회/등록/수정/삭제
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 23.     조예슬      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
import kr.or.ddit.admin.lecture.service.AdminSubjectService;
import kr.or.ddit.admin.lecture.vo.LecSearchVO;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
/**
 * @author 조예슬
 * @since 2021. 2. 12.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 12.   조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminSubjectController extends BaseController{
	@Inject
	private AdminSubjectService service;
	
	@ModelAttribute("subVO")
	public SubjectVO subVO() {
		return new SubjectVO();
	}
	// 등록/수정폼 지정
	private void addModify(Model model,String type) {
		model.addAttribute("TYPE", type);
	}
	/**관리자 과목조회 페이지**/
	@RequestMapping("/admin/subjectList.do")
	public String subjectList(Model model) {
		model.addAttribute("pageTitle", "과목 관리");
		return "admin/adminLecture/subjectList";
	}
	/**관리자 과목 검색 조회 ajax**/
	@RequestMapping(value="/admin/subjectSearchList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String lectureListForAjax(@RequestParam(value="page", required=false,defaultValue="1" ) int currentPage,
								@ModelAttribute("searchVO") LecSearchVO lecsearchVO,
								Model model)  { 
		try {
			PagingVO<SubjectVO> pagingVO = new PagingVO<>(15,5);
			SearchVO searchVO = new SearchVO();
			if(lecsearchVO.getSearchType()!=null &&lecsearchVO.getSearchWord()!=null) {
				searchVO.setSearchType(lecsearchVO.getSearchType());
				searchVO.setSearchWord(lecsearchVO.getSearchWord());
			}
			SubjectVO subVO = new SubjectVO();
			if(StringUtils.isNotBlank(lecsearchVO.getCollege())) {
				subVO.setCode(lecsearchVO.getCollege());
			}
			if(lecsearchVO.getDepartment()!=null) {
				subVO.setDepNo(lecsearchVO.getDepartment());
			}
			if(lecsearchVO.getSubDetail()!=null) {
				subVO.setSubDetail(lecsearchVO.getSubDetail());
			}
			pagingVO.setSearchVO(searchVO);
			pagingVO.setSearchDetail(subVO);
			int totalRecord = service.retrieveSubjectCount(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			
			pagingVO.setCurrentPage(currentPage);
			List<SubjectVO> subList = service.retrieveSubjectList(pagingVO);
			pagingVO.setDataList(subList);
			model.addAttribute("pagingVO", pagingVO);
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	/**관리자과목등록페이지**/
	@RequestMapping("/admin/insertSubjectForm.do")
	public String insertSubjectForm(Model model) {
		addModify(model,"INSERT");
		model.addAttribute("pageTitle", "과목 등록");
		model.addAttribute("pageLink", "/admin/subjectList.do");
		return "admin/adminLecture/subjectForm";
	}
	/**과목등록**/
	@RequestMapping(value="/admin/insertSubject.do",method=RequestMethod.POST)
	public String insertSubject(@Validated(InsertGroup.class) @ModelAttribute("subVO")SubjectVO subVO,
									BindingResult errors, Model model,RedirectAttributes redirectModel) {
		boolean valid = !errors.hasErrors();
		if(valid) {
			try {
				int cnt = service.createSubject(subVO);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("등록성공").type(NotyType.success).build());
				return "redirect:/admin/subjectView.do?subCode="+subVO.getSubCode();
			} catch (Exception e) {
				addModify(model,"INSERT");
				model.addAttribute("message", NotyMessageVO.builder("서버오류").build());
				return "admin/adminLecture/subjectForm";
			}
			
		}else {
			addModify(model,"INSERT");
			model.addAttribute("message", NotyMessageVO.builder("입력 형식에 맞게 입력해주세요.").build());
			return "admin/adminLecture/subjectForm";
		}
		
	}
	/**과목상세조회**/
	@RequestMapping("/admin/subjectView.do")
	public String subjectView(@RequestParam("subCode")String subCode,Model model) {
		try {
			SubjectVO subVO = new SubjectVO();
			subVO.setSubCode(subCode);
			
			subVO = service.retrieveSubject(subVO);
			model.addAttribute("subVO", subVO);
			model.addAttribute("pageTitle", "과목 상세 조회");
			model.addAttribute("pageLink", "/admin/subjectList.do");
		} catch (Exception e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		
		return "admin/adminLecture/subjectView";
	}
	/**과목수정폼**/
	@RequestMapping(value="/admin/updateSubjectForm.do",method=RequestMethod.POST)
	public String updateSubjectForm(@ModelAttribute("subVO")SubjectVO subVO,Model model) {
		subVO = service.retrieveSubject(subVO);
		model.addAttribute("subVO", subVO);
		addModify(model,"MODIFY");
		model.addAttribute("pageTitle", "과목 수정");
		model.addAttribute("pageLink", "/admin/subjectList.do");
		return "admin/adminLecture/subjectForm";
	}
	/**과목수정**/
	@RequestMapping(value="/admin/updateSubject.do",method=RequestMethod.POST)
	public String updateSubject(@Validated(UpdateGroup.class) @ModelAttribute("subVO")SubjectVO subVO,BindingResult errors,
								Model model,RedirectAttributes redirectModel) {
		boolean valid = !errors.hasErrors();
		if(valid) {
			try {
				int cnt = service.modifySubject(subVO);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("수정완료").type(NotyType.success).build());
				return "redirect:/admin/subjectView.do?subCode="+subVO.getSubCode();
			} catch (Exception e) {
				addModify(model,"MODIFY");
				model.addAttribute("message", NotyMessageVO.builder("서버오류").build());
				return "admin/adminLecture/subjectForm";
			}
			
		}else {
			model.addAttribute("message", NotyMessageVO.builder("입력형식에 맞게 입력하세요.").build());
			addModify(model,"MODIFY");
			return "admin/adminLecture/subjectForm";
		}
	}
	
	
}
