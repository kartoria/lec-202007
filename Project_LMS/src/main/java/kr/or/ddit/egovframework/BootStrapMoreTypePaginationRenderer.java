package kr.or.ddit.egovframework;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

public class BootStrapMoreTypePaginationRenderer implements PaginationRenderer{

	public String getMorePage(PaginationInfo paginationInfo, String jsFunction) {
		int currentPage = paginationInfo.getCurrentPageNo();
		int totalPage = paginationInfo.getTotalPageCount();
		StringBuffer html = new StringBuffer();
		if(currentPage<totalPage) {
			int nextPage = currentPage + 1;
			html.append("<a class='btn border-success w-75' data-page='"+nextPage+"' onclick='"+jsFunction+"(event)'>더보기</a>");
			html.append("<button type='button' class='m-3 btn btn-secondary scrollTop'>↑</button>");
		}
		return html.toString();
	}
	
	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		return getMorePage(paginationInfo, jsFunction);
	}

}
