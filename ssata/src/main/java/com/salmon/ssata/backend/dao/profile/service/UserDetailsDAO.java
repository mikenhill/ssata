package com.salmon.ssata.backend.dao.profile.service;

import java.util.List;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;

public interface UserDetailsDAO {

	public UserDetailsDTO getByUserName(String username) throws DaoProfileException;
	public UserDetailsDTO getById(String id) throws DaoProfileException;
	public void create(UserDetailsDTO userDetailsDTO) throws DaoProfileException;
	public void update(UserDetailsDTO userDetailsDTO) throws DaoProfileException;
	public List<UserDetailsDTO> getUserByRole(String role)  throws DaoProfileException;	
}
