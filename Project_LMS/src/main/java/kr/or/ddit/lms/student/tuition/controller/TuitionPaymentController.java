package kr.or.ddit.lms.student.tuition.controller;

import java.io.File;
import java.net.URLEncoder;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.controller.IssuedNumberController;
import kr.or.ddit.enrolment.controller.EnrolmentPDFController;
import kr.or.ddit.lms.student.tuition.service.TuitionService;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.TuitionVO;

/**
 * @author 전진원
 * @since 2021. 2. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 15.    전진원      	등록금납부확인서 pdf 좌표 잡음
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class TuitionPaymentController extends BaseController {
	
	
	@Autowired
	ServletContext context; 
	
	@Inject
	EnrolmentPDFController enrolmentController;
	
	@Inject
	TuitionService service;
	
	@Inject
	private IssuedNumberController issued;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
	public static final String IMPORT_PAYMENTINFO_URL = "https://api.iamport.kr/payments/";
	public static final String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";
	public static final String IMPORT_PREPARE_URL = "https://api.iamport.kr/payments/prepare";
	
	public static final String KEY = "5144797885838132";
	public static final String SECRET = "0b0gA8Dbi8B08oS0WJoMRJ0FK9Py3pe3BejcnXnF6ZGenzfjjD6an6Q8gFHj5VmGaGTxEgdKfTZDteXM";
	
	String getImpUid = "";
	@RequestMapping("/lms/student/tuition/tuitionpayment.do")
	public String tuitionPayment(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model,
			VirtualAccountTuitionVO virtualAccountTuitionVO) {
		VirtualAccountTuitionVO firstPayCheck = service.selectFirstPay(authMember);
		VirtualAccountTuitionVO vaTuitionVO = service.selectPayImp(authMember);
		int resultCount = service.selectTuitionBillDateCheckCount(authMember);
		if(vaTuitionVO != null) {
			getImpUid = vaTuitionVO.getPayImp();
			System.out.println(getImpUid);
		}
		model.addAttribute("vaTuitionVO", vaTuitionVO);
		model.addAttribute("resultCount", resultCount);
		model.addAttribute("firstPayCheck", firstPayCheck);
		model.addAttribute("pageTitle", "납부 확인서 발급");
		return "lms/tuition/tuitionpayment";
	}
	
	String result = "";
	// 토큰 발급
	@RequestMapping(value="/lms/student/tuition/getTokenTuitionPayment.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getTokenTuitionPayment()  {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(IMPORT_TOKEN_URL);
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("imp_key", KEY);
		m.put("imp_secret", SECRET);
		try {
			post.setEntity(new UrlEncodedFormEntity(convertParameterTuitionPayment(m)));
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
	@RequestMapping(value="/lms/student/tuition/getStatusTuitionPayment.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	// 아임포트 결제정보를 조회해서 결제금액을 뽑아주는 함수
	public Map<String, Object> getStatusTuitionPayment(
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
			if(getImpUid != null) {
				status = resNode.get("status").asText();
				System.out.println(status);
			}
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
	
		
	@RequestMapping(value="/lms/student/tuition/paidUpdate.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> paidUpdate(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember, VirtualAccountTuitionVO virtualAccountTuitionVO
			) throws MessagingException{
		virtualAccountTuitionVO.setMemId(authMember.getMemId());
		if(virtualAccountTuitionVO.getPayAmount() == 0 || virtualAccountTuitionVO.getPayDate() != null) {
			int result = service.piadVirtualAccountUpdate(virtualAccountTuitionVO);
			DecimalFormat formatter = new DecimalFormat("###,###"); 
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom("projectsmartlms@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
		    messageHelper.setTo(authMember.getMemMail()); // 받는사람 이메일
		    messageHelper.setSubject(authMember.getMemName()+"님 등록금 납부 내역입니다."); // 메일 내용
		    messageHelper.setText(	"안녕하세요, "+authMember.getMemName()+"님? \n\n"
		    						+authMember.getMemName()+"님의 등록금은 "+formatter.format(virtualAccountTuitionVO.getPayAmount())
		    						+"원으로 전액 납부 완료하셨습니다. \n\n문의사항이 있다면 학생관리처에 연락해주시기 바랍니다.\n\n"
		    						+"언제나 "+authMember.getMemName()+"님의 행복한 캠퍼스 생활을 간절히 바라며...\n\n"
		    						+"좋은 하루 되세요. 감사합니다."); // 메일제목은 생략이 가능하다

		    mailSender.send(message);
		} 
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
	// Map을 사용해서 Http요청 파라미터를 만들어 주는 함수
	private List<NameValuePair> convertParameterTuitionPayment(Map<String,String> paramMap){
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		Set<Entry<String, String>> entries = paramMap.entrySet();
		for (Entry<String, String> entry : entries) {
			paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return paramList;
	}
	
	@RequestMapping("/lms/student/tuition/tuitionpaymentPDF.do")
	public void tuitionPaymentPDF(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {
		TuitionVO  tuitionVO = service.selectTuitionList(authMember);
		List<TuitionVO> tuitionList = service.selectSfundValue(authMember);

		TuitionVO tuition =service.selectBank(authMember);
		DepartmentVO departmentVO = service.selectDepName(authMember);
		DecimalFormat formatter = new DecimalFormat("###,###"); 
		
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/TuitionPay.PNG", doc);
		
	    
//	    // ttc 파일 사용하기
	    File fontFile = new File("C:/Windows/fonts/gulim.ttc");
	    PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
	
		// 페이지 추가
		PDPage blankPage = new PDPage(PDRectangle.A4);
        doc.addPage(blankPage);
        
        // 현재 페이지 설정
        PDPage page = doc.getPage(0);
        
        // 컨텐츠 스트림 열기
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        
        // 배경 이미지  그리기
        contentStream.drawImage(pdImage, 0, 0, 595, 820);
        //글자 크기
        int fontSize =12;
        
        String issuedFormat = issued.issuedNumberController("납부확인서");
        if(issuedFormat !=null) {
        	enrolmentController.drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
        }
        
        if(departmentVO!=null) {
        	enrolmentController.drawText(departmentVO.getDepName(), fontGulim, fontSize, 260, 665, contentStream);
        }
        enrolmentController.drawText(authMember.getMemId(), fontGulim, fontSize, 260, 590, contentStream);
        enrolmentController.drawText(authMember.getMemName(), fontGulim, fontSize, 490, 665, contentStream);
        enrolmentController.drawText(authMember.getMemTel().replaceFirst("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"), fontGulim, fontSize, 470, 590, contentStream);

        enrolmentController.drawText("0원", fontGulim, fontSize, 260, 540, contentStream);
        if(tuitionVO!=null) {
        	enrolmentController.drawText(formatter.format(tuitionVO.getDepFee())+"원", fontGulim, fontSize, 480, 540, contentStream);
        }else {
        	enrolmentController.drawText("0원", fontGulim, fontSize, 480, 540, contentStream);
        	
        }
        int sfundTotal =0;
        if(tuitionList!=null) {
        	for(TuitionVO tuitionVO2 : tuitionList) {
        		sfundTotal += tuitionVO2.getSfundValue();
        	}
        	enrolmentController.drawText(formatter.format(sfundTotal)+"원", fontGulim, fontSize, 260, 513, contentStream);
        }else {
        	enrolmentController.drawText("0원", fontGulim, fontSize, 260, 513, contentStream);
        }
        if(tuitionVO!=null) {
        	if(tuitionList!=null) {
        		enrolmentController.drawText(formatter.format(tuitionVO.getDepFee()-sfundTotal)+"원", fontGulim, fontSize, 480, 513, contentStream);
        		enrolmentController.drawText(formatter.format(tuitionVO.getDepFee()-sfundTotal)+"원", fontGulim, fontSize, 260, 487, contentStream);
        	}else {
        		enrolmentController.drawText(formatter.format(tuitionVO.getDepFee())+"원", fontGulim, fontSize, 480, 513, contentStream);
        		enrolmentController.drawText(formatter.format(tuitionVO.getDepFee())+"원", fontGulim, fontSize, 260, 487, contentStream);
        	}
        }else {
        	enrolmentController.drawText("0원", fontGulim, fontSize, 480, 513, contentStream);
        	enrolmentController.drawText("0원", fontGulim, fontSize, 260, 487, contentStream);
        	enrolmentController.drawText("0원", fontGulim, fontSize, 480, 513, contentStream);
        	enrolmentController.drawText("0원", fontGulim, fontSize, 260, 487, contentStream);
        	
        }
        enrolmentController.drawText(tuitionVO.getPayStart()+" ~ "+tuitionVO.getPayEnd(), fontGulim, fontSize, 260, 462, contentStream);
        if(tuition!=null) {
        	enrolmentController.drawText(tuition.getPayBank() +" "+ tuition.getPayAcn(), fontGulim, fontSize, 260, 438, contentStream);
        }
        enrolmentController.drawText("학생 사무처", fontGulim, fontSize, 260, 413, contentStream);
		

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        enrolmentController.drawText(year+"", fontGulim, fontSize+2, 210, 192, contentStream);
        enrolmentController.drawText(month+"", fontGulim, fontSize+2, 275, 192, contentStream);
        enrolmentController.drawText(day+"", fontGulim, fontSize+2, 315, 192, contentStream);
        
        
        
        
        enrolmentController.drawText(authMember.getMemName(), fontGulim, fontSize+5, 480, 118, contentStream);
        
        // 컨텐츠 스트림 닫기
        contentStream.close();

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("등록금 납부 확인서", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
		
	}
	
	@RequestMapping("/lms/student/tuition/tuitionpaymentInsteadPDF.do")
	public void PaymentInsteadPDF(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {
		
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
		PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/TuitionPay.PNG", doc);
		
//	    // ttc 파일 사용하기
		File fontFile = new File("C:/Windows/fonts/gulim.ttc");
		PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
		// 페이지 추가
		PDPage blankPage = new PDPage(PDRectangle.A4);
		doc.addPage(blankPage);
		
		// 현재 페이지 설정
		PDPage page = doc.getPage(0);
		
		// 컨텐츠 스트림 열기
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);
		
		// 배경 이미지  그리기
		contentStream.drawImage(pdImage, 0, 0, 595, 820);
		//글자 크기
		int fontSize =12;
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		enrolmentController.drawText(year+"", fontGulim, fontSize+2, 210, 192, contentStream);
		enrolmentController.drawText(month+"", fontGulim, fontSize+2, 275, 192, contentStream);
		enrolmentController.drawText(day+"", fontGulim, fontSize+2, 315, 192, contentStream);
		
		
		
		
		enrolmentController.drawText(authMember.getMemName(), fontGulim, fontSize+5, 480, 118, contentStream);
		
		// 컨텐츠 스트림 닫기
		contentStream.close();
		
		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("등록금 납부 확인서", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
		
		
		
	}
}
