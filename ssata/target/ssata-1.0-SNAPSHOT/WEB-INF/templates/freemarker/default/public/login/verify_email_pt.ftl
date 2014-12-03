<!DOCTYPE HTML>

<#import "/spring.ftl" as spring />

<!--[if IE 6]><html class="ie6" lang="en"> <![endif]-->
<!--[if IE 7]><html class="ie7" lang="en"><![endif]-->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if gt IE 9]><!--> <html lang="en"> <!--<![endif]-->

	<#include "/private/common/html_head_ct.ftl">
	
	<body class="page_404">
		
		<#include "/public/common/header_ct.ftl">
		
		<div id="wrapper">
		
		  <header>
		    <h1>One more step!</h1>
		  </header>
		
		  <div id="content">
		
		    <p>
	    	   Please verify your email address and then <a href="<@spring.url '/public/index.html'/>">login</a>.
			</p>
		
		  </div><!-- end content -->
		
		</div>
		<!-- end wrapper -->
<#include "/public/common/footer_ct.ftl">
</body>
</html>
