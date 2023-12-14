<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Election Report</title>
</head>
<body>



 <%-- <% if (partyVoteList != null && !partyVoteList.isEmpty()) { %>
        <table>
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
                <% for (VoteResult party : partyVoteList) { 
                
                	String base64PartyLogo = Base64.getEncoder().encodeToString(
                			(party.getPartyLogo()
                					.getBytes(1, (int) 
                					(party.getPartyLogo()
                							.length()))));
              
                %>
                    <tr>
                        <td><%= party.getPartyName() %></td>
                        <td><%= party.getElectionName() %></td>
                        <td><%= party.getConstitution() %></td>
                        <td><%= party.getVotes() %></td>
                        <td class="party-logo">
                            <img src="data:image/png;base64, 
							<%=base64PartyLogo%>"
							width="100" />
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No party vote data found.</p>
    <% } %>
    
    
    <% if (candidateVoteList != null && !candidateVoteList.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Candidate Name</th>
                    <th>Party Name</th>
                    <th>Constitution</th>
                    <th>Candidate Vote Count</th>
                </tr>
            </thead>
            <tbody>
                <% boolean isFirstRow = true; %>
                <% for (Candidate candidate : candidateVoteList) { %>
                    <tr class="<%= isFirstRow ? "highlighted-row" : "" %>">
                        <td><%= candidate.getCandidateName() %></td>
                        <td><%= candidate.getPartyName() %></td>
                        <td><%= candidate.getConstitution() %></td>
                        <td><%= candidate.getVoteCount() %></td>
                    </tr>
                    <% isFirstRow = false; %>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No candidate data found.</p>
    <% } %>  --%>
</body>
</html>