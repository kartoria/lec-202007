package kr.or.ddit.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

public class MemberVoValidateTest {
    private static Validator validator;
    
    @Before
    public void setup() {
    	validator = Validation.byDefaultProvider().configure().messageInterpolator(
	    				new ResourceBundleMessageInterpolator(
	    					new PlatformResourceBundleLocator("kr/or/ddit/msg/message")
	    				)
    				).buildValidatorFactory().getValidator();
    }
    
    
	@Test
	public void validateTest() {
		MemberVO member = new MemberVO();
		member.setMem_mail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		Set<ConstraintViolation<MemberVO>> violationSet = validator.validate(member);
		for(ConstraintViolation<MemberVO> violation : violationSet) {
			String propertyName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			System.out.printf("%s : %s \r\n", propertyName, message);
		}
		
	}
}
