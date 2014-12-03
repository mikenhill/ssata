package com.salmon.ssata.backend.dto.profile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author mdani
 *
 */

@Entity
@Table(name="authorities")
public class AuthorityDTO implements GrantedAuthority,Serializable {

	private static final long serialVersionUID = 1L;
	
	private String	authority					= "";
	private String	description					= "";
	
	@Id
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
