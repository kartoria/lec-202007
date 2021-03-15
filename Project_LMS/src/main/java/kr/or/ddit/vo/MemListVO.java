package kr.or.ddit.vo;

import java.util.List;

import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.admin.student.vo.StudentListVO;
import kr.or.ddit.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemListVO {
List<MemberVO> memList;
}
