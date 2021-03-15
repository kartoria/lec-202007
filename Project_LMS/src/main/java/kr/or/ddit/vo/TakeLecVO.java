package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of="tlecNo")
@Builder
public class TakeLecVO {
	@NotNull @Min(0) private Integer tlecNo;   //수강번호
	@NotBlank@Size(max=10) private String memId;   //학번
	@NotBlank@Size(max=10) private String lecCode;   //강의번호
	 private Integer tlecScore;   //강의평가점수
	 @Size(max=1) private String tlecGrd;   //현재학년
	 
	 
	 
	 
	 
}
