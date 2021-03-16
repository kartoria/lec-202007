package project;

public class MemberVO {
	/*
	   SELECT  'private String ' || lower(column_name) || ';'
	   FROM    ALL_TAB_COLUMNS
       WHERE   TABLE_NAME = 'MEMBER';
	*/

	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_regno1;
	private String mem_regno2;
	private char mem_delete;
	
	{
		mem_delete = 0;
	}
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_regno1() {
		return mem_regno1;
	}
	public void setMem_regno1(String mem_regno1) {
		this.mem_regno1 = mem_regno1;
	}
	public String getMem_regno2() {
		return mem_regno2;
	}
	public void setMem_regno2(String mem_regno2) {
		this.mem_regno2 = mem_regno2;
	}
	
	public char getMem_delete() {
		return mem_delete;
	}
	public void setMem_delete(char mem_delete) {
		this.mem_delete = mem_delete;
	}
}
