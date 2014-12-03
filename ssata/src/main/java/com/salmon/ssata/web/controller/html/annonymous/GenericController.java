/**
 * 
 */
package com.salmon.ssata.web.controller.html.annonymous;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.salmon.ssata.backend.service.GenericServiceImpl;
import com.salmon.ssata.backend.service.common.BootstrapUserServiceImpl;
import com.salmon.ssata.web.models.GenericModel;

/**
 * @author mdani
 *
 */
@Controller
@Scope("request")
public abstract class GenericController{
	
	private Logger logger = Logger.getLogger("salmon/ssata");
	
	private Locale locale = Locale.getDefault();
	
	private ResourceBundle screenCopiesBundle = ResourceBundle.getBundle("screenCopyValues",Locale.getDefault());
	
	@Autowired
	protected ApplicationContext applicationContext;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired(required=false)
	public GenericModel genericModel;
	
	@Autowired
	@Qualifier("GenericServiceImpl")
	protected GenericServiceImpl genericServiceImpl;
	
	public GenericServiceImpl getGenericServiceImpl() {
		return genericServiceImpl;
	}

	@Autowired
	protected BootstrapUserServiceImpl bootstrapUserServiceImpl;
	
	public BootstrapUserServiceImpl getBootstrapUserServiceImpl() {
		return bootstrapUserServiceImpl;
	}

	public void setBootstrapUserServiceImpl(
			BootstrapUserServiceImpl bootstrapUserServiceImpl) {
		this.bootstrapUserServiceImpl = bootstrapUserServiceImpl;
	}

	public void setGenericServiceImpl(GenericServiceImpl genericServiceImpl) {
		this.genericServiceImpl = genericServiceImpl;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * 
	 */
	public GenericController() {
		
	}
	
	/**
	 * 
	 * @param bindingResult
	 * @return
	 * 
	 * Possible codes:
	 * E.g @NotEmpty - private string name; - object name - form:
	 * 		NotBlank.form.name
	 *		NotBlank.name
	 *		NotBlank.java.lang.String
	 *		NotBlank
	 */
	public Map<String, String> getErrors(BindingResult bindingResult) {
		getLogger().info(new Date());
		Map<String, String> errors = new HashMap<String, String>();
		if(bindingResult.hasErrors()) {
			for(FieldError fieldError: bindingResult.getFieldErrors()) {
				String errorMessage = null;
				
				try{
				if((errorMessage = messageSource.getMessage(fieldError.getDefaultMessage(), null, locale))== null)
					errorMessage = fieldError.getDefaultMessage();
				}
				catch (NoSuchMessageException nsme){
					errorMessage = fieldError.getDefaultMessage();
				}
				errors.put(fieldError.getField(), errorMessage);
			}
		}
		getLogger().info(new Date());
		return errors;
	}
		
	public Logger getLogger() {
		return logger;
	}

	public ResourceBundle getScreenCopiesBundle() {
		return screenCopiesBundle;
	}
	
	public String getScreenValue(String key){
		return screenCopiesBundle.getObject(key).toString();
	}
}
