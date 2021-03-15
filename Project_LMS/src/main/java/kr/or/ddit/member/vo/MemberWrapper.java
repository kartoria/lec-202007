package kr.or.ddit.member.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberWrapper extends User{
	
	private static Collection<GrantedAuthority> makeAuthorities(String memType){
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(memType));
		return authorities;
	}
	
	public MemberWrapper(MemberVO realMember) {
		super(realMember.getMemId()
			, realMember.getMemPass()
			, realMember.getMemGraduation() == null
			, realMember.getMemGraduation() == null
			, realMember.getMemGraduation() == null
			, realMember.getMemGraduation() == null
			, makeAuthorities(realMember.getMemType())
		);
		this.realMember = realMember;
	}
	private MemberVO realMember;
	public MemberVO getRealMember() {
		return realMember;
	}
	
}