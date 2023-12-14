<%@page import="java.util.Base64"%>
<%@page import="com.mysql.cj.util.Base64Decoder"%>
<%@page import="voting.app.entities.Candidate"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidates</title>

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

	<div id="google_translate_element"></div>
	<script
		src="https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
	<script>
	
		function googleTranslateElementInit() {
			new google.translate.TranslateElement(

			{
				pageLanguage : 'en'
			}, 'google_translate_element');
		}
	</script>

	<%
	List<Candidate> candidateList = (List<Candidate>) 
					request.getAttribute("candidate_list");
	
	%>

	<table>
		<caption>Candidate List</caption>
		<tr>
			<th>Candidate Name</th>
			<th>Candidate Image</th>
			<th>Party Name</th>
			<th>Party Manifesto</th>
			<th>Party Logo</th>
			<th>ElectionName</th>
			<th>Election Constitution</th>

		</tr>
		<%
		for (int i = 0; i < candidateList.size(); i++) {

			String base64Logo = Base64.getEncoder().encodeToString(
			(candidateList.get(i)
					.getPartyLogo())
					.getBytes(1, (int) 
					(candidateList.get(i)
							.getPartyLogo()
							.length())));
			
			String base64Mainfesto = Base64.getEncoder().encodeToString(
					(candidateList.get(i)
							.getPartyManifesto())
							.getBytes(1, (int) 
							(candidateList.get(i)
									.getPartyManifesto()
									.length())));
			
			String base64Image = Base64.getEncoder().encodeToString(
					(candidateList.get(i)
							.getPartyManifesto())
							.getBytes(1, (int) 
							(candidateList.get(i)
									.getPartyManifesto()
									.length())));
		%>

		<tr>
			<td><%=candidateList.get(i).getCandidateName()%></td>
			<td><img src="data:image/png;base64, <%=base64Image%>"
				width="100" /></td>
			<td><%=candidateList.get(i).getPartyName()%></td>
			<td><img src="data:image/png;base64, <%=base64Mainfesto%>"
				width="100" /></td>
			<td><img src="data:image/png;base64, <%=base64Logo%>"
				width="100" /></td>
			<td><%=candidateList.get(i).getElectionName()%></td>
			<td><%=candidateList.get(i).getConstitution()%></td>
			
		</tr>
		<%
		}
		%>

	</table>
	

</body>
</html>