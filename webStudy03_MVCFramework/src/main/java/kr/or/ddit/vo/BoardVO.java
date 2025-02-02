package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.fileupload.MultipartFile;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"bo_no"})
@ToString(exclude= {"bo_files", "attachList", "replyList"})
public class BoardVO implements Serializable{
	
	@NotNull(groups=UpdateGroup.class)
	private Integer bo_no;
	
	private String bo_content;
	
	@NotBlank
	@Size(max = 200)
	private String bo_title;
	
	@NotBlank
	@Size(max = 80)
	private String bo_writer;
	
	private String bo_date;
	private Integer bo_hit;
	private Integer bo_rec;
	
	@NotBlank
	@Size(max = 200)
	private String bo_pass;
	
	@NotBlank
	@Size(max = 50)
	private String bo_ip;
	
	@Size(max = 200)
	private String bo_mail;
	
	private Integer bo_parent;

	private Integer rnum;
	private Integer rep_cnt;
	private int[] delAttNos;

	private transient List<MultipartFile> bo_files;

	public void setBo_files(List<MultipartFile> bo_files) {
		if(bo_files==null || bo_files.size()==0) return;
		this.bo_files = bo_files;
		this.attachList = new ArrayList<>();
		for(MultipartFile tmp : bo_files) {
			if(StringUtils.isBlank(tmp.getOriginalFilename())) continue; 
			attachList.add(new AttachVO(tmp));
		}
	}

	private transient List<AttachVO> attachList;
	private transient List<ReplyVO> replyList;
	private int startAttNo;
}
