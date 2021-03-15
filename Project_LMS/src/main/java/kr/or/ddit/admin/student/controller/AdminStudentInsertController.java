/**
 * @author PC-NEW05
 * @since 2021. 1. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 21.     PC-NEW05      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.admin.student.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import kr.or.ddit.admin.student.service.AdminStudentService;
import kr.or.ddit.admin.tuition.service.AdminTuitionService;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;

/**
 * @author 전진원
 * @since 2021. 2. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 21.     전진원       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminStudentInsertController extends BaseController{
	
	@Inject
	AdminTuitionService tuitionService;
	@Inject
	AdminStudentService service;
	@Inject
	PasswordEncoder passwordEncoder;
	@Inject
	private AdminTuitionService adminService;
	
	@Autowired
	ApplicationContext container;    
	
	//관리자 학생 등록 페이지 접속
	@RequestMapping("/admin/studentForm.do")
	public String studentForm(Model model) {
//		ClassPathResource tmpl = new ClassPathResource("kr/or/ddit/jxlsTemplate/excel/studentInsertSample.xlsx");
		List<CodeVO> codeList = tuitionService.selectCodeName();
		CodeVO codeVO = tuitionService.selectSmst();
		model.addAttribute("codeList", codeList);
		model.addAttribute("codeVO", codeVO);
		model.addAttribute("pageTitle", "신입생등록");
		return "admin/adminStudent/studentForm";
	}
	
	//학과 코드 받아오기
	@RequestMapping(value= "/admin/studentFormDepNo.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> selectDepNo(
			@RequestParam(value="depName") String depName
			){
		
		DepartmentVO departmentVO = new DepartmentVO();
		departmentVO.setDepName(depName);
		
		departmentVO =  service.selectDepNo(departmentVO);
		
		Map<String, Object> resultMap = Collections.singletonMap("departmentVO", departmentVO);
		
		
		return resultMap;
	}
	
		//학과 명 조회
		@RequestMapping(value = "/admin/adminDepNameList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public Map<String, Object> selectDepName(
				@RequestParam("codeName") String codeName
				) {
			CodeVO code = new CodeVO();
			code.setCodeName(codeName);
			List<DepartmentVO> departmentList =adminService.selectDepName(code);
			
			Map<String, Object> resultMap = Collections.singletonMap("departmentList", departmentList);
			return resultMap;
		}
	
		
	//학번 부여 하는 곳 
	public String makeMemId(MemberVO member, int depNo) {
			String memNumber=null;
				//DB에 이미 있으므로 가장 마지막 학번에 +1
				if(member !=null) {
					memNumber = member.getMemId();
				//DB에 없으므로 년도 + 학과 코드, 번호는 밑에서 부여
				}else {
					CodeVO codeVO = tuitionService.selectSmst();
					String smst = codeVO.getCodeName();
					memNumber = smst + depNo ;
				}
				
				return memNumber;
	}
		
	//신입생 한번에 등록
	@RequestMapping(value= "/admin/studentFormInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> studentInsert(
			@RequestParam(value="array") String[][] array,
			@RequestParam(value="depNo") int depNo
			){

		
		List<MemberVO> memberList = new ArrayList<>();
		MemberVO member = new MemberVO();
		
		member.setDepNo(depNo);
		member = service.selectMaxMemId(member);
		//학번 만드는곳
		String memNumber=makeMemId(member,depNo);
		
		//array의 length가 1이면(1명만 추가 할 경우) 2차원 배열로 인식을 못해서 강제로 jsp에서 2번쨰에 널값 넣어서 체크
		//2번째 배열이 널이면 강제로 추가해준 곳
		if(array[1].length==0) {
			MemberVO memberVO = new MemberVO();
			memberVO.setMemName(array[0][0]);
			memberVO.setDepNo(depNo);
			memberVO.setMemReg1(array[0][1]);
			memberVO.setMemReg2(array[0][2]);
			if(member !=null) {
				memberVO.setMemId(memNumber);
				memberVO.setMemPass(passwordEncoder.encode(memNumber));
			}else {
				memberVO.setMemId(memNumber+"01");
				memberVO.setMemPass(passwordEncoder.encode(memNumber+"01"));
			}
			memberList.add(memberVO);
		}else {
			for(int i =0 ; i<array.length;i++) {
				int j = i+1;
				MemberVO memberVO = new MemberVO();
				memberVO.setMemName(array[i][0]);
				memberVO.setDepNo(depNo);
				memberVO.setMemReg1(array[i][1]);
				memberVO.setMemReg2(array[i][2]);
				if(member !=null) {
					int number = Integer.parseInt(memNumber)+j;
					memberVO.setMemId(number+"");
					memberVO.setMemPass(passwordEncoder.encode(number+""));
				}else {
					if(i<9) {
						memberVO.setMemId(memNumber+"0"+j);
						memberVO.setMemPass(passwordEncoder.encode(memNumber+"0"+j));
					}else {
						memberVO.setMemId(memNumber+""+j);
						memberVO.setMemPass(passwordEncoder.encode(memNumber+""+j));
					}
				}
				memberList.add(memberVO);
			}
		}
		int result = service.insertStudent(memberList);
		
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}

	
	//신입생 등록 엑셀로 처리 
	@RequestMapping(value="/excelUpload.do", method=RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object>  excelUpload(@RequestPart("excelFile") MultipartFile excelFile
			,Model model)  {
	    List<MemberVO> memberList = new ArrayList<>();
	    ClassPathResource tmpl = new ClassPathResource("kr/or/ddit/jxlsTemplate/excel/excelStudentList.xml");
	    Map<String, Object> resultMap = new HashMap<>();
	    int result = 0;
	    try(
	    	InputStream is = new BufferedInputStream(tmpl.getInputStream());
	    		){
	    	XLSReader reader = ReaderBuilder.buildFromXML(is);
	    	Map<String, Object> beans = new HashMap<>();
	    	beans.put("memberList", memberList);
	    	try {
	    		InputStream excelStream = excelFile.getInputStream();
	    		reader.read(excelStream, beans);
	    		MemberVO member = new MemberVO();
	    		
	    		//학과 코드
	    		int depNo = memberList.get(0).getDepNo();
	    		
	    		//이미 학과에 등록된 학생이 있는지 확인
	    		member.setDepNo(depNo);
	    		member = service.selectMaxMemId(member);
	    		
	    		//학번 만드는곳
	    		String memNumber=makeMemId(member,depNo);
	    		
	    		//학번, 초기비밀번호(학번과 동일) 부여
	    		for(int i =0 ; i<memberList.size();i++) {
					int j = i+1;
					if(member !=null) {
						int number = Integer.parseInt(memNumber)+j;
						memberList.get(i).setMemId(number+"");
						memberList.get(i).setMemPass(passwordEncoder.encode(number+""));
					}else {
						if(i<9) {
							memberList.get(i).setMemId(memNumber+"0"+j);
							memberList.get(i).setMemPass(passwordEncoder.encode(memNumber+"0"+j));
						}else {
							memberList.get(i).setMemId(memNumber+""+j);
							memberList.get(i).setMemPass(passwordEncoder.encode(memNumber+""+j));
						}
					}
					
				}
	    		result = service.insertStudent(memberList);
	    		
	    		
	    	}catch (InvalidFormatException  e) {
	            LOGGER.error(this.getClass().getName() + " " + e.getMessage());
             }
	    }catch (SAXException | IOException e) {
            LOGGER.error(this.getClass().getName() + " " + e.getMessage());
         }
	    if(result!=0) {
//			model.addAttribute("message", notyInsertSuccessMessage());
			resultMap = Collections.singletonMap("memberList",memberList );
			
	    }
	    return  resultMap;
	}
	
}
