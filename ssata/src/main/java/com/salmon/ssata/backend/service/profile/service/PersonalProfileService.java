package com.salmon.ssata.backend.service.profile.service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.web.forms.profile.ForgotPwForm;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.profile.ProfileModel;

public interface PersonalProfileService{

	public ProfileModel createProfile(RegistrationForm form, UserDetailsDTO userDetailsDTO) throws DaoProfileException, ServiceProfileException ;
	public ProfileModel getProfileByUser(UserDetailsDTO userDetailsDTO) throws DaoProfileException, ServiceProfileException;
	public ProfileModel assembleProfileModel() throws ServiceProfileException, DaoProfileException;
	public ProfileModel updateProfile(RegistrationForm form, UserDetailsDTO userDetailsDTO) throws DaoProfileException, ServiceProfileException ;
	public boolean sendPasswordResetInstructions(String email) throws DaoProfileException, ServiceProfileException;
	public boolean isChangePwLinkValid(String code, String email)throws DaoProfileException, ServiceProfileException;
	public boolean updatePassword(ForgotPwForm form)throws DaoProfileException, ServiceProfileException;
	
}
