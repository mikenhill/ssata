package com.salmon.ssata.web.controller.html.annonymous.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.backend.service.profile.service.PersonalProfileService;
import com.salmon.ssata.web.controller.html.annonymous.GenericController;
import com.salmon.ssata.web.forms.profile.ForgotPwForm;

/**
 * @author mdani
 *
 */

@Controller()
@Scope("request")
@RequestMapping(value="/public")
public class ForgotPasswordController extends GenericController{
	
	private Logger logger = Logger.getLogger("salmon/ssata");
	
	@Autowired
	private PersonalProfileService personalProfileService;

	@RequestMapping(value = "/forgot.html", method = RequestMethod.GET)
	public ModelAndView forgot_pw_get(HttpServletRequest req, HttpServletResponse res) throws DaoProfileException, ServiceProfileException{
		
		String viewStr = "/public/forgetpw/forgot_pw_pt";
		String title = "com.salmon.ssata.register";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/forgot.html", method = RequestMethod.POST)
	public ModelAndView forgot_pw_post(HttpServletRequest req, HttpServletResponse res, 
			ForgotPwForm form, BindingResult validatedResult) throws DaoProfileException, ServiceProfileException{
		
		//This is required to determine and set things like global urls for email. 
		this.getGenericServiceImpl().init();
		
		String viewStr = "";
		String title = "com.salmon.ssata.register";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		
		if(personalProfileService.sendPasswordResetInstructions(form.getEmail())){
			title = "com.salmon.ssata.forgot.title";
			viewStr = "/public/forgetpw/next_instructions_pt";
		}else{
			title = "com.salmon.ssata.login.title";
			viewStr = "/public/login/login_pt";
		}
		
		modelAndView.setViewName(viewStr);
		modelAndView.addObject("genericModel", genericModel);
		return modelAndView;
	}
	
	@RequestMapping(value = "/change.html", method = RequestMethod.POST)
	public ModelAndView change_pw_post(HttpServletRequest req, HttpServletResponse res, 
			ForgotPwForm form, BindingResult validatedResult) throws DaoProfileException, ServiceProfileException{
		
		//This is required to determine and set things like global urls for email. 
		this.getGenericServiceImpl().init();
		
		String viewStr = "/public/changepw/change_password_pt";
		String title = "com.salmon.ssata.login.title";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		
		if(form.getPasswordOne().equals(form.getPasswordTwo())){
			if(getPersonalProfileService().updatePassword(form)){
				title = "com.salmon.ssata.forgot.title";
				viewStr = "/public/login/login_pt";
			}else{
				title = "com.salmon.ssata.login.title";
				viewStr = "/public/changepw/change_password_pt";
			}
		}
		
		modelAndView.setViewName(viewStr);
		modelAndView.addObject("genericModel", genericModel);
		return modelAndView;
	}
	
	@RequestMapping(value = "/{code}/{email}/change.html", method = RequestMethod.GET)
	public ModelAndView change_pw_get(@PathVariable String code,@PathVariable String email) 
			 							throws DaoProfileException, ServiceProfileException{
		
		String viewStr = "";
		String title = "com.salmon.ssata.login.title";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		
		if(getPersonalProfileService().isChangePwLinkValid(code, email)){
			title = "com.salmon.ssata.forgot.title";
			viewStr = "/public/changepw/change_password_pt";
		}else{
			title = "com.salmon.ssata.login.title";
			viewStr = "/public/forgetpw/forgot_pw_pt";
		}
		
		modelAndView.setViewName(viewStr);
		modelAndView.addObject("genericModel", genericModel);
		modelAndView.addObject("email",email);
		
		return modelAndView;
	}
	
	public PersonalProfileService getPersonalProfileService() {
		return personalProfileService;
	}

	public void setPersonalProfileService(
			PersonalProfileService personalProfileService) {
		this.personalProfileService = personalProfileService;
	}
	
}