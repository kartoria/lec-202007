package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
public class GuestBookVO {
	@NotNull @Min(0) 
	private Integer gb_no;
	@NotBlank @Size(max=30)
	private String gb_writer;
	@NotBlank @Size(max=300)
	private String gb_pass;
	@NotBlank @Size(max=40)
	private String gb_ip;
	private String gb_content;
	private Integer gb_parent;
	private Integer gb_depth;
	private String gb_date;
	private String gb_profile;
	private String gb_secret;
}
