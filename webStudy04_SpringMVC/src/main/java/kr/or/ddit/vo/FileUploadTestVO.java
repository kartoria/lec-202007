package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileUploadTestVO {
	private String textParam;
	private Integer numberParam;
	
	private List<MultipartFile> uploadFile;
	public void setUploadFile(List<MultipartFile> uploadFile) {
		if(uploadFile==null || uploadFile.size()==0) return;
		this.uploadFile = new ArrayList<>();;
		for(MultipartFile tmp : uploadFile) {
			if(StringUtils.isNotBlank(tmp.getOriginalFilename())) {
				String mime = tmp.getContentType();
				if(mime==null || !mime.startsWith("image/")) {
					throw new IllegalArgumentException("이미지만 업로드할 수 있음.");
				}
				this.uploadFile.add(tmp);
			}
		}
	}
}

















