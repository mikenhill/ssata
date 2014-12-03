<#assign genericModelFlag = false>

<#if genericModel?has_content>

	<#assign genericModelFlag = true>
	<#assign model = genericModel>

</#if>

  <!--[if IE 6]><html class="ie6" lang="en"> <![endif]-->
  <!--[if IE 7]><html class="ie7" lang="en"><![endif]-->
  <!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
  <!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
  <!--[if gt IE 9]><!-->
  <html lang="en"> <!--<![endif]-->


  <head>
	
  	<#if genericModelFlag>
  		<title>${model.title}</title>
  	<#else>
  		<title>Spring Salmon Aptitude Test Application</title>
  	</#if>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">

    <script type="text/javascript" src="//use.typekit.net/oce2izg.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

    <!--[if lt IE 9]>
    <script src="<@spring.url '/prod-imports/scripts-dev/html5shiv.min.js'/>"></script>
    <![endif]-->

    <link rel="stylesheet" href="<@spring.url '/prod-imports/resources/css/main.css'/>"/>

  </head>