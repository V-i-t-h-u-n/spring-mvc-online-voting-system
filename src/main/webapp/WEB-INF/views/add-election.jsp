<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Election</title>

<style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f5f9; /* Light blue background */
            display: flex;
            flex-direction: column;
            min-height: 100vh;
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
        }

        label {
            margin-bottom: 5px;
        }

        select,
        input[type="date"],
        input[type="submit"] {
            padding: 8px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 300px; /* Adjusted width for dropdown */
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
<%
	String ctx = application.getContextPath();
	//out.print(ctx);
	String home = ctx + "/home";
	%>
    <header>
        <h1>Add Election</h1>
        <p><a href="<%=home%>" class="home-button">Home Page</a></p>
    </header>

    <form action="addElectionFormDetails" method="post">
        <h1></h1>
        <label for="electionList">Choose an Election:</label>
        <select name="electionList" id="electionList" required="required">
            <option value="Lok sabha">Members of Parliament in the Lok Sabha </option>
            <option value="Rajya sabha">Members of Parliament in the Rajya Sabha</option>
            <option value="counsil">Members of State Legislative Councils</option>
            <option value="Legislative assembly">Members of State Legislative assembly</option>
        </select>
        <label for="electionDate">Election Date:</label>
        <input type="date" name="electionDate" min="<%= java.time.LocalDate.now() %>" required="required">
        <input type="submit" value="Submit">
    </form>
    
	<footer>
		<p>&copy; 2023 Your Voting App. All rights reserved.</p>
	</footer>

</body>
</html>