package com.salmon.ssata.backend.service.profile.service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.GenericModel;
import com.salmon.ssata.web.models.profile.ProfileModel;

/**
 * @author mdani
 *
 */

public interface UserService{

	public UserDetailsDTO doesUserExists(String email) throws ServiceProfileException;
	public	boolean	isUserInRole(String role) throws ServiceProfileException;
	public ProfileModel createUser(RegistrationForm form, String emailCode) throws DaoProfileException, ServiceProfileException;
	public UserDetailsDTO getCurrentUser() throws ServiceProfileException;
	public boolean isAnonymousType();
	public ProfileModel updateUser(RegistrationForm form) throws DaoProfileException, ServiceProfileException;
	public void setProfileInGenericModel(GenericModel genericModel, UserDetailsDTO userDTO) throws DaoProfileException;
}
