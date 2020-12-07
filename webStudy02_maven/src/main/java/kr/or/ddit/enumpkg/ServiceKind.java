package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.MenuVO;

public enum ServiceKind {
	GUGUDAN(MenuVO.getBuilder()
			.menuText("구구단")
			.menuPath("/html/02/gugudan.jsp")
			.build()),
	LOCALEMESSAGE(MenuVO.getBuilder()
			.menuText("로케일메세지")
			.menuPath("/html/02/messageLocale.jsp")
			.build()),
	CALCULATOR(MenuVO.getBuilder()
			.menuText("계산기")
			.menuPath("/html/02/calculator.jsp")
			.build()),
	CALENDAR(MenuVO.getBuilder()
			.menuText("달력")
			.menuPath("/html/04/calender.jsp")
			.build()),
	IMPLICITOBJECT(MenuVO.getBuilder()
			.menuText("오브젝트")
			.menuPath("/html/06/implicitObject.jsp")
			.build()),
	BTS(MenuVO.getBuilder()
			.menuText("BTS")
			.menuPath("/bts")
			.build());
	
	private MenuVO menu;
	private ServiceKind(MenuVO menu) {
		this.menu = menu;
	}
	
	
	public MenuVO getMenu() {
		return menu;
	}
	
}
