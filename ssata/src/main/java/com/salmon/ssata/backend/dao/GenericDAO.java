/**
 * 
 */
package com.salmon.ssata.backend.dao;

import com.salmon.ssata.backend.dto.GenericDTO;

/**
 * @author mhill
 *
 */
public interface GenericDAO {

	public void create(GenericDTO dto);
	public void update(GenericDTO dto);
    public void updateAll(String query);
	public void delete(GenericDTO dto);
}
