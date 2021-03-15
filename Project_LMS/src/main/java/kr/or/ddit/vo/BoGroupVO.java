package kr.or.ddit.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 게시판 코드 VO
 */
public class BoGroupVO {
	@NotBlank
	@Size(max = 10)
	private String bo_group_code;
	@NotBlank
	@Size(max = 50)
	private String bo_group_name;
	@NotBlank
	@Size(max = 1)
	private String parent_yn;
	@NotBlank
	@Size(max = 1)
	private String reply_yn;
}
