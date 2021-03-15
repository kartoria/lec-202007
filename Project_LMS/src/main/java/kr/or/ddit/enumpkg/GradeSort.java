package kr.or.ddit.enumpkg;

public enum GradeSort{
	ORDER_BY_ID("학번"), ORDER_BY_RANK("석차");
	
	private String inputValue;
	private GradeSort(String inputValue){
		this.inputValue = inputValue;
	}
	
	public String getGradeSort() {
		return inputValue;
	}
	
	public static GradeSort getInputValueConstant(String value) {
		GradeSort[] gradeSorts = values();
		GradeSort finded = ORDER_BY_RANK;
		for(GradeSort temp : gradeSorts){
			if(value.toUpperCase().contains(temp.name())){
				finded = temp;
				break;
			}
		}
		return finded;
	}  
	public static String getInputValue(String value) {
		return getInputValueConstant(value).getGradeSort();
	}
}
