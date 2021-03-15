package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.io.FileUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="attNo")
@ToString(exclude="realFile")
@Builder
public class AttachVO {
	public AttachVO(MultipartFile realFile) {
		this.realFile = realFile;
		this.attSavename = UUID.randomUUID().toString();
		this.attFilename = realFile.getOriginalFilename();
		this.fileAttMime = realFile.getContentType();
		this.attFilesize = realFile.getSize();
		this.attFancy = FileUtils.byteCountToDisplaySize(attFilesize);
	}

	private transient MultipartFile realFile;
	public void saveTo(File saveFolder) throws IOException {
		if(realFile!=null) {
			realFile.transferTo(new File(saveFolder, attSavename));
		}
	}

	@NotNull
	@Min(0)
	private Integer attNo;
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
	private Integer boNo;
	@NotNull
	@Min(0)
	private Long attFilesize;
	@NotBlank
	@Size(max = 20)
	private String attFancy;
	private Integer attDowncount;
}
