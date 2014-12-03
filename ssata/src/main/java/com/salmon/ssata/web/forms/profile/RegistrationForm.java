package com.salmon.ssata.web.forms.profile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author mdani
 *
 */

public class RegistrationForm {
	
	public interface BasicCheck {}
	
	@NotBlank (message="first.name.blank", groups = BasicCheck.class)
  	@Size (min=2, max=255, message="first.name.not.exceed", groups = BasicCheck.class)
  	private String firstName;

  	@NotBlank (message="last.name.blank", groups = BasicCheck.class)
  	@Size (min=2, max=255, message="last.name.not.exceed", groups = BasicCheck.class)
  	private String lastName;	    
    
	@Email	(message="email.not.valid", groups = BasicCheck.class)
	@Size (max=255, message="email.not.exceed", groups = BasicCheck.class)	
	private String userName;
	private String userNameCopy;

	@NotBlank (message="password.blank", groups = BasicCheck.class)
	@Size (min=8, max=255, message="password.not.exceed", groups = BasicCheck.class)
	private String password;
	
	@NotBlank (message="confirm.password.blank", groups = BasicCheck.class)
	@Size (min=8, max=255, message="confirm.password.not.exceed", groups = BasicCheck.class)
	private String confirmPassword;
	
	@AssertTrue(message="Password has to match Confirm Password")
	  private boolean isValid() {
	    return this.password.equals(this.confirmPassword);
	  }
	
	@Size (min=2, max=255, message="company.name.not.exceed", groups = BasicCheck.class)
	//@NotBlank (message="company.name.blank")
	private String companyName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
}
