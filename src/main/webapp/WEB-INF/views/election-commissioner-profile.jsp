<%@page import="java.util.Base64"%>
<%@page import="voting.app.entities.ElectionAdmin"%>
<%@page import="voting.app.entities.RegionalCommissioner"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Profile</title>
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

.profile-card-container {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 20px;
	padding: 50px;
	background-color: transparent;
	flex-wrap: wrap;
}

.profile-box {
	width: 150px;
	height: 150px;
	background-color: rgba(255, 255, 255, 0.9); /* More transparent */
	padding: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin-bottom: 20px;
}

.profile-box h3 {
	margin: 0;
	margin-bottom: 5px;
	font-size: 1.2em; /* Larger font size */
}

.profile-box p {
	margin: 0;
	font-size: 1em; /* Larger font size */
}

.id-proof {
	width: 100%;
	max-width: 150px; /* Adjusted ID proof size */
	height: auto;
	margin-bottom: 10px;
}

a {
	text-decoration: none;
	color: black;
}

h3 {
	color: #FFCC80
}
</style>

</head>
<body>

	<%
	ElectionAdmin elecomm = (ElectionAdmin) session.getAttribute("elecCommAdmin");

	String base64IdProof = Base64.getEncoder()
			.encodeToString((elecomm.getIdProof().getBytes(1, (int) (elecomm.getIdProof().length()))));
	%>

	<header>
		<h1>Election Commissioner Profile</h1>
		<button class="logout-button">
			<a href="landing-page">Go to Home</a>
	</header>

	<div class="profile-card-container">
		<div class="profile-box">
			<h3>User Name</h3>
			<p><%=elecomm.getUserName()%></p>
		</div>

		<div class="profile-box">
			<h3>Email ID</h3>
			<p><%=elecomm.getEmail()%></p>
		</div>

		<div class="profile-box">
			<h3>Date of Birth</h3>
			<p><%=elecomm.getDateOfbirth()%></p>
		</div>

		<div class="profile-box">
			<h3>Nationality</h3>
			<p><%=elecomm.getNationality()%></p>
		</div>

		<div class="profile-box">
			<h3>Role</h3>
			<p>
				<%
				if (elecomm.isLoggedIn() == true) {
					out.print("Sub Admin");
				} else {
					out.print("Root Admin");
				}
				%>
			</p>
		</div>

		<div class="profile-box">
			<h3>ID proof</h3>
			<img class="id-proof"
				src="data:image/png;base64, <%=base64IdProof%>"
				alt="ID Proof Image">

		</div>
		<a href="elecomm-profile-update" class="logout-button">Update your
			profile</a>
	</div>

	<!-- Footer -->
	<footer> &copy; 2023 YourWebsite. All Rights Reserved. </footer>
</body>
</html>