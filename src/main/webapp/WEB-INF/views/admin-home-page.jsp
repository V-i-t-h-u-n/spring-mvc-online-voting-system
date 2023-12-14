<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home page</title>
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

        header {
            background-color: #FFCC80; /* Less saturated orange header */
            color: #333;
            padding: 20px 0;
            text-align: center;
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

        footer {
            text-align: center;
            padding: 20px 0;
            background-color: #FFCC80; /* Less saturated orange footer */
            color: #333;
            margin-top: auto; /* Push the footer to the bottom */
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome Admin</h1>
    </header>

    <div class="link-container">
        <div class="link-card">
            <h2>Election Commissioner Login</h2>
            <a href="election-commissioner-login">Election Commissioner Login</a>
        </div>
        <div class="link-card">
            <h2>Regional Commissioner Login</h2>
            <a href="regional-login/regional-commissioner-login">Regional Commissioner Login</a>
        </div>
        <div class="link-card">
            <h2>Regional Commissioner Registration</h2>
            <a href="regional-login/regional-commissioner-registration">Regional Commissioner Registration</a>
        </div>
        <div class="link-card">
            <h2>See Candidate List</h2>
            <a href="candidate/candidate_list">See List</a>
        </div>
        <div class="link-card">
            <h2>Admin FAQ's</h2>
            <a href="admin-faq">See FAQ's</a>
        </div>
    </div>

    <footer>
        <p>&copy; 2023 Your Voting App. All rights reserved.</p>
    </footer>
</body>
</html>