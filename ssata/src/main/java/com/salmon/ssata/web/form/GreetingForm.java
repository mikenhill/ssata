package com.salmon.ssata.web.form;

import com.salmon.ssata.domain.Color;
import com.salmon.ssata.domain.Greeting;

public class GreetingForm {
	//the domain model persistent data
		private Greeting greeting;
	 
		//the other non persistent data ... used in user interface only
		private Color color;
	 
		public Greeting getGreeting() {
			return greeting;
		}
	 
		public void setGreeting(Greeting greeting) {
			this.greeting = greeting;
		}
	 
		public Color getColor() {
			return color;
		}
	 
		public void setColor(Color color) {
			this.color = color;
		}
}
