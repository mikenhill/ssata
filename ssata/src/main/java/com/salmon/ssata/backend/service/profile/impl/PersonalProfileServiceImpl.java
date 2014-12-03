package com.salmon.ssata.backend.service.profile.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dao.profile.service.PersonalProfileDAO;
import com.salmon.ssata.backend.dao.profile.service.UserDetailsDAO;
import com.salmon.ssata.backend.dto.profile.PersonalProfileDTO;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.GenericServiceImpl;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.backend.service.profile.service.CompanyProfileService;
import com.salmon.ssata.backend.service.profile.service.PersonalProfileService;
import com.salmon.ssata.backend.service.profile.service.UserService;
import com.salmon.ssata.common.ErrorMessages;
import com.salmon.ssata.web.forms.profile.ForgotPwForm;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.profile.ProfileModel;

/**
 * @author mdani
 *
 */

@Service("PersonalProfileServiceImpl")
public class PersonalProfileServiceImpl extends GenericServiceImpl implements PersonalProfileService{

	@Autowired
	private PersonalProfileDAO personalProfileDAOImpl;
		
	@Autowired
	private CompanyProfileService companyProfileServiceImpl;
	
	@Autowired
	private UserService userServiceImpl;

	@Autowired
	private UserDetailsDAO userDetailsDAOImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean updatePassword(ForgotPwForm form)throws DaoProfileException, ServiceProfileException{
		
		UserDetailsDTO existingUser = this.getUserServiceImpl().doesUserExists(form.getEmail());
		
		if(existingUser != null){
			
			existingUser.setPassword(getPasswordEncoder().encodePassword(form.getPasswordOne(),existingUser.getSalt()));
			this.getUserDetailsDAOImpl().update(existingUser);
			
			//Fire an email
			Map<String, Object> dataModel = new HashMap<String, Object>();

			dataModel.put("email", existingUser.getUsername());
				
			this.getTemplateComposerServiceImpl().sendPwChangedMail(dataModel);
			
			return true;
		}
		
		return false;
	}
	
	public boolean sendPasswordResetInstructions(String email) throws DaoProfileException, ServiceProfileException{
		
		UserDetailsDTO existingUser = this.getUserServiceImpl().doesUserExists(email);
		
		if(existingUser != null){
		
			ProfileModel profileModel = getProfileByUser(existingUser);
			
			String pwChangeCode = RandomStringUtils.randomAlphanumeric(10);	
			existingUser.setPwChangeReqCode(pwChangeCode);
			this.getUserDetailsDAOImpl().update(existingUser);
			
			//Fire an email
			Map<String, Object> dataModel = new HashMap<String, Object>();

			dataModel.put("code", pwChangeCode);
			dataModel.put("firstName", profileModel.getNameFirst());
			dataModel.put("email", profileModel.getUserName());
				
			this.getTemplateComposerServiceImpl().sendPwResetMail(dataModel);
			
			return true;
			
		}else{
			throw new ServiceProfileException(ErrorMessages.SERVICE_PERSONAL_PROFILE_CANT_CREATE);
		}
		
	}
	
