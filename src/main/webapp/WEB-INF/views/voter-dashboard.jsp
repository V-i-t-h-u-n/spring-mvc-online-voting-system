<%@page import="java.util.List"%>
<%@page import="voting.app.entities.NewElection"%>
<%@page import="voting.app.entities.Voter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
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

a {
	text-decoration: none;
	color: black;
}

header {
	background-color: #FFCC80; /* Less saturated orange header */
	color: #333;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px;
}

.logout-button {
	background: none;
	border: none;
	font-size: 16px;
	cursor: pointer;
}

.link-card {
	width: 300px;
	padding: 20px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	margin: 20px auto;
}

.table-button {
	background: none;
	border: none;
	font-size: 18px;
	cursor: pointer;
	display: block;
	margin: 10px auto;
}

table {
	width: 80%;
	margin: 10px auto;
	border-collapse: collapse;
	display: none;
}

table th {
	background-color: #FFCC80; /* Less saturated orange heading */
	color: #333;
	padding: 10px;
	cursor: pointer;
}

table th:hover {
	background-color: #FFB74D; /* Darker less saturated orange on hover */
}

table td, table th {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}

footer {
	text-align: center;
	padding: 20px 0;
	background-color: #FFCC80; /* Less saturated orange footer */
	color: #333;
	margin-top: auto; /* Push the footer to the bottom */
	width: 100%;
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

.table-button+p {
	margin-bottom: 10px;
}

.link-container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-wrap: wrap;
	gap: 20px;
	padding: 20px;
	margin-top: 20px;
}

