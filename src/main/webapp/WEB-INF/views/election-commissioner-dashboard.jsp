<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
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

.container {
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 20px;
}

.widget {
	display: flex;
	flex-wrap: wrap;
	gap: 70px;
	padding-left: 30px;
}

.widget div {
	width: 300px;
	padding: 20px;
	background-color: #ffffff; /* White background */
	border-radius: 10px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
	text-align: center;
	margin: 0;
	transition: all 0.3s ease; /* Smooth transition on hover */
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	height: 150px;
}

.widget a, a {
	text-decoration: none;
	color: #333;
	font-weight: bold;
}

.widget div:hover {
	transform: translateY(-5px); /* Lift on hover */
	box-shadow: 0 6px 10px rgba(0, 0, 0, 0.2); /* Slightly darker shadow */
}

footer {
	text-align: center;
	padding: 20px 0;
	background-color: #FFCC80; /* Less saturated orange footer */
	color: #333;
	margin-top: auto; /* Push the footer to the bottom */
	width: 100%;
}

.card-heading {
	font-size: 1.5em;
	margin-bottom: 10px;
}

.card-button {
	background-color: #FFCC80;
	border: none;
	border-radius: 5px;
	padding: 8px 20px;
	cursor: pointer;
	transition: all 0.3s ease;
}

.card-button:hover {
	background-color: #FFB74D;
}
</style>
</head>
<body>
<%
	String ctx = application.getContextPath();
	//out.print(ctx);
	String home = ctx + "/home";
	%>


	<header>
		<h1>Election Commissioner Dashboard</h1>
		<button class="logout-button">
			<a href="<%=home%>">Go to Home</a>
			<button class="logout-button"><a href= "logout">Logout</a></button>
	</header>
	
	<div class="container">
		<div class="widget">
			<div>
				<h2 class="card-heading">Your Profile</h2>
				<a href="election-commissioner-profile" class="card-button">Go to Profile</a>
			</div>
			<div>
				<h2 class="card-heading">Regional Admin Approval</h2>
				<a href="regmanager-approval" class="card-button">Approve
					Candidates</a>
			</div>
			<div>
				<h2 class="card-heading">Candidate Approval</h2>
				<a href="candidate-approval" class="card-button">Approve
					Candidates</a>
			</div>
			<div>
				<h2 class="card-heading">Voter Approval</h2>
				<a href="voter-approval" class="card-button">Approve Voters</a>
			</div>
			<div>
				<h2 class="card-heading">Add Election</h2>
				<a href="add-election" class="card-button">Add
					New Election</a>
			</div>
			<div>
				<h2 class="card-heading">Delete Election</h2>
				<a href="delete_election" class="card-button">Add
					New Election</a>
			</div>
			<div>
				<h2 class="card-heading">Add Party</h2>
				<a href="add-party" class="card-button">Add New
					Party</a>
			</div>
			<div>
				<h2 class="card-heading">See All Election Results</h2>
				<a href="voting-result-page" class="card-button">View Results</a>
			</div>
		</div>
	</div>

	<footer>
        <p>&copy; 2023 Your Voting App. All rights reserved.</p>
	</footer>

</body>
</html>