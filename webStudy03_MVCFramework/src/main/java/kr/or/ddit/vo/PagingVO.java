package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PagingVO<T> {
	public PagingVO(int screenSize, int blockSize) {
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord  ; // 총 게시물 수
	private int screenSize=10; // 한 페이지당 리스트 수
	private int blockSize=5  ; // 한 화면에 보여줄 페이지 수 
	private int totalPage    ; // 총 페이지 수
	private int currentPage  ; // 
	private int startPage    ;
	private int endPage      ;
	private int startRow     ;
	private int endRow       ;
	private List<T> dataList ;
	private SearchVO searchVO;
	private T searchDetail;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = currentPage * screenSize;
		this.startRow = endRow - screenSize + 1;
		this.startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		this.endPage = startPage + (blockSize - 1);
	}
	
	private static final String LIPTRN = "<li class='page-item %s' %s>";
	private static final String APTRN = "<a class='page-link' href='#' data-page='%s'>%s</a>";
	private static final String SPANPTRN = "<span class='page-link'>%s</span>";
	
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");
		
		if (endPage > totalPage) endPage = totalPage;
		
		if(startPage > blockSize) {
			html.append(String.format(LIPTRN, "", ""));
			html.append(String.format(APTRN, (startPage - blockSize), "이전"));
		}else {
			html.append(String.format(LIPTRN, "disabled", ""));
			html.append(String.format(SPANPTRN, "이전"));
		}
		html.append("</li>");
		
		for(int pageNum=startPage; pageNum<=endPage; pageNum++){
			if(pageNum == currentPage) {
				html.append(String.format(LIPTRN, "active", "aria-current='page'"));
				html.append(String.format(SPANPTRN, pageNum));
			}else {
				html.append(String.format(LIPTRN, "", ""));
				html.append(String.format(APTRN, pageNum, pageNum));
			}
			html.append("</li>");
		} 
		
		if(endPage < totalPage){
			html.append(String.format(LIPTRN, "", ""));
			html.append(String.format(APTRN, (endPage + 1), "다음"));
		}else {
			html.append(String.format(LIPTRN, "disabled", ""));
			html.append(String.format(SPANPTRN, "다음"));
		}
		html.append("</li>");
		html.append("</ul>");
		html.append("</nav>");
		return html.toString();
	}
}
