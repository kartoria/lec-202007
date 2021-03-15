package kr.or.ddit.lms.student.tuition.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.student.tuition.service.TuitionService;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.TuitionVO;

/**
 * @author  전진원
 * @since 2021. 2. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 4.     전진원      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class TuitionDivisionController extends BaseController{
	@Inject
	TuitionService service;
	@Autowired
	private JavaMailSender mailSender;

	public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
	public static final String IMPORT_PAYMENTINFO_URL = "https://api.iamport.kr/payments/";
	public static final String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";
	public static final String IMPORT_PREPARE_URL = "https://api.iamport.kr/payments/prepare";
	
	public static final String KEY = "5144797885838132";
	public static final String SECRET = "0b0gA8Dbi8B08oS0WJoMRJ0FK9Py3pe3BejcnXnF6ZGenzfjjD6an6Q8gFHj5VmGaGTxEgdKfTZDteXM";
	
	String getImpUid = "";
	String result = "";
	// 토큰 발급
	@RequestMapping(value="/lms/student/tuition/getTokenTuitionDivision.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getTokenTuitionDivision(){
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(IMPORT_TOKEN_URL);
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("imp_key", KEY);
		m.put("imp_secret", SECRET);
		try {
			post.setEntity(new UrlEncodedFormEntity(convertParameterTuitionDivision(m)));
			HttpResponse res = client.execute(post);
			ObjectMapper mapper = new ObjectMapper();
			String body = EntityUtils.toString(res.getEntity());
			JsonNode rootNode = mapper.readTree(body);
			JsonNode resNode = rootNode.get("response");
			result = resNode.get("access_token").asText();
			System.out.println(result);
			resultMap = Collections.singletonMap("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	// 결제 상태 조회
	@RequestMapping(value="/lms/student/tuition/getStatusTuitionDivision.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	// 아임포트 결제정보를 조회해서 결제금액을 뽑아주는 함수
	public Map<String, Object> getStatusTuitionDivision(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember, VirtualAccountTuitionVO virtualAccountTuitionVO
			) {
		String status = "";
		long paid_at = 0;
		int paid_amount = 0;
		String token = result;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(IMPORT_PAYMENTINFO_URL + getImpUid);
		Map<String, Object> resultMap = new HashMap<>();
		get.setHeader("Authorization", token);
		try {
			HttpResponse res = client.execute(get);
			ObjectMapper mapper = new ObjectMapper();
			String body = EntityUtils.toString(res.getEntity());
			JsonNode rootNode = mapper.readTree(body);
			JsonNode resNode = rootNode.get("response");
			status = resNode.get("status").asText();
			System.out.println(status);
			if(status.contains("paid")) {
				paid_at = resNode.get("paid_at").asInt();
				System.out.println(paid_at);
				Date date = new Date(paid_at*1000L);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
				String formattedDate = sdf.format(date);
				resultMap.put("formattedDate", formattedDate);
				paid_amount = resNode.get("amount").asInt();
				resultMap.put("paid_amount", paid_amount);
			}
			resultMap.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	// Map을 사용해서 Http요청 파라미터를 만들어 주는 함수
	private List<NameValuePair> convertParameterTuitionDivision(Map<String,String> paramMap){
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		Set<Entry<String, String>> entries = paramMap.entrySet();
		for (Entry<String, String> entry : entries) {
			paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return paramList;
	}
	
	// 결제 상태 조회해서 결제 완료라면 업데이트
	@RequestMapping(value="/lms/student/tuition/paidDivisionUpdate.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> paidDivisionUpdate(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember, VirtualAccountTuitionVO virtualAccountTuitionVO
			) throws MessagingException{
		virtualAccountTuitionVO.setMemId(authMember.getMemId());
		VirtualAccountTuitionVO emailTuitionVO = service.selectEmailDivision(authMember);
		if(virtualAccountTuitionVO.getPayAmount() == 0 || virtualAccountTuitionVO.getPayDate() != null) {
			int result = service.paidDivisionUpdate(virtualAccountTuitionVO);
			if(emailTuitionVO.getPayNumber() == 1) {
				DecimalFormat formatter = new DecimalFormat("###,###"); 
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
			    messageHelper.setTo(authMember.getMemMail()); // 받는사람 이메일
			    messageHelper.setSubject(authMember.getMemName()+"님 1차 분할 납부 내역입니다."); // 메일 내용
			    messageHelper.setText(	"안녕하세요, "+authMember.getMemName()+"님? \n\n"
			    						+authMember.getMemName()+"님의 1차 분할 완납 금액은 "+formatter.format(emailTuitionVO.getPayExcpect())
			    						+"원으로 납부 완료하셨습니다. \n\n문의사항이 있다면 학생관리처에 연락해주시기 바랍니다.\n\n"
			    						+"언제나 "+authMember.getMemName()+"님의 행복한 캠퍼스 생활을 간절히 바라며...\n\n"
			    						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다

			    mailSender.send(message);
			}
			if(emailTuitionVO.getPayNumber() == 2 && emailTuitionVO.getPayAcn() != "0") {
				DecimalFormat formatter = new DecimalFormat("###,###"); 
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
			    messageHelper.setTo(authMember.getMemMail()); // 받는사람 이메일
			    messageHelper.setSubject(authMember.getMemName()+"님 2차 분할 납부 내역입니다."); // 메일 내용
			    messageHelper.setText(	"안녕하세요, "+authMember.getMemName()+"님? \n\n"
			    						+authMember.getMemName()+"님의 2차 분할 완납 금액은 "+formatter.format(emailTuitionVO.getPayExcpect())
			    						+"원으로 납부 완료하셨습니다. \n\n문의사항이 있다면 학생관리처에 연락해주시기 바랍니다.\n\n"
			    						+"언제나 "+authMember.getMemName()+"님의 행복한 캠퍼스 생활을 간절히 바라며...\n\n"
			    						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다

			    mailSender.send(message);
			}
			if(emailTuitionVO.getPayNumber() == 3) {
				DecimalFormat formatter = new DecimalFormat("###,###"); 
				List<VirtualAccountTuitionVO> emailAmountList = service.selectEmailDivisionList(authMember);
				int max = emailAmountList.size();
				int amount = 0;
				for(int i=0; i<max; i++) {
					amount += emailAmountList.get(i).getPayExcpect();
				}
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
			    messageHelper.setTo(authMember.getMemMail()); // 받는사람 이메일
			    messageHelper.setSubject(authMember.getMemName()+"님 3차 분할 납부 내역입니다."); // 메일 내용
			    messageHelper.setText(	"안녕하세요, "+authMember.getMemName()+"님? \n\n"
			    						+authMember.getMemName()+"님의 3차 분할 완납 금액은 "+formatter.format(emailTuitionVO.getPayExcpect())
			    						+"원이고, 총 실납입액 "+formatter.format(amount)+"원으로 납부 완료하셨습니다. \n\n문의사항이 있다면 학생관리처에 연락해주시기 바랍니다.\n\n"
			    						+"언제나 "+authMember.getMemName()+"님의 행복한 캠퍼스 생활을 간절히 바라며...\n\n"
			    						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다

			    mailSender.send(message);
			}
		} 
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
	//분할 납부 현황 조회
	@RequestMapping("/lms/student/tuition/tuitiondivision.do")
	public String tuitionDivision(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model,VirtualAccountTuitionVO virtualAccountTuitionVO
			) {
		int countSchedule = service.selectCountSchedule();
		int countPayCheck = service.selectPayCountDivisionCheck(authMember);
		VirtualAccountTuitionVO firstPayCheck = service.selectFirstPay(authMember);
		VirtualAccountTuitionVO firstPayImp = service.selectPayImp(authMember);
		VirtualAccountTuitionVO divisionAmount = service.selectDivisionAmount(authMember);
		List<VirtualAccountTuitionVO> divisionGetDateList = null;
		VirtualAccountTuitionVO vaTuitionVO = null;
		// 등록 기간일 때
		if(countSchedule != 0) {
			vaTuitionVO = service.selectDivisionPayImp(authMember);
			if(vaTuitionVO != null) {
				getImpUid = vaTuitionVO.getPayImp()+"";
				System.out.println(getImpUid);
				divisionGetDateList = service.tuitionDivisionDateCheck(authMember);
			}
		} 
		TuitionVO  tuitionVO= service.selectTuitionList(authMember);
		int sfundCount = service.selectSfundCount(authMember);
		if(sfundCount>0) {
			List<TuitionVO> tuitionList = service.selectSfundValue(authMember);
			if(tuitionList!=null) {
				int sFundtotal=0;
				for(TuitionVO tuitionVO2 :tuitionList) {
					sFundtotal +=tuitionVO2.getSfundValue();
				}
				tuitionVO.setSfundValue(sFundtotal);
			}else {
				tuitionVO.setSfundValue(0);
			}
		}
		
		List<TuitionVO> tuitionList = service.selectTuitionPayList(authMember);
		
		model.addAttribute("tuitionVO",tuitionVO);
		model.addAttribute("tuitionList",tuitionList);
		model.addAttribute("vaTuitionVO", vaTuitionVO);
		model.addAttribute("countSchedule", countSchedule);
		model.addAttribute("divisionGetDateList",divisionGetDateList);
		model.addAttribute("firstPayCheck",firstPayCheck);
		model.addAttribute("firstPayImp",firstPayImp);
		model.addAttribute("countPayCheck",countPayCheck);
		model.addAttribute("divisionAmount",divisionAmount);
		model.addAttribute("pageTitle", "분할 납부 신청");
		return "lms/tuition/tuitiondivision";
	}
	
	//분할 납부 신청
	@RequestMapping("/lms/student/tuition/tuitionUpdate.do")
	public void tuitionUpdate(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model
			) {
		int result = service.tuitionUpdate(authMember);
		if(result>0) {
			model.addAttribute("tuitionMessage","OK");
		}else {
			model.addAttribute("tuitionMessage","FAILE");
		}
	}
	
	// 분할 납부 납부예상금액 가져오기
	@RequestMapping(value="/lms/student/tuition/tuitionDivisionSelectPayExpect.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> tuitionDivisionSelectPayExpect(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember, VirtualAccountTuitionVO virtualAccountTuitionVO 
			){
		virtualAccountTuitionVO.setMemId(authMember.getMemId());
		VirtualAccountTuitionVO result = service.tuitionDivisionSelectPayExpect(authMember);
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
	// 분할 납부 가상계좌 업데이트 
	@RequestMapping(value="/lms/student/tuition/tuitionDivisionUpdate.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> tuitionDivisionUpdate(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember, VirtualAccountTuitionVO virtualAccountTuitionVO 
			) throws MessagingException{
		virtualAccountTuitionVO.setMemId(authMember.getMemId());
		int result = service.tuitionDivisionUpdate(virtualAccountTuitionVO);
		Map<String, Object> resultMap = null;
		if (result > 0) {
			resultMap = Collections.singletonMap("result", result);
			VirtualAccountTuitionVO emailTuitionVO = service.selectEmailDivision(authMember);
			if(emailTuitionVO.getPayNumber() == 1) {
				DecimalFormat formatter = new DecimalFormat("###,###"); 
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(emailTuitionVO.getMemMail()); // 받는사람 이메일
				messageHelper.setSubject(emailTuitionVO.getMemName()+"님 1차 분할 납부 가상계좌 발급 메일입니다."); // 메일 내용
				messageHelper.setText(	"안녕하세요, "+emailTuitionVO.getMemName()+"님? \n\n"
						+emailTuitionVO.getMemName()+"님 1차 분할 납부 가상계좌 발급 번호는 "+emailTuitionVO.getPayBank()+" "
						+emailTuitionVO.getPayAcn()+"입니다.\n\n"+"납부 기한은 "
						+emailTuitionVO.getPayStart()+" ~ "
						+emailTuitionVO.getPayEnd()+" 까지 납부 기한 내에 "+formatter.format(emailTuitionVO.getPayExcpect())+"원을 입금해주시기 바랍니다. \n\n"
						+"문의사항이 있다면 학생관리처에 연락해주세요. \n\n"
						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다
				mailSender.send(message);
			} else if(emailTuitionVO.getPayNumber() == 2) {
				DecimalFormat formatter = new DecimalFormat("###,###"); 
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(emailTuitionVO.getMemMail()); // 받는사람 이메일
				messageHelper.setSubject(emailTuitionVO.getMemName()+"님 2차 분할 납부 가상계좌 발급 메일입니다."); // 메일 내용
				messageHelper.setText(	"안녕하세요, "+emailTuitionVO.getMemName()+"님? \n\n"
						+emailTuitionVO.getMemName()+"님 2차 분할 납부 가상계좌 발급 번호는 "+emailTuitionVO.getPayBank()+" "
						+emailTuitionVO.getPayAcn()+"입니다.\n\n"+"납부 기한은 "
						+emailTuitionVO.getPayStart()+" ~ "
						+emailTuitionVO.getPayEnd()+" 까지 납부 기한 내에 "+formatter.format(emailTuitionVO.getPayExcpect())+"원을 입금해주시기 바랍니다. \n\n"
						+"문의사항이 있다면 학생관리처에 연락해주세요. \n\n"
						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다
				mailSender.send(message);
			} else if(emailTuitionVO.getPayNumber() == 3) {
				DecimalFormat formatter = new DecimalFormat("###,###"); 
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(emailTuitionVO.getMemMail()); // 받는사람 이메일
				messageHelper.setSubject(emailTuitionVO.getMemName()+"님 3차 분할 납부 가상계좌 발급 메일입니다."); // 메일 내용
				messageHelper.setText(	"안녕하세요, "+emailTuitionVO.getMemName()+"님? \n\n"
						+emailTuitionVO.getMemName()+"님 3차 분할 납부 가상계좌 발급 번호는 "+emailTuitionVO.getPayBank()+" "
						+emailTuitionVO.getPayAcn()+"입니다.\n\n"+"납부 기한은 "
						+emailTuitionVO.getPayStart()+" ~ "
						+emailTuitionVO.getPayEnd()+" 까지 납부 기한 내에  "+formatter.format(emailTuitionVO.getPayExcpect())+"원을 입금해주시기 바랍니다. \n\n"
						+"문의사항이 있다면 학생관리처에 연락해주세요. \n\n"
						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다
				mailSender.send(message);
			}
		}
		return resultMap;
	}
	
}
