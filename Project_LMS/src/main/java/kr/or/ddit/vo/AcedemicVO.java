package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class AcedemicVO {

	@NotBlank @Size(max = 10)
	private String memId; // 사용자번호
	
	private String depName;   //학과명 (departVO)
	private String scrSmst;   //학기 (SemesterScoreVO)
	
	@NotBlank @Size(max = 10)
	private String memType; // 회원유형
	@NotBlank @Size(max = 2000)
	private transient String memPass; // 비밀번호
	@NotBlank @Size(max = 20)
	private String memName; // 이름
	@Size(max = 6)
	private String memReg1; // 주민등록번호 앞자리
	@Size(max = 7)
	private transient String memReg2; // 주민등록번호 뒷자리
	@Size(max = 20)
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
	private String memState; // 상태코드
	@Size(max = 20)
	private String memPosition; // 직급코드
	@NotBlank @Size(max = 6)
	private String memAdmission; // 입학년도
	@Size(max = 6)
	private String memGraduation; // 졸업년도
	
	
	
	DepartmentVO departmentVO;
	SemesterScoreVO semesterVO;
}
