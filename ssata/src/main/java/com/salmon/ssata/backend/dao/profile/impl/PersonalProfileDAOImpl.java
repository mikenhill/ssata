package com.salmon.ssata.backend.dao.profile.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salmon.ssata.backend.dao.GenericDAOImpl;
import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dao.profile.service.CompanyProfileDAO;
import com.salmon.ssata.backend.dao.profile.service.PersonalProfileDAO;
import com.salmon.ssata.backend.dto.profile.CompanyProfileDTO;
import com.salmon.ssata.backend.dto.profile.PersonalProfileDTO;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.common.ErrorMessages;

/**
 * @author mdani
 *
 */

@Repository
public class PersonalProfileDAOImpl extends GenericDAOImpl 
									implements PersonalProfileDAO{

	@Autowired
	CompanyProfileDAO companyProfileDAO;
	
	@Autowired
	public PersonalProfileDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void createProfile(PersonalProfileDTO profileDTO) throws DaoProfileException{
		
		try{
			
			if(profileDTO != null){
				
				super.create(profileDTO);

			}else{
				throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_CANT_PERSIST);
			}
			
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_CANT_CREATE);
		}
		
	}
	
	@Override
	public void updateProfile(PersonalProfileDTO profileDTO) throws DaoProfileException{
		
		try{
			
			if(profileDTO != null){
				
				super.update(profileDTO);

			}else{
				throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_CANT_PERSIST);
			}
			
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_CANT_CREATE);
		}
		
	}
	
	
	@Override
	public PersonalProfileDTO getByUsername(String userName) throws DaoProfileException {
		// TODO Auto-generated method stub
		
		PersonalProfileDTO profileDTO = null;
		UserDetailsDTO userDTO = null;
		
		try{

			@SuppressWarnings("unchecked")
			List<UserDetailsDTO> userDTOList = 
					(List<UserDetailsDTO>) this.getHibernateTemplate()
					.find("from UserDetailsDTO as user where user.username = ?", userName);

			if (userDTOList.size() > 0) {
				userDTO = userDTOList.get(0);
				
				@SuppressWarnings("unchecked")
				List<PersonalProfileDTO> profileDTOList = 
						(List<PersonalProfileDTO>) this.getHibernateTemplate()
						.find("from PersonalProfileDTO as profile where profile.user = ?", userDTO);
		
				if (profileDTOList.size() > 0) {
					profileDTO = profileDTOList.get(0);
					return profileDTO;
				}else{
					throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_USERNAME_DATA_NOT_FOUND);
				}
				
				
			}else{
				throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_USERNAME_USERDATA_NOT_FOUND);
			}

		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_ERROR_USERNAME);
		}

	}

	@Override
	public PersonalProfileDTO getByUser(UserDetailsDTO user) throws DaoProfileException {
		// TODO Auto-generated method stub
		PersonalProfileDTO profileDTO = null;
		
		try{

			@SuppressWarnings("unchecked")
			List<PersonalProfileDTO> profileDTOList = 
				(List<PersonalProfileDTO>) this.getHibernateTemplate()
						.find("from PersonalProfileDTO as profile where profile.user = ?", user);
		
			if (profileDTOList.size() > 0) {
				profileDTO = profileDTOList.get(0);
				return profileDTO;
			}else{
				throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_USEROBJ_NOT_FOUND);
			}
				
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_ERROR_USEROBJ);
		}
	}

	@Override
	public PersonalProfileDTO getById(String id) throws DaoProfileException {
		// TODO Auto-generated method stub
		PersonalProfileDTO profileDTO = null;
		
		try{

			@SuppressWarnings("unchecked")
			List<PersonalProfileDTO> profileDTOList = 
				(List<PersonalProfileDTO>) this.getHibernateTemplate()
						.find("from PersonalProfileDTO as profile where profile.id = ?", id);
		
			if (profileDTOList.size() > 0) {
				profileDTO = profileDTOList.get(0);
				return profileDTO;
			}else{
				throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_USEROBJ_NOT_FOUND);
			}
				
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_ERROR_USEROBJ);
		}
	}

	@Override
	public List<PersonalProfileDTO> getProfilesByCompnayId(String companyProfileId) throws DaoProfileException {
		try{
			
			CompanyProfileDTO company = companyProfileDAO.getById(companyProfileId);

			@SuppressWarnings("unchecked")
			List<PersonalProfileDTO> profileDTOList = 
				(List<PersonalProfileDTO>) this.getHibernateTemplate()
						.find("from PersonalProfileDTO as profile where profile.company = ?",company );
		
			if (profileDTOList.size() > 0) {
				return profileDTOList;
			}else{
				throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_ERROR_COMPANY_ID);
			}
				
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_PERSONAL_PROFILE_ERROR_COMPANY_ID);
		}

	}
	
	
}
