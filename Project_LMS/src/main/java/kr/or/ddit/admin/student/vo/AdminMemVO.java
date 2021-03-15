package kr.or.ddit.admin.student.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.stereotype.ExcelProperties;
import kr.or.ddit.stereotype.ExcelPropetyHeader;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ExcelProperties({"depName", "memId","memName","memTel","memState","memAdmission"})
@Builder
public class AdminMemVO {
	@Size(max = 10)
//	@ExcelPropetyHeader("학번")
	private String memId; // 사용자번호
	private Integer boNo;
	private Integer depNo;    // 학과번호 (departVO)
//	@ExcelPropetyHeader("학과")
	private String depName;   //학과명 (departVO)
	private String scrSmst;   //학기 (SemesterScoreVO)
	private String memType; // 회원유형
	@Size(max = 2000)
	private transient String memPass; // 비밀번호
	@NotBlank(groups= {UpdateGroup.class}) 
	@Size(max = 20)
//	@ExcelPropetyHeader("이름")
	private String memName; // 이름
	@Size(max = 6)
	private String memReg1; // 주민등록번호 앞자리
	@Size(max = 7)
	private transient String memReg2; // 주민등록번호 뒷자리
	@Size(max = 20)
//	@ExcelPropetyHeader("전화번호")
	private String memTel; // 전화번호
	@Size(max = 50)
	private String memMail; // 이메일
	@Size(max = 10)
	private String memZip; // 우편번호
	@Size(max = 20)
	private String memAddr1; // 주소
	@Size(max = 50)
	private String memAddr2; // 상세주소
	@Size(max = 20)
	private String memAcn; // 계좌번호
	@Size(max = 50)
	private String memBank; // 은행
	@Size(max = 20)
	private String memGrd; // 학년
	@Size(max = 20)
//	@ExcelPropetyHeader("근무상태")
	private String memState; // 상태코드
	@Size(max = 20)
	private String memPosition; // 직급코드
//	@ExcelPropetyHeader("입사년도")
	private String memAdmission; // 입학년도
	@Size(max = 6)
	private String memGraduation; // 졸업년도
	
	private String memImg;//회원 사진
	
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
	
	private String codeName;

	private String description;
}
