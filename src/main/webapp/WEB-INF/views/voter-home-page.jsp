<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voter Home</title>
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

        footer {
            text-align: center;
            padding: 20px 0;
            background-color: #FFCC80; /* Less saturated orange footer */
            color: #333;
        }

        .card-container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
            gap: 20px;
            padding: 20px;
            margin-top: 20px;
        }

        .card {
            width: 300px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .card h2 {
            color: #FFCC80; /* Less saturated orange heading */
        }

        .card a {
            display: block;
            padding: 10px 20px;
            background-color: #FFCC80; /* Less saturated orange button */
            color: #333;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        .card a:hover {
            background-color: #FFB74D; /* Darker less saturated orange on hover */
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome Voter</h1>
    </header>

    <div class="card-container">
        <div class="card">
            <h2>Voter Registration</h2>
            <a href="voter/voter-registration">Voter Registration</a>
        </div>
        <div class="card">
            <h2>Voter Login</h2>
            <a href="voter/voter-login">Click to Login</a>
        </div>
        <div class="card">
            <h2>See Candidate List</h2>
            <a href="candidate_list">See List</a>
        </div>
        <div class="card">
            <h2>See FAQ's</h2>
            <a href="voter-faq">See FAQs</a>
        </div>
    </div>

    <footer>
        <p>&copy; 2023 Your Voting App. All rights reserved.</p>
    </footer>
</body>
</html>