package kr.or.ddit.admin.scholarship.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.scholarship.service.AdminScholarshipService;
import kr.or.ddit.admin.scholarship.vo.ScholarshipPayedDetailVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarshipFundVO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.SearchVO;
/**
 * @author 조예슬
 * @since 2021. 1. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 25.   조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminScholarshipController {
	@Inject
	private AdminScholarshipService service;
	/**관리자 수혜목록조회
	 * @throws Exception **/
	@RequestMapping("/admin/scholarshipList.do")
	public String  scholarshipList(@RequestParam(value="page", required=false,defaultValue="1" ) int currentPage,
									@ModelAttribute("searchVO") SearchVO searchVO,
										Model model) {
		try {
			PagingVO<ScholarshipVO> pagingVO = new PagingVO<>(10,5);
			pagingVO.setSearchVO(searchVO);
			int totalRecord = service.retrieveScholarFundCount(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setCurrentPage(currentPage);
			List<ScholarshipVO> scholarList = service.retrieveScholarFundList(pagingVO);
			pagingVO.setDataList(scholarList);
			model.addAttribute("paginationInfo",new CustomPaginationInfo<>(pagingVO));
			model.addAttribute("pageTitle", "장학금 수혜목록 조회");
		} catch (DataAccessException e) {
			model.addAttribute("msg", "서버오류");
		}
		
		return "admin/adminScholarship/scholarshipPayedList";
	}
	
	/**관리자 조회조건 학기정보 가져오기**/
	@RequestMapping(value="/admin/getAllScholarSemester.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ScholarshipFundVO> getAllScholarSemester() {
		List<ScholarshipFundVO> smstList = service.retrieveAllScholarSemester();
		return smstList;
	}
	/**장학금 수혜목록 상세조회**/
	@RequestMapping("/admin/{sfundNo}/scholarshipDetail.do")
	public String scholarshipDetail(@PathVariable(value="sfundNo", required=true) int sfundNo, Model model) {
		ScholarshipFundVO sfundVO = new ScholarshipFundVO();
		sfundVO.setSfundNo(sfundNo);
		ScholarshipPayedDetailVO schVO = service.retreveScholarPayedDetail(sfundVO);
		model.addAttribute("schVO", schVO);
		model.addAttribute("pageTitle", "장학금 수혜 상세조회");
		return "admin/adminScholarship/modal/scholarshipPayedDetail";
	}
	
	
}
