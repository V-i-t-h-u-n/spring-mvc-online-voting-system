<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update your profile</title>

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f0f5f9; /* Light blue background */
	display: flex;
	flex-direction: column;
	min-height: 100vh;
	align-items: center;
}

header {
	background-color: #FFCC80; /* Less saturated orange header */
	color: #333;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	width: 100%;
	box-sizing: border-box;
}

.logout-button {
	background: #FFCC80; /* Same as header color for consistency */
	border: none;
	font-size: 16px;
	cursor: pointer;
	padding: 10px 20px;
	border-radius: 5px;
	transition: all 0.3s ease;
}

.logout-button:hover {
	transform: translateY(-3px);
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

form {
	width:75%;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	
}

label {
	display: block;
	margin-bottom: 5px;
}

 input[type="text"], input[type="file"]  {
	width: 100%;
	padding: 18px;
	margin-bottom: 10px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
}


a{
text-decoration: none;
color:black;

}


footer {
	text-align: center;
	padding: 20px 0;
	background-color: #FFCC80; /* Less saturated orange footer */
	color: #333;
	margin-top: auto; /* Push the footer to the bottom */
	width: 100%;
}

</style>
</head>
<body>
 <%
	String ctx = application.getContextPath();
	//out.print(ctx);
	String targetUrl = ctx + "/voter/voter-update";
	String home = ctx + "/home";
	%>
	
	
	<header>
		<h3>Voter Profile</h3>
		<button class="logout-button">
			<a href="">Go to Home</a>
	</header>
	 <form:form modelAttribute="voter" action="<%=targetUrl%>" method="post" enctype="multipart/form-data">
		
		<form:hidden path="voterId" />
		
		<label for="voterName">Name:</label>
		<form:input path="voterName"  />
		<br /> 
 		<label for="voterEmail">Email:</label>
		<form:input path="voterEmail" />
		<br /> 
		<label for="voterPhoneNumber">Mobile:</label>
		<form:input path="voterPhoneNumber" />
		<br />
		<label for="voterDateOfBirth">Date of Birth:</label>
		<form:input path="voterDateOfBirth" />
		<br />
		
		<label for="voterGender">Gender:</label>
			Male &nbsp &nbsp<form:radiobutton path="voterGender" value="m"/>  
			Female &nbsp &nbsp<form:radiobutton path="voterGender" value="f"/> 
		<br />
		
		<label for="voterNationality">Nationality:</label>
		<form:input path="voterNationality" /><br />
		<label for="constitution">Constitution:</label>
		<form:input path="constitution" /><br />
		<label for="voterAadharNo">Aadhar No:</label>
		<form:input path="voterAadharNo" />
		<br />
		<label for=voterIdProof>Id Proof:</label>
		<form:input path="voterIdProof" type="file"/>
		<br />
		<label for="voterProfilePic">Profile Picture:</label>
		<form:input path="voterProfilePic" type="file"/>
		<br />
		<button type="submit" class = "logout-button">Save Details</button>
	</form:form> 
	
	<footer>
		<p>&copy; 2023 Your Voting App. All rights reserved.</p>
	</footer>
</body>
</html>