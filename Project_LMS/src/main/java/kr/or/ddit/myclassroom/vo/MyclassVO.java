package kr.or.ddit.myclassroom.vo;

import kr.or.ddit.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class MyclassVO {

	MemberVO memberVO;
	String lecCode;
	String subName;
	String memName;
	String memId;
	
}
