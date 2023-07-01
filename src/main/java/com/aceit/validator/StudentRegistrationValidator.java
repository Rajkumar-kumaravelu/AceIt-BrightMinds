package com.aceit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aceit.model.Student;

@Component
public class StudentRegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error-firstName", "*firstName cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error-lastName", "*lastName cannot be empty");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error-userName", "*userName cannot be empty");
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error-email", "*email address is empty");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error-password", "*Password cannot be empty");
    
        Student studentRegistration = (Student) o;
        
        
        String email = studentRegistration.getEmail();
        
        EmailValidator emailValidator = new EmailValidator();

            boolean isValidEmail = emailValidator.validate(email);
            if (!isValidEmail) {
                System.out.println("into email validation");
                errors.rejectValue("email", "error-email", "*invalid email address");
            }
		
	}

}
