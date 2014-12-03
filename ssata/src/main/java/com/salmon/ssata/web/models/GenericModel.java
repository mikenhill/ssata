package com.salmon.ssata.web.models;

/**
 * @author mdani
 *
 */

import java.io.Serializable;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@Scope("session")
public class GenericModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private	BindingResult bindingResult;
	private int notifications;
	private Map<String, String> errors;
    private String success;
	private String title;
	//TODO: Shared Data Bug
	private boolean emailConfirmed;
	
	private String userName;
	private String userEmail;
	private String personalProfileId;
	private String companyProfileId;
    private String role;
	
	public void init(String title){
		this.title = title;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	public int getNotifications() {
		return notifications;
	}

	public void setNotifications(int notifications) {
		this.notifications = notifications;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public String getPersonalProfileId() {
		return personalProfileId;
	}

	public void setPersonalProfileId(String personalProfileId) {
		this.personalProfileId = personalProfileId;
	}

	public String getCompanyProfileId() {
		return companyProfileId;
	}

	public void setCompanyProfileId(String companyProfileId) {
		this.companyProfileId = companyProfileId;
	}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
