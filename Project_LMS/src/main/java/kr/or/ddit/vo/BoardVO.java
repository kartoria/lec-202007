package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 게시판 VO
 */
@Data
@EqualsAndHashCode(of="boNo")
@ToString(exclude= {"boFiles", "attachList"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO implements Serializable {
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer boNo;
	
	@NotBlank @Size(max = 10)
	private String memId;
	
	@NotBlank @Size(max = 200)
	private String boTitle;
	
	@NotBlank @Size(max = 3000)
	private String boContent;
	
	private String boDate;
	
	@Min(0)
	private Integer boHit;
	
	private Integer boParent;
	
	@NotBlank
	@Size(max = 10)
	private String boGroupCode;
	
	@Size(max = 10)
	private String lecCode;
	
	@Size(max = 1)
	private String boDelete;
	
	@Size(max = 1)
	private String boSecret;
	
	private Integer rnum;
	
	// files
	private transient List<MultipartFile> boFiles;
	// 첨부파일 리스트
	private List<AttachVO> attachList;
	
	// files setter
	public void setBoFiles(List<MultipartFile> boFiles) {
		// 만약 파일의 값이 없다면 return
		if(boFiles==null || boFiles.size()==0) return;
		this.boFiles = boFiles;
		this.attachList = new ArrayList<>();
		// 파일 하나씩 꺼내서 List에 추가해주기
		for(MultipartFile tmp : boFiles) {
			if(StringUtils.isBlank(tmp.getOriginalFilename())) continue;
			attachList.add(new AttachVO(tmp));
		}
	}
	// 첨부파일 번호
	private int startAttNo;
	// 삭제 첨부파일 번호
	private int[] delAttNos;
	
	// 등록 수정 구분 (create, modify)
	private String flag; 
	private String memName;
	private String memPass;
	
	private MemberVO memberVO;
	
	// 답글 list
	List<BoardVO> repList;
}
