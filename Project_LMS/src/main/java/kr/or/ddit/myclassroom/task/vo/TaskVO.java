package kr.or.ddit.myclassroom.task.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.ReplyVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 과제 VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "repNo")
@ToString(exclude = "taskAttList")
@Builder
public class TaskVO implements Serializable {
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	@Min(0)
	private Integer taskNo;
	@NotBlank
	@Size(max = 10)
	private String lecCode;
	@NotBlank
	@Size(max = 200)
	private String taskTitle;
	@NotBlank
	@Size(max = 2000)
	private String taskContent;
	@NotNull
	@Min(0)
	private Integer taskCredit;
	private String taskSday;
	private String taskDday;
	private Integer rnum;
	
	private LectureVO lecVO;
	private String memId;
	
	private List<TaskSubmitVO> taskSubmitList;
	
}
