<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="voting.app.entities.VoteResult"%>
<%@page import="voting.app.entities.Candidate"%>
<%@page import="java.util.Base64"%>
<%@page import="voting.app.entities.Party"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Election Result</title>
<style>
/* Basic CSS styles */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 20px;
	background-color: #f5f5f5;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.party-section {
	margin-bottom: 20px;
}

.party-details {
	display: flex;
	align-items: center;
	border-bottom: 1px solid #ccc;
	padding: 10px 0;
}

.party-logo {
	width: 80px;
	height: 80px;
	margin-right: 20px;
	border-radius: 50%;
	object-fit: cover;
}

.party-info {
	flex: 1;
}

.party-name {
	font-size: 1.2em;
	font-weight: bold;
	margin-bottom: 5px;
}

.party-wins {
	color: green;
	font-weight: bold;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

.party-logo img {
	width: 100px; /* Adjust size as needed */
	height: auto;
}

.highlighted-row {
	/* Define highlighted row styles */
	background-color: #FFD700; /* Example background color */
}

.hidden-content {
	display: none;
}

 .toggle-button {
            cursor: pointer;
            user-select: none;
            text-align: center;
            margin-top: 20px;
        }

        .toggle-button button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #3498db;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .toggle-button button:hover {
            background-color: #2980b9;
        }

        .hidden-content {
            display: none;
        }
</style>
</head>
<body>

	<%
	List<VoteResult> partyVoteList = (List<VoteResult>) 
							request.getAttribute("partyVoteList");

	List<Candidate> candidateVoteList = (List<Candidate>) 
							request.getAttribute("candidateVoteList");
	%>

	<div class="container">

		<h1>Election Report</h1>

		<%
		int totalConstitution = 0;

		// Calculate the total votes across all constituencies
		for (VoteResult party : partyVoteList) {
			totalConstitution += party.getVotes();
		}

		// Map to store constituencies won by each party
		Map<String, Integer> constituenciesMap = new TreeMap<>();

		String winnerParty = "";
		int maxConstitutions = 0;

		// Aggregate constituencies won by each party
		for (VoteResult party : partyVoteList) {
			int constituenciesWon = (int) Math.floor
					((party.getVotes() * 1.0 / totalConstitution) *
							totalConstitution);

			// Aggregate constituencies won for each party
			constituenciesMap.put(party.getPartyName(),
			constituenciesMap.getOrDefault(party.getPartyName(), 0) + constituenciesWon);

			// Determine the party with the maximum constituencies won
			if (constituenciesMap.get(party.getPartyName()) > maxConstitutions) {
				maxConstitutions = constituenciesMap.get(party.getPartyName());
				winnerParty = party.getPartyName();
			}
		}
		%>

		<div class="party-section">
			<h2>Party Results</h2>

			<!-- Display aggregated constituencies won for each party -->
			<%
			for (Map.Entry<String, Integer> entry : constituenciesMap.entrySet()) {
			%>
			<div class="party-details">
				<div class="party-info">
					<div class="party-name"><%=entry.getKey()%></div>
					<p>
						Constituencies Won:
						<%=entry.getValue()%></p>
					<%
					if (entry.getKey().equals(winnerParty)) {
					%>
					<p class="party-wins">Winner</p>
					<%
					}
					%>
				</div>

			</div>
			<%
			}
			%>

			<p>
				Total Constituencies:
				<%=totalConstitution%></p>
			<p>
				Winner:
				<%=winnerParty%></p>
		</div>

		<div class="toggle-button">
            <button onclick="toggleDetails()">See Detailed Report</button>
        </div>

		<!-- Content to be toggled -->
		<div class="hidden-content">
			<%
			if (partyVoteList != null && !partyVoteList.isEmpty()) {
			%>
			<table>
				<caption>Party Vote Details</caption>
				<thead>
					<tr>
						<th>Party Name</th>
						<th>Election Details</th>
						<th>Constitution</th>
						<th>Vote Count</th>
						<th>Party Logo</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (VoteResult party : partyVoteList) {
						String base64PartyLogo = Base64.getEncoder()
						.encodeToString((party.getPartyLogo().getBytes(1, (int) (party.getPartyLogo().length()))));
					%>
					<tr>
						<td><%=party.getPartyName()%></td>
						<td><%=party.getElectionName()%></td>
						<td><%=party.getConstitution()%></td>
						<td><%=party.getVotes()%></td>
						<td class="party-logo"><img
							src="data:image/png;base64, <%=base64PartyLogo%>"
							alt="Party Logo"></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			} else {
			%>
			<p>No party vote data found.</p>
			<%
			}
			%>

			<%
			if (candidateVoteList != null && !candidateVoteList.isEmpty()) {
			%>
			<table>
				<caption>Candidate Vote Details</caption>
				<thead>
					<tr>
						<th>Candidate Name</th>
						<th>Party Name</th>
						<th>Constitution</th>
						<th>Candidate Vote Count</th>
					</tr>
				</thead>
				<tbody>
					<%
					boolean isFirstRow = true;
					%>
					<%
					for (Candidate candidate : candidateVoteList) {
					%>
					<tr class="<%=isFirstRow ? "highlighted-row" : ""%>">
						<td><%=candidate.getCandidateName()%></td>
						<td><%=candidate.getPartyName()%></td>
						<td><%=candidate.getConstitution()%></td>
						<td><%=candidate.getVoteCount()%></td>
					</tr>
					<%
					isFirstRow = false;
					%>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			} else {
			%>
			<p>No candidate data found.</p>
			<%
			}
			%>
		</div>



		<script>
            // JavaScript to toggle hidden content visibility
            function toggleDetails() {
                const hiddenContent = document.querySelector('.hidden-content');
                const toggleButton = document.querySelector('.toggle-button button');
                const isHidden = hiddenContent.style.display === 'none' || hiddenContent.style.display === '';

                if (isHidden) {
                    hiddenContent.style.display = 'block';
                    toggleButton.textContent = 'Hide Details';
                } else {
                    hiddenContent.style.display = 'none';
                    toggleButton.textContent = 'See Detailed Report';
                }
            }
        </script>
	</div>

</body>
</html>