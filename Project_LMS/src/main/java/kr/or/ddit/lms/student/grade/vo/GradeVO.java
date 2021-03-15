package kr.or.ddit.lms.student.grade.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = {"tlecNo"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeVO {
	
	//take_lec
	private Integer tlecNo;        //수강번호
	private String  memId;         //사용자번호
	private String  lecCode;       //강의번호
	private Integer tlecScore;	   //강의평가점수
	private String  tlecGrd;	   //신청당시 학년
	
	// lecture
	private String  subCode;       //과목코드
	private String  lecGrd;        //개설학년
	private String  lecSmst;       //개설학기
	private Integer assNo;         //평가번호
	private Integer totalScore;    //총점
	private String  slistCode;     //성적등급
	                               
	//member                       
	private String  memName;       //이름
	private String  memType;       //회원유형
	private String  memGrd;        //학년
	                               
	//subject                      
	private String  subName;       //과목명
	private Integer subCredit;     //학점
	private String  subDetail;     //이수구분
	private String  subDate;       //개설년도
	
	//code
	private String  groupCode;     //그룹코드
	private String  code;          //코드
	private String  groupCodeName; //그룹코드이름
	private String  codeName;      //코드명
	
	//DB에 없는 데이터
	private Integer totalCredit;         // 총 신청학점
	private Integer totalGetCredit;      // 총 획득 학점
	private Float   totalGradeMulCredit; // 평점 계 {학점 * 평점  (3학점 과목을 A+받으면 3*4.5 = 13.5)}
	private Float   avgGradeMulCredit;   // 평점 평균 (평점 계 / 총 신청학점)
	private Integer totalGEGrade;        // general elective 교양 선택 
	private Integer totalGRGrade;        // general required 교양 필수
	private Integer totalMEGrade;        // major elective 전공 선택
	private Integer totalMRGrade;		 // major required 전공필수
	private Integer totalFEGrade;		 // 자유선택
	
	
	private String rank;
	private List<GradeVO> smstGradeList;
	
}
