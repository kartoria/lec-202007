package kr.or.ddit.enumpkg;


public enum CalculatorSign {
	PLUS('+', (num1, num2)->{return num1+num2;}), 
	MINUS('-', (num1, num2)->{return num1-num2;}), 
	MULTIPLE('*', (num1, num2)->{return num1*num2;}), 
	DIVISION('/', new RealOperator() {
		public float operate(float num1, float num2) {
			System.out.println("계산했음");
			return num1/num2;
		}
	});
	
	@FunctionalInterface
	private static interface RealOperator{
		public float operate(float num1, float num2);
	}
	
	private RealOperator realoperator;
	private char calSign;
	
	// enum 객체를 생성할때 문자열을 같이 넣어줌
	private CalculatorSign(char calSign, RealOperator realoperator){ 
		this.calSign = calSign;
		this.realoperator = realoperator;
	}
	
	// 객체 생성할때 넣어준 문자열을 가져옴
	public char getCalSign(){ 
		return calSign;
	}
	
	public float calculation(float num1, float num2) {
		return realoperator.operate(num1, num2);
	}
	
	
}
