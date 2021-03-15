package kr.or.ddit.lms.professor.studentmanagement.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.professor.studentmanagement.service.ProfessorStudentManagementService;
import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.lms.student.profile.service.ProfileService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author wlsdn
 * @since 2021. 3. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 3. 8.     wlsdn      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class ProfessorStudentManagementController extends BaseController {
	
	@Inject
	ProfessorStudentManagementService service;
	
	@Inject
	private ProfileService profileService;
	//학생리스트
	@RequestMapping("/lms/professor/student/list.do")
	public String studentManagement(
			@RequestParam(value="page", required=false,defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			,Model model
			) {
		PagingVO<MemberVO> paging = new PagingVO<>(10,5);
		paging.setSearchVO(searchVO);
		String searchType = searchVO.getSearchType();
		paging.setMemId(authMember.getMemId());
		int totalCount = service.selectMemberListCount(paging);
		paging.setTotalRecord(totalCount);
		paging.setCurrentPage(currentPage);
		List<MemberVO> memberList = service.selectMemberList(paging);
		paging.setDataList(memberList);
		
		model.addAttribute("paginationInfo", new CustomPaginationInfo<MemberVO>(paging));		
		model.addAttribute("pageTitle", "지도 학생");

		
		return "lmsProfessor/student/studentManagement";
	}
	
	
	//교수 학생 프로필 조회
	@RequestMapping("/lms/professor/student/profile.do")
	public String studentManagementPofile(
			@RequestParam("memId") String memId,
			Model model) {
			ProfileVO profile = new ProfileVO();
			MemberVO member = new MemberVO();
			member.setMemId(memId);
			profile = profileService.profileView(member);
			model.addAttribute("profileVO",profile);
		
		return "admin/adminTuition/adminstuProfile/adminStuProfile";
	}
	
	/**교수 학생 엑셀다운로드**/
	@RequestMapping("/lms/professor/student/downloadExcel.do")
	public void downExcel(@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO")  SearchVO searchVO , Model model,@AuthenticationPrincipal(expression="realMember") MemberVO authMember,HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/msexcel");
		response.setHeader("Content-Disposition", "attachment;filename=\"studentList.xlsx\"");
		
		try {
			InputStream io = new ClassPathResource("kr/or/ddit/jxlsTemplate/excel/studentList.xlsx").getInputStream();
			OutputStream os = response.getOutputStream();
			PagingVO<MemberVO> paging = new PagingVO<>(10,5);
			paging.setSearchVO(searchVO);
			paging.setMemId(authMember.getMemId());
			int totalCount = service.selectMemberListCount(paging);
			paging.setTotalRecord(totalCount);
			paging.setCurrentPage(currentPage);
			List<MemberVO> memberList = service.selectAllMemberList(paging);
			Context context = new Context();
			context.putVar("dataList", memberList);
			
			JxlsHelper.getInstance().processTemplate(io, os, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
//	public ExcelDownloadView excel(@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//			, @ModelAttribute("searchVO")  SearchVO searchVO , Model model,@AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
//		
//		studentManagement(currentPage, searchVO, authMember,model);
//		model.addAttribute(ExcelDownloadView.TYPENAME, MemberVO.class);
//		model.addAttribute(ExcelDownloadView.FILENAME, "memberList.xlsx");
//		
//		return new ExcelDownloadView() {
//			@Override
//			protected void makeDataRow(Map<String, Object> model, Workbook workbook, Sheet sheet, int rowNum,
//					String[] properties) throws Exception {
//				
//				CustomPaginationInfo<MemberVO> pagination = (CustomPaginationInfo<MemberVO>) model.get("paginationInfo");
//				List<?> dataList = pagination.getPagingVO().getDataList();
//				
//				CellStyle dataStyle = createStyle(workbook);
//				
//				int columnCount = createRows(sheet, dataList, rowNum, dataStyle, properties);
//				
//				for(int i=0; i<columnCount; i++) {
//					sheet.autoSizeColumn(i);
//				}
//				
//				Footer footer = sheet.getFooter();
//				footer.setRight(String.format("%d of %d", pagination.getCurrentPageNo(), pagination.getTotalPageCount()));
//			}
//		};
//	}

	
	
}
