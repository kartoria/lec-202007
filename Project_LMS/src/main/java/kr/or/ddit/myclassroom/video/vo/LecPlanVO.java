package kr.or.ddit.myclassroom.video.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.vo.LectureVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="lecCode")
public class LecPlanVO {
	@NotBlank
	@Size(max = 10)
	private String lecCode;
	@NotNull
	@Min(0)
	private Integer lecWeek;
	@NotBlank
	@Size(max = 200)
	private String lecContent;
	
	
	
	
}
