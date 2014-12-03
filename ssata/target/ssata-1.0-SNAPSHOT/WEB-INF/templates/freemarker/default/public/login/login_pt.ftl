<!DOCTYPE HTML>

<#import "/spring.ftl" as spring />

	<#include "/public/common/html_head_ct.ftl">

	<body class="homepage">
		
		<div id="wrapper" class="clearfix">
		
			<div class="logo"></div>
			
			<#include "/public/login/login_form_ct.ftl">
			
			<#include "/public/forgetpw/forget_password_form_ct.ftl">
			
			<#include "/public/forgetpw/next_instructions_ct.ftl">
			

		</div><!-- end wrapper -->
		
		<#include "/public/login/register_ct.ftl">
  			<#include "/public/common/footer_ct.ftl">
	</body>
</html>
