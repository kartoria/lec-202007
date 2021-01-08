package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="rep_no")
@ToString(exclude="rep_content")
public class ReplyVO {
	@NotNull
	private Integer rep_no;
	@NotBlank
	@Size(max = 80)
	private String rep_writer;
	@Size(max = 7)
	private String rep_date;
	@NotBlank
	@Size(max = 200)
	private String rep_pass;
	@Size(max = 400)
	private String rep_content;
	@NotNull
	private Integer bo_no;
	@NotBlank
	@Size(max = 50)
	private String rep_ip;
}
