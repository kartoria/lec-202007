package kr.or.ddit.commons.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import kr.or.ddit.commons.service.IssuedNumberService;
import kr.or.ddit.commons.vo.IssuedVO;

@Controller
public class IssuedNumberController {

	@Inject
	IssuedNumberService service;
	
	//문서 코드 받아오기 
	public String issuedNumberController(String issName) {
		IssuedVO issuedVO = new IssuedVO();
		issuedVO.setIssName(issName);
		IssuedVO issuedVO2 =  service.selectIssuedNumber(issuedVO);
		int max =service.selectIssuedMaxNumber();
		
		String result = issuedVO2.getCode() +"-"+max;
		
		return result;
		
	}
	
	
}
