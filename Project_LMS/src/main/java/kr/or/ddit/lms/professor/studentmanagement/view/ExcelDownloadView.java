package kr.or.ddit.lms.professor.studentmanagement.view;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import kr.or.ddit.stereotype.ExcelProperties;
import kr.or.ddit.stereotype.ExcelProperties.ExcelMetaData;

public abstract class ExcelDownloadView extends AbstractXlsxView {
	public static final String TYPENAME = "dataType";
	public static final String FILENAME = "fileName";
	
// 대용량 데이터에 대한 처리가 필요한 경우, in memory 방식이 아닌 임시 파일 저장 형태로 워크북을 직렬화함.	
//	@Override
//	protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
//		return new SXSSFWorkbook();
//	}
	
	/* 
	 * 문서 생성 기본 구조
	 * 1. Workbook 생성
	 *    : 다운로드 파일명 정의 
	 * 2. Sheet 생성
	 * 	
	 * 3. Header 생성
	 * 
	 * 4. Data body 생성
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 1. 생성된 문서의 다운로드 파일명 정의
		setContentType("application/octet-stream");
		String filename = (String) model.get(FILENAME);
		if(filename==null)
			filename = String.format("ExcelData_[%1$tY_%1$tm_%1$td].xlsx", Calendar.getInstance());
		filename = URLEncoder.encode(filename, "UTF-8").replace("+", " ");
		response.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		
		Class<?> elementType = (Class)model.get(TYPENAME);
		
		// 2. Sheet 생성
		Sheet sheet = createSheet(workbook);
		
		
		// 3. Header 생성
		String[] properties = null;
		String[] headerNames = null;
		int rowNum = 0;
		if(elementType!=null) {
			ExcelMetaData metaData = ExcelProperties.ExcelMetaData.parsingMataData(elementType);
			properties = metaData.getProperties();
			headerNames = metaData.getHeaderNames();
			CellStyle headerStyle = createHeaderStyle(workbook);
			System.err.println(Arrays.toString(headerNames));
			rowNum = createHeader(sheet, headerStyle, headerNames, rowNum);
		}
		
		// 4. Data body 생성
		makeDataRow(model, workbook, sheet, rowNum, properties);
	}
	
	
	
	protected Sheet createSheet(Workbook workbook) {
		return workbook.createSheet();
	}
	
	protected CellStyle createHeaderStyle(Workbook workbook){
		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeight((short)500);
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}
	
	protected int createHeader(Sheet sheet, CellStyle headerStyle, String[] headerNames, int rowNum) {
		Header header = sheet.getHeader();
		header.setLeft(" Data List ");
		Row headerRow = sheet.createRow(rowNum++);
		int colNum = 0;
        for (String headerName : headerNames) {
            createCell(headerRow, headerStyle, headerName, colNum++);
        }
        return rowNum;
	}
	
	
	/**
	 * 4. Data body 생성은 hook 로 남김.
	 * @param model
	 * @param workbook
	 * @param sheet
	 * @param rowNum
	 * @param properties
	 * @throws Exception
	 */
	protected abstract void makeDataRow(Map<String, Object> model, Workbook workbook, Sheet sheet, int rowNum, String[] properties) throws Exception;
	
	protected CellStyle createStyle(Workbook workbook){
		Font font = workbook.createFont();
		font.setFontName("Arial");
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}
	
	protected String[] parseProperties(Object element) throws Exception{
		String[] properties = null;
		if(element instanceof Map) {
			Set<String> keySet = ((Map)element).keySet();
			properties = new String[keySet.size()];
			properties = new ArrayList<>(keySet).toArray(properties);
		}else {
			properties = ExcelProperties.ExcelMetaData.parsingMataData(element.getClass()).getProperties();
		}
		return properties;
	}
	
	protected Object parsePropertyValue(Object element, String propName){
		try {
			if(element instanceof Map) {
				Map<?, ?> map = (Map)element;
				return map.get(propName);
			}else {
				Field fld = element.getClass().getDeclaredField(propName);
				fld.setAccessible(true);
				return fld.get(element);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected int createRows(Sheet sheet, List<?> list, int rowNum, CellStyle style, String[] properties) throws Exception {
		int colNum = -1;
		for( Object element : list ) {
			Row row = sheet.createRow(rowNum++);
			if(properties==null) properties = parseProperties(element);
			int rowColCnt = 0; 
			for(String propName : properties) {
				if(rowColCnt >= properties.length) break;
				Object value = parsePropertyValue(element, propName);
				createCell(row, style, Objects.toString(value, ""), rowColCnt++);
			}
			if(colNum<rowColCnt) colNum = rowColCnt;
		}
		return colNum;
	}

	protected Cell createCell(Row row, CellStyle style, String value, int colNum) {
		Cell cell = row.createCell(colNum);
		cell.setCellStyle(style);
		cell.setCellValue(value);
		return cell;
	}
}
