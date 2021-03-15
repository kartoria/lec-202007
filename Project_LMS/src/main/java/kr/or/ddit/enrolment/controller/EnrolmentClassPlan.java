package kr.or.ddit.enrolment.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.controller.IssuedNumberController;
import kr.or.ddit.enrolment.service.EnrolmentService;
import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.SubjectVO;



/**
 * @author PC-17
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-17      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class EnrolmentClassPlan {
	
	@Autowired
	ServletContext context; 
	
	@Inject
	private EnrolmentService service;
	
	private static final Logger logger = LoggerFactory.getLogger(EnrolmentPDFController.class);

	@Inject
	private IssuedNumberController issued;
	
	
	@RequestMapping("/plan.do")
	public void enrolmentClassPlan(
			HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam("lecCode") String lecCode
			)throws Exception{
		EnrolmentVO lecture = new EnrolmentVO();
		lecture.setLecCode(lecCode);
		
		
	   //강의계획 DB에서 받아오기
        LectureVO lectureList1 = service.selectLecturePlan1(lecture);
	 	List<LectureTimeVO> lectureList2 = service.selectLecturePlan2(lecture);
        SubjectVO lectureList3 = service.selectLecturePlan3(lecture);
        MemberVO lectureList4 = service.selectLecturePlan4(lecture);
		
		
        drawPDF(lectureList1,lectureList2,lectureList3,lectureList4,response);
		
	}
	
	public void drawPDF(LectureVO lectureList1, List<LectureTimeVO> lectureList2, SubjectVO lectureList3 ,MemberVO lectureList4 ,HttpServletResponse response ) throws Exception {
			final int pageCount = 2;
			final String webroot = this.context.getRealPath("/");
			
			// 문서 만들기
			final PDDocument doc = new PDDocument();
			
		    
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
           
	        //글자 크기
	        int fontSize =10;
	        
	        String lectureTime = "";
	        	
        	// 배경이미지 로드
    	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/planBack.PNG", doc);
        	
    	    // 배경 이미지  그리기
	        contentStream.drawImage(pdImage, 0, 0, 595, 820);
	        
	        
	        String issuedFormat =issued.issuedNumberController("강의계획서");
	        if(issuedFormat !=null) {
	        	drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
	        }
	        

	        
	        if(lectureList2.size()>0) {
		        for(int idx =0; idx<lectureList2.size();idx++) {
		        	lectureTime += lectureList2.get(idx).getLtimeDay();
		        	lectureTime += lectureList2.get(idx).getLtimeStart();
		        	lectureTime += "~";
		        	lectureTime += lectureList2.get(idx).getLtimeDay();
		        	lectureTime += lectureList2.get(idx).getLtimeEnd();
		        	if(idx!=lectureList2.size()-1) {
		        		lectureTime += ", ";
		        	}
		        }
		        
		        if(lectureTime!=null) {
		        	drawText(lectureTime, fontGulim, fontSize, 250, 580, contentStream);
		        }
		        if(lectureList2.get(0).getRoomCode()!=null) {
		        	drawText(lectureList2.get(0).getRoomCode(), fontGulim, fontSize, 250, 512, contentStream);
		        }
	        }
	        
	        if(lectureList1!=null) {
	        
		        if(lectureList1.getLecCode() !=null) {
		        	drawText(lectureList1.getLecCode(), fontGulim, fontSize, 250, 645, contentStream);
		        }
		        if(lectureList1.getLecGrd() !=null) {
		        	drawText(lectureList1.getLecGrd(), fontGulim, fontSize, 250, 545, contentStream);
		        }
		        if(lectureList1.getLecFull()!=null) {
		        	drawText(lectureList1.getLecFull()+"", fontGulim, fontSize, 475, 545, contentStream);
		        }
		        if(lectureList1.getLecMbk()!=null) {
		        	drawText(lectureList1.getLecMbk(), fontGulim, fontSize+2, 250, 385, contentStream);
		        }
		        if(lectureList1.getLecSbk()!=null) {
		        	drawText(lectureList1.getLecSbk(), fontGulim, fontSize+2, 250, 350, contentStream);
		        }
		        
	        } 
	        
	        if(lectureList1!=null) {
		        if(lectureList3.getSubName()!=null) {
		        	drawText(lectureList3.getSubName(), fontGulim, fontSize, 250, 613, contentStream);
		        }
		        if(lectureList3.getSubDetail()!=null) {
		        	drawText(lectureList3.getSubDetail(), fontGulim, fontSize, 475, 645, contentStream);
		        }
		        if(lectureList3.getSubCredit()!=null) {
		        	drawText(lectureList3.getSubCredit()+"", fontGulim, fontSize, 475, 613, contentStream);
		        }
	        }
	        
	        if(lectureList1!=null) {
		        if(lectureList4.getMemName()!=null) {
		        	drawText(lectureList4.getMemName(), fontGulim, fontSize, 250, 483, contentStream);
		        }
		        if(lectureList4.getMemTel()!=null) {
		        	drawText(lectureList4.getMemTel(), fontGulim, fontSize, 475, 483, contentStream);
		        }
		        if(lectureList4.getMemMail()!=null) {
		        	drawText2(lectureList4.getMemMail(), fontGulim, fontSize, 250, 455, contentStream);
		        }
	        }
	       
	          
	        // 컨텐츠 스트림 닫기
	        contentStream.close();

			// 파일 다운로드 설정
			response.setContentType("application/pdf");
			String fileName = URLEncoder.encode("강의계획서", "UTF-8");
			response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
			
			// PDF 파일 출력
			doc.save(response.getOutputStream());
			doc.close();
	}
	
	public void downloadPdf() {
		
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
	
	private void drawText2(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
		String result[] = text.split("@");
		String result2 ="@";
		result2 += result[1 ];
		
		contentStream.beginText(); 
		contentStream.setFont(font, fontSize);
		contentStream.newLineAtOffset(left, bottom);
		contentStream.showText(result[0]);
		contentStream.endText();
		contentStream.beginText(); 
		contentStream.setFont(font, fontSize);
		contentStream.newLineAtOffset(left, bottom-10);
		contentStream.showText(result2);
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
	public void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin, String[][] content, PDType0Font fontGulim) throws Exception {
        final int rows = content.length;
        final int cols = content[0].length;
        
        final float rowHeight = 50f;
        final float tableWidth = (page.getMediaBox().getWidth() - (2 * margin));
        final float tableHeight = rowHeight * rows;
        
        final float colWidth = tableWidth / (float)cols;
        final float cellMargin = 5f;

        // 행을 그린다.(가로줄)
        float nexty = y ;
        for(int i = 0; i <= rows; i++) {
            drawLine(contentStream, margin, nexty, margin + tableWidth, nexty);
            nexty -= rowHeight;
        }

        // 열을 그린다.(세로줄)
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
                drawText(text, fontGulim, 8, textx, texty, contentStream);
                textx += colWidth;
            }
            texty -= rowHeight;
            textx = margin + cellMargin;
        }
    }
}
