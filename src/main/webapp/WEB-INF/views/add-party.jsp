<%@page import="voting.app.entities.NewElection"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Parties</title>

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

        /* Form Styles */
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 60%; /* Adjusted width for the form */
            background-color: #fff; /* White background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        label {
            margin-bottom: 10px;
        }

        select,
        input[type="text"],
        input[type="file"],
        input[type="submit"] {
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #FFCC80; /* Less saturated orange */
            color: #333;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #FFB74D; /* Slightly darker on hover */
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

<%-- <%= request.getAttribute("electionList") %>
 --%>
 
 <%
	String ctx = application.getContextPath();
	//out.print(ctx);
	String home = ctx + "/home";
	%>
 
 
<%
	List<NewElection> electionList = 
	(List<NewElection>) request.getAttribute("electionList");
	%>
	<header>
        <h1>Add Party</h1>
        <p><a href="<%=home%>" class="home-button">Home Page</a></p>
    </header>
	
	<form action="addPartyForm" method="post" enctype="multipart/form-data">
        <label for="newParty"><b>Party name</b></label>
        <input type="text" placeholder="Enter party name" name="newParty" id="party-name" required>

        <label for="electionList"><b>Choose an Election</b></label>
        <select name="electionId" id="electionList">
            <% for(int i = 0; i < electionList.size(); i++) { %>
                <option value="<%= electionList.get(i).getElectionId() %>">
                    <%= electionList.get(i).getElectionName() + " - " + electionList.get(i).getElectionDate() %>
                </option>
            <% } %>
        </select>

        <label for="logo"><b>Party Logo</b></label>
        <input type="file" placeholder="Choose Party Logo" name="partyLogo" id="logo" accept=".jpg, .jpeg, .png, .pdf" required>

        <label for="manifesto"><b>Party Manifesto</b></label>
        <input type="file" placeholder="Choose Party Manifesto" name="partyManifesto" id="manifesto" accept=".jpg, .jpeg, .png, .pdf" required>

        <button type="submit" class="registerbtn">Add Party</button>
    </form>
    
    <footer>
        <p>&copy; 2023 Your Voting App. All rights reserved.</p>
	</footer>
    
	
	
	<%-- <form action="addPartyForm" method = "post" 
			enctype="multipart/form-data">
			
			<br><br>
			<label for="newParty"><b>Party name</b></label>
            <input type="text" placeholder="Enter party name " name="newParty" id="party-name" required>
            <br>
			
			
			<label for="electionList"><b>Choose a Election</b></label>
             <select name="electionId" id="electionList">
            <%
            	for(int i = 0;i < electionList.size();i++){
            %>
           
                <option value="<%=electionList.get(i).getElectionId()%>">
                	<%= electionList.get(i).getElectionName() + " - " +
                		electionList.get(i).getElectionDate()
                %>
                </option>
            <%
            	}
            %>
              </select>
              
            <label for="logo"><b>Party Logo</b></label>
            <input type="file" placeholder="Choose Party Logo" name="partyLogo" id="logo" accept=".jpg, .jpeg, .png, .pdf" required>
            <br><br>
            
            <label for="manifesto"><b>Party Manifesto</b></label>
            <input type="file" placeholder="Choose Party Manifesto" name="partyManifesto" id="manifesto" accept=".jpg, .jpeg, .png, .pdf" required>
            <br><br>
              
              <button type="submit" class="registerbtn">Add Party</button>
			
	</form>
 --%>	
	

</body>
</html>






<!-- <style>
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

        /* Form Styles */
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 60%; /* Adjusted width for the form */
            background-color: #fff; /* White background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        label {
            margin-bottom: 10px;
        }

        select,
        input[type="text"],
        input[type="file"],
        input[type="submit"] {
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #FFCC80; /* Less saturated orange */
            color: #333;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #FFB74D; /* Slightly darker on hover */
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
        <p>Add Party</p>
        <p><a href="landing-page" class="home-button">Home Page</a></p>
        <p><a href="#" class="logout-button">Logout</a></p>
    </header>

    <form action="addPartyForm" method="post" enctype="multipart/form-data">
        <label for="newParty"><b>Party name</b></label>
        <input type="text" placeholder="Enter party name" name="newParty" id="party-name" required>

        <label for="electionList"><b>Choose an Election</b></label>
        <select name="electionId" id="electionList">
            Your options here
        </select>

        <label for="logo"><b>Party Logo</b></label>
        <input type="file" placeholder="Choose Party Logo" name="partyLogo" id="logo" accept=".jpg, .jpeg, .png, .pdf" required>

        <label for="manifesto"><b>Party Manifesto</b></label>
        <input type="file" placeholder="Choose Party Manifesto" name="partyManifesto" id="manifesto" accept=".jpg, .jpeg, .png, .pdf" required>

        <button type="submit" class="registerbtn">Add Party</button>
    </form> -->