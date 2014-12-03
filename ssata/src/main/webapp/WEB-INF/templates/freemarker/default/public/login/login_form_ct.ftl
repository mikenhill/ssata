					
  				<div id="login" class="clearfix">
  						 		
    				<h1>Log in</h1>  								
  									
					<#assign security = JspTaglibs["http://www.springframework.org/security/tags"]/>
	    			<@security.authorize ifNotGranted="ROLE_USER">
						<form name="login" action="<@spring.url '/j_spring_security_check'/>" method="post">
					    	
					    	<fieldset>
					    	
								<label for="j_username" class="visuallyhidden">Email</label>
								<input type="email" id="j_username" name="j_username" value="" 
										placeholder="Email" class="input" autocorrect="off" 
															autocapitalize="off" spellcheck="false" required />
        						
        						<label for="j_password" class="visuallyhidden">Password</label>
								<input type="password" id="j_password" name="j_password" value="" 
										placeholder="Password" class="input" autocorrect="off" 
															autocapitalize="off" spellcheck="false" required/>
															        						
        						<input type="submit" name="loginSubmit" value="Log In" class="submit-one"/>

					      	</fieldset>

    					</form>
					</@security.authorize>
					
					<div class="forget-password"><p><a href="<@spring.url '/public/forgot.html'/>">Forgotten password?</a></p></div>
				</div>					
