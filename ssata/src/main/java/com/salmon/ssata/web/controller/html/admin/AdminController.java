package com.salmon.ssata.web.controller.html.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.web.controller.html.annonymous.GenericController;

@Controller()
@Scope("request")
@RequestMapping(value="/admin")
public class AdminController extends GenericController{
	
	static Logger logger = Logger.getLogger(AdminController.class.getName());
	
	@Autowired
	private com.salmon.ssata.backend.service.profile.impl.UserServiceImpl userService;
	
	@RequestMapping(value = "/testtype/maintain_test_type", method = RequestMethod.GET)
	public ModelAndView admin_test_type(HttpServletRequest req, HttpServletResponse res) throws DaoProfileException, ServiceProfileException{
		
		logger.info("Enterring: admin_test_type");
		
		if(this.userService.isAnonymousType() == false){
			
		}else{
			throw new ServiceProfileException(com.salmon.ssata.common.ErrorMessages.SERIVCE_PERSONAL_PROFILE_ERROR_USEROBJ);
		}
		
		String viewStr = "/admin/testtype/maintain_test_type";
		String title = "com.salmon.ssata.admin.testtype";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		genericModel.setErrors(null);
		
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}

	@RequestMapping(value = "/admin_home", method = RequestMethod.GET)
	public ModelAndView admin_home(HttpServletRequest req, HttpServletResponse res) throws DaoProfileException, ServiceProfileException{
		
		String viewStr = "/admin/admin_home";
		String title = "com.salmon.ssata.admin.home";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		genericModel.setErrors(null);
		
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}	
	
}
