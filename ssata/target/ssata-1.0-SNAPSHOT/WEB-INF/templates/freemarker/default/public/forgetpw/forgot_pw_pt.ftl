<!DOCTYPE HTML>

<#import "/spring.ftl" as spring />

	<#include "/public/common/html_head_ct.ftl">

	<body class="homepage">
		
		<div id="wrapper" class="clearfix">
		
			<div class="logo"></div>
			
			  				<div id="login" class="clearfix">
  								
    				<h1>Lost Password</h1>  								
  									
						<form name="password" action="<@spring.url '/public/forgot.html'/>" method="post">
					    	
					    	<fieldset>
					    		<legend><span>If you have forgotten your password, you can use this form to reset your password. You will receive an
		          					email with instructions.</span>
		        				</legend>
					    	
								<label for="email" class="visuallyhidden">Email</label>
								<input type="email" id="email" name="email" value="" 
										placeholder="Email" class="input" autocorrect="off" 
															autocapitalize="off" spellcheck="false" />
															        						
        						<input type="submit" name="forgotSubmit" value="Reset Password" class="submit-one"/>

					      	</fieldset>

    					</form>
				</div>					


		</div><!-- end wrapper -->
		
		<#include "/public/login/register_ct.ftl">
		<#include "/public/common/footer_ct.ftl">
  			
	</body>
</html>
