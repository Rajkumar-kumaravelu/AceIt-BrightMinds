package com.aceit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.aceit.DAO.*;
import com.aceit.model.*;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error-UserName", "*UserName cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error-password", "*Password cannot be empty");

		User registeredUser = (User) target;

		String userName = registeredUser.getUserName();

		UserDAO userDAO = new UserDAO();

		boolean IsRepeatedUsername = userDAO.checkUserNameExists(userName);

		if (IsRepeatedUsername) {
			errors.rejectValue("userName", "error-userName", "*username already exist");
		}

	}

}
