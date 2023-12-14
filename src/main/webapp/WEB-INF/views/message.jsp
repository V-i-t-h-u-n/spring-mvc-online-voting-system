<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<p>
	<% 
		String message = (String)request.getAttribute("message");
		if(message!=null) {
			out.print(message);
		}
	%>
</p>