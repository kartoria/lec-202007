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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="ltimeNo")
public class LectureTimeVO {
	@NotNull 
	@Min(0) 
	private Integer ltimeNo;   //인덱스번호
	@NotBlank
	@Size(max=4) 
	private String roomCode;   //강의실코드
	@Size(max=1)
	private String ltimeStart;   //강의시작시간
	@Size(max=1) 
	private String ltimeEnd;   //강의마지막시간
	private String lecCode;   //강의코드
	@NotBlank
	@Size(max=4) 
	private String ltimeDay;   //강의요일
	
//	SubjectVO subjectvo;
}
