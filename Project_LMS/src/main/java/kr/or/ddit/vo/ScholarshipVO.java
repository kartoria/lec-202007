package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 장학 유형 VO
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScholarshipVO implements Serializable {
private String schCode;   //장학코드
@NotBlank
@Size(max=50) 
private String schName;   //장학명
private String schWhen;   //선후불여부
@NotBlank
@Size(max=200) 
private String schType;   //장학분류
@NotBlank
@Size(max=2000) 
private String schDetail;   //장학상세설명
@Size(max=15) 
private String schAmount;   //지급액
@NotBlank
@Size(max=30) 
private String schAmountDetail;  // 지급상세
private String schUse;		//사용여부
	
	
}
