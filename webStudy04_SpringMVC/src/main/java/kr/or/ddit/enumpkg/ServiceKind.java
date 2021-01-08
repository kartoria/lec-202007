package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.MenuVO;

public enum ServiceKind {
	GUGUDAN(MenuVO.getBuilder().menuText("구구단").menuPath("/02/gugudan.jsp").build()),
	LOCALEMESSAGE(MenuVO.getBuilder()
			.menuText("로케일메시지")
			.menuPath("/02/messageLocale.jsp")	
			.build()),
	CALCULATOR(MenuVO.getBuilder()
			.menuText("연산기")
			.menuPath("/03/calculateForm.jsp")
			.build()),
	CALENDAR(MenuVO.getBuilder()
			.menuText("달력")
			.menuPath("/04/calendar.jsp")
			.build()),
	IMPLICITOBJECT(MenuVO.getBuilder()
			.menuText("기본객체")
			.menuPath("/06/implicitObject.jsp")
			.build()),
	BTS(MenuVO.getBuilder()
			.menuText("방탄소년단")
			.menuURI("/bts")
			.build()), 
	SESSIONTIMER(MenuVO.getBuilder()
			.menuText("세션타이머")
			.menuURI("/sessionTimer.do")
			.build()),
	EMPLOYEE(MenuVO.getBuilder()
			.menuText("조직도")
			.menuURI("/employees/hirarchy.do")
			.build()),
	SERVEREXPLORER(MenuVO.getBuilder()
			.menuText("서버 탐색기")
			.menuURI("/server/explorer.do")
			.build()) 
	;
	private ServiceKind(MenuVO menu) {
		this.menu = menu;
	}
	private MenuVO menu;
	public MenuVO getMenu() {
		return menu;
	}
}







