package kr.or.ddit.vo;

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
public class InterestLecVO {
	@NotBlank@Size(max=10) private String lecCode;   //강의번호
	@NotBlank@Size(max=10) private String memId;   //학번
}
