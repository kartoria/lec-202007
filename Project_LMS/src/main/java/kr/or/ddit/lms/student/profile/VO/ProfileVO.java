package kr.or.ddit.lms.student.profile.VO;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileVO  {
	private String depName ;	//학과명
	
	@NotBlank @Size(max = 10)
	private String memId; // 사용자번호
	
	@Size(max = 2000)
	private String memPass; // 비밀번호
	
	private Integer depNo; // 학과번호
	
	@Size(max = 10)
	private String memType; // 회원유형
	@NotNull
	@NotBlank @Size(max = 20)
	private String memName; // 이름
	@NotNull
	@Size(max = 6)
	private String memReg1; // 주민등록번호 앞자리
	@NotBlank
	@Size(max = 20)
	private String memTel; // 전화번호
	@NotBlank
	@Size(max = 50)
	private String memMail; // 이메일
	@NotBlank
	@Size(max =100)
	private String memAddr1; // 주소
	@NotBlank
	@Size(max = 50)
	private String memAddr2; // 상세주소
	@NotBlank
	@Size(max = 20)
	private String memAcn; // 계좌번호
	@Size(max = 50)
	@NotBlank
	private String memBank; // 은행
	@Size(max = 20)
	private String memGrd; // 학년
	
	private String memImg;//회원 사진
	
	@Size(max = 20)
	private String memPosition; // 직급코드
	
	
	private MultipartFile memImage;
	
	public void setMemImage(MultipartFile memImage) {
		if(memImage!=null && !memImage.isEmpty() ) {
			this.memImage = memImage;
			this.memImg = UUID.randomUUID().toString();
		}
	}
	public void saveTo(File saveFolder) throws IOException {
		if(memImage!=null) {
			memImage.transferTo(new File(saveFolder, memImg));
		}
	}
}
