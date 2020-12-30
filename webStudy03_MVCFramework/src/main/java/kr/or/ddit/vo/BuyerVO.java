package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.rule.TelNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 거래처 정보 조회시, 해당 거래처와의 거래 물품 목록을 함께 조회함.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerVO  implements Serializable{
	private int rnum;
	@NotBlank(groups=UpdateGroup.class)
	@Size(max = 6, groups=UpdateGroup.class)
	private String buyer_id;
	@NotBlank
	@Size(max = 40)
	private String buyer_name;
	@NotBlank
	@Size(max = 4)
	private String buyer_lgu;
	@Size(max = 40)
	private String buyer_bank;
	@Size(max = 40)
	private String buyer_bankno;
	@Size(max = 15)
	private String buyer_bankname;
	@Size(max = 7)
	private String buyer_zip;
	@Size(max = 100)
	private String buyer_add1;
	@Size(max = 80)
	private String buyer_add2;
	@NotBlank
	@TelNumber
	@Size(max = 14)
	private String buyer_comtel;
	@NotBlank
	@Size(max = 20)
	private String buyer_fax;
	@NotBlank
	@Size(max = 40)
	private String buyer_mail;
	@Size(max = 10)
	private String buyer_charger;
	@Size(max = 2)
	private String buyer_telext;
	
	private String lprod_nm;
	private List<ProdVO> prodList;
}
