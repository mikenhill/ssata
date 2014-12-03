/**
 * 
 */
package com.salmon.ssata.backend.service;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.common.TemplateComposerServiceImpl;

/**
 * @author mdani
 *
 */
@Service("GenericServiceImpl")
@Scope("session")
public class GenericServiceImpl implements GenericService, Serializable{

	private static final long serialVersionUID = 1L;

	transient private Logger logger = Logger.getLogger("ssata");
	
	//protected final Logger log = Logger.getLogger(this.getClass());
	
	private ResourceBundle serverNames = ResourceBundle.getBundle("servernames");
	
//	//@Value("${ssata.dev.url}")
//	private String siteUrl;
	
	private UserDetailsDTO userInSession;
	
	@Autowired
	transient private ApplicationContext applicationContext;
	
	@Autowired
	transient private TemplateComposerServiceImpl templateComposerServiceImpl;
	
	/*
	@Autowired(required = false)
	private UserService userService;
	
	@Autowired(required = false)
	private UserDetailsDAO userDetailsDAO;
	*/

	@Autowired
	transient private HttpServletRequest httpServletRequest; 
	
	public GenericServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	public UserDetailsDAO getUserDetailsDAO() {
		return userDetailsDAO;
	}

	public void setUserDetailsDAO(UserDetailsDAO userDetailsDAO) {
		this.userDetailsDAO = userDetailsDAO;
	}
	*/
	public UserDetailsDTO getUserInSession() {
		return userInSession;
	}

	public void setUserInSession(UserDetailsDTO userInSession) {
		this.userInSession = userInSession;
	}
	
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpRequest) {
		this.httpServletRequest = httpRequest;
	}
	
	public TemplateComposerServiceImpl getTemplateComposerServiceImpl() {
		return templateComposerServiceImpl;
	}

	public void setTemplateComposerServiceImpl(
			TemplateComposerServiceImpl templateComposerServiceImpl) {
		this.templateComposerServiceImpl = templateComposerServiceImpl;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Logger getLogger() {
		return logger;
	}
	
	/*
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	*/	
		
	public void init(){
		//genericServiceImpl.setSiteUrl(httpServletContext.getServerInfo() + httpServletContext.getContextPath());
		StringBuffer url = new StringBuffer();
		String hostName = "localhost";
		try{
			InetAddress ip = InetAddress.getLocalHost();

			System.out.println("@@@@@@ host name out  "+ ip.getHostName());
			
			if(serverNames.containsKey(ip.getHostName())){
				hostName = serverNames.getString(ip.getHostName());
			}

		}
		
		catch (Exception e){
			System.out.println("@@@@@@ Exception in Inet Address find........... "+e.getMessage());
			e.printStackTrace();

		}

		
		if(this.getHttpServletRequest().getServerName().equalsIgnoreCase("localhost")){
			url.append("http://localhost:")
			   .append(this.getHttpServletRequest().getServerPort());
		}else{
			url.append("http://")
				.append(hostName);
		}
		
		url.append(this.getHttpServletRequest().getContextPath() + "/");
		
		//url.append("https://www.dailybuild.ssata.co/" + this.getHttpServletRequest().getContextPath() + "/");
		
		//this.templateComposerServiceImpl.setSiteUrl(url.toString());
	}
}