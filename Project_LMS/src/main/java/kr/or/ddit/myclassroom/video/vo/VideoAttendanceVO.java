package kr.or.ddit.myclassroom.video.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class VideoAttendanceVO {
	@Size(max=8) 
	private String attenDate;   //출석일
	@NotBlank
	private String lecCode;   //강의코드
	@NotBlank
	@Size(max=10) 
	private String memId;   //학번
	@NotNull 
	@Min(0) 
	private Integer week;   //주차
	@Size(max=100) 
	private String attenNote;   //비고
	@NotNull 
	@Min(0) 
	private Integer attenNo;   //출결번호
	@Size(max=20) 
	private String attenCode;   //출결코드
	@NotNull 
	@Min(0) 
	private String ltimeNo;   //강의시간번호
}
