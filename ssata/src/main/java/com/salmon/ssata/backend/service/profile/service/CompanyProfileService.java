package com.salmon.ssata.backend.service.profile.service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.profile.CompanyModel;
import com.salmon.ssata.web.models.profile.ProfileModel;

public interface CompanyProfileService {

	public ProfileModel createProfile(RegistrationForm form,
			String personalProfileId) throws DaoProfileException,
			ServiceProfileException;

	public ProfileModel updateProfile(RegistrationForm form,
			String personalProfileId) throws DaoProfileException,
			ServiceProfileException;

	public ProfileModel getProfileByUser(String personalProfileId)
			throws DaoProfileException, ServiceProfileException;
	
	public CompanyModel getCompanyByProfileId(String personalProfileId) 
			throws DaoProfileException, ServiceProfileException;

	
}
