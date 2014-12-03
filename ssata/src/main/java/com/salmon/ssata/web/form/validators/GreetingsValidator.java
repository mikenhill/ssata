package com.salmon.ssata.web.form.validators;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.salmon.ssata.web.form.GreetingForm;

@Component
public class GreetingsValidator implements Validator {
	public boolean supports(Class clazz) {
		return GreetingForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "greeting.greetingText",
				"required.greetingText", "greeting text is required.");
	}
}
