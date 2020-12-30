package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.fileupload.MultiPartFile;
import lombok.Data;

@Data
public class FileUploadTestVO {
	private String TextParam;
	private Integer numberParam;
	
	private List<MultiPartFile> uploadFile;
	public void setUploadFile(List<MultiPartFile> uploadFile) {
		if(uploadFile == null || uploadFile.size() == 0) return;
		this.uploadFile = new ArrayList<>();
		for(MultiPartFile tmp : uploadFile) {
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
