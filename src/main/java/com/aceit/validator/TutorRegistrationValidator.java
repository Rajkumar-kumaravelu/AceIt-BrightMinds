package com.aceit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aceit.DAO.TutorDAO;
import com.aceit.model.Tutor;

@Component
public class TutorRegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Tutor.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error-firstName", "*firstName cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error-lastName", "*lastName cannot be empty");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
		// "error-userName", "*userName cannot be empty");

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error-email",
		// "*Invalid email address");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		// "error-password", "*Password cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "error-subject", "*subject cannot be empty");

		Tutor tutorRegistration = (Tutor) target;

		String email = tutorRegistration.getEmail();

		TutorDAO tutorDAO = new TutorDAO();

		EmailValidator emailValidator = new EmailValidator();

		boolean isValidEmail = emailValidator.validate(email);
		if (!isValidEmail) {
			System.out.println("into email validation");
			errors.rejectValue("email", "error-email", "*invalid email address");
		}

	}

}
