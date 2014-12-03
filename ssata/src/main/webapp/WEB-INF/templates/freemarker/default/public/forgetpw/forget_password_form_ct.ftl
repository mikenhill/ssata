					
		  <div id="password">
		
		    <h1>Lost Password</h1>
		
		    <form name="password" action="<@spring.url '/public/forgot.html'/>" method="post">
		      <fieldset>
		        <legend><span>If you have forgotten your password, you can use this form to reset your password. You will receive an
		          email with instructions.</span>
		        </legend>
		        <label for="passwordEmail" class="visuallyhidden">Email</label>
		        <input type="email" id="passwordEmail" name="email" value="" placeholder="Email" class="input"
		               autocorrect="off" autocapitalize="off" spellcheck="false" required/>
		        <input type="submit" name="forgotSubmit" value="Reset Password" class="submit-one"/>
		      </fieldset>
		    </form>
		
		    <div class="cancel-password"><p><a href="">Cancel</a></p></div>
		
		  </div>
		  
