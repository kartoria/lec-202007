package kr.or.ddit.enumpkg;

public enum MemberType {
	ROLE_STUDENT("ROLE_STUDENT"),ROLE_PROFESSOR("ROLE_PROFESSOR"),ROLE_ADMIN("ROLE_ADMIN");
	
	private String role;
	private MemberType(String role) {
		this.role = role;
	}
	
}
