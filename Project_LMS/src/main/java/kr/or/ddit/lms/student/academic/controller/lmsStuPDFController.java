package kr.or.ddit.lms.student.academic.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.controller.IssuedNumberController;
import kr.or.ddit.commons.service.IssuedNumberService;
import kr.or.ddit.lms.student.academic.service.AcademicService;
import kr.or.ddit.lms.student.tuition.service.TuitionService;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.TuitionVO;


/**
 * @author 김성준
 * @since 2021. 1. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 25.    김성준            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class lmsStuPDFController extends BaseController {

	@Autowired
	ServletContext context; 
	
	@Inject
	private AcademicService service;
	
	@Inject
	TuitionService tutitionService;

	@Inject
	private IssuedNumberController issued;
	
	private static final Logger logger = LoggerFactory.getLogger(lmsStuPDFController.class);
	
	// 등록금 고지서
	@RequestMapping(value = "/lms/student/tuition/tuitionbillPdf.do", method = RequestMethod.GET)
	public void tuitionbillPdf(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {
		final int pageCount = 2;
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/tuitionbill.JPG", doc);
		
	    // 폰트 생성
	    // ttf 파일 사용하기
//					    InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
//					    PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
	    
	    // ttc 파일 사용하기
	    File fontFile = new File("C:/Windows/fonts/gulim.ttc");
	    PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	    
	    // 두 개의 페이지를 만든다.
		for(int i = 0; i < pageCount-1; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
	        doc.addPage(blankPage);
	        
	        // 현재 페이지 설정
	        PDPage page = doc.getPage(i);
	        
	        // 컨텐츠 스트림 열기
	        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	        
            // 배경 이미지  그리기
	        contentStream.drawImage(pdImage, 0, 0, 595, 842);

	        // 데이터 받아오기
//	        MemberVO memberVO = service.selectLeaveAcademic(authMember);
	        VirtualAccountTuitionVO virtualAccountTuitionVO = service.selectTuidionAcademic(authMember);
	        List<VirtualAccountTuitionVO> vatuitionList = service.selectScholarship(authMember);
	        int listSize = vatuitionList.size();
	        int amount = 0;
	        String name = "";
	        DecimalFormat formatter = new DecimalFormat("###,###"); 
	        for(int j=0; j<listSize; j++) {
	        	amount += vatuitionList.get(j).getSfundValue();
//	        	if(j==listSize-1) {
//	        		name += vatuitionList.get(j).getSchName();
//	        	}else {
	        		name += vatuitionList.get(j).getSchName();
        			name += ", ";
//        			System.out.println(name);
//	        	}
        			String str = name;
        			str = str.substring(0, str.length()-2);
	        }
	        
	        
	        
	        // 글씨 쓰기
	        int fontsize = 12;
	        int fontsize1 = 9;
	        int resultTuitionSum = 0;
	        if(virtualAccountTuitionVO != null) {
	        	resultTuitionSum = virtualAccountTuitionVO.getDepFee() - amount;
	        }
	        String smst1 ="";
	        String smst2 = "";
	        if(virtualAccountTuitionVO != null) {
	        	smst1 = virtualAccountTuitionVO.getSmst().substring(0, 4);
	        	smst2 = virtualAccountTuitionVO.getSmst().substring(5, 6);
	        }
	        String issuedFormat =issued.issuedNumberController("등록금고지서");
           if(issuedFormat !=null) {
              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
           }
	        if(virtualAccountTuitionVO != null) {
		        drawText(virtualAccountTuitionVO.getMemId(), fontGulim, fontsize, 215, 689, contentStream);
		        drawText(virtualAccountTuitionVO.getMemName(), fontGulim, fontsize, 430, 689, contentStream);
		        drawText(virtualAccountTuitionVO.getDepName(), fontGulim, fontsize, 200, 661, contentStream);
		        drawText(smst1+"년도 "+smst2+"학기", fontGulim, fontsize, 410, 661, contentStream);
//		        drawText(virtualAccountTuitionVO.getMemGrd()+" 학기", fontGulim, fontsize, 200, 627, contentStream);
	        }
//	        drawText(virtualAccountTuitionVO.getPayNumber()+" 회차", fontGulim, fontsize, 407, 627, contentStream);
//	        drawText("-", fontGulim, fontsize, 420, 627, contentStream);
//	        drawText("-", fontGulim, fontsize, 420, 627, contentStream);

	        if(virtualAccountTuitionVO != null) {
	        	drawText(virtualAccountTuitionVO.getPayStart(), fontGulim, fontsize, 205, 633, contentStream);
	        }
	        
	        if(virtualAccountTuitionVO != null) {
	        	drawText(virtualAccountTuitionVO.getPayEnd(), fontGulim, fontsize, 420, 633, contentStream);
	        }
	        if(virtualAccountTuitionVO != null) {
		        if(virtualAccountTuitionVO.getPayAcn() != null  && virtualAccountTuitionVO.getPayBank() != null) {
		        	drawText(virtualAccountTuitionVO.getPayBank()+" "+virtualAccountTuitionVO.getPayAcn(), fontGulim, fontsize, 200, 605, contentStream);
		        }
	        }
	        
	        if(virtualAccountTuitionVO != null) {
	        	drawText(formatter.format(virtualAccountTuitionVO.getDepFee())+"원", fontGulim, fontsize, 200, 550, contentStream);
	        }

//	        drawText("-", fontGulim, fontsize, 247, 510, contentStream);

	        if(amount != 0) {
	        	drawText(formatter.format(amount)+"원", fontGulim, fontsize, 420, 550, contentStream);
	        } else {
	        	drawText("0원", fontGulim, fontsize, 440, 550, contentStream);
	        }
	        
	        if(virtualAccountTuitionVO!=null) {
	        	if(vatuitionList.size() == 0) {
//	        	drawText("0원", fontGulim, fontsize1, 405, 510, contentStream);
	        		drawText(formatter.format(virtualAccountTuitionVO.getDepFee())+"원", fontGulim, fontsize, 200, 521, contentStream);
	        	} else {
	        		if(resultTuitionSum>0) {
	        			drawText(formatter.format(resultTuitionSum)+"원",fontGulim, fontsize, 200, 521, contentStream);
	        		}else {
	        			drawText("0 원",fontGulim, fontsize, 200, 521, contentStream);
	        			
	        		}
	        	}
	        }

//	        drawText("장학내역", fontGulim, fontsize, 200, 455, contentStream);
	        if(name != "") {
//	        	drawText(name, fontGulim, fontsize, 200, 455, contentStream);
	        }
	        
	        int yIndex = 476;
	        int yIndex2 = 476;
	        int indexNumber = 1;
	        for(int k=0; k<listSize; k++) {
	        	if(k < 4) {
	        		drawText(indexNumber+". "+vatuitionList.get(k).getSchName(), fontGulim, fontsize, 200, yIndex-=20, contentStream);
	        	}
	        	if(k >= 4 && k < 7) {
	        		drawText(indexNumber+". "+vatuitionList.get(k).getSchName(), fontGulim, fontsize, 360, yIndex2-=20, contentStream);
	        	}
	        	indexNumber++;
	        }
	        if(listSize>7) {
        		drawText("이하 생략", fontGulim, fontsize, 360, 396, contentStream);

	        }
	        

	        
	        // 컨텐츠 스트림 닫기
	        contentStream.close();
		}

			// 파일 다운로드 설정
			response.setContentType("application/pdf");
			String fileName = URLEncoder.encode("등록금 고지서", "UTF-8");
			response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
			
			// PDF 파일 출력
			doc.save(response.getOutputStream());
			doc.close();
			
		}
		
	// 분할납부 확인서
		@RequestMapping(value = "/lms/student/tuition/tuitionDivisionPDF.do", method = RequestMethod.GET)
		public void tuitionDivisionPdf(HttpServletResponse response
				,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
				) throws Exception {
			final int pageCount = 2;
			final String webroot = this.context.getRealPath("/");
			
			// 문서 만들기
			final PDDocument doc = new PDDocument();
			
			// 배경이미지 로드
			PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/tuitionDivison.jpg", doc);
			
			// 폰트 생성
			// ttf 파일 사용하기
//						    InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
//						    PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
			
			// ttc 파일 사용하기
			File fontFile = new File("C:/Windows/fonts/gulim.ttc");
			PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
			
			
			// 두 개의 페이지를 만든다.
			for(int i = 0; i < pageCount-1; i++) {
				// 페이지 추가
				PDPage blankPage = new PDPage(PDRectangle.A4);
				doc.addPage(blankPage);
				
				// 현재 페이지 설정
				PDPage page = doc.getPage(i);
				
				// 컨텐츠 스트림 열기
				PDPageContentStream contentStream = new PDPageContentStream(doc, page);
				
				// 배경 이미지  그리기
				contentStream.drawImage(pdImage, 0, 0, 595, 842);
				
				// 데이터 받아오기
//		        MemberVO memberVO = service.selectLeaveAcademic(authMember);
				VirtualAccountTuitionVO virtualAccountTuitionVO = service.selectTuidionAcademic(authMember);
				List<VirtualAccountTuitionVO> vatuitionList = service.selectScholarship(authMember);
				List<TuitionVO> tuitionList = tutitionService.selectTuitionPayList(authMember);
				int listSize = tuitionList.size();
				int amount = 0;
				String name = "";
				DecimalFormat formatter = new DecimalFormat("###,###"); 
				
				for(int j=0; j<listSize; j++) {
					amount += tuitionList.get(j).getPayAmount();
				}
				
				
				// 글씨 쓰기
				int fontsize = 12;
				int fontsize1 = 9;
				int resultTuitionSum = 0;
				if(virtualAccountTuitionVO != null) {
					resultTuitionSum = virtualAccountTuitionVO.getDepFee() - amount;
				}
				String issuedFormat =issued.issuedNumberController("분할납부확인서");
		           if(issuedFormat !=null) {
		              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
		           }
				if(virtualAccountTuitionVO != null) {
					drawText(virtualAccountTuitionVO.getMemId(), fontGulim, fontsize, 215, 666, contentStream);
					drawText(virtualAccountTuitionVO.getMemName(), fontGulim, fontsize, 430, 666, contentStream);
					drawText(virtualAccountTuitionVO.getDepName(), fontGulim, fontsize,  205, 638, contentStream);
					drawText(virtualAccountTuitionVO.getMemGrd(), fontGulim, fontsize, 415, 638, contentStream);
				}
				drawText(formatter.format(amount)+"원", fontGulim, fontsize, 190, 583, contentStream);
				int yIndex = 570;
				int indexNumber = 1;
				
				for(int k=0; k<listSize; k++) {
					drawText(indexNumber+"차 분할 납부 : "+formatter.format(tuitionList.get(k).getPayAmount())+"원", fontGulim, fontsize, 190, yIndex-=20, contentStream);
					indexNumber++;
				}
				drawText("총 납부 금액 : "+formatter.format(amount)+"원", fontGulim, fontsize, 190, 489, contentStream);
				
				// 컨텐츠 스트림 닫기
				contentStream.close();
			}	
			
			// 파일 다운로드 설정
			response.setContentType("application/pdf");
			String fileName = URLEncoder.encode("분할 납부 확인서", "UTF-8");
			response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
			
			// PDF 파일 출력
			doc.save(response.getOutputStream());
			doc.close();
			
		}
	
	// 분할납부 고지서
	@RequestMapping(value = "/lms/student/tuition/tuitionDivisionPayPDF.do", method = RequestMethod.GET)
	public void tuitionDivision(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			, @RequestParam(value="payNumber", required=true) int payNumber
			) throws Exception {
		final int pageCount = 2;
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
		PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/TuitionNumber.jpg", doc);
		
		
		// ttc 파일 사용하기
		File fontFile = new File("C:/Windows/fonts/gulim.ttc");
		PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
		
		// 두 개의 페이지를 만든다.
		for(int i = 0; i < pageCount-1; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
			doc.addPage(blankPage);
			
			// 현재 페이지 설정
			PDPage page = doc.getPage(i);
			
			// 컨텐츠 스트림 열기
			PDPageContentStream contentStream = new PDPageContentStream(doc, page);
			
			// 배경 이미지  그리기
			contentStream.drawImage(pdImage, 0, 0, 595, 842);
			
			// 데이터 받아오기
			VirtualAccountTuitionVO virtualAccountTuitionVO = new VirtualAccountTuitionVO();
			virtualAccountTuitionVO.setMemId(authMember.getMemId());
			virtualAccountTuitionVO.setPayNumber(payNumber);
			VirtualAccountTuitionVO virtualAccountTuitionPayVO = tutitionService.selectDivisionPDF(virtualAccountTuitionVO);
			DecimalFormat formatter = new DecimalFormat("###,###"); 
			
			String smst = virtualAccountTuitionPayVO.getSmst();
			String year = smst.substring(0, 4);
			String sm = smst.substring(5, 6);
			
			// 글씨 쓰기
			int fontsize = 12;
			int fontsize1 = 9;
			
			String issuedFormat =issued.issuedNumberController("분할납부고지서");
           if(issuedFormat !=null) {
              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
           }
			drawText(authMember.getMemId(), fontGulim, fontsize, 215, 612, contentStream);
			drawText(authMember.getMemName(), fontGulim, fontsize, 430, 612, contentStream);
			
			if(virtualAccountTuitionPayVO != null) {
				drawText(virtualAccountTuitionPayVO.getDepName(), fontGulim, fontsize,  205, 586, contentStream);
				drawText(year+"년도 "+sm+"학기", fontGulim, fontsize, 410, 586, contentStream);
				drawText(virtualAccountTuitionPayVO.getPayStart(), fontGulim, fontsize,  205, 562, contentStream);
				drawText(virtualAccountTuitionPayVO.getPayEnd(), fontGulim, fontsize, 415, 562, contentStream);
				drawText(virtualAccountTuitionPayVO.getPayBank()+" "+virtualAccountTuitionPayVO.getPayAcn(), fontGulim, fontsize, 205, 537, contentStream);
				drawText(formatter.format(virtualAccountTuitionPayVO.getPayExcpect())+"원", fontGulim, fontsize, 205, 487, contentStream);
				drawText(formatter.format(virtualAccountTuitionPayVO.getPayAmount())+"원", fontGulim, fontsize, 205, 462, contentStream);
			}
			
			
			// 컨텐츠 스트림 닫기
			contentStream.close();
		}
		
		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("분할 납부 고지서", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
		
	}
	
	
	// 졸업 증명서
	@RequestMapping(value = "/lms/student/academic/graduatedPdf.do", method = RequestMethod.GET)
	public void graduatedPdf(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {
		final int pageCount = 2;
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/graduated.jpg", doc);
		
	    // 폰트 생성
	    // ttf 파일 사용하기
//				    InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
//				    PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
	    
	    // ttc 파일 사용하기
	    File fontFile = new File("C:/Windows/fonts/gulim.ttc");
	    PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	    
	    // 두 개의 페이지를 만든다.
		for(int i = 0; i < pageCount-1; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
	        doc.addPage(blankPage);
	        
	        // 현재 페이지 설정
	        PDPage page = doc.getPage(i);
	        
	        // 컨텐츠 스트림 열기
	        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	        
            // 배경 이미지  그리기
	        contentStream.drawImage(pdImage, 0, 0, 595, 842);

	        // 데이터 받기
	        MemberVO memberVO = service.selectLeaveAcademic(authMember);

	        // 현재시간 받기
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date time = new Date();
	        String dateformat = format.format(time); 
	        String[] date = dateformat.split("-");
	        String year = date[0];
	        String month = date[1];
	        String day = date[2];
	        
	        // 글씨 쓰기
	        int fontsize = 18;
	        
	        String issuedFormat =issued.issuedNumberController("졸업증명서");
           if(issuedFormat !=null) {
              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
           }
	        if(memberVO != null) {
	        	
	        drawText(memberVO.getMemName(), fontGulim, fontsize, 360, 693, contentStream);

	        drawText(memberVO.getMemId(), fontGulim, fontsize, 350, 655, contentStream);

	        drawText(memberVO.getMemReg1()+"-"+memberVO.getMemReg2(), fontGulim, fontsize, 320, 617, contentStream);

	        drawText(memberVO.getDepName(), fontGulim, fontsize, 340, 580, contentStream);
	        }
	        
	        drawText(year, fontGulim, 18, 260, 240, contentStream);
	        drawText(month, fontGulim, 18, 365, 240, contentStream);
	        drawText(day, fontGulim, 18, 437, 240, contentStream);
	        
	        // 컨텐츠 스트림 닫기
	        contentStream.close();
		}

			// 파일 다운로드 설정
			response.setContentType("application/pdf");
			String fileName = URLEncoder.encode("졸업 증명서", "UTF-8");
			response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
			
			// PDF 파일 출력
			doc.save(response.getOutputStream());
			doc.close();
			
		}
		
	
	// 전과
	@RequestMapping(value = "/lms/student/academic/transferPdf.do", method = RequestMethod.GET)
	public void transferPdf(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {

		final int pageCount = 2;
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/transfer.jpg", doc);
		
	    // 폰트 생성
	    // ttf 파일 사용하기
//			    InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
//			    PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
	    
	    // ttc 파일 사용하기
	    File fontFile = new File("C:/Windows/fonts/gulim.ttc");
	    PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	    
	    // 두 개의 페이지를 만든다.
		for(int i = 0; i < pageCount-1; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
	        doc.addPage(blankPage);
	        
	        // 현재 페이지 설정
	        PDPage page = doc.getPage(i);
	        
	        // 컨텐츠 스트림 열기
	        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	        
            // 배경 이미지  그리기
	        contentStream.drawImage(pdImage, 0, 0, 595, 842);

	        // 데이터 받기
	        MemberVO memberVO = service.selectLeaveAcademic(authMember);

	        // 현재시간 받기
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date time = new Date();
	        String dateformat = format.format(time); 
	        String[] date = dateformat.split("-");
	        String year = date[0];
	        String month = date[1];
	        String day = date[2];
	        
	        String issuedFormat =issued.issuedNumberController("전과신청서");
	           if(issuedFormat !=null) {
	              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
	           }
	        
	        // 글씨 쓰기
	        if(memberVO != null) {
	        	
	        drawText(memberVO.getMemId(), fontGulim, 12, 160, 585, contentStream);

	        drawText(memberVO.getMemName(), fontGulim, 12, 370, 585, contentStream);

	        drawText(memberVO.getDepName(), fontGulim, 12, 160, 532, contentStream);

	        drawText(memberVO.getMemTel().replaceFirst("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"), fontGulim, 12, 370, 532, contentStream);

	        drawText(memberVO.getMemGrd()+" 학기", fontGulim, 12, 160, 480, contentStream);
	        
	        drawText(memberVO.getMemReg1()+"-"+memberVO.getMemReg2(), fontGulim, 12, 370, 480, contentStream);

	        }
//	        drawText("경영학과", fontGulim, 12, 160, 428, contentStream);
//
//	        drawText("경영학을 공부해보고 싶어서", fontGulim, 12, 160, 375, contentStream);

	        drawText(year, fontGulim, 12, 310, 265, contentStream);
	        drawText(month, fontGulim, 12, 380, 265, contentStream);
	        drawText(day, fontGulim, 12, 435, 265, contentStream);
	        
	        // 컨텐츠 스트림 닫기
	        contentStream.close();
		}

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("전과 신청서", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
		
	}
	
	// 복학신청서
	@RequestMapping(value = "/lms/student/academic/returningPdf.do", method = RequestMethod.GET)
	public void returingPdf(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {

		final int pageCount = 2;
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/returning.jpg", doc);
		
	    // 폰트 생성
	    // ttf 파일 사용하기
//		    InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
//		    PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
	    
	    // ttc 파일 사용하기
	    File fontFile = new File("C:/Windows/fonts/gulim.ttc");
	    PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	    
	    // 두 개의 페이지를 만든다.
		for(int i = 0; i < pageCount-1; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
	        doc.addPage(blankPage);
	        
	        // 현재 페이지 설정
	        PDPage page = doc.getPage(i);
	        
	        // 컨텐츠 스트림 열기
	        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	        
            // 배경 이미지  그리기
	        contentStream.drawImage(pdImage, 0, 0, 595, 842);

	        // 데이터 받기
	        MemberVO memberVO = service.selectLeaveAcademic(authMember);

	        // 현재시간 받기
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date time = new Date();
	        String dateformat = format.format(time); 
	        String[] date = dateformat.split("-");
	        String year = date[0];
	        String month = date[1];
	        String day = date[2];
	        
	        String issuedFormat =issued.issuedNumberController("복학신청서");
	           if(issuedFormat !=null) {
	              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
	           }
	        
	        // 글씨 쓰기
	        if(memberVO != null) {
	        	
	        drawText(memberVO.getMemId(), fontGulim, 12, 160, 590, contentStream);

	        drawText(memberVO.getMemName(), fontGulim, 12, 370, 590, contentStream);

	        drawText(memberVO.getDepName(), fontGulim, 12, 160, 548, contentStream);

	        drawText(memberVO.getMemTel().replaceFirst("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"), fontGulim, 12, 370, 548, contentStream);

	        drawText(memberVO.getMemGrd()+" 학기", fontGulim, 12, 160, 505, contentStream);
	        
	        drawText(memberVO.getMemReg1()+"-"+memberVO.getMemReg2(), fontGulim, 12, 370, 505, contentStream);
	        }

	        drawText(year, fontGulim, 12, 310, 	401, contentStream);
	        drawText(month, fontGulim, 12, 380, 401, contentStream);
	        drawText(day, fontGulim, 12, 435, 401, contentStream);
	        
	        // 컨텐츠 스트림 닫기
	        contentStream.close();
		}

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("복학 신청서", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
		
	}
	
	// 자퇴신청서
	@RequestMapping(value = "/lms/student/academic/dropOutPdf.do", method = RequestMethod.GET)
	public void dropOutPdf(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {

		final int pageCount = 2;
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/dropout.jpg", doc);
		
	    // 폰트 생성
	    // ttf 파일 사용하기
//	    InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
//	    PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
	    
	    // ttc 파일 사용하기
	    File fontFile = new File("C:/Windows/fonts/gulim.ttc");
	    PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	    
	    // 두 개의 페이지를 만든다.
		for(int i = 0; i < pageCount-1; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
	        doc.addPage(blankPage);
	        
	        // 현재 페이지 설정
	        PDPage page = doc.getPage(i);
	        
	        // 컨텐츠 스트림 열기
	        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	        
            // 배경 이미지  그리기
	        contentStream.drawImage(pdImage, 0, 0, 595, 842);
	        
	        // 데이터 받기
	        MemberVO memberVO = service.selectLeaveAcademic(authMember);

	        // 현재시간 받기
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date time = new Date();
	        String dateformat = format.format(time); 
	        String[] date = dateformat.split("-");
	        String year = date[0];
	        String month = date[1];
	        String day = date[2];
	        
	        // 글씨 쓰기
	        
	        String issuedFormat =issued.issuedNumberController("자퇴신청서");
	           if(issuedFormat !=null) {
	              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
	           }
	        
	        if(memberVO != null) {
	        	
	        drawText(memberVO.getMemId(), fontGulim, 12, 160, 590, contentStream);

	        drawText(memberVO.getMemName(), fontGulim, 12, 370, 590, contentStream);

	        drawText(memberVO.getDepName(), fontGulim, 12, 160, 548, contentStream);

	        drawText(memberVO.getMemTel().replaceFirst("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"), fontGulim, 12, 370, 548, contentStream);

	        drawText(memberVO.getMemGrd()+" 학기", fontGulim, 12, 160, 505, contentStream);
	        
	        drawText(memberVO.getMemReg1()+"-"+memberVO.getMemReg2(), fontGulim, 12, 370, 505, contentStream);

	        }
	        
	        drawText(year, fontGulim, 12, 310, 308, contentStream);
	        drawText(month, fontGulim, 12, 380, 308, contentStream);
	        drawText(day, fontGulim, 12, 435, 308, contentStream);
	        
	        // 컨텐츠 스트림 닫기
	        contentStream.close();
		}

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("자퇴 신청서", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
		
	}
	
	// 휴학신청서
	@RequestMapping(value = "/lms/student/academic/leavePdf.do", method = RequestMethod.GET)
	public void leavePdf(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {

		final int pageCount = 2;
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/leave.jpg", doc);
		
	    // 폰트 생성
	    // ttf 파일 사용하기
	    //InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
	    //PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
	    
	    // ttc 파일 사용하기
	    File fontFile = new File("C:/Windows/fonts/gulim.ttc");
	    PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	    
	    // 두 개의 페이지를 만든다.
		for(int i = 0; i < pageCount-1; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
	        doc.addPage(blankPage);
	        
	        // 현재 페이지 설정
	        PDPage page = doc.getPage(i);
	        
	        // 컨텐츠 스트림 열기
	        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	        
            // 배경 이미지  그리기
	        contentStream.drawImage(pdImage, 0, 0, 595, 842);
	        
	        // 데이터 받기
	        MemberVO memberVO = service.selectLeaveAcademic(authMember);
	        
	        // 현재시간 받기
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date time = new Date();
	        String dateformat = format.format(time); 
	        String[] date = dateformat.split("-");
	        String year = date[0];
	        String month = date[1];
	        String day = date[2];
	        
	        String issuedFormat =issued.issuedNumberController("휴학신청서");
	           if(issuedFormat !=null) {
	              drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
	           }
	        
	        // 글씨 쓰기
	        
	        if(memberVO != null) {
	        
	        drawText(memberVO.getMemId(), fontGulim, 12, 160, 590, contentStream);

	        drawText(memberVO.getMemName(), fontGulim, 12, 370, 590, contentStream);

	        drawText(memberVO.getDepName(), fontGulim, 12, 160, 548, contentStream);

	        drawText(memberVO.getMemTel().replaceFirst("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"), fontGulim, 12, 370, 548, contentStream);

	        drawText(memberVO.getMemGrd()+" 학기", fontGulim, 12, 160, 505, contentStream);
	        
	        drawText(memberVO.getMemReg1()+"-"+memberVO.getMemReg2(), fontGulim, 12, 370, 505, contentStream);
	
	        }
	        
	        drawText(year, fontGulim, 12, 310, 309, contentStream);
	        drawText(month, fontGulim, 12, 380, 309, contentStream);
	        drawText(day, fontGulim, 12, 435, 309, contentStream);
	       
	        
	        // 컨텐츠 스트림 닫기
	        contentStream.close();
		}

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("휴학 신청서", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
		
	}
	
	/**
	 * 글씨를 쓴다.
	 * @param text
	 * @param font
	 * @param fontSize
	 * @param left
	 * @param bottom
	 * @param contentStream
	 * @throws Exception
	 */
	private void drawText(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
        contentStream.beginText(); 
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(left, bottom);
        contentStream.showText(text);
        contentStream.endText();
	}
	private void drawText1(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
		contentStream.beginText(); 
		contentStream.setFont(font, fontSize);
		contentStream.newLineAtOffset(left, bottom);
		contentStream.showText(text);
		contentStream.newLine();
		contentStream.endText();
	}
	
	/**
	 * 라인을 그린다.
	 * @param contentStream
	 * @param xStart
	 * @param yStart
	 * @param xEnd
	 * @param yEnd
	 * @throws IOException
	 */
	private void drawLine(PDPageContentStream contentStream, float xStart, float yStart, float xEnd, float yEnd) throws IOException {
		contentStream.moveTo(xStart,yStart);
		contentStream.lineTo(xEnd,yEnd);
		contentStream.stroke();
	}
	
	/**
	 * 테이블을 그린다.
	 * @param page
	 * @param contentStream
	 * @param y
	 * @param margin
	 * @param content
	 * @throws Exception
	 */
	public void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin, String[][] content,  PDType0Font fontGulim ) throws Exception {
        final int rows = content.length;
        final int cols = content[0].length;
        
        final float rowHeight = 25f;
        final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        final float tableHeight = rowHeight * rows;
        
        final float colWidth = tableWidth / (float)cols;
        final float cellMargin = 3f;

        // 행을 그린다.
        float nexty = y ;
        for(int i = 0; i <= rows; i++) {
            drawLine(contentStream, margin, nexty, margin + tableWidth, nexty);
            nexty -= rowHeight;
        }

        // 열을 그린다.
        float nextx = margin;
        for(int i = 0; i <= cols; i++) {
            drawLine(contentStream, nextx, y, nextx, y - tableHeight);
            nextx += colWidth;
        }

        float textx = margin + cellMargin;
        float texty = y - 15;
        for(int i = 0; i < content.length; i++) {
            for(int j = 0 ; j < content[i].length; j++) {
                String text = content[i][j];
//                drawText(text, PDType1Font.HELVETICA_BOLD, 12, textx, texty, contentStream);
                drawText(text, fontGulim, 12, textx, texty, contentStream);
                textx += colWidth;
            }
            texty -= rowHeight;
            textx = margin + cellMargin;
        }
    }
}
