package kr.or.ddit.vo;

import java.io.Serializable;

public class memberVO implements Serializable{
	private String mail;
	private String name;
	private String pass;
	
	public memberVO(String mail, String name, String pass) {
		this.mail = mail;
		this.name = name;
		this.pass = pass;
	}
	
	public String getMail() {
		return mail;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		memberVO other = (memberVO) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "memberVO [mail=" + mail + ", name=" + name + ", pass=" + pass + "]";
	}
	
	public static MVBuilder getBuilder() {
		return new MVBuilder();
	}
	public static class MVBuilder{
		private String mail;
		private String name;
		private String pass;
		
		public MVBuilder mail(String mail) {
			this.mail = mail;
			return this;
		}
		public MVBuilder name(String name) {
			this.name = name;
			return this;
		}
		public MVBuilder pass(String pass) {
			this.pass = pass;
			return this;
		}
		public memberVO build() {
			return new memberVO(mail, name, pass);
		}
	}
}
