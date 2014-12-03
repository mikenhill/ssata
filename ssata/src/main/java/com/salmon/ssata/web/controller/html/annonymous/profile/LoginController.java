package com.salmon.ssata.web.controller.html.annonymous.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salmon.ssata.web.controller.html.annonymous.GenericController;
import com.salmon.ssata.web.models.GenericModel;

@Controller()
@Scope("request")
@RequestMapping(value="/public")
public class LoginController extends GenericController{

	
	@RequestMapping("/login.html")
	public ModelAndView login_get(HttpServletRequest req, HttpServletResponse res) 
									throws Exception{
		
		String viewStr = "/public/login/login_pt";
		
		
		genericModel.setTitle(super.getScreenValue("com.salmon.ssata.login.title"));
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}
	
	@RequestMapping("/logout.html")
	public ModelAndView logout_get(HttpServletRequest req, HttpServletResponse res) 
										throws Exception{
		
		String viewStr = "redirect:/j_spring_security_logout";
		
		genericModel.setTitle(super.getScreenValue("com.salmon.ssata.login.title"));
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		return modelAndView;
	}
	
	@RequestMapping("/failure_login.html")
	public ModelAndView faliure_get(HttpServletRequest req, HttpServletResponse res){

		String viewStr = "/public/failure_login";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue("com.salmon.ssata.login.title"));
		
		modelAndView.addObject("message","Username Or Password is Incorrect!");
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}
	
	@RequestMapping("/failure_role.html")
	public ModelAndView faliure_role_get(HttpServletRequest req, HttpServletResponse res){

		String viewStr = "/public/failure_role";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue("com.salmon.ssata.failure.role"));
		
		//modelAndView.addObject("message","Username Or Password is Incorrect!");
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}	
	
	
}