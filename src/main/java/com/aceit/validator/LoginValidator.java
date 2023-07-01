package com.aceit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.aceit.model.*;
import com.aceit.DAO.*;

@Component
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> type) {
		return Login.class.isAssignableFrom(type);
	}

	@Override
	public void validate(Object o, Errors errors) {
		

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "UserName", "error-UserName", "*UserName cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error-password", "*Password cannot be empty");

        Login login = (Login) o;

        String userName = login.getUserName();
        String password = login.getPassword();
        
        UserDAO userDAO = new UserDAO();
        
        boolean isValidUser = userDAO.validateLoggedInUserName(userName, password);
        
        if (!isValidUser) {
            errors.rejectValue("userName", "error-userName", "*Invalid UserName");
            errors.rejectValue("password", "error-password", "*Invalid Password");

        } 
		
	}
	
	

   
}
