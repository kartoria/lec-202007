package kr.or.ddit.member.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirstLoginVO {
	@Size(max = 10)
	private String memId; // 사용자번호
	@NotBlank
	@Size(max = 6)
	private String memReg1; // 주민등록번호 앞자리
	@NotBlank
	@Size(max = 7)
	private transient String memReg2; // 주민등록번호 뒷자리
	@NotBlank
	@Size(max = 20)
	private String memTel; // 전화번호
}
