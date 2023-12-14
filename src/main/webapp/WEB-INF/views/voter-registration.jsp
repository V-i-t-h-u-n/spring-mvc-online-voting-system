<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voter Registration</title>

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

		<form action="voter-Registration" method = "post" enctype="multipart/form-data" class="form">

			<label for="">Name</label> 
			<input type="text" placeholder="Enter your name" name = "name" required /> <br>
		  
		    <label for="">Email	Id</label>
		    <input type="email" placeholder="Enter email Id" name = "email" required /> <br>

			<!-- <label for="">Phone Number</label> 
			<input  type="text" placeholder="Enter phone number" name = "phone" required /> <br> -->
			
			<label for="phone">Phone Number</label>
			<input type="text" id="phone" placeholder="Enter phone number" name="phone" oninput="validatePhone()" required />
			<p id="phoneError" style="color: red;"></p>
			
			
			<label	for="">Birth Date</label> 
			<input type="date" placeholder="Enter birth date" name= "dob" id = "dob" required /> <br>
		
			<script>
                document.addEventListener('DOMContentLoaded',function(){
                        var today=new Date();
                        var year=today.getFullYear()-18;
                        var month=String(today.getMonth()+1).padStart(2,0);
                        var day=String(today.getDate()).padStart(2,0);

                        var formatedToday=year+'-'+month+'-'+day;

                        document.getElementById('dob').max=formatedToday;
                });
            </script>
		
		
			<label for="">Gender</label> <br>
			<input type="radio" id="check-male" name="gender"  value = "m" checked />	
			<label for="check-male">male</label> 
			
			<input type="radio" id="check-female" name="gender"  value = "f"/>
			<label for="check-female">Female</label>
			
			<input type="radio" id="check-others" name="gender" value = "o"/> 				
			<label	for="check-others">Others</label> <br> <br>
				
			<label for="">Nationality</label>
			<input type="text"	placeholder="Enter Nationality" name= "nationality" required /> <br>
			
			<label for="">Constitution</label>
			<input type="text"	placeholder="Enter Constitution" name= "constitution" required /> <br>
			
			<label for="">Id proof</label>
			<input type="file" name = "idProof" name="idProof" required /> <br>
			 
	<!-- 		 <label for="">Aadhar Number</label> 
			<input type="text" placeholder="Enter your aadhar number" name = "aadhar" required /> <br>
	 -->
			<label for="aadhar">Aadhar Number</label>
			<input type="text" id="aadhar" placeholder="Enter your Aadhar number" name="aadhar" oninput="validateAadhar()" required />
			<p id="aadharError" style="color: red;"></p>
	
	
			<label for="">Profile pic</label>
			<input type="file"  name = "profilePic" name = "profilePic" /> <br>

			<label for="password">Password</label> 
			<input type="password" placeholder="Enter Password" id="password" name = "password" required /> <br>
			
			<label for="confirm-password">Confirm Password</label> <input
				type="password" placeholder="Confirm Password" id="confirm-password"
				required /> <br>
			

			<script>
			
				function checkVoterAge(){
					var pwd = document.getElementById("password").value;
					var confirmPwd = document.getElementById("confirm-password").value;
					if (pwd !== confirmPwd)
						alert("Check the password field")
				}
				
				
				function validateAadhar() {
				    var aadharInput = document.getElementById('aadhar').value;
				    var aadharPattern = /^\d{12}$/; // Regex pattern for 12 digits

				    var errorElement = document.getElementById('aadharError');

				    if (aadharPattern.test(aadharInput)) {
				        // Valid Aadhar number
				        errorElement.textContent = ''; // Clear any previous error message
				    } else {
				        errorElement.textContent = 'Please enter a valid 12-digit Aadhar number.';
				    }
				}
				
				
				function validatePhone() {
				    var phoneInput = document.getElementById('phone').value;
				    var phonePattern = /^\d{10}$/; // Regex pattern for 10 digits

				    var errorElement = document.getElementById('phoneError');

				    if (phonePattern.test(phoneInput)) {
				        // Valid phone number
				        errorElement.textContent = ''; // Clear any previous error message
				    } else {
				        errorElement.textContent = 'Please enter a valid 10-digit phone number.';
				    }
				}


				
			</script>
			<button type="submit" class="registerbtn" onclick = "checkVoterAge()">Register</button>
		</form>
	</div>
</body>
</html>