package kr.or.ddit.myclassroom.video.vo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.TakeLecVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="videoNo")
@ToString
public class VideoVO {
	@Min(0)
	private Integer videoNo;
	@NotNull(groups= {InsertGroup.class})
	@Min(0)
	private Integer week;
	@Size(max = 10)
	private String memId;
	@NotBlank
	@Size(max = 10)
	private String lecCode;
	@Size(max = 2000)
	private String videoContent;
	@NotBlank
	@Size(max = 200)
	private String videoTitle;
	@NotBlank
	@Size(max = 200)
	private String videoUrl;
	@Size(max = 10)
	private String videoDate;
	@Min(0)
	private Integer videoHit;
	@Size(max = 10)
	private String videoAttstart;
	@Size(max = 10)
	private String videoAttend;
	@NotBlank
	@Size(max = 20)
	private String videoLen;
}
