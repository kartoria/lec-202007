package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "repNo")
@ToString(exclude = "repContent")
@Builder
public class ReplyVO {
	@NotNull
	@Min(0)
	private Integer repNo; // 댓글번호
	@NotBlank
	@Size(max = 2000)
	private String repContent; // 내용
	@Size(max = 7)
	private String repDate; // 작성일자
	@NotNull
	@Min(0)
	private Integer boNo; // 게시글번호
	@NotBlank
	@Size(max = 10)
	private String memId; // 사용자번호
	
	private MemberVO member;
}
