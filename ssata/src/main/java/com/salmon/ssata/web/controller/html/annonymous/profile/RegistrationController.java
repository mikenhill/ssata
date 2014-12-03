package com.salmon.ssata.web.controller.html.annonymous.profile;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.ServiceException;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.backend.service.profile.service.RegistrationService;
import com.salmon.ssata.common.SsataConstants;
import com.salmon.ssata.web.controller.html.annonymous.GenericController;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.profile.ProfileModel;
import com.salmon.ssata.web.models.validators.EmailValidator;
import com.salmon.ssata.web.models.validators.PasswordValidator;

/**
 * @author mdani
 *
 */

@Controller()
@Scope("request")
@RequestMapping(value="/public")
public class RegistrationController extends GenericController{
	
	@Autowired
	SmartValidator validator;
	
	@Autowired
	private RegistrationService registrationServiceImpl;

	private Logger logger = Logger.getLogger("salmon/ssata");

	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public ModelAndView register_get(HttpServletRequest req, HttpServletResponse res) throws DaoProfileException, ServiceProfileException{
		
		String viewStr = "/public/register/register_pt";
		String title = "com.salmon.ssata.register";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		genericModel.setErrors(null);
		
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}

	@RequestMapping(value = "/register/{code}/{email}/verify.html", method = RequestMethod.GET)
	public ModelAndView verify_get(@PathVariable String code,@PathVariable String email) 
																	throws ServiceException{
		
		String viewStr = "";
		boolean verifyFlag = false;
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		
		String title = "com.salmon.ssata.verify.email";
		
		if(!code.equalsIgnoreCase("") || !email.equalsIgnoreCase("")){
			
			verifyFlag = this.registrationServiceImpl.changeVerificationStatus(email,code);
			
			if(verifyFlag == true){
				viewStr = "/public/register/verification_success";
				title = "com.salmon.ssata.verification.success";
			}else{
				viewStr = "/public/register/registration_success";
				title = "com.salmon.ssata.register.success";
				throw new ServiceException("com.salmon.ssata.service.registration.incompleteVerification");
			}
			
		}else{
			viewStr = "/public/register/registration_success";
			title = "com.salmon.ssata.register.success";
			throw new ServiceException("com.salmon.ssata.service.registration.cantVefiry");
		}
	
		modelAndView.setViewName(viewStr);
		
		genericModel.setTitle(super.getScreenValue(title));
		
		modelAndView.addObject("genericModel", genericModel);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public ModelAndView register_post(HttpServletRequest req, HttpServletResponse res,
			RegistrationForm form, BindingResult validatedResult) throws DaoProfileException, ServiceProfileException, Exception{
		
		
		
		//This is required to determine and set things like global urls for email. 
		this.getGenericServiceImpl().init();
		
		String viewStr = "/public/register/register_pt";
		String title = "com.salmon.ssata.register";
		ModelAndView modelAndView =null;
		ProfileModel profileModel;
		
		try{
			
			//Commence with basic level validation
			validator.validate(form, validatedResult, RegistrationForm.BasicCheck.class);	
			
			
			if (!validatedResult.hasErrors()) {
				//Now validate the email to see if already in use - but only if changed
				EmailValidator emailValidator = new EmailValidator(this.getBootstrapUserServiceImpl());
				emailValidator.validate(form, validatedResult);
				PasswordValidator passwordValidator = new PasswordValidator();
				passwordValidator.validate(form, validatedResult);
				
				//Only allow salmon.com emails
				
				
			}			
			
			
			//Check for any errors	
			if(validatedResult.hasErrors()){
				modelAndView = new ModelAndView();				
				modelAndView.setViewName(viewStr);
				genericModel.setTitle(super.getScreenValue(title));
				genericModel.setErrors(getErrors(validatedResult));
				modelAndView.addObject("genericModel", genericModel);
				modelAndView.addObject("form",form);				
				return modelAndView;
			} else {
				profileModel = this.registrationServiceImpl.registerUser(form); 
				modelAndView = new ModelAndView();
				title = "com.salmon.ssata.register.success";
				genericModel.setTitle(super.getScreenValue(title));
				modelAndView.addObject("profileModel", profileModel);
				
				viewStr = "/public/register/registration_success";
				modelAndView.addObject("genericModel", genericModel);
				modelAndView.setViewName(viewStr);
			}
			
		}catch(DaoProfileException dpe){
			throw dpe;
		}
		catch(ServiceProfileException spe){
			throw spe;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	
		return modelAndView;
	}
	
	@RequestMapping(value = "/{registerEmail}/check_email_duplicate.html", method = RequestMethod.GET)
	public @ResponseBody String ajaxCheckDuplicateEmail(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable String registerEmail) throws ServiceException, Exception{

		String responseCode = "";
		UserDetailsDTO user = null;
		user = (UserDetailsDTO) this.getBootstrapUserServiceImpl().loadUserByUsername(registerEmail);
		if (user != null) {
			responseCode = SsataConstants.DUPLICATE_EMAIL;
		}
		
		return responseCode;		
	}
	
	
	
	@RequestMapping("/failure_register.html")
	public ModelAndView faliure_get(HttpServletRequest req, HttpServletResponse res){

		String viewStr = "/public/failure_register";
		
		ModelAndView modelAndView = new ModelAndView(viewStr);
		modelAndView.addObject("message", "Could not create user!");
		genericModel.setTitle(super.getScreenValue("com.salmon.ssata.register.failure"));
		
		modelAndView.addObject("genericModel", genericModel);

		
		return modelAndView;
	}
	
	public RegistrationService getRegistrationService() {
		return registrationServiceImpl;
	}

	public void setRegistrationService(RegistrationService registrationServiceImpl) {
		this.registrationServiceImpl = registrationServiceImpl;
	}
		
}