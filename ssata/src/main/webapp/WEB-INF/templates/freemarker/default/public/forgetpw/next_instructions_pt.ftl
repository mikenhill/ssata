<!DOCTYPE HTML>

<#import "/spring.ftl" as spring />

	<#include "/public/common/html_head_ct.ftl">

	<body class="homepage">

		<div id="wrapper" class="clearfix">

			<div class="logo"></div>

			  		<div id="login" class="clearfix">

    				<h1>Password Link Sent</h1>

            <div class="login"><p><a href="<@spring.url '/public/index.html'/>">login</a></p></div>

				</div>


		</div><!-- end wrapper -->

		<#include "/public/login/register_ct.ftl">

    <#include "/public/common/footer_ct.ftl">

	</body>
</html>