	public boolean isChangePwLinkValid(String code, String email)throws DaoProfileException, ServiceProfileException{
		
		UserDetailsDTO existingUser = this.getUserServiceImpl().doesUserExists(email);
	
		if(existingUser != null){
			if(code.equals(existingUser.getPwChangeReqCode())){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public ProfileModel createProfile(RegistrationForm form,
									UserDetailsDTO userDetailsDTO) throws DaoProfileException, ServiceProfileException {
		ProfileModel profileModel = null;
		try {

			PersonalProfileDTO profileDTO = new PersonalProfileDTO();
			
			//mmh: we are now using 2 fields for the first and last name
			profileDTO.setFirstName(form.getFirstName());
			profileDTO.setLastName(form.getLastName());

			profileDTO.setUser(userDetailsDTO);
			
			personalProfileDAOImpl.createProfile(profileDTO);
			
			profileDTO = personalProfileDAOImpl.getByUsername(form.getUserName());
			
			profileModel = new ProfileModel();
			//mnh: Modified to only include the last name if it was added at the registration screen
			profileModel.setNameFirst(profileDTO.getFirstName());
			profileModel.setNameLast(profileDTO.getLastName());
			profileModel.setUserName(profileDTO.getUser().getUsername());
			profileModel.setPersonalProfileId(profileDTO.getId());
			
		}catch (DaoProfileException dpe) {
			throw dpe;
			
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_PERSONAL_PROFILE_CANT_CREATE);
			
		}
		
		return profileModel;
	}
	
	@Override
	public ProfileModel getProfileByUser(UserDetailsDTO userDetailsDTO) throws DaoProfileException, ServiceProfileException {
		
		ProfileModel profileModel = null;
		
		try {

			PersonalProfileDTO profileDTO = personalProfileDAOImpl.getByUser(userDetailsDTO);
				
			profileModel = new ProfileModel();
			profileModel.setNameFirst(profileDTO.getFirstName());
			profileModel.setNameLast(profileDTO.getLastName());
			profileModel.setUserName(userDetailsDTO.getUsername());
			profileModel.setPersonalProfileId(profileDTO.getId());
			profileModel.setCompanyProfileId(profileDTO.getCompany().getId());
			
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERIVCE_PERSONAL_PROFILE_ERROR_USEROBJ);
			
		}
		
		return profileModel;
	}	
	
	@Override
	public ProfileModel updateProfile(RegistrationForm form,
									UserDetailsDTO userDetailsDTO) throws DaoProfileException, ServiceProfileException {
		ProfileModel profileModel = null;
		try {

			PersonalProfileDTO profileDTO = personalProfileDAOImpl.getByUser(userDetailsDTO);
			
			//mmh: we are now using 2 fields for the first and last name
			profileDTO.setFirstName(form.getFirstName());
			profileDTO.setLastName(form.getLastName());
			
			personalProfileDAOImpl.updateProfile(profileDTO);
			
			profileDTO = personalProfileDAOImpl.getByUsername(form.getUserName());
			
			profileModel = new ProfileModel();
			profileModel.setNameFirst(profileDTO.getFirstName());
			profileModel.setNameLast(profileDTO.getLastName());
			profileModel.setUserName(profileDTO.getUser().getUsername());
			profileModel.setPersonalProfileId(profileDTO.getId());
			
		}catch (DaoProfileException dpe) {
			throw dpe;
			
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_PERSONAL_PROFILE_CANT_CREATE);
			
		}
		
		return profileModel;
	}

	
	public ProfileModel assembleProfileModel() throws ServiceProfileException, DaoProfileException{
		
		ProfileModel profileModel = null;
		ProfileModel companyModel = null;
		try{
			
			profileModel = this.getProfileByUser(this.getUserServiceImpl().getCurrentUser());
			
			companyModel = this.companyProfileServiceImpl.getProfileByUser(profileModel.getPersonalProfileId());
		
			profileModel.setCompanyName(companyModel.getCompanyName());

		}catch (DaoProfileException dpe) {
			throw dpe;		
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_ERROR_ASSEMBLE);		
		}
		return profileModel;
	}
	
	public PersonalProfileDAO getPersonalProfileDAOImpl() {
		return personalProfileDAOImpl;
	}

	public void setPersonalProfileDAOImpl(
			PersonalProfileDAO personalProfileDAOImpl) {
		this.personalProfileDAOImpl = personalProfileDAOImpl;
	}
	
	public CompanyProfileService getCompanyProfileServiceImpl() {
		return companyProfileServiceImpl;
	}

	public void setCompanyProfileServiceImpl(
			CompanyProfileService companyProfileServiceImpl) {
		this.companyProfileServiceImpl = companyProfileServiceImpl;
	}
	
	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public UserDetailsDAO getUserDetailsDAOImpl() {
		return userDetailsDAOImpl;
	}

	public void setUserDetailsDAOImpl(UserDetailsDAO userDetailsDAOImpl) {
		this.userDetailsDAOImpl = userDetailsDAOImpl;
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}




