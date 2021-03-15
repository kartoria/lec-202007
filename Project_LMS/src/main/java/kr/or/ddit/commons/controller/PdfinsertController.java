package kr.or.ddit.commons.controller;

import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.service.PdfinsertService;
import kr.or.ddit.commons.vo.IssuedVO;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class PdfinsertController {

	@Inject
	PdfinsertService service;
	
	//증명서 발급 기록 남기기
	@RequestMapping(value="/pdf/pdfInsert.do" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> enrolmentViewPaging(
			@RequestParam("pdfName") String pdfName
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			){
		
			IssuedVO issuedVO = new IssuedVO();
			issuedVO.setIssName(pdfName);
			issuedVO.setMemId(authMember.getMemId());
			
			int result = service.insertPdfIssued(issuedVO);
			
			
			Map<String, Object> resultMap = Collections.singletonMap("result", result);
			return resultMap;
		}
}
