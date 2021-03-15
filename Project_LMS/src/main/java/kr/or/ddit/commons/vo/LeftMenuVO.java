package kr.or.ddit.commons.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of= "menuNo")
@ToString
public class LeftMenuVO {
	private Integer menuNo;
	private Integer menuPerent;
	private Integer menuOrder;
	private String menuUri;
	private String menuPath;
	private String menuText;
	private String menuIcon;
	
	private Integer childCount;
	
}
