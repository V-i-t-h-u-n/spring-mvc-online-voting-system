<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Regional Admin Registration</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: black;
}

* {
	box-sizing: border-box;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=email], input[type=date], input[type=file],
	select, input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn, .submitbtn {
	background-color: #04AA6D;
	color: white;
	padding: 16px 20px;
	margin: 8px;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

/* Add a blue text color to links */
a {
	color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
	background-color: #f1f1f1;
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Registration Form</h1>

		<form action="handle-reg-manager-register" method="post"
			enctype="multipart/form-data" class="form">

			<label for="">UserName</label> <input type="text"
				placeholder="Enter UserName" name="userName" required /> <br>

			<label for="">Email</label> <input type="text"
				placeholder="Enter Email" name="email" required /> <br> 
				
				<label
				for="">Birth Date
				</label> 
				<input type="date"
				placeholder="Enter birth date" name="dob" required /> <br> 
							
				
				<label
				for="">Nationality</label> <input type="text"
				placeholder="Enter Nationality" name="nationality" required /> <br>

			<label for="">Id proof</label> <input type="file" name="idProof"
				name="idProof" required /> <br> 
				
				<label for="password">Password</label>
			
			<input type="password" placeholder="Enter Password" id="password"
				name="password" required /> <br> 
				
			<label for="confirm-password">Confirm
				Password</label> 
				
			<input type="password" placeholder="Confirm Password"
				id="confirm-password" required /> <br>


			<button type="submit" class="registerbtn" onclick = "validatePassword()">
			
			Register</button>
			
			<script>
				function validatePassword(){
					var pwd = document.getElementById("password").value;
					var confirmPwd = document.getElementById("confirm-password").value;
					if (pwd !== confirmPwd)
						alert("Check the password field")
				}
			</script>
		</form>
	</div>
</body>
</html>