package kr.ac.sunmoon.urs.lockdevice;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LockDeviceValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return LockDevice.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LockDevice lockDevice = (LockDevice) target;
		
		if (lockDevice.getNo() == null) {
			errors.rejectValue("no", "empty");
		}
	}
}
