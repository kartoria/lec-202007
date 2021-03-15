package kr.or.ddit.myclassroom.test.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// 객관식 문항
@Data
@EqualsAndHashCode(of="testNo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailVO implements Serializable {
	@NotNull @Min(0) private Integer testNo;
	@NotNull @Min(0) private Integer testQno;
	@NotNull @Min(0) private Integer dtlNo;
	@NotBlank@Size(max=100) private String dtlContent;
}
