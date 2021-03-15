package kr.or.ddit.admin.lecture.controller;

import java.util.ArrayList;
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

import kr.or.ddit.admin.lecture.service.AdminLectureService;
import kr.or.ddit.admin.lecture.vo.AdminLectureVO;
import kr.or.ddit.admin.lecture.vo.TimeListVO;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.SubjectVO;
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
public class AdminLectureInsertController extends BaseController{
	@Inject
	private AdminLectureService service;

	@ModelAttribute("lecVO")
	public LectureVO lecVO() {
		return new LectureVO();
	}
	// 현재 학기 조회 메서드
	private CodeVO getCurrentSmst() {
		CodeVO currentSmst = new CodeVO();
		currentSmst.setGroupCode("A00");
		currentSmst = service.retrieveCurrentSmst(currentSmst);
		return currentSmst;
	}
	/**강의등록 초기페이지**/
	@RequestMapping("/admin/insertLectureForm.do")
	public String lectureForm(Model model) { 
		model.addAttribute("pageTitle", "강의 등록");
		return "admin/adminLecture/lectureForm";

	}
	/**강의등록 초기페이지**/
	@RequestMapping(value="/admin/insertLectureGetCollege.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String lectureFormForAjax(Model model) { 
		//학부코드가져오기
		List<CodeVO> collegeList;
		try {
			collegeList = service.retrieveCollegeList();
			// 현재학기조회
			CodeVO currentSmst = getCurrentSmst();
			
			model.addAttribute("currentSmst", currentSmst);
			model.addAttribute("collegeList", collegeList);
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
		
	}
	
	/**학부 선택 후 해당 학과/강의실 가져오기**/
	@RequestMapping(value="/admin/selectLecCollege.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String  selectColleage(@RequestParam("college")String college,Model model)  {
		try {
			//학과
			List<DepartmentVO> depList;
			depList = service.retrieveDepartmentList(college);
			//강의실
			List<CodeVO> roomList = service.retrieveRoomList(college);
			model.addAttribute("department", depList);
			model.addAttribute("lecRoom", roomList);
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	/**학과선택후 해당개설 과목 /교수 정보 가져오기**/
	@RequestMapping(value="/admin/selectLecDepartment.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String selectDepartment(@RequestParam("depNo")String depNo,Model model) {
		List<SubjectVO> subList;
		try {
			subList = service.retrieveSubjectList(depNo);
			model.addAttribute("subject", subList);
			List<MemberVO> proList;
			try {
				proList = service.retrieveProfessorList(depNo);
				model.addAttribute("proList", proList);
			} catch (DataAccessException e) {
				LOGGER.error(this.getClass().getName() + " " + e.getMessage());
			}
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	/**강의실선택시 정보가져오기**/
	@RequestMapping(value="/admin/selectLectureRoom.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String selectLectureRoom(@ModelAttribute("adminlecVO")AdminLectureVO adminlecVO,Model model) {
		List<LectureVO> lecVO;
		try {
			lecVO = service.retrieveAvaliableDays(adminlecVO);
			model.addAttribute("roomListVO", lecVO);
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	/**교수선택시 시간표가져오기**/
	@RequestMapping(value="/admin/selectLectureProfessor.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String selectProfessor(@ModelAttribute("adminlecVO")AdminLectureVO adminlecVO,Model model) {
		List<LectureVO> lecVO;
		try {
			lecVO = service.retrieveProfessorTimeTable(adminlecVO);
			model.addAttribute("roomListVO", lecVO);
		} catch (DataAccessException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		return "jsonView";
	}
	
	
	
	/**강의등록**/
	@RequestMapping(value="/admin/insertLecture.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String insertLecture(@Validated(InsertGroup.class) @ModelAttribute("lecVO")LectureVO lecVO,BindingResult errors, Model model) {
		boolean valid = !errors.hasErrors();
		if(valid) {
			List<TimeListVO> timeList = lecVO.getTimeList();
			// 요일을 다 선택해서 등록시 테이블에  5row가 insert되야하므로 VO 5개 생성
			//월
			LectureTimeVO timeVO1 = new LectureTimeVO();
			//화
			LectureTimeVO timeVO2 = new LectureTimeVO();
			//수
			LectureTimeVO timeVO3 = new LectureTimeVO();
			//목
			LectureTimeVO timeVO4 = new LectureTimeVO();
			//금
			LectureTimeVO timeVO5 = new LectureTimeVO();
			List<LectureTimeVO> lecTimeList = new ArrayList<>();
			// 수업 시작시간 / 종료시간 set해주기
			for(int i =1 ; i<timeList.size();i++) {
				if(timeList.get(i).getMon()!=null) {
					timeVO1.setLtimeDay("월");
					if(timeVO1.getLtimeStart()==null) {
						timeVO1.setLtimeStart(timeList.get(i).getMon());
					}
					timeVO1.setLtimeEnd(timeList.get(i).getMon());
				}
				if(timeList.get(i).getTue()!=null) {
					timeVO2.setLtimeDay("화");
					if(timeVO2.getLtimeStart()==null) {
						timeVO2.setLtimeStart(timeList.get(i).getTue());
					}
					timeVO2.setLtimeEnd(timeList.get(i).getTue());
				}
				if(timeList.get(i).getWed()!=null) {
					timeVO3.setLtimeDay("수");
					if(timeVO3.getLtimeStart()==null) {
						timeVO3.setLtimeStart(timeList.get(i).getWed());
					}
					timeVO3.setLtimeEnd(timeList.get(i).getWed());
				}
				if(timeList.get(i).getThu()!=null) {
					timeVO4.setLtimeDay("목");
					if(timeVO4.getLtimeStart()==null) {
						timeVO4.setLtimeStart(timeList.get(i).getThu());
					}
					timeVO4.setLtimeEnd(timeList.get(i).getThu());
				}
				if(timeList.get(i).getFri()!=null) {
					timeVO5.setLtimeDay("금");
					if(timeVO5.getLtimeStart()==null) {
						timeVO5.setLtimeStart(timeList.get(i).getFri());
					}
					timeVO5.setLtimeEnd(timeList.get(i).getFri());
				}

			}
			// 요일에 비어있지 않을경우 list 에 담아주기
			if(StringUtils.isNotBlank(timeVO1.getLtimeDay())) {
				lecTimeList.add(timeVO1);
			}
			if(StringUtils.isNotBlank(timeVO2.getLtimeDay())) {
				lecTimeList.add(timeVO2);
			}
			if(StringUtils.isNotBlank(timeVO3.getLtimeDay())) {
				lecTimeList.add(timeVO3);
			}
			if(StringUtils.isNotBlank(timeVO4.getLtimeDay())) {
				lecTimeList.add(timeVO4);
			}
			if(StringUtils.isNotBlank(timeVO5.getLtimeDay())) {
				lecTimeList.add(timeVO5);
			}
			
			// 수업 사이즈에 상관없이 테이블에 들어가는 강의실코드는 동일해서 for문을 돌려 set
			for(int idx = 0 ; idx<lecTimeList.size() ; idx++) {
				lecTimeList.get(idx).setRoomCode(lecVO.getRoomCode());
			}
			
			try {
				String lecCode = service.createLecture(lecVO,lecTimeList);
				//ajax
				model.addAttribute("msg", "성공");
				model.addAttribute("lecCode", lecCode);
			} catch (DataAccessException e) {
				//ajax
				model.addAttribute("msg", "등록에 실패했습니다.");
				LOGGER.error(this.getClass().getName() + " " + e.getMessage());
			}
			
			
		}else {
			model.addAttribute("msg", "모든 항목을 입력해주세요.");
		}
		return "jsonView";
		
	}

}
