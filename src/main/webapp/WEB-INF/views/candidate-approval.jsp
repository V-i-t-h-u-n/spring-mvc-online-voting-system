<%@page import="java.util.Base64"%>
<%@page import="voting.app.entities.ElectionAdmin"%>
<%@page import="voting.app.entities.Candidate"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidate Approval</title>

<style>
/* General Styles */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f0f5f9; /* Light blue background */
	display: flex;
	flex-direction: column;
	align-items: center;
}

/* Header Styles */
header {
	background-color: #FFCC80; /* Less saturated orange header */
	color: #333;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px;
	width: 100%;
	box-sizing: border-box;
	margin-bottom: 20px;
}

.logout-button, .home-button {
	background: #FFCC80; /* Same as header color for consistency */
	border: none;
	font-size: 16px;
	cursor: pointer;
	padding: 10px 20px;
	border-radius: 5px;
	transition: all 0.3s ease;
	text-decoration: none;
	color: #333;
	margin-left: 10px;
}

.logout-button:hover, .home-button:hover {
	transform: translateY(-3px);
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

table {
	border-collapse: collapse;
	width: 100%;
}

table caption {
	font-weight: bold;
	font-size: 1.2em;
	margin-bottom: 10px;
}

th, td {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

th {
	background-color: #FFCC80;
}

tr.granted {
	background-color: #d4edda;
}

tr.revoked {
	background-color: #f8d7da;
}

a {
	text-decoration: none;
	color: black;
}

/* Footer Styles */
footer {
	text-align: center;
	padding: 20px 0;
	background-color: #FFCC80; /* Less saturated orange footer */
	color: #333;
	width: 100%;
	margin-top: auto; /* Push the footer to the bottom */
}
</style>

</head>
<body>

 <%
	String ctx = application.getContextPath();
	//out.print(ctx);
	String home = ctx + "/home";
	%>

	<%
	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("candidate_approval_list");
	%>
	<header>
		<h1>Candidate Approval</h1>
		<button class="logout-button">
			<a href="<%=home%>">Go to Home</a>
	</header>


	<table>
		<caption>Candidate List</caption>
		<thead>
			<tr>
				<th>Candidate Name</th>
				<th>Party Name</th>
				<th>Election Name</th>
				<th>Id Proof</th>
				<th>Approval Status</th>
				<th>Approve</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < candidateList.size(); i++) {
				
				
				String idProof = Base64.getEncoder().encodeToString(
						(candidateList.get(i).getIdProof()).getBytes(1, (int) 
								(candidateList.get(i).getIdProof().length())));	
				
				
			%>
			<tr
				class="<%if (candidateList.get(i).isStatus()) {%>granted<%}
				else {%>revoked<%}%>">
				<td><%=candidateList.get(i).getCandidateName()%></td>
				<td><%=candidateList.get(i).getPartyName()%></td>
				<td><%=candidateList.get(i).getElectionName() %></td>
				<td><img src="data:image/png;base64, <%=idProof%>" width="100" />
				<td>
					<%
					if (candidateList.get(i).isStatus() == true) {
						out.print("Access Provided");
					} else {
						out.print("Access Not Provided");
					}
					%>
				</td>
				<td>
					<form action="accessAction" method="post"
						onsubmit="updateCellColor(<%=i%>)">
						<input type="radio" id="grant-access<%=i%>" name="access"
							value="grant" <%if (candidateList.get(i).isStatus()) {%> checked
							<%}%> /> <label for="grant-access<%=i%>">Grant Access</label>
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
							id="revoke-access<%=i%>" name="access" value="revoke"
							<%if (!candidateList.get(i).isStatus()) {%> checked <%}%> /> <label
							for="revoke-access<%=i%>">Revoke Access</label> <input
							type="hidden" name="id"
							value="<%=candidateList.get(i).getCandidateId()%>"> <input
							class="logout-button" type="submit" name="submit" value="Click" />
					</form>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>


	<footer>
		<p>&copy; 2023 Your Voting App. All rights reserved.</p>
	</footer>


	<%-- <table>
		<caption>Candidate List</caption>
		<tr>
			<th>candidate Name</th>
			<th>party Name</th>
			<th>electionName</th>
			<th>Approve</th>
		</tr>
		<%
		for (int i = 0; i < candidateList.size(); i++) {	%>

		<tr>
			<td><%=candidateList.get(i).getCandidateName()%></td>
			<td><%=candidateList.get(i).getPartyName()%></td>
			<td><%=candidateList.get(i).getElectionId()%></td>
			<td>
			<form action = "accessAction" method = "post">
				<input type="radio" id="grant-access" name="access" value = "grant" checked />
				 <label for="grant-access">Grant Access</label> 
				 &nbsp &nbsp &nbsp &nbsp
				 <input type="radio" id="revoke-access" name="access" value = "revoke"/>
				 <label for="revoke-access">Revoke Access</label>
				 <input type = "hidden" name = "id" 
				 value = "<%=candidateList.get(i).getCandidateId()%>">
				<input type = "submit" name = "submit" value = "Click"/>
			 </form>
		</tr>
		<%
		}
		%>
	</table>
	
		<p><a href = "landing-page">Home Page</a></p> --%>
</body>
</html>