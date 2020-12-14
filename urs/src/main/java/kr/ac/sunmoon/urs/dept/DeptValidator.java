package kr.ac.sunmoon.urs.dept;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DeptValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Dept.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Dept dept = (Dept) target;

		if (dept.getName().trim().isEmpty()) {
			errors.rejectValue("name", "empty");
		}
		
		if (dept.getTel().trim().isEmpty()) {
			errors.rejectValue("tel", "empty");
		}
		
		if (dept.getLocation().trim().isEmpty()) {
			errors.rejectValue("location", "empty");
		} 
	}
}