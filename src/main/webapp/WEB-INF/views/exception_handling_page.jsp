<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exception Handling</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1.
	response.setHeader("Pragma", "no-cache");//HTTP 1.0.
	%>
	<h2>${errormessage}</h2>
	<p>
		<a href="landing-page">Home Page</a>
	</p>
</body>
</html>