package kr.or.ddit.admin.student.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDepartmentInfoVO {

private Integer depNo;
private String depName;
private String depTel;
private Integer depFee;
private String depBorn;
private String colCode;
private String colName;
private Integer stuCnt;
private Integer proCnt;
	
}
