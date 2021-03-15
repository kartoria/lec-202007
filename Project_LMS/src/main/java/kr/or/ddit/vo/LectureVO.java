package kr.or.ddit.vo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.admin.lecture.vo.TimeListVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
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
@EqualsAndHashCode(of="lecCode")
@ToString(exclude="lecPlanList")
public class LectureVO {
	private String lecCode; // 강의번호
	@NotBlank
	@Size(max = 20)
	private String subCode; // 과목코드
	@NotBlank
	@Size(max = 10)
	private String memId; // 사용자번호
	@NotNull
	@Min(0)
	private Integer lecFull; // 정원
	private Integer lecNmt; // 수강인원
	@NotBlank
	@Size(max = 4)
	private String roomCode; // 강의실코드
	@NotBlank
	@Size(max = 1)
	private String lecGrd; // 개설학년
	@NotNull
	@Min(0)
	private Integer lecDays; // 강좌일수
	@Size(max = 50)
	private String lecMbk; // 주교재
	@Size(max = 50)
	private String lecSbk; // 부교재
	@NotBlank
	private String lecSmst; // 개설학기
	private String lecDelete; // 폐강여부
	
	SubjectVO subjectVo;
	MemberVO memberVo;
	TakeLecVO takeLecVo;
	LectureTimeVO lectureTimeVo;
	CodeVO code;
	DepartmentVO departmentVo;
	
	List<LecPlanVO> lecPlanList;
	List<LectureTimeVO> lecTimeList;
	List<TimeListVO> timeList;
	List<String> ltimeDayStartEndList;
}
