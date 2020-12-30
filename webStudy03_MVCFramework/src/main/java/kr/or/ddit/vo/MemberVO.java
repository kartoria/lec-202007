package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.fileupload.MultiPartFile;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.rule.TelNumber;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"mem_id", "mem_regno1", "mem_regno2"})
@ToString(exclude= {"mem_pass", "mem_regno1", "mem_regno2"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO implements Serializable{
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	private String mem_id;
	
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	@Size(min=5, max=12)
	private transient String mem_pass;
	
	@NotBlank
	private String mem_name;
	
	@NotBlank(groups=InsertGroup.class)
	@Pattern(regexp="[0-9]{6}")
	private transient String mem_regno1;
	
	@NotBlank(groups=InsertGroup.class)
	@NotBlank
	@Pattern(regexp="[0-9]{7}")
	private transient String mem_regno2;
	
	private String mem_bir;
	
	@NotBlank
	private String mem_zip;
	
	@NotBlank
	private String mem_add1;
	
	@NotBlank
	private String mem_add2;
	
	@NotBlank
	@TelNumber
	private String mem_hometel;
	
	@NotBlank
	@TelNumber
	private String mem_comtel;
	
	@TelNumber(value="\\d{2,3} \\d{3,4} \\d{4}", placeholder="000 0000 0000")
	private String mem_hp;
	
	@NotBlank
	@Email
	private String mem_mail;
	
	private String mem_job;
	
	private String mem_like;
	
	private String mem_memorial;
	
	private String mem_memorialday;
	
	@Min(0)
	@Max(Integer.MAX_VALUE)
	private Integer mem_mileage;
	
	private String mem_delete;
	
	private List<ProdVO> prodList; // Member has many Prod;
	
	private String mem_role;
	
	
	public String getTest() {
		return "테스트";
	}
	
	
	private MultiPartFile mem_image; // client 데이터 받기용.
	public void setMem_image(MultiPartFile mem_image) throws IOException {
		if(mem_image!=null && StringUtils.isNotBlank(mem_image.getOriginalFilename())) {
			this.mem_image = mem_image;
			this.mem_img = mem_image.getBytes();
		}
	}
	
	private byte[] mem_img;   // DB 전달 용
	
	public String getBase64Image() {
		String encoded = null;
		if(mem_img!= null) {
			encoded = Base64.getEncoder().encodeToString(mem_img);
		}
		return encoded;
	}
}
