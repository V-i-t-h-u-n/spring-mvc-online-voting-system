<%@page import="voting.app.entities.VoterStatsByVotersParticipation"%>
<%@page import="voting.app.entities.VoterStatsByGenderParticipation"%>
<%@page import="voting.app.entities.VoterStatsbyConstitutionParticipation"%>
<%@page import="voting.app.entities.VoterStatsByAge"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
  <title>Voter Stats</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 20px;
    }
    h2 {
      margin-top: 30px;
      color: #333;
    }
    table {
      border-collapse: collapse;
      width: 100%;
      background-color: #fff;
      box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
      border-radius: 8px;
      overflow: hidden;
      margin-bottom: 20px;
    }
    th, td {
      border: 1px solid #ddd;
      text-align: left;
      padding: 12px;
    }
    th {
      background-color: #f15a29;
      color: white;
      font-weight: bold;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    tr:hover {
      background-color: #f5f5f5;
    }
  </style>
</head>
<body>
<%
	List<VoterStatsByAge> statsByAges = 
				(List<VoterStatsByAge>)request.getAttribute("statsByAges");

	List<VoterStatsbyConstitutionParticipation> constitutionParticipations = 
			(List<VoterStatsbyConstitutionParticipation>)request.getAttribute("constitutionParticipations");

	List<VoterStatsByGenderParticipation> genderParticipations = 
			(List<VoterStatsByGenderParticipation>)request.getAttribute("genderParticipations");

	List<VoterStatsByVotersParticipation> statsByVotersParticipations = 
			(List<VoterStatsByVotersParticipation>)request.getAttribute("statsByVotersParticipations");
	%>
	
	<h2>Voters by Age</h2>
<table>
  <tr>
    <th>Age Range</th>
    <th>Total Voters</th>
  </tr>
  <% for (VoterStatsByAge stats : statsByAges) { %>
  <tr>
    <td><%= stats.getAgeRange() %></td>
    <td><%= stats.getTotalCountOfVoters() %></td>
  </tr>
  <% } %>
</table>

<h2>Constitutions</h2>
<table>
  <tr>
    <th>Constitution Name</th>
    <th>Total Voters Registered</th>
    <th>Total Votes Casted</th>
  </tr>
  <% for (VoterStatsbyConstitutionParticipation stats : constitutionParticipations) { %>
  <tr>
    <td><%= stats.getConstitution() %></td>
    <td><%= stats.getTotalVotersRegistered() %></td>
    <td><%= stats.getTotalVotesCasted() %></td>
  </tr>
  <% } %>
</table>

<h2>Gender Participation</h2>
<table>
  <tr>
    <th>Total Male Voters</th>
    <th>Total Female Voters</th>
    <th>Percentage of Male Voters</th>
    <th>Percentage of Female Voters</th>
  </tr>
  <% for (VoterStatsByGenderParticipation stats : genderParticipations) { %>
  <tr>
    <td><%= stats.getMaleVoters() %></td>
    <td><%= stats.getFemaleVoters() %></td>
    <td><%= stats.getPercentageOfMaleVoted() %></td>
    <td><%= stats.getPercentageOfFemaleVoted() %></td>
  </tr>
  <% } %>
</table>

<h2>Voter Participation</h2>
<table>
  <tr>
    <th>Total Voters Registered</th>
    <th>Total Votes Casted</th>
  </tr>
  <% for (VoterStatsByVotersParticipation stats : statsByVotersParticipations) { %>
  <tr>
    <td><%= stats.getTotalVotersRegistered() %></td>
    <td><%= stats.getTotalVotesCasted() %></td>
  </tr>
  <% } %>
</table>

</body>
</html> 

 


  
