<!DOCTYPE HTML>

<#import "/spring.ftl" as spring />

<!--[if IE 6]><html class="ie6" lang="en"> <![endif]-->
<!--[if IE 7]><html class="ie7" lang="en"><![endif]-->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if gt IE 9]><!--> <html lang="en"> <!--<![endif]-->

	<#include "/public/common/html_head_ct.ftl">
	
	<body class="page_404">
	
		<#include "/public/common/header_ct.ftl">

		<div id="wrapper">
		
		  <header>
		    <h1>Verification Success!</h1>
		  </header>
		
		  <div id="content">
		
		    <p>
		    	Thanks for registering with us, please <a href="<@spring.url '/public/index.html'/>">login</a>. 
			</p>
		
		  </div><!-- end content -->
		
		</div>
		<!-- end wrapper -->

</body>
</html>
