package kr.or.ddit;

public class CustomException extends RuntimeException{

	public CustomException() {
		super();
	}

	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CustomException(String message) {
		super(message);
		
	}

	public CustomException(Throwable cause) {
		super(cause);
		
	}

}
