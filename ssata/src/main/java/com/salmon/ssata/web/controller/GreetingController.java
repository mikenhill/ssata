package com.salmon.ssata.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.salmon.ssata.domain.Greeting;
import com.salmon.ssata.domain.Color;
import com.salmon.ssata.web.form.GreetingForm;
import com.salmon.ssata.web.form.validators.GreetingsValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class GreetingController {

	@Autowired
	private GreetingsValidator greetingsValidator;
	
	protected static Logger logger = Logger.getLogger("GreetingController");

	// note there is no actual greetings.html file!!
	@RequestMapping(value = "/addgreeting.html", method = RequestMethod.GET)
	public ModelAndView showAddGreetingPage(
			@ModelAttribute("greetingform") GreetingForm greetingForm,
			Map<String, Object> model) {

		logger.info("entering showAddGreetingPage()");

		// color list is hardcoded for now
		List<Color> colorList = new ArrayList<Color>();
		colorList.add(new Color("Indian Red", "F75D59"));
		colorList.add(new Color("Red", "FF0000"));
		colorList.add(new Color("Salmon", "F9966B"));
		colorList.add(new Color("Lemon Chiffon", "FFF8C6"));
		colorList.add(new Color("Olive Green", "BCE954"));
		colorList.add(new Color("Steel Blue", "C6DEFF"));
		colorList.add(new Color("Medium Purple", "9E7BFF"));
		model.put("colorlist", colorList);

		ModelAndView modelAndView = new ModelAndView("addgreeting");
		modelAndView.addObject("greetingForm", "greetingForm");
		
		// resolves to /WEB-INF/jsp/addgreeting.jsp
		return modelAndView;
	}

	@RequestMapping(value = "/greetings.html", method = RequestMethod.POST)
	public String addGreetingAndShowAll(
			@ModelAttribute("greetingform") GreetingForm greetingForm,
			Map<String, Object> model, BindingResult validatedResult) {

		logger.info("entering addGreetingAndShowAll()");

		// Now validate the password		
		greetingsValidator.validate(greetingForm, validatedResult);

		if (validatedResult.hasErrors()) {
			model.put("greetingForm", greetingForm);
			return "addgreeting";
		} else {

			Greeting greeting = greetingForm.getGreeting();
			model.put("greeting", greeting);

			String selectedColorCode = greetingForm.getColor().getColorCode();
			if (selectedColorCode.equals("")) // if no color selected, then make
												// default white
				selectedColorCode = "FFFFFF";
			model.put("colorcode", selectedColorCode);

			// This will resolve to /WEB-INF/jsp/greetings.jsp
			return "greetings";
		}
	}

}