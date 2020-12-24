package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude= {"employee_id", "emp_name", "email", "manager_id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeVO implements Comparable<EmployeeVO>{
	private String employee_id;
	private String emp_name;
	private String email;
	private String phone_number;
	private String hire_date;
	private String salary;
	private String manager_id;
	private String commission_pct;
	private String retire_date;
	private String department_id;
	private String job_id;
	private String create_date;
	private String update_date;
	
	@Override
	public int compareTo(EmployeeVO o) {
		return this.emp_name.toLowerCase().compareTo(o.emp_name.toLowerCase());
	}
}
