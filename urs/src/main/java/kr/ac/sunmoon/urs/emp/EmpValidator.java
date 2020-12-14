package kr.ac.sunmoon.urs.emp;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.ac.sunmoon.urs.member.Member;

public class EmpValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member) target;
		
		if (member.getMemberNo() == 0) {
			errors.rejectValue("memberNo", "empty");
		}
		
		if (member.getName().trim().isEmpty()) {
			errors.rejectValue("name", "empty");
		}
		
		if (member.getCardNo().trim().isEmpty()) {
			errors.rejectValue("cardNo", "empty");
		}
	}

}
