package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.fileupload.MultipartFile;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.rule.TelNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 거래처 정보 조회시, 해당 거래처와의 거래 물품 목록을 함께 조회함.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerVO implements Serializable{
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
	
	private MultipartFile buyer_image;
	private MultipartFile charger_image;
	
	private String buyer_img;
	private byte[] charger_img;
	
	public void setBuyer_image(MultipartFile buyer_image) {
		if(buyer_image!=null && StringUtils.isNotBlank(buyer_image.getSubmittedFileName())) {
			this.buyer_image = buyer_image;
			this.buyer_img = buyer_image.getSavename();
		}
	}
	
	public void setCharger_image(MultipartFile charger_image) throws IOException {
		if(charger_image!=null && StringUtils.isNotBlank(charger_image.getSubmittedFileName())) {
			this.charger_image = charger_image;
			this.charger_img = charger_image.getBytes();
		}
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(buyer_image!=null) {
			buyer_image.saveToWithNewName(saveFolder);
		}
	}
	
	public String getBase64ChargerImage() {
		String encoded = null;
		if(charger_img!=null) {
			encoded = Base64.getEncoder().encodeToString(charger_img);
		}
		return encoded;
	}
}
