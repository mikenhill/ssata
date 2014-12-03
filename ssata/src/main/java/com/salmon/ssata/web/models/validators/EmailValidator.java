package com.salmon.ssata.web.models.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.salmon.ssata.backend.service.common.BootstrapUserServiceImpl;
import com.salmon.ssata.common.SsataConstants;
import com.salmon.ssata.web.forms.profile.RegistrationForm;




public class EmailValidator implements Validator {

	@Autowired
	private BootstrapUserServiceImpl bootstrapUserServiceImpl;	
	
	public EmailValidator (BootstrapUserServiceImpl bootstrapUserServiceImpl) {
		this.bootstrapUserServiceImpl = bootstrapUserServiceImpl;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistrationForm rForm = (RegistrationForm)target;
		//Check to see if this email already exists.
		UserDetails user = bootstrapUserServiceImpl.loadUserByUsername(rForm.getUserName());
		if (user != null) {
			errors.rejectValue("userName", "rForm.userName.EmailUnique", "email.not.unique");
		}
		
		//Ensure the email is on the valid list
		if (!rForm.getUserName().toLowerCase().
				endsWith(SsataConstants.VALID_PASSWORD_SUFFIX.toLowerCase())) {
			errors.rejectValue("userName", "rForm.userName.EmailNotValid", "email.not.valid.company");
		}
		
	}

}
