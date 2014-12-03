package com.salmon.ssata.backend.service.profile.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dao.profile.service.CompanyProfileDAO;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.GenericServiceImpl;
import com.salmon.ssata.backend.service.common.email.SMTPService;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.backend.service.profile.service.CompanyProfileService;
import com.salmon.ssata.backend.service.profile.service.RegistrationService;
import com.salmon.ssata.common.ErrorMessages;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.profile.ProfileModel;

/**
 * @author mdani
 *
 */

@Service("RegistrationServiceImpl")
public class RegistrationServiceImpl extends GenericServiceImpl implements RegistrationService{
	
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private CompanyProfileService companyProfileService;
	
	@Autowired
	private CompanyProfileDAO companyProfileDAOImpl; 
	
	@Autowired
	private SMTPService smtpService;
	
	private ProfileModel profileModel;
	
	public boolean changeVerificationStatus(String email, String code) throws ServiceProfileException{
		
		try{
			
			UserDetailsDTO unverifiedUser = this.userService.doesUserExists(email);			
			
			if(unverifiedUser != null){
				if(code.equals(unverifiedUser.getEmailVeriCode())){
					unverifiedUser.setEmailConfirmed(true);
					this.userService.getUserDetailsDAO().update(unverifiedUser);
				}
			}
			
			UserDetailsDTO verifiedUser = 
					this.userService.getUserDetailsDAO().getByUserName(email);
			
			if(verifiedUser.isEmailConfirmed() == true){
				return true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_ROLE_ERROR);
		}
		
		return false;
	}	
	
	/* this method only used to enable users when it is created as part of test data */
	public boolean changeStatusTestData(String email) throws ServiceProfileException{
		
		try{
			
			UserDetailsDTO unverifiedUser = this.userService.doesUserExists(email);			
			
			if(unverifiedUser != null){
				unverifiedUser.setEmailConfirmed(true);
				this.userService.getUserDetailsDAO().update(unverifiedUser);
			}
			
			UserDetailsDTO verifiedUser = 
					this.userService.getUserDetailsDAO().getByUserName(email);
			
			if(verifiedUser.isEmailConfirmed() == true){
				return true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_ROLE_ERROR);
		}
		
		return false;
	}	
	
	public ProfileModel registerUser(RegistrationForm form) throws DaoProfileException, ServiceProfileException{
		
		ProfileModel personalProfileModel = null;
		ProfileModel corporateProfileModel = null;
		
		try{
			
			String verificationCode = RandomStringUtils.randomAlphanumeric(10);
			
			personalProfileModel = 
					this.userService.createUser(form, verificationCode);

			if(personalProfileModel != null){
				corporateProfileModel = 
							this.companyProfileService.createProfile(form, personalProfileModel.getPersonalProfileId());
				personalProfileModel.setCompanyName(corporateProfileModel.getCompanyName());
				
				this.setProfileModel(personalProfileModel);
					
				//Fire an email
				Map<String, Object> dataModel = new HashMap<String, Object>();
	
				dataModel.put("code", verificationCode);
				dataModel.put("firstName", personalProfileModel.getNameFirst());
				dataModel.put("email", personalProfileModel.getUserName());
					
				sendVerificationMail(dataModel);
			}
			
		}catch (DaoProfileException dpe) {

			throw dpe;		
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_REGISTER_CANT_REGISTER);		
		}
		
		return personalProfileModel;
	}
	
	
	@Override
	public ProfileModel updateUser(RegistrationForm form) throws DaoProfileException, ServiceProfileException{
		
		ProfileModel personalProfileModel = null;
		ProfileModel corporateProfileModel = null;
		
		try{
						
			personalProfileModel = 
					this.userService.updateUser(form);
			corporateProfileModel = this.companyProfileService.updateProfile(form, personalProfileModel.getPersonalProfileId());
			personalProfileModel.setCompanyName(corporateProfileModel.getCompanyName());
			
			this.setProfileModel(personalProfileModel);
			
		}catch (DaoProfileException dpe) {

			throw dpe;		
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_REGISTER_CANT_REGISTER);		
		}
		
		return personalProfileModel;
	}

	
	public void sendVerificationMail(Map<String,Object>dataModel){
		
		String[] templateString = this.getTemplateComposerServiceImpl()
						.getTemplate(dataModel, "", "/public/email/verify_registration_et.ftl");
		
		smtpService.getSmtpPropertyImpl().setSMTPProerty(getProfileModel().getUserName(), 
									"Email Verification", templateString[0], templateString[1]);
		
		smtpService.createMail(smtpService.getSmtpPropertyImpl());

	}	
	
	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	public ProfileModel getProfileModel() {
		return profileModel;
	}

	public void setProfileModel(ProfileModel profileModel) {
		this.profileModel = profileModel;
	}
	
	public SMTPService getSmtpService() {
		return smtpService;
	}

	public void setSmtpService(SMTPService smtpService) {
		this.smtpService = smtpService;
	}
	
	public CompanyProfileService getCompanyProfileService() {
		return companyProfileService;
	}

	public void setCompanyProfileServiceImpl(
			CompanyProfileService companyProfileService) {
		this.companyProfileService = companyProfileService;
	}

}
