package com.salmon.ssata.backend.service.common;

import java.io.StringWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.salmon.ssata.backend.service.common.email.SMTPService;

import freemarker.template.Template;

@Service
//@Scope("session")
public class TemplateComposerServiceImpl {

	@Autowired
	private FreeMarkerConfigurer freemarkerConfigurer;
	
	@Autowired
	private SMTPService smtpService;
	
	private String siteUrl;
	
	public void sendPwChangedMail(Map<String,Object>dataModel){
		
		String[] templateString = 
						getTemplate(dataModel, "", "/public/email/pw_changed_et.ftl");
		
		smtpService.getSmtpPropertyImpl().setSMTPProerty(dataModel.get("email").toString(), 
									"Change Password Request", templateString[0], templateString[1]);
		
		smtpService.createMail(smtpService.getSmtpPropertyImpl());
	}	

	public void sendPwResetMail(Map<String,Object>dataModel){
		
		String[] templateString = 
						getTemplate(dataModel, "", "/public/email/pw_reset_request_et.ftl");
		
		smtpService.getSmtpPropertyImpl().setSMTPProerty(dataModel.get("email").toString(), 
									"Change Password Request", templateString[0], templateString[1]);
		
		smtpService.createMail(smtpService.getSmtpPropertyImpl());

	}	
	
	public String[] getTemplate(Map<String, Object> dataModel, String textTemp, String htmlTemp){
		
		String[] textWritter = new String[2];
		
		try {

			dataModel.put("url", this.getSiteUrl());
			
			Template textTemplate = this.freemarkerConfigurer
								.getConfiguration().getTemplate(textTemp);
			final StringWriter textWriter = new StringWriter();
			textTemplate.process(dataModel, textWriter);

			Template htmlTemplate = this.freemarkerConfigurer
							.getConfiguration().getTemplate(htmlTemp);
			
			final StringWriter htmlWriter = new StringWriter();
			htmlTemplate.process(dataModel, htmlWriter);
			textWritter[0] = textWriter.toString();
			textWritter[1] = htmlWriter.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textWritter;
	}
	
	public FreeMarkerConfigurer getFreemarkerConfigurer() {
		return freemarkerConfigurer;
	}

	public void setFreemarkerConfigurer(FreeMarkerConfigurer freemarkerConfigurer) {
		this.freemarkerConfigurer = freemarkerConfigurer;
	}
	
	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	
	public SMTPService getSmtpService() {
		return smtpService;
	}

	public void setSmtpService(SMTPService smtpService) {
		this.smtpService = smtpService;
	}
	
}
