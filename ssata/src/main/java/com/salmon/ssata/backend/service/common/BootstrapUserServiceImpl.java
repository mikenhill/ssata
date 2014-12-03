package com.salmon.ssata.backend.service.common;

/**
 * @author mdani
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salmon.ssata.backend.dao.profile.impl.UserDetailsDAOImpl;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;

@Service("BootstrapUserServiceImpl")
public class BootstrapUserServiceImpl implements 
				UserDetailsService, ApplicationContextAware{
	
	@Autowired
	private UserDetailsDAOImpl userDetailsDAO;

	@Autowired
	private ApplicationContext	applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public UserDetailsDAOImpl getUserDetailsDAO() {
		return userDetailsDAO;
	}

	public void setUserDetailsDAO(UserDetailsDAOImpl userDetailsDAO) {
		this.userDetailsDAO = userDetailsDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		UserDetailsDTO userDTO = null;
		
		try{
			
			userDTO = userDetailsDAO.getByUserName(userName.toLowerCase().trim());
			
		}catch(Exception e){
			throw new UsernameNotFoundException("Username or Password Incorrect!");
		}
		
		return userDTO;
	}

}
