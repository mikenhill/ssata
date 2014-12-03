/**
 * 
 */
package com.salmon.ssata.backend.dto.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.salmon.ssata.backend.dto.GenericDTO;

/**
 * @author mdani
 *
 */

@Entity
@Table(name="user_details")
public class UserDetailsDTO extends GenericDTO implements UserDetails, Serializable {

	private static final long serialVersionUID 		= 1L;
	
	private String	username						= "";
	private String	password						= "";
	private String	salt							= null;
	private boolean	accountNonExpired				= true;
	private boolean	accountNonLocked				= true;
	private boolean	credentialsNonExpired			= true;
	private boolean enabled							= true;
	private Set<AuthorityDTO> roles					= new HashSet<AuthorityDTO>();
	private	String	primaryEmail					= "";

	private	boolean	emailConfirmed					= false;
	private Date	lastLogin						= null;
	private	String	lastIP							= null;
	private String  emailVeriCode					= "";
	private String  pwChangeReqCode					= "";
	
	@Column(unique = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		username = username.trim().toLowerCase();
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		password = password.trim();
		this.password = password;
	}
	
	public String getSalt() {
		if (this.salt == null){
			this.setSalt(UUID.randomUUID().toString());
		}
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	public Set<AuthorityDTO> getRoles() {
		return roles;
	}
	public void setRoles(Set<AuthorityDTO> roles) {
		this.roles = roles;
	}
	
	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (AuthorityDTO role : this.getRoles()){
			authorities.add(role);
		}
		return authorities;
	}
	
	public String getPwChangeReqCode() {
		return pwChangeReqCode;
	}
	public void setPwChangeReqCode(String pwChangeReqCode) {
		this.pwChangeReqCode = pwChangeReqCode;
	}
	
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getEmailVeriCode() {
		return emailVeriCode;
	}
	public void setEmailVeriCode(String emailVeriCode) {
		this.emailVeriCode = emailVeriCode;
	}
	
	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}
	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	
}
