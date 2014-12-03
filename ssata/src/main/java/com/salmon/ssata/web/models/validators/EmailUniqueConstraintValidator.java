package com.salmon.ssata.web.models.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.salmon.ssata.web.controller.html.annonymous.profile.RegistrationController;


	public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUnique, String> {
		 
		@Autowired
		private RegistrationController registrationController;	
		
		public EmailUniqueConstraintValidator (RegistrationController registrationController) {
			this.registrationController = registrationController;
		}
		
	    @Override
	    public void initialize(EmailUnique emailUnique) { }
	 
	    @Override
	    public boolean isValid(String email, ConstraintValidatorContext cxt) {
	    	
	    	UserDetails user = registrationController.getBootstrapUserServiceImpl().loadUserByUsername(email);
			return user == null;
	    }
	 
	}