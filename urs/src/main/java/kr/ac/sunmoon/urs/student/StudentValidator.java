package kr.ac.sunmoon.urs.student;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.ac.sunmoon.urs.member.Member;

public class StudentValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member) target;
		
		if (member.getName().trim().isEmpty()) {
			errors.rejectValue("name", "empty");
		}
		
		if (member.getPhone().trim().isEmpty()) {
			errors.rejectValue("phone", "empty");
		}
		
		if (member.getCardNo().trim().isEmpty()) {
			errors.rejectValue("cardNo", "empty");
		}
	}
}
