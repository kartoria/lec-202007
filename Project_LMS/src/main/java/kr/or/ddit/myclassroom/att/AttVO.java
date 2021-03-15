package kr.or.ddit.myclassroom.att;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttVO {
	@NotBlank@Size(max=1) private String attenTime;   //강의시간
	@NotBlank@Size(max=10) private String ainfoCode;   //출결내용
	
	
	private String  attenNo  ;	   // 출석번호
	private String  attenDate;     // 출석일
	private String  lecCode; 	   // 강의코드
	private String  memId    ;     // 학번
	private Integer week     ;     // 주차
	private String  attenNote;     // 비고
	
	private String  codeName ;     // 출결내용
	private String  ltimeNo  ;     // 강의시간번호
	
	
	private String memName;
	private String attenCode; 		//출결
	
}	

