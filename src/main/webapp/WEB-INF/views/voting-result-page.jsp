<%@page import="voting.app.entities.NewElection"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Election Results</title>

<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f5f9;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            margin: 0;
            padding: 20px;
        }
        .card {
            width: 300px;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
            margin: 10px;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .card-content {
            padding: 20px;
        }
        .election-name {
            font-size: 20px;
            font-weight: bold;
            color: #333333;
            margin-bottom: 10px;
        }
        .election-date {
            font-size: 18px;
            color: #007bff;
            margin-bottom: 20px;
        }
        .see-result {
            display: block;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            color: #ffffff;
            background-color: #FFCC80;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .see-result:hover {
            background-color: #ff4d4d;
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
            margin-bottom: 20px;
        }

        .logout-button,
        .home-button {
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

        .logout-button:hover,
        .home-button:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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
	
	<header>
        <h1>Show Result</h1>
    </header>
	
	<%
	List<NewElection> electList = 
				(List<NewElection>)request.getAttribute("elecList");
	%>
	
	 <% if (electList == null || electList.isEmpty()) { %>
        <p>No elections found.</p>
    <% } else { %>
        <% for (NewElection election : electList) { %>
            <div class="card">
                <div class="card-content">
                    <div class="election-name"><%= election.getElectionName() %></div>
                    <div class="election-date"><%= election.getElectionDate() %></div>
                    <div class="status-label" id="status<%= election.getElectionId() %>"></div>
                    <a href="seeResult?electionId=<%= election.getElectionId() %>" 
                    	class="see-result">See Result</a><br>
                    <a href = "seeElectionStats?electionId=<%= election.getElectionId() %>"
                    class="see-result"
                    >See Election Stats</a>
                </div>
            </div>
        <% } %>
    <% } %>

    <script>
    <% for (NewElection election : electList) { %>
        var status<%= election.getElectionId() %> = getStatusLabel('<%= election.getElectionDate() %>');
        document.getElementById('status<%= election.getElectionId() %>').innerText = status<%= election.getElectionId() %>;
    <% } %>

    function getStatusLabel(electionDate) {
        var date = new Date(electionDate);
        var currentDate = new Date();
        
        // Set hours, minutes, seconds, milliseconds to 0 for comparison
        date.setHours(0, 0, 0, 0);
        currentDate.setHours(0, 0, 0, 0);

        if (date > currentDate) {
            return "Election Yet to Start";
        } else if (date < currentDate) {
            return "Election Over";
        } else {
            return "Election Active";
        }
    }
</script>

	<footer>
		<p>&copy; 2023 Your Voting App. All rights reserved.</p>
	</footer>
</body>
</html>