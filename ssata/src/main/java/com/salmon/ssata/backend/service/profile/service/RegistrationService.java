package com.salmon.ssata.backend.service.profile.service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.profile.ProfileModel;

/**
 * @author mdani
 *
 */

public interface RegistrationService {

	public boolean changeVerificationStatus(String email, String code) throws ServiceProfileException;
	public boolean changeStatusTestData(String email) throws ServiceProfileException;
	public ProfileModel registerUser(RegistrationForm form) throws DaoProfileException, ServiceProfileException;
	public ProfileModel updateUser(RegistrationForm form) throws DaoProfileException, ServiceProfileException;
	
}
