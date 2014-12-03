package com.salmon.ssata.backend.service.profile.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dao.profile.impl.UserDetailsDAOImpl;
import com.salmon.ssata.backend.dao.profile.service.PersonalProfileDAO;
import com.salmon.ssata.backend.dto.profile.AuthorityDTO;
import com.salmon.ssata.backend.dto.profile.PersonalProfileDTO;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.GenericServiceImpl;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.backend.service.profile.service.PersonalProfileService;
import com.salmon.ssata.backend.service.profile.service.UserService;
import com.salmon.ssata.common.ErrorMessages;
import com.salmon.ssata.common.SsataConstants;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.GenericModel;
import com.salmon.ssata.web.models.profile.ProfileModel;

/**
 * @author mdani
 *
 */

@Service("UserServiceImpl")

public class UserServiceImpl extends GenericServiceImpl implements UserService{
	
	@Autowired
	private PersonalProfileService personalProfileService;
	
	@Autowired
	private UserDetailsDAOImpl userDetailsDAO;
	
	@Autowired
	private	PasswordEncoder	passwordEncoder;
	
	@Autowired
	private SaltSource	saltSource;
	
	@Autowired
	private PersonalProfileDAO personalProfileDAO;

	@Resource(name="AUTHORITIES")
	private Map<String,AuthorityDTO> authorities; 
	
	private boolean anonymousType;
	
	public UserDetailsDTO getCurrentUser() throws ServiceProfileException{
		
		try{
			
			UserDetailsDTO existingUser = 
					this.getPrincipalObject();			
			
			if(existingUser != null){
				return existingUser;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_ROLE_ERROR);
		}
		
		return null;
	}
	
	public UserDetailsDTO doesUserExists(String email) throws ServiceProfileException{
		
		try{
			
			UserDetailsDTO existingUser = 
					this.userDetailsDAO.getByUserName(email);			
			
			if(existingUser != null){
				return existingUser;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_ROLE_ERROR);
		}
		
		return null;
	}
	
	public	boolean	isUserInRole(String role) throws ServiceProfileException{
		boolean resultFlag = false;
		
		try {
			
			UserDetailsDTO principalObj = this.getPrincipalObject();
			
			if(principalObj != null){
				for (AuthorityDTO authorityDTO : this.getPrincipalObject().getRoles()){
					if (authorityDTO.getAuthority().equals(role)){
						resultFlag = true;
						this.setUserInSession(principalObj);
						personalProfileService.assembleProfileModel();
						break;
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_ROLE_ERROR);
		}
		
		return resultFlag;
	}
	
	public UserDetailsDTO getPrincipalObject() throws Exception{
		
		UserDetailsDTO userDetailsDTO = null;
		Object principalObj = null;
		
		try{
			
			principalObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if(principalObj.equals("anonymousUser")){
				this.setAnonymousType(true);
				return null;
			}else{
				this.setAnonymousType(false);
				userDetailsDTO = (UserDetailsDTO) principalObj;
				return userDetailsDTO;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

	}
	
	public ProfileModel createUser(RegistrationForm form, String emailCode) throws DaoProfileException, ServiceProfileException{
		
		ProfileModel profileModel =  null;
		try{
			
			UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
			AuthorityDTO authorityDTO = null;
			
			authorityDTO = this.authorities.get(SsataConstants.ROLE_EMPLOYEE);
			
			if(this.userDetailsDAO.getByUserName(form.getUserName()) == null){
				
				if(authorityDTO != null){
				
					userDetailsDTO.setEmailVeriCode(emailCode);
					userDetailsDTO.setEmailConfirmed(false);
					
					userDetailsDTO.setUsername(form.getUserName());
					
					userDetailsDTO.setPassword(
							passwordEncoder.encodePassword(form.getPassword(), 
									saltSource.getSalt(userDetailsDTO)));
					
					userDetailsDTO.setPrimaryEmail(form.getUserName());
					userDetailsDTO.getRoles().add(authorityDTO);
					
					this.userDetailsDAO.create(userDetailsDTO);
					
					UserDetailsDTO registeredUser = 
								this.userDetailsDAO.getByUserName(form.getUserName());			
						
					this.setUserInSession(registeredUser);
					
					profileModel = this.personalProfileService
										.createProfile(form, registeredUser);
					
				}else{
					throw new ServiceProfileException(ErrorMessages.SERVICE_USER_ROLE_ERROR);
				}
			}
			
		}catch (DaoProfileException dpe) {
			throw dpe;		
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_CANT_CREATE);		
		}
		
		return profileModel;
	}
	
	public ProfileModel updateUser(RegistrationForm form) throws DaoProfileException, ServiceProfileException{
		
		ProfileModel profileModel =  null;
		try{
			
			UserDetailsDTO userDetailsDTO = this.getCurrentUser();
			
			userDetailsDTO.setUsername(form.getUserName());
			
			//mnh - only allow a password update if it is not the password mask. We prefil the password field with the mask and therefore we should not update to 
			//this value.
			if (form.getPassword() != null && !form.getPassword().equals(SsataConstants.PASSWORD_MASK)) {
				userDetailsDTO.setPassword(passwordEncoder.encodePassword(form.getPassword(),saltSource.getSalt(userDetailsDTO)));
			}
			
			userDetailsDTO.setPrimaryEmail(form.getUserName());

			this.userDetailsDAO.update(userDetailsDTO);
			
			UserDetailsDTO registeredUser = this.userDetailsDAO.getByUserName(form.getUserName());			
				
			this.setUserInSession(registeredUser);
			
			profileModel = this.personalProfileService.updateProfile(form, registeredUser);
				
		}catch (DaoProfileException dpe) {
			throw dpe;		
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_USER_CANT_CREATE);		
		}
		
		return profileModel;
	}

	
	public boolean isAnonymousType() {
		return anonymousType;
	}

	public void setAnonymousType(boolean anonymousType) {
		this.anonymousType = anonymousType;
	}

	public UserDetailsDAOImpl getUserDetailsDAO() {
		return userDetailsDAO;
	}

	public void setUserDetailsDAO(UserDetailsDAOImpl userDetailsDAO) {
		this.userDetailsDAO = userDetailsDAO;
	}
	
	public PersonalProfileService getPersonalProfileService() {
		return personalProfileService;
	}

	public void setPersonalProfileService(
			PersonalProfileService personalProfileService) {
		this.personalProfileService = personalProfileService;
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public SaltSource getSaltSource() {
		return saltSource;
	}

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}
	
	@Override
	public void setProfileInGenericModel(GenericModel genericModel, UserDetailsDTO userDTO) throws  DaoProfileException{
		
		PersonalProfileDTO personalProfileDTO = personalProfileDAO.getByUser(userDTO);
		if(personalProfileDTO != null){
			genericModel.setUserEmail(userDTO.getUsername());
			genericModel.setUserName(personalProfileDTO.getFirstName() + " "+ personalProfileDTO.getLastName());
		}
		
	}

	
}
