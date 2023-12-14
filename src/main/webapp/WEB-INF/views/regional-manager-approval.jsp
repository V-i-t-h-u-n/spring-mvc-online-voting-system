<%@page import="java.util.Base64"%>
<%@page import="voting.app.entities.RegionalCommissioner"%>
<%@page import="voting.app.entities.Voter"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Regional Manager Approval</title>
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

        tr.granted { background-color: #d4edda; }
        tr.revoked { background-color: #f8d7da; }


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
	<header>
		<h1>Admin Approval</h1>
		<p>
			<a href="landing-page" class="home-button">Home Page</a>
		</p>
		<p>
			<a href="#" class="logout-button">Logout</a>
		</p>
	</header>
	<%
	List<RegionalCommissioner> regManagerList = (List<RegionalCommissioner>) request
			.getAttribute("regManager_approval_list");
	%>
	<table>
		<caption>Regional Manager List</caption>
		<tr>
			<th>UserName</th>
			<th>DateOfBirth</th>
			<th>Id proof</th>
			<th>Approval Status</th>
			<th>Change Status</th>
		</tr>
<%
		for (int i = 0; i < regManagerList.size(); i++) {

			String idProof = Base64.getEncoder().encodeToString(
			(regManagerList.get(i).getIdProof()).getBytes(1, (int) 
					(regManagerList.get(i).getIdProof().length())));
		%>

		 <tr class="<% if (regManagerList.get(i).isAdmin()) { %>granted<% } else { %>revoked<% } %>">
			<td><%=regManagerList.get(i).getUserName()%></td>
			<td><%=regManagerList.get(i).getDateOfbirth()%></td>
			<td><img src="data:image/png;base64, <%=idProof%>" width="100" />
			</td>
			<td>
				<%
				if (regManagerList.get(i).isAdmin() == true) {
					out.print("Access Provided");
				} else {
					out.print("Access Not Provided");
				}
				%>
			</td>
			<td>
				<form action="regManagerAccessAction" method="post">
					<input type="radio" id="grant-access" name="access" value="grant"
						checked /> <label for="grant-access">Grant Access</label> &nbsp
					&nbsp &nbsp &nbsp <input type="radio" id="revoke-access"
						name="access" value="revoke" /> <label for="revoke-access">Revoke
						Access</label> <input type="hidden" name="id"
						value="<%=regManagerList.get(i).getId()%>"> <input
						type="submit" name="submit" value="Click" />
				</form>
		</tr>
		<%
		}
		%>
	</table>
	
	<footer>
		<p>&copy; 2023 Your Voting App. All rights reserved.</p>
	</footer>

	<%-- <p>
		<a href="landing-page">Home Page</a>
	</p>
	<%
	List<RegionalCommissioner> regManagerList = (List<RegionalCommissioner>) request
			.getAttribute("regManager_approval_list");
	%>
	<table>
		<caption>Regional Manager List</caption>
		<tr>
			<th>UserName</th>
			<th>DateOfBirth</th>
			<th>Id proof</th>
			<th>Approval Status</th>
			<th>Change Status</th>
		</tr>
		<%
		for (int i = 1; i < regManagerList.size(); i++) {

			String idProof = Base64.getEncoder().encodeToString(
			(regManagerList.get(i).getIdProof()).getBytes(1, (int) (regManagerList.get(i).getIdProof().length())));
		%>

		<tr>
			<td><%=regManagerList.get(i).getUserName()%></td>
			<td><%=regManagerList.get(i).getDateOfbirth()%></td>
			<td><img src="data:image/png;base64, <%=idProof%>" width="100" />
			</td>
			<td>
				<%
				if (regManagerList.get(i).isAdmin() == true) {
					out.print("Access Provided");
				} else {
					out.print("Access Not Provided");
				}
				%>
			</td>
			<td>
				<form action="regManagerAccessAction" method="post">
					<input type="radio" id="grant-access" name="access" value="grant"
						checked /> <label for="grant-access">Grant Access</label> &nbsp
					&nbsp &nbsp &nbsp <input type="radio" id="revoke-access"
						name="access" value="revoke" /> <label for="revoke-access">Revoke
						Access</label> <input type="hidden" name="id"
						value="<%=regManagerList.get(i).getId()%>"> <input
						type="submit" name="submit" value="Click" />
				</form>
		</tr>
		<%
		}
		%>
	</table>

	<p>
		<a href="landing-page">Home Page</a>
	</p>--%>
</body> 
</html>