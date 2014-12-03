<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 
<html>
 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<title>Spring Greetings</title>
</head>
 
<body>
 
<h1>Spring Greetings</h1>
<form:form action="greetings.html" modelAttribute="greetingform"> 	
	<form:errors path="*" />
	<table>
		<tr>
			<td>Add your greeting:</td>
                        <td><form:input path="greeting.greetingText" size="100"/></td>
                        <td><form:errors path="greeting.greetingText"/></td>
		</tr>
		<tr>
			<td>What's your favorite color?</td>
			<td> 
				<form:select path="color.colorCode">
            		<form:option value="" label="--Please Select"/>
            		<form:options items="${colorlist}" itemValue="colorCode" itemLabel="colorName"/>
        		</form:select>
			</td>
		</tr>		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="Submit" />	
			</td>
		</tr>
	</table>
</form:form>
 
</body>
</html>