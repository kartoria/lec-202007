package common;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegExClass {

	/**
	 * ^[a-zA-Z]{1} : 첫 글자는 대소문자 구분없이 영어로 시작한다.
	 * [a-zA-Z0-9_]{4,15}$ : 대소문자구분없이, 4~15자리이다.
	 * @author 신광진
	 * @param mem_id
	 * @return boolean
	 */
	public boolean checkId(String mem_id) {
		//입력되어야 하는 글자수는 총 5~16자리이다.
		String regex_id = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,15}$";
	
		//matches의 첫 번째 파라미터는 문자열패턴
		//matches의 두 번째 파라미터는 비교할 문자열
		return Pattern.matches(regex_id, mem_id);
	}
	
	/**
	 * [a-zA-Z0-9!@#$%^*+=-]{4,11}$ : 대소문자구분없는 알파벳, 특수문자를 포함하는 4~11자리 문자열이다.
	 * 정규식에서 문자열을 다루는 방법에 대한 공부가 미숙하여 코드분석용으로 받은 movie프로젝트의 정규식을 참고하였음.
	 * 추후 정규식에 대해 공부하여 수정할 예정
	 * @author 신광진
	 * @param mem_pw
	 * @return
	 */
	public boolean checkPw(String mem_pw) {
		String regex_pw = "[a-zA-Z0-9!@#$%^*+=-]{4,11}$";
		return Pattern.matches(regex_pw, mem_pw);
	}
	
	/**
	 * ^[가-힣]*$ : 한글로만 구성된 문자열이며, 끝 인덱스는 제한이 없다.
	 * 사람의 이름은 보통 3글자이지만, 예외인 경우가 있으므로 문자열 끝 인덱스는 제한없이 설정한다.
	 * @author 신광진
	 * @param mem_name
	 * @return
	 */
	public boolean checkName(String mem_name) {
		String regex_name = "^[가-힣]*$";
		return Pattern.matches(regex_name, mem_name);
	}
	
	/**
	 * ^01(?:0|1|[6-9]) : 문자열의 시작은 01이고 뒤에는 0 또는 1 또는 6~9사이의 숫자이다.
	 * (?:\d{3}|\d{4}) : 숫자로만 구성된 문자열 3자리 또는 4자리이다.
	 * \d{4}$ : 숫자로만 구성된 문자열 4자리이다.
	 * @author 신광진
	 * @param mem_hp
	 * @return
	 */
	public boolean checkHp(String mem_hp) {
		
		//'-'를 포함하여 핸드폰 번호를 입력한 경우
		String regex_hp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";

		
		if(Pattern.matches(regex_hp, mem_hp)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * \\d{6} : 숫자로만 구성된 6자리 문자열
	 * View class에서 회원가입을 선택한 경우, 회원의 가입여부를 판단하기 위해 주민등록번호를 확인한다.
	 * @author 신광진
	 * @param mem_regno1
	 * @return
	 */
	public boolean checkRegno1(String mem_regno1) {
		
		// regex_regno1은 숫자로만 구성된 6자리 문자열 패턴일 경우 true를 반환한다.
		String regex_regno1 = "\\d{6}";
		
		if(Pattern.matches(regex_regno1, mem_regno1)) {
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * [1-4]{1}\\d{6} : 첫자리는 1~4중 하나이고, 나머지 6자리는 숫자로만 구성된 문자열
	 * View class에서 회원가입을 선택한 경우, 회원의 가입여부를 판단하기 위해 주민등록번호를 확인한다.
	 * @author 신광진
	 * @param mem_regno2
	 * @return
	 */
	public boolean checkRegno2(String mem_regno2) {
		String regex_regno2 = "[1-4]{1}\\d{6}";
		
		if(Pattern.matches(regex_regno2, mem_regno2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * ^[가-힣]{50}$ : 한글로만 구성된 50글자의 문자열
	 * @author 신광진
	 * @param review
	 * @return
	 */
	public boolean checkReview(String review) {
		
		String regex_review = "^[가-힣]{50}$";
		
		if(Pattern.matches(regex_review, review)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 날짜타입의 입력을 확인하기 위한 메서드
	 * @author 신광진
	 * @param mem_birth
	 * @return
	 */
	public boolean checkDate(String mem_birth) {
		
		String regex_birth = "^((19|20)\\d\\d)[/](0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])";
		
		return Pattern.matches(regex_birth, mem_birth);
	}

	/**
	 * 이메일 형식을 확인하는 메서드
	 * @author 신광진
	 * @param mem_mail
	 * @return
	 */
	public boolean checkEmail(String mem_mail) {

		String regex_mail = "[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

		return Pattern.matches(regex_mail, mem_mail);

	}
}
