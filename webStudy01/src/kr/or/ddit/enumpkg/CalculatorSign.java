package kr.or.ddit.enumpkg;

public enum CalculatorSign {
	PLUS("+"), MINUS("-"), MULTIPLE("*"), DIVISION("/");
	private String calSign;
	
	// enum 객체를 생성할때 문자열을 같이 넣어줌
	CalculatorSign(String calSign){ 
		this.calSign = calSign;
	}
	
	// 객체 생성할때 넣어준 문자열을 가져옴
	public String getCalSign(){ 
		return calSign;
	}
	
	// 파라미터로 넘어온 값과 enum객체들을 비교해서 맞는걸로 넘겨줌
	public static String getCal(String cal){ 
		CalculatorSign[] calSign = values();
		CalculatorSign finded = PLUS;
		for(CalculatorSign temp : calSign){
			if(temp.name().equals(cal.toUpperCase())){
				finded = temp;
				break;
			}
		}
		return finded.getCalSign();
	}
}
