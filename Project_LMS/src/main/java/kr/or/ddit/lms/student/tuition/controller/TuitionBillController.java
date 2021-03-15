package kr.or.ddit.lms.student.tuition.controller;

import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.student.tuition.service.TuitionService;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class TuitionBillController extends BaseController {

	@Inject
	private TuitionService service;

	@Autowired
	private JavaMailSender mailSender;
	
	public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
	public static final String IMPORT_PAYMENTINFO_URL = "https://api.iamport.kr/payments/";
	public static final String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";
	public static final String IMPORT_PREPARE_URL = "https://api.iamport.kr/payments/prepare";
	
	public static final String KEY = "5144797885838132";
	public static final String SECRET = "0b0gA8Dbi8B08oS0WJoMRJ0FK9Py3pe3BejcnXnF6ZGenzfjjD6an6Q8gFHj5VmGaGTxEgdKfTZDteXM";

	
	@RequestMapping("/lms/student/tuition/tuitionbill.do")
	public String tuitionBill(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model,
			VirtualAccountTuitionVO virtualAccountTuitionVO) {
		VirtualAccountTuitionVO vaTuitionVO = service.selectPayImp(authMember);
		VirtualAccountTuitionVO firstPayCheck = service.selectFirstPay(authMember);
		int resultCount = service.selectTuitionBillDateCheckCount(authMember);
		model.addAttribute("vaTuitionVO", vaTuitionVO);
		model.addAttribute("resultCount", resultCount);
		model.addAttribute("firstPayCheck", firstPayCheck);
		model.addAttribute("pageTitle", "등록금 고지서");
		return "lms/tuition/tuitionbill";
	}
	
	String impUid = "";
	@RequestMapping(value = "/lms/student/tuition/tuitionbillUpdate.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> vbankNumUpdate(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			Model model, VirtualAccountTuitionVO virtualAccountTuitionVO,
			@RequestParam("payImp") String payImp
			) throws MessagingException {
		impUid = payImp; 
		System.out.println(payImp);
		virtualAccountTuitionVO.setMemId(authMember.getMemId());
		int result = service.virtualAccounUpdate(virtualAccountTuitionVO);
		Map<String, Object> resultMap = null;
		if (result > 0) {
			resultMap = Collections.singletonMap("result", result);
			
			VirtualAccountTuitionVO emailTuitionVO = service.selectEmail(authMember);
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
		    messageHelper.setTo(emailTuitionVO.getMemMail()); // 받는사람 이메일
		    messageHelper.setSubject(emailTuitionVO.getMemName()+"님 가상계좌 발급 메일입니다."); // 메일 내용
		    messageHelper.setText(	"안녕하세요, "+emailTuitionVO.getMemName()+"님? \n\n"
		    						+emailTuitionVO.getMemName()+"님 가상계좌 발급 번호는 "+emailTuitionVO.getPayBank()+" "
		    						+emailTuitionVO.getPayAcn()+"입니다.\n\n"+"납부 기한은 "
		    						+emailTuitionVO.getPayStart()+" ~ "
		    						+emailTuitionVO.getPayEnd()+" 까지 납부 기한 내에 입금해주시기 바랍니다. \n\n"
		    						+"문의사항이 있다면 학생관리처에 연락해주세요. \n\n"
		    						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다
		    
		    mailSender.send(message);
//			email.setContent(authMember.getMemId()+"님 가상계좌 발급 번호는 "+virtualAccountTuitionVO.getPayAcn()+"입니다."+" 납부 기한은"
//			 +virtualAccountTuitionVO.getPayStart()+" ~ "+virtualAccountTuitionVO.getPayEnd()+" 까지 이오니 납부 기한 내에 입금해주시기 바랍니다.");
//			email.setReceiver(authMember.getMemMail());
//			email.setSubject(authMember.getMemId()+"님 가상계좌 발급 메일입니다.");
//			try {
//				EmailService.mailSend(authMember.getMemMail(), subject, text);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		return resultMap;
	}

	
	@RequestMapping(value = "/lms/student/tuition/tuitionbillPaySatrtDate.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> tuitionbillPaySatrtDate(
			 Model model
			) {
		VirtualAccountTuitionVO getStartDate = service.selectPayStartDate();
		Map<String, Object> resultMap = Collections.singletonMap("getStartDate", getStartDate);
		return resultMap;
	}
	
//	@RequestMapping(value = "/lms/student/tuition/tuitionbillpayExcept.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public Map<String, Object> vbankPayExcept(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
//			Model model, TuitionVO tuitionVO) {
//		tuitionVO.setMemId(authMember.getMemId());
//		TuitionVO vbanktuitionVO = service.selectTuitionList(authMember);
//		Map<String, Object> resultMap = Collections.singletonMap("vbanktuitionVO", vbanktuitionVO);
//		return resultMap;
//	}
	
	

	@RequestMapping(value="/lms/student/tuition/getDepFee.do", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getDepFee(
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model,
			VirtualAccountTuitionVO virtualAccountTuitionVO
			){
		virtualAccountTuitionVO.setMemId(authMember.getMemId());
		VirtualAccountTuitionVO vatuitionVO = service.selectDepFee(authMember);
		Map<String, Object> resultMap = Collections.singletonMap("vatuitionVO", vatuitionVO);
		return resultMap;
	}
	
	
}
