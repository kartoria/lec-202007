package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.filter.fileupload.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="att_no")
@ToString(exclude="realFile")
public class AttachVO {
	public AttachVO(MultipartFile realFile) {
		this.realFile = realFile;
		this.att_savename = realFile.getSavename();
		this.att_filename = realFile.getOriginalFilename();
		this.att_mime = realFile.getContentType();
		this.att_filesize = realFile.getSize();
		this.att_fancy = FileUtils.byteCountToDisplaySize(att_filesize);
	}

	private MultipartFile realFile;
	public void saveTo(File saveFolder) throws IOException {
		if(realFile!=null) {
			realFile.saveToWithNewName(saveFolder);
		}
	}
	
	private Integer att_no;
	@NotBlank	
	@Size(max = 280)
	private String att_savename;
	@NotBlank
	@Size(max = 200)
	private String att_filename;
	@Size(max = 120)
	private String att_mime;
	@NotNull
	private Integer bo_no;
	@NotNull
	private Long att_filesize;
	@NotBlank
	@Size(max = 20)
	private String att_fancy;
	private Integer att_downcount;
	

}
