<%@page import="voting.app.entities.NewElection"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Page</title>
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
List<NewElection> deleteElection = (List<NewElection>)request.getAttribute("toDeleteElection");
%>

<table>
<tr>
<th>Election Id</th>
<th>Election Name</th>
<th>Election Date</th>
<th>Action</th>
</tr>

<% for(int i=0;i<deleteElection.size();i++){ %>
<tr>
<td><%=deleteElection.get(i).getElectionId() %></td>
<td><%=deleteElection.get(i).getElectionName() %></td>
<td><%=deleteElection.get(i).getElectionDate()%></td>
<td><form action="handle_delete_election" method="get" id="deleteForm">
<input type="hidden" name="electionId" value="<%=deleteElection.get(i).getElectionId() %>">
<input type="submit" value="DELETE" class = "logout-button" id = "delete-btn">
</form></td>

</tr>
<%} %>
</table>
<form action="deleted_election" method="get">
<input type="submit" value="View Deleted Election">
</form>
<script>

document.addEventListener('DOMContentLoaded', function() {
    var deleteBtn = document.getElementById('delete-btn');
    var deleteForm = document.getElementById('deleteForm');

    deleteBtn.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission
        
        var confirmed = confirm('Are you sure you want to delete?');

        if (confirmed) {
            deleteForm.submit(); // Submit the form
        }
        // If user clicks cancel, nothing happens
    });
});

</script>
</body>
</html>