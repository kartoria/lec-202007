package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.stereotype.ExcelProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="cstNo")
@Builder
public class CounselingVO {
	private String proName;
	private String stuName;
	private int rnum;
	@NotNull
	@Min(0) 
	private Integer cstNo;   // 상담 번호
	
	@NotBlank
	@Size(max=8)
	private String cstDate;   // 상담 일자
	
	@NotNull
	@Min(0)
	private String cstStudent;   // 학번
	
	@NotNull 
	@Min(0) 
	private String cstProfessor;   // 교수 번호
	
	@NotBlank
	@Size(max=2)
	private String cstApptm;   // 상담희망시간
	
	@NotBlank
	@Size(max=1)
	private String cstAccpt;   // 접수 여부
	
	@Size(max=2000) 
	private String cstContent;   // 상담 내용
	
	private Integer cstTime;   // 상담 시간
	
	@Size(max=100)
	private String cstNote;   // 비고
	
	MemberVO memberVO;
	DepartmentVO departVO;
}
