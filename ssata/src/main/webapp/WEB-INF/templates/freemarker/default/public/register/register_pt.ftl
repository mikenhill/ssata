
<#assign security = JspTaglibs["http://www.springframework.org/security/tags"]/>
 
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
	
	<#include "/public/common/html_head_ct.ftl">

	<body class="register">
		
		<#include "/public/common/header_ct.ftl">		
		<div id="wrapper">
			
  			<header>
    			<h1>Create an account</h1>
  			</header>

  			 <div id="content">

             <div id="error-messages">

             <#if genericModel.errors??>
             	<#list genericModel.errors?keys as error >
             		<p>${genericModel.errors[error]}</p>
             	</#list>
             </#if></br>
             
			</div>
			
<style type="text/css">
#error-messages2{font-size:1.4em;font-family:'proxima-nova', sans-serif;margin-bottom:1.78571em;font-weight:600;color:#8B0000;position:relative}
</style>
			
			<div id="error-messages2">
			</div>

           <form id="registration-form" action="<@spring.url '/public/register.html'/>" method="post">
             <fieldset>
               <legend>Personal details</legend>

               <label for="registerNameFirst" class="visuallyhidden">First name</label>
               <input type="text" id="registerNameFirst" name="firstName" value="<#if form??>${form.firstName}</#if>"
                      class="input" placeholder="First Name *" />
                      
               <label for="registerNameLast" class="visuallyhidden">Last name</label>
               <input type="text" id="registerNameLast" name="lastName" value="<#if form??>${form.lastName}</#if>"
                      class="input" placeholder="Last Name *" />

               <label for="registerEmail" class="visuallyhidden">Email</label>
               <input type="email" id="registerEmail" name="userName" value="<#if form??>${form.userName}</#if>"
                      class="input" placeholder="Email *" />

               <label for="registerPasswordOne" class="visuallyhidden">*Password</label>
               <input type="password" id="registerPasswordOne" name="password" value="" class="input"
                      placeholder="Password *" />

               <label for="registerPasswordTwo" class="visuallyhidden">*Confirm Password</label>
               <input type="password" id="registerPasswordTwo" name="confirmPassword" value="" class="input"
                      placeholder="Confirm Password *" />
                      
				<label for="companyName" class="visuallyhidden">Name</label>
               	<input type="text" id="companyName" name="companyName"
                      value="<#if form?? && form.companyName??>${form.companyName}</#if>" class="input"
                      placeholder="Company Name *" />                      

				<input type="submit" name="registerSubmit" value="Register" class="submit-one"/>

             </fieldset>


           </form>

			</div><!-- end #content -->
			
		</div>
		<!-- end wrapper -->

   <#include "/public/common/footer_ct.ftl">

   <script>
   var timeoutAjaxFunction;
   var ajaxInProgress;
   
   $(function() {
	    $("#registerEmail").keyup(function() {    
	    	
	        if (timeoutAjaxFunction) {
	             clearTimeout(timeoutAjaxFunction);   
	        }	        
	        reloadAjax();
	    });
	});
   
   function reloadAjax() {	   
	   
	   if (!ajaxInProgress) {
		   //First clear any error messages
		   $( "#error-messages2" ).html('');
		   
		   if ( $("#registerEmail").val().length > 3) {
			   
			   //Declare the function tobe called if the timeout occurs
		   		timeoutAjaxFunction = setTimeout(function() {
		   			ajaxInProgress = true;
		   			var theUrl = "<@spring.url '/public/" +$( "#registerEmail ").val()+ "/check_email_duplicate.html' />" ;
		   			$.ajax({
                        type : "Get" ,
                        url: theUrl,                     
                        success : function (duplicateEmail) {
                        	
                        	if (duplicateEmail) {
                        		$( "#registerEmail" ).focus();
                        		$( "#error-messages2" ).html('<p>This email address is already in use</p>');                        	
                        	}
                        },
                        error : function (response) {
                        	alert( "error done: " + response );
                        }
		   			});

		   			
		   			//$( "#error-messages2" ).append('0');
		   			ajaxInProgress = false;
		   		}, 1000);
		   }
	   }	   
   }
   
</script>


	</body>
</html>
