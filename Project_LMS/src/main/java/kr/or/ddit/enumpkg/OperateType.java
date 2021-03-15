package kr.or.ddit.enumpkg;

public enum OperateType{
	PLUS('+', (l, r)->{ return l + r; }), 
	MINUS('-', (l, r)->{ return l - r;}), 
	MULTIPLY('*', new RealOperator() {
		public int operate(int l, int r) {
			return l * r;
		}
	}), 
	DIVIDE('/', (l, r)->{return l / r;}), 
	MODULAR('%', (l, r)->{ return l % r;});
	
	@FunctionalInterface
	private static interface RealOperator{
		public int operate(int leftOp, int rightOp);
	}
	
	private char sign;
	private RealOperator realOperator;
	private OperateType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	public char getSign() {
		return sign;
	}
	public int operator(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
}