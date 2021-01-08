package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 자바빈(ValueObject, DataTransferObject, Model) : javaBean 규약에 따라 정의된 재사용 가능한 객체
 * 1. 상태 유지를 위한 property
 * 2. 캡슐화
 * 3. 캡슐화된 property 에 접근하기 위한 인터페이스 제공
 * 4. 상태비교 방법 제공
 * 5. 상태확인 방법 제공
 * 6. 상태 기록 방법 
 * 
 * immutable object 정의
 *
 */
public class BtsVO implements Serializable{
	
	private BtsVO(String code, String name, String url) {
		super();
		this.code = code;
		this.name = name;
		this.url = url;
	}

	private String code;
	private String name;
	private String url;

	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	public String getUrl() {
		return url;
	}

//	public void setUrl(String url) {
//		this.url = url;
//	}

	public String getCode() {
		return code;
	}

//	public void setCode(String code) {
//		this.code = code;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		BtsVO other = (BtsVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BtsVO [code=" + code + ", name=" + name + "]";
	}
	
	public static BtsVOBuilder getBuilder() {
		return new BtsVOBuilder();
	}
	public static class BtsVOBuilder{
		private String code;
		private String name;
		private String url;
		public BtsVOBuilder code(String code) {
			this.code = code;
			return this;
		}
		public BtsVOBuilder name(String name) {
			this.name = name;
			return this;
		}
		public BtsVOBuilder url(String url) {
			this.url = url;
			return this;
		}
		
		public BtsVO build() {
			return new BtsVO(code, name, url);
		}
	}
	
	
}














