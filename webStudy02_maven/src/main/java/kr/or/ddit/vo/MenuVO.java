package kr.or.ddit.vo;

import java.io.Serializable;

public class MenuVO implements Serializable{
	public MenuVO() {
		super();
	}
	private MenuVO(String menuText, String menuPath, String menuURI, String menuName) {
		super();
		this.menuText = menuText;
		this.menuPath = menuPath;
		this.menuURI = menuURI;
		this.menuName = menuName;
	}
	private String menuText;
	private String menuPath; // jsp path
	private String menuURI; // model2 servlet mapping uri
	private String menuName;
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	
	public String getMenuURI() {
		return menuURI;
	}
	public void setMenuURI(String menuURI) {
		this.menuURI = menuURI;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuPath == null) ? 0 : menuPath.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuVO other = (MenuVO) obj;
		if (menuPath == null) {
			if (other.menuPath != null)
				return false;
		} else if (!menuPath.equals(other.menuPath))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MenuVO [menuText=" + menuText + ", menuPath=" + menuPath + ", menuURI=" + menuURI + ", menuName="
				+ menuName + "]";
	}
	
	
	public static MenuVOBuilder getBuilder(){
		return new MenuVOBuilder();
	}
	public static class MenuVOBuilder{
		private String menuText;
		private String menuPath;
		private String menuURI;
		private String menuName;
		public MenuVOBuilder menuText(String menuText){
			this.menuText = menuText;
			return this;
		}
		public MenuVOBuilder menuPath(String menuPath){
			this.menuPath = menuPath;
			return this;
		}
		public MenuVOBuilder menuURI(String menuURI){
			this.menuURI = menuURI;
			return this;
		}
		public MenuVOBuilder menuName(String menuName){
			this.menuName = menuName;
			return this;
		}
		public MenuVO build() {
			return new MenuVO(menuText, menuPath, menuURI, menuName);
		}
		
	}
}
