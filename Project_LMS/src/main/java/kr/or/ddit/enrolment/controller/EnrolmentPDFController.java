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

import kr.or.ddit.commons.controller.IssuedNumberController;
import kr.or.ddit.enrolment.service.EnrolmentService;
import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.lms.student.tuition.service.TuitionService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;

/**
 * @author 전진원
 * @since 2021. 1. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 25.     전진원	     최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class EnrolmentPDFController  {
	
	@Autowired
	ServletContext context; 
	
	@Inject
	private EnrolmentService service;
	
	@Inject
	TuitionService tuitionService;
	
	@Inject
	private IssuedNumberController issued;
	
	private static final Logger logger = LoggerFactory.getLogger(EnrolmentPDFController.class);
	
	@RequestMapping(value = "/enrolment/schedulePdf.do", method = RequestMethod.GET)
	public void scheduleList(HttpServletResponse response
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws Exception {
		
		 //수강 신청 내역 - 시간 받아오기
        List<EnrolmentVO> LectureTimeList = service.selectLectureTime(authMember);
        DepartmentVO departmentVO = tuitionService.selectDepName(authMember);
        
        schedulePDF(LectureTimeList, response,authMember,departmentVO);
		
	}
	// 시간표 작성
	public void schedulePDF(List<EnrolmentVO> LectureTimeList, HttpServletResponse response, MemberVO authMember, DepartmentVO departmentVO) throws Exception {

		
		final String webroot = this.context.getRealPath("/");
		
		// 문서 만들기
		final PDDocument doc = new PDDocument();
		
		// 배경이미지 로드
	    PDImageXObject pdImage = PDImageXObject.createFromFile(webroot + "resources/timeBack.PNG", doc);
		
	    
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
        int fontSize =8;
        
        String issuedFormat =issued.issuedNumberController("시간표");
        if(issuedFormat !=null) {
        	drawText("문서 번호 : ["+issuedFormat+"]", fontGulim, 15, 25, 820, contentStream);
        }
        
        if(departmentVO!=null) {
        	drawText(departmentVO.getDepName(), fontGulim, fontSize, 140, 725, contentStream);
        	
        }
        drawText(authMember.getMemId(), fontGulim, fontSize, 140, 697, contentStream);
		if(authMember.getMemGrd()!=null) {
			drawText((int)((Integer.parseInt(authMember.getMemGrd())/2.0)+0.5)+"학년" , fontGulim, fontSize, 240, 697, contentStream);
		}
		drawText(authMember.getMemName(), fontGulim, fontSize, 395, 697, contentStream);

		
        //시간표 글자 쓰기
        int max = LectureTimeList.size();
		for(int idx=0; idx<max;idx++) {
			switch (LectureTimeList.get(idx).getLtimeDay()) {
			case "월":
				for(int j=Integer.parseInt(LectureTimeList.get(idx).getLtimeStart());j<=Integer.parseInt(LectureTimeList.get(idx).getLtimeEnd());j++) {
					switch (j) {
					case 1:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 612, contentStream);
						break;
					case 2:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 560, contentStream);
						break;
					case 3:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 510, contentStream);
						break;
					case 4:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 463, contentStream);
						break;
					case 5:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 413, contentStream);
						break;
					case 6:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 360, contentStream);
						break;
					case 7:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 313, contentStream);
						break;
					case 8:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 264, contentStream);
						break;
					case 9:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 126, 214, contentStream);
						break;
					}
				}
				break;
			case "화":
				for(int j=Integer.parseInt(LectureTimeList.get(idx).getLtimeStart());j<=Integer.parseInt(LectureTimeList.get(idx).getLtimeEnd());j++) {
					switch (j) {
					case 1:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 612, contentStream);
						break;
					case 2:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 560, contentStream);
						break;
					case 3:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 510, contentStream);
						break;
					case 4:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 463, contentStream);
						break;
					case 5:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 413, contentStream);
						break;
					case 6:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 360, contentStream);
						break;
					case 7:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 313, contentStream);
						break;
					case 8:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 264, contentStream);
						break;
					case 9:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 200, 214, contentStream);
						break;
					}
				}
				
				break;
			case "수":
				for(int j=Integer.parseInt(LectureTimeList.get(idx).getLtimeStart());j<=Integer.parseInt(LectureTimeList.get(idx).getLtimeEnd());j++) {
					switch (j) {
					case 1:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 612, contentStream);
						break;
					case 2:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 560, contentStream);
						break;
					case 3:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 510, contentStream);
						break;
					case 4:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 463, contentStream);
						break;
					case 5:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 413, contentStream);
						break;
					case 6:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 360, contentStream);
						break;
					case 7:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 313, contentStream);
						break;
					case 8:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 264, contentStream);
						break;
					case 9:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 272, 214, contentStream);
						break;
					}
				}
				
				break;
			case "목":
				for(int j=Integer.parseInt(LectureTimeList.get(idx).getLtimeStart());j<=Integer.parseInt(LectureTimeList.get(idx).getLtimeEnd());j++) {
					switch (j) {
					case 1:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 612, contentStream);
						break;
					case 2:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 560, contentStream);
						break;
					case 3:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 510, contentStream);
						break;
					case 4:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 463, contentStream);
						break;
					case 5:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 413, contentStream);
						break;
					case 6:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 360, contentStream);
						break;
					case 7:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 313, contentStream);
						break;
					case 8:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 264, contentStream);
						break;
					case 9:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 348, 214, contentStream);
						break;
					}
				}
				
				break;
			case "금":
				for(int j=Integer.parseInt(LectureTimeList.get(idx).getLtimeStart());j<=Integer.parseInt(LectureTimeList.get(idx).getLtimeEnd());j++) {
					switch (j) {
					case 1:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 612, contentStream);
						break;
					case 2:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 560, contentStream);
						break;
					case 3:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 510, contentStream);
						break;
					case 4:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 463, contentStream);
						break;
					case 5:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 413, contentStream);
						break;
					case 6:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 360, contentStream);
						break;
					case 7:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 313, contentStream);
						break;
					case 8:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 264, contentStream);
						break;
					case 9:
						drawText2(LectureTimeList.get(idx).getSubName(), fontGulim, fontSize, 417, 214, contentStream);
						break;
					}
				}
				
				break;

			}
			
		}
        
        // 컨텐츠 스트림 닫기
        contentStream.close();

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("시간표", "UTF-8");
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
	public void drawText(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
        contentStream.beginText(); 
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(left, bottom);
    	contentStream.showText(text);
        contentStream.endText();
	}
	//줄바꿈 글씨
	public void drawText2(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
		contentStream.beginText(); 
		contentStream.setFont(font, fontSize);
		contentStream.newLineAtOffset(left, bottom);
		int len = text.length();//길이 구하기
		String text2 ="";
		String text3 ="";
		String text4 ="";
		if(len>6) {
			for(int i = 0; i < len; i++) {
				if(i<5) {
					text2 += text.charAt(i);
				}else if(i<10){
					text3 += text.charAt(i);
				}else {
					text4 += text.charAt(i);
				}
			}
			
			contentStream.showText(text2);
			contentStream.endText();
			
			contentStream.beginText(); 
			contentStream.setFont(font, fontSize);
			contentStream.newLineAtOffset(left, bottom-10);
			contentStream.showText(text3);
			contentStream.endText();
			
			if(len>12) {
				contentStream.beginText(); 
				contentStream.setFont(font, fontSize);
				contentStream.newLineAtOffset(left, bottom-20);
				contentStream.showText(text4);
				contentStream.endText();
			}
			
			
			
		}else {
			contentStream.showText(text);
			contentStream.endText();
		}
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
	public void drawLine(PDPageContentStream contentStream, float xStart, float yStart, float xEnd, float yEnd) throws IOException {
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
