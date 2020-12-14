package kr.ac.sunmoon.urs.rental;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RentalValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Rental.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Rental rental = (Rental) target;
		if (rental.getNo() == 0) {
			errors.rejectValue("no", "empty");
		}
		
		if (rental.getStudentNo() == 0) {
			errors.rejectValue("studentNo", "empty");
		}
		
		if (rental.getLockDeviceNo() == 0) {
			errors.rejectValue("lockDeviceNo", "empty");
		}
		
		if (rental.getRentalDate() == null) {
			errors.rejectValue("rentalDate", "empty");
		}
		
		if (rental.getStatus().trim().isEmpty()) {
			errors.rejectValue("status", "empty");
		}
	}

}
