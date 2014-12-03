package com.salmon.ssata.web.models.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.salmon.ssata.common.SsataConstants;
import com.salmon.ssata.web.forms.profile.RegistrationForm;

public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistrationForm rForm = (RegistrationForm)target;
		//Check to see if password is being updated and matches confirm password
		if (!rForm.getPassword().equals(SsataConstants.PASSWORD_MASK) && 
			!rForm.getConfirmPassword().equals(SsataConstants.PASSWORD_MASK) &&
			!rForm.getPassword().equals(rForm.getConfirmPassword())) {
			errors.rejectValue("password", "rForm.password.PasswordMatch", "password.match");
		}
	}

}
