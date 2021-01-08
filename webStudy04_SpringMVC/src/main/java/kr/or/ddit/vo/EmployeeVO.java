package kr.or.ddit.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeVO {
	@JsonProperty("key")
	private Integer employee_id;
	@JsonProperty("title")
	private String emp_name;
	@JsonProperty("tooltip")
	private String email;
	private String phone_number;
	private String hire_date;
	private Integer salary;
	@JsonProperty("parentId")
	private Integer manager_id;
	private Double commission_pct;
	private String retire_date;
	private Integer department_id;
	private String job_id;
	private String create_date;
	private String update_date;
	public boolean folder;
	public boolean isLazy() {
		return folder;
	}
//	public boolean lazy;
}	
