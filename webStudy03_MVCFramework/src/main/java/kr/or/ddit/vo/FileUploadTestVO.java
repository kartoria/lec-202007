package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.fileupload.MultipartFile;
import lombok.Data;

@Data
public class FileUploadTestVO {
	private String TextParam;
	private Integer numberParam;
	
	private List<MultipartFile> uploadFile;
	public void setUploadFile(List<MultipartFile> uploadFile) {
		if(uploadFile == null || uploadFile.size() == 0) return;
		this.uploadFile = new ArrayList<>();
		for(MultipartFile tmp : uploadFile) {
			if(tmp!=null && StringUtils.isNotBlank(tmp.getOriginalFilename())) {
				String mime = tmp.getContentType();
				if(mime==null || !mime.startsWith("image/")) {
					throw new IllegalArgumentException("이미지만 업로드할 수 없음");
				}
				this.uploadFile.add(tmp);
			}
		}
	}
}
