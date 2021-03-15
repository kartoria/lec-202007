package kr.or.ddit.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeVO {

@NotBlank@Size(max=20) 
private String groupCode;   //그룹코드
@NotBlank@Size(max=20) 
private String code;   //코드
@NotBlank@Size(max=100) 
private String groupCodeName;   //그룹코드이름
@NotBlank@Size(max=30) 
private String codeName;   //코드명
@Size(max=100) 
private String description;   //설명	

	
}
