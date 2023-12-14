<%@page import="java.util.Base64"%>
<%@page import="voting.app.entities.Voter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voter Profile</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f0f5f9; /* Light blue background */
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

header {
	background-color: #FFCC80; /* Less saturated orange header */
	color: #333;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px;
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

footer {
	text-align: center;
	padding: 20px 0;
	background-color: #FFCC80; /* Less saturated orange footer */
	color: #333;
	margin-top: auto; /* Push the footer to the bottom */
	width: 100%;
}

.profile-container {
	border-radius: 12px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	padding: 40px;
	width: 1000px;
	text-align: center;
	display: flex;
	flex-direction: column;
	align-items: center;
}

h1 {
	font-size: 24px;
	margin-bottom: 20px;
}

.user-info {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: space-around;
	align-items: center;
	margin-top: 20px;
}

.info-card {
	background-color: #f9d49c9c;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	width: 200px;
	min-height: 100px;
	display: flex;
	flex-direction: column;
}

.card-label {
	font-weight: bold;
	margin-bottom: 10px;
}

.card-value {
	margin: 0;
}

img.profile-image {
	width: 100px;
	height: auto;
}

a {
	margin-top:20px;
	text-decoration: none;
	color:black;	
}


</style>
</head>
<body>
	<center>
		<%
		Voter voter = (Voter) session.getAttribute("voterProfile");

		String base64IdProof = Base64.getEncoder()
				.encodeToString((voter.getVoterIdProof()
						.getBytes(1, (int) (voter.getVoterIdProof().length()))));

		String base64ProfilePic = Base64.getEncoder()
				.encodeToString((voter.getVoterProfilePic().getBytes(1, (int) (voter.getVoterProfilePic().length()))));
		%>

		<header>
			<h1>Voter Profile</h1>
			<button class="logout-button">
				<a href="home-page">Go to Home</a>
		</header>


		<div class="profile-container">
			<h1>Voter Profile</h1>
			<div class="user-info">
				<div class="info-card">
					<div class="card-label">Profile Pic:</div>
					<div class="card-value">
						<img src="data:image/png;base64, <%=base64ProfilePic%>"
							class="profile-image" />
					</div>
				</div>

				<div class="info-card">
					<div class="card-label">Name</div>
					<div class="card-value"><%=voter.getVoterName()%></div>
				</div>

				<div class="info-card">
					<div class="card-label">Email Id</div>
					<div class="card-value"><%=voter.getVoterEmail()%></div>
				</div>
				<div class="info-card">
					<div class="card-label">Mobile</div>
					<div class="card-value"><%=voter.getVoterPhoneNumber()%></div>
				</div>
				<div class="info-card">
					<div class="card-label">Date of Birth</div>
					<div class="card-value"><%=voter.getVoterDateOfBirth()%></div>
				</div>
				<div class="info-card">
					<div class="card-label">Gender</div>
					<div class="card-value">
						<%=voter.getVoterGender().equals("m") ? "Male" : voter.getVoterGender().equals("f") ? "Female" : "Others"%></div>
				</div>
				<div class="info-card">
					<div class="card-label">Nationality - Constitution</div>
					<div class="card-value"><%=voter.getVoterNationality() + "-" + voter.getConstitution()%></div>
				</div>
				<div class="info-card">
					<div class="card-label">Aadhar No</div>
					<div class="card-value"><%=voter.getVoterAadharNo()%></div>
				</div>

				<div class="info-card">
					<div class="card-label">Id proof</div>
					<div class="card-value">
						<img src="data:image/png;base64, 
				<%=base64IdProof%>"
							width="100" />
					</div>
				</div>

				<!-- Repeat the structure for other fields -->
			</div>
			
			<a href="voter-profile-update" class="logout-button">Update
				your profile</a>
		</div>
		
			<footer> &copy; 2023 Your Voting App. All Rights Reserved. </footer>
		

		<%-- <table>
			<caption>Voter Profile</caption>
			<tr>

				<th><h3>Name</h3></th>
				<td><h3><%=voter.getVoterName()%></h3></td>
			</tr>
			<tr>
				<th><h3>Email Id</h3></th>
				<td><h3><%=voter.getVoterEmail()%></h3></td>
			</tr>
			<tr>
				<th><h3>Mobile</h3></th>
				<td><h3><%=voter.getVoterPhoneNumber()%></h3></td>
			</tr>
			<tr>
				<th><h3>Date of Birth</h3></th>
				<td><h3><%=voter.getVoterDateOfBirth()%></h3></td>
			</tr>
			<tr>
				<th><h3>Gender</h3></th>
				<td><h3><%=voter.getVoterGender()%></h3></td>
			</tr>
			<tr>
				<th><h3>Nationality</h3></th>
				<td><h3><%=voter.getVoterNationality()%></h3></td>
			</tr>
			
			<tr>
				<th><h3>Aadhar number</h3></th>
				<td><h3><%=voter.getVoterAadharNo()%></h3></td>
			</tr>
			
			<tr>
				<th><h3>Id proof</h3></th>
				<td><h3><img src="data:image/png;base64, 
				<%=base64IdProof%>"
				width="100" /></h3></td>
			</tr>
			
			<tr>
				<th><h3>Profile Pic</h3></th>
				<td><h3><img src="data:image/png;base64, 
				<%=base64ProfilePic%>"
				width="100" /></h3></td>
			</tr>
		</table>
		
		<p><a href = "voter-profile-update">Update your profile</a></p>
	</center>
 --%>
</body>
</html>