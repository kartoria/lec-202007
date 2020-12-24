package kr.or.ddit.vo;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="lprod_id")
public class LProdVO {
	private Integer lprod_id;
	private String lprod_gu;
	private String lprod_nm;
	
	private Set<ProdVO> prodList; // has many
}
