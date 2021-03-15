package kr.or.ddit.myclassroom.task.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.io.FileUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.LectureVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="tasksubmitNo")
@ToString(exclude="realFile")
@Builder
public class TaskSubmitVO implements Serializable{

	private transient MultipartFile realFile;
	public void setRealFile(MultipartFile realFile) {
		this.realFile = realFile;
		this.attSavename = UUID.randomUUID().toString();
		this.attFilename = realFile.getOriginalFilename();
		this.fileAttMime = realFile.getContentType();
		this.attFilesize = realFile.getSize();
		this.attFancy = FileUtils.byteCountToDisplaySize(attFilesize);
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(realFile!=null) {
			realFile.transferTo(new File(saveFolder, attSavename));
		}
	}
	
	@NotNull
	@Min(0)
	private Integer tasksubmitNo;
	@NotBlank
	@Size(max = 10)
	private String memId; // 학번
	@NotNull
	@Min(0)
	private Integer taskNo;
	@NotBlank
	@Size(max = 70)
	private String attSavename;
	@NotBlank
	@Size(max = 50)
	private String attFilename;
	@Size(max = 30)
	private String fileAttMime;
	@NotNull
	@Min(0)
	private Long attFilesize;
	@NotBlank
	@Size(max = 20)
	private String attFancy;
	private Integer taskScr; // 과제점수
	@NotNull
	@NotBlank
	@Size(max = 100)
	private String taskReply;
	@NotNull
	private String taskSubmitDate; // 제출 날짜
	
	private MemberVO memberVO;
	
	private int taskCredit;
	
	private String lecCode;  // 강의코드
	
}
