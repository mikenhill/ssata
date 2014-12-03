package com.salmon.ssata.backend.dao.profile.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salmon.ssata.backend.dao.GenericDAOImpl;
import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dao.profile.service.UserDetailsDAO;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.common.ErrorMessages;

/**
 * @author mdani
 *
 */

@Repository
public class UserDetailsDAOImpl extends GenericDAOImpl 
									implements UserDetailsDAO{

	@Autowired
	public UserDetailsDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public UserDetailsDTO getByUserName(String username) throws DaoProfileException {

		UserDetailsDTO userDetailsDTO = null;
		
		try{
			
			@SuppressWarnings("unchecked")
			List<UserDetailsDTO> userDTOList = 
					(List<UserDetailsDTO>) this.getHibernateTemplate()
					.find("from UserDetailsDTO as user where user.username = ?", username);
	
			if (userDTOList.size() > 0) {
				userDetailsDTO = userDTOList.get(0);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_USER_DETAILS_ERROR_USERNAME);
		}
		
		return userDetailsDTO;
	}
	
	@Override
	public UserDetailsDTO getById(String id) throws DaoProfileException {
		
		UserDetailsDTO userDetailsDTO = null;
		
		try{
			
			userDetailsDTO = this.getHibernateTemplate().get(UserDetailsDTO.class, id);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_USER_DETAILS_ERROR_USERID);
		}
		
		return userDetailsDTO;
	}
	
	@Override
	public void create(UserDetailsDTO userDetailsDTO) throws DaoProfileException{
		
		try{
			
			super.create(userDetailsDTO);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_USER_DETAILS_CANT_CREATE);
		}
		
	}

	@Override
	public void update(UserDetailsDTO userDetailsDTO) throws DaoProfileException{
		
		try{
			
			super.update(userDetailsDTO);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_USER_DETAILS_CANT_CREATE);
		}
		
	}

	@Override
	public List<UserDetailsDTO> getUserByRole(String role) throws DaoProfileException {
		
		try{
			
			Criteria userCriteria = super.getCriteria(UserDetailsDTO.class);
			userCriteria.createCriteria("roles").add(Restrictions.eq("authority", role));
			
			@SuppressWarnings("unchecked")
			List<UserDetailsDTO> userDTOList = 	(List<UserDetailsDTO>) userCriteria.list();

			return userDTOList;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_USER_DETAILS_ERROR_USERNAME);
		}

	}
	
	
}
