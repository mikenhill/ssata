package com.salmon.ssata.web.models.profile;

import com.salmon.ssata.web.models.GenericModel;

/**
 * @author mdani
 *
 */

public class ProfileModel extends GenericModel{

	private String nameFirst;
	private String nameLast;
	private String companyName;
	private String userName;
	private String personalProfileId;
	private String companyProfileId;
	private String passwordMask;

	

	public String getPasswordMask() {
		return passwordMask;
	}

	public void setPasswordMask(String passwordMask) {
		this.passwordMask = passwordMask;
	}

	public String getPersonalProfileId() {
		return personalProfileId;
	}

	public void setPersonalProfileId(String personalProfileId) {
		this.personalProfileId = personalProfileId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameLast() {
		return nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	public String getCompanyProfileId() {
		return companyProfileId;
	}

	public void setCompanyProfileId(String companyProfileId) {
		this.companyProfileId = companyProfileId;
	}
	
}
