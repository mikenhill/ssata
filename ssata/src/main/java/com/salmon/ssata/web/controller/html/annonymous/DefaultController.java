package com.salmon.ssata.web.controller.html.annonymous;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.backend.service.profile.impl.UserServiceImpl;
import com.salmon.ssata.backend.service.profile.service.PersonalProfileService;
import com.salmon.ssata.common.SsataConstants;
import com.salmon.ssata.web.controller.html.admin.AdminController;
import com.salmon.ssata.web.models.profile.ProfileModel;

/**
 * @author mhill
 *
 */

@Controller
@Scope("request")
@RequestMapping("/public")
public class DefaultController extends GenericController{
	
	static Logger logger = Logger.getLogger(DefaultController.class.getName());
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private PersonalProfileService personalProfileService; 
	
	
	@RequestMapping(value = "/return_html.html", method = RequestMethod.GET)
	public ModelAndView blank_get(HttpServletRequest req, HttpServletResponse res) throws DaoProfileException, ServiceProfileException{
		
		String viewStr = "/public/blank";
		ModelAndView modelAndView = new ModelAndView(viewStr);
		return modelAndView;
	}	
	
	@RequestMapping("/index.html")
	public ModelAndView roleCheck(HttpServletRequest req, HttpServletResponse res) throws DaoProfileException, ServiceProfileException {
		String viewStr = "";
		ModelAndView modelAndView = new ModelAndView();;
		ProfileModel profileModel = null;
		String title = "com.salmon.ssata.login.title";
		UserDetailsDTO user = null;

		logger.info("Enterring: roleCheck");
		
		if(this.userService.isUserInRole(SsataConstants.ROLE_ADMIN) || 
							this.userService.isUserInRole(SsataConstants.ROLE_CANDIDATE) || 
									this.userService.isUserInRole(SsataConstants.ROLE_EMPLOYEE)){
			
			if(this.userService.isAnonymousType() == false){
				
				user = this.userService.getUserInSession();
				if(user.isEmailConfirmed()){
					
					profileModel = this.personalProfileService.getProfileByUser(user);

					modelAndView.addObject("profileModel", profileModel);
					//TODO: Shared Data Bug
					genericModel.setEmailConfirmed(true);
					genericModel.setPersonalProfileId(profileModel.getPersonalProfileId());
					genericModel.setCompanyProfileId(profileModel.getCompanyProfileId());
					this.userService.setProfileInGenericModel(genericModel, user);
					title = "com.salmon.ssata.recent.activity";
					
				}else{
					
					SecurityContextHolder.clearContext();
					HttpSession session = req.getSession();
					
					if(session != null){
						user = null;
						session.invalidate();
					}
					
					viewStr = "/public/login/verify_email_pt";
					title = "com.salmon.ssata.verify.email";
					modelAndView.setViewName(viewStr);					
					return modelAndView;
				}
			}
			
			if(this.userService.isUserInRole(SsataConstants.ROLE_ADMIN)){
                genericModel.setRole(SsataConstants.ROLE_ADMIN);
                viewStr = "/admin/admin_home";
                
			}else if(this.userService.isUserInRole(SsataConstants.ROLE_EMPLOYEE)){
                genericModel.setRole(SsataConstants.ROLE_EMPLOYEE);
                viewStr = "/empl/employee_home";
				
			}else if(this.userService.isUserInRole(SsataConstants.ROLE_CANDIDATE)){
                genericModel.setRole(SsataConstants.ROLE_CANDIDATE);
                viewStr = "/cand/candidate_home";			
				
			}else{
				viewStr = "";
			}

			
		}else if(this.userService.isAnonymousType() == true){
				
			viewStr = "/public/login/login_pt";
				
		}else{
			
			modelAndView.addObject("message","User not in role!");
			viewStr = "redirect:/public/failure_role.html";
			
			title = "com.salmon.ssata.failure.role";
			
		}
		
		genericModel.setTitle(super.getScreenValue(title));
		modelAndView.addObject("genericModel", genericModel);			
		
		modelAndView.setViewName(viewStr);
		
		return modelAndView;
	}
	
	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	public PersonalProfileService getPersonalProfileService() {
		return personalProfileService;
	}

	public void setPersonalProfileService(
			PersonalProfileService personalProfileService) {
		this.personalProfileService = personalProfileService;
	}
	
}
