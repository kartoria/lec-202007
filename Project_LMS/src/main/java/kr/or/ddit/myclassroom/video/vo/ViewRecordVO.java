package kr.or.ddit.myclassroom.video.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewRecordVO {
@NotNull 
@Min(0) 
private Integer videoNo;   //게시판번호
private String memId;   //학번
@NotNull 
@Min(0) 
private Integer week;   //주차
@NotBlank
//@Size(max=30) 
private String viewTime;   //시청시간
//@Size(max=8) 
private String viewLast;   //마지막 시청기록
@NotBlank
//@Size(max=20) 
private String viewSession;   //세션시간
@NotBlank
//@Size(max=20) 
private String videoLen;   //영상길이

private String lecCode;			// 강의코드
private Integer tlecNo;			// 수강번호
}
