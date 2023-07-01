package com.aceit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aceit.model.Answer;
import com.aceit.model.Login;

@Component
public class AnswerPostValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
        return Answer.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer", "error-answer", "*empty answer cannot be submitted");

        Answer answer = (Answer) target;

        String submittedAnswer = answer.getAnswer();
        
        final int maxLength = 50;
        
        if(submittedAnswer.length()>maxLength) {
        	errors.rejectValue("answer", "error-answer", "*maximum permitted length is 50");
        }
        
        
	}

}