.link-card {
	width: 300px;
	padding: 20px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.link-card h2 {
	color: #FFCC80; /* Less saturated orange heading */
}

.link-card a {
	display: block;
	padding: 10px 20px;
	background-color: #FFCC80; /* Less saturated orange button */
	color: #333;
	text-decoration: none;
	border-radius: 5px;
	margin-top: 20px;
}

.link-card a:hover {
	background-color: #FFB74D; /* Darker less saturated orange on hover */
}
</style>
</head>
<body>

<%-- 
<%
	String ctx = application.getContextPath();
	//out.print(ctx);
	String home = ctx + "logout";
	%> --%>

	<%
	List<NewElection> electionList = (List<NewElection>) session.getAttribute("eletionList");

	List<NewElection> pastElections = (List<NewElection>) session.getAttribute("pastElections");

	List<NewElection> futureElections = (List<NewElection>) session.getAttribute("futureElections");
	%>



	<header>
		<h1>Voter Dashboard</h1>
		<button class="logout-button">
			<a href="logout">Logout</a>
		</button>
	</header>

	<div class="link-container">
		<div class="link-card">
			<h2>Your Profile</h2>
			<a href="voter-profile">Your Profile</a>
		</div>
		<div class="link-card">
			<h2>See All Election Results</h2>
			<a href="voting-result-page">See All Election Results</a>
		</div>
	</div>


	<button class="table-button" onclick="toggleTable('active-elections')">Active
		Election List</button>
		
		<%
		if (!electionList.isEmpty()) {
		%>
	<table id="active-elections">
		<caption>Active Election List</caption>
		<tr>
			<th>Election Date</th>
			<th>Election Name</th>
		</tr>
		<% for (int i = 0; i < electionList.size(); i++) { %>
		
		
		<tr>
			 
			 
			<td><%= electionList.get(i).getElectionDate() %></td>
			<td>
				<p>
					<a
						href="voting-page?name=
                    <%= String.join("+", electionList.get(i).
                    		getElectionName().split(" ")) %>
                    &date=<%= electionList.get(i).getElectionDate() %>
                    &electId=<%= electionList.get(i).getElectionId() %>">
						<%= electionList.get(i).getElectionName() %>
					</a>
				</p>
			</td>

			<%
			 }
		
		}
		 else {
				%>
				<p>No elections found...</p>
				<%
				}
		%>
	</table>


	<button class="table-button" onclick="toggleTable('past-elections')">Past
		Elections</button>
	<table id="past-elections">
		<%
		if (!pastElections.isEmpty()) {
		%>
		<tr>
			<th>Election Date</th>
			<th>Election Name</th>
		</tr>
		<%
		for (int i = 0; i < pastElections.size(); i++) {
		%>
		<tr>
			<td><%=pastElections.get(i).getElectionDate()%></td>
			<td><%=pastElections.get(i).getElectionName()%></td>
		</tr>
		<%
		}
		%>
		<%
		} else {
		%>
		<p>No elections found...</p>
		<%
		}
		%>
	</table>

	<button class="table-button" onclick="toggleTable('future-elections')">Future
		Elections</button>
	<table id="future-elections">
		<%
		if (!futureElections.isEmpty()) {
		%>

		<tr>
			<th>Election Date</th>
			<th>Election Name</th>
		</tr>
		<%
		for (int i = 0; i < futureElections.size(); i++) {
		%>
		<tr>
			<td><%=futureElections.get(i).getElectionDate()%></td>
			<td><%=futureElections.get(i).getElectionName()%></td>
		</tr>
		<%
		}
		%>

		<%
		} else { %>
		<p>No elections found...</p>
		<%
		}
		%>

	</table>

	<footer>
		<p>&copy; 2023 Voting App. All rights reserved.</p>
	</footer>

	<script>
		function toggleTable(id) {
			const table = document.getElementById(id);
			if (table.style.display === 'none') {
				table.style.display = 'table';
			} else {
				table.style.display = 'none';
			}
		}
	</script>
</body>

<%-- <style>
	table.election-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

table.election-table caption {
    font-size: 1.2em;
    font-weight: bold;
    margin-bottom: 10px;
}

table.election-table th {
    background-color: #f5ca89;
    text-align: left;
    padding: 8px;
}

table.election-table td,
table.election-table th {
    border: 1px solid #ddd;
    padding: 8px;
}

table.election-table tr:nth-child(even) {
    background-color: #f9f9f9;
}

table.election-table tr:hover {
    background-color: #f1f1f1;
}
</style>
</head>
<body>


	<h1>Cast your Vote!</h1>
	<p>
		<a href="voter-profile">Your Profile</a>
	</p>
	<p>
		<a href="voting-result-page"> See All Election Results </a>
	</p>

	<%
	List<NewElection> electionList = 
			(List<NewElection>) session.getAttribute("eletionList");
	
	List<NewElection> pastElections = 
			(List<NewElection>) session.getAttribute("pastElections");
	
	List<NewElection> futureElections = 
			(List<NewElection>) session.getAttribute("futureElections");
	%>
	
	
	<table class="election-table">
        <caption>Active Election List</caption>
        <tr>
            <th>Election Date</th>
            <th>Election Name</th>
        </tr>
        <% for (int i = 0; i < electionList.size(); i++) { %>
        <tr>
            <td><%= electionList.get(i).getElectionDate() %></td>
            <td>
                <p>
                    <a href="voting-page?name=
                    <%= String.join("+", electionList.get(i).
                    		getElectionName().split(" ")) %>
                    &date=<%= electionList.get(i).getElectionDate() %>
                    &electId=<%= electionList.get(i).getElectionId() %>">
                        <%= electionList.get(i).getElectionName() %>
                    </a>
                </p>
            </td>
        </tr>
        <% } %>
    </table>
	
	
	<table class="election-table">
        <caption>Past Elections</caption>
        <tr>
            <th>Election Date</th>
            <th>Election Name</th>
        </tr>
        <% for (int i = 0; i < pastElections.size(); i++) { %>
        <tr>
            <td><%= pastElections.get(i).getElectionDate() %></td>
            <td><%= pastElections.get(i).getElectionName() %></td>
        </tr>
        <% } %>
    </table>
    
    
    <table class="election-table">
        <caption>Future Elections</caption>
        <tr>
            <th>Election Date</th>
            <th>Election Name</th>
        </tr>
        <% for (int i = 0; i < futureElections.size(); i++) { %>
        <tr>
            <td><%= futureElections.get(i).getElectionDate() %></td>
            <td><%= futureElections.get(i).getElectionName() %></td>
        </tr>
        <% } %>
    </table>
    
	
	</body> --%>

<!-- 	<form action = "" method = "get">
		<input type = "text" name = "name">
		
		<input type = "date" name = "date">
		
		<input type = "submit" name = "submit">
	</form>
 -->

<%-- <%=
		 pastElections
 
 %>
 <%=
 	futureElections
 %>
 
	<table>
		<caption>Election List</caption>
		<tr>
			<th>Election Date</th>
			<th>Election Name</th>
		</tr>
		<%
		for (int i = 0; i < electionList.size(); i++) {
		%>
		<tr>
			<td><%=electionList.get(i).getElectionDate()%></td>
			<td><p>
			<a href = "voting-page?name=
			<%=
			String.join("+", electionList.get(i).getElectionName().split(" "))
			%>
			&date=<%=electionList.get(i).getElectionDate()%>
			&electId=<%=electionList.get(i).getElectionId()%>"> 
				<%=electionList.get(i).getElectionName()%>
			 </a></p></td>
		</tr>
		<%
		}
		%>
	</table>


</body> --%>
</html>