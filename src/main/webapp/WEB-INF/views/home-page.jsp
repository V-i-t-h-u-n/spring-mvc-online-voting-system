<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Voting System</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f8f9;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Ensure the page fills the viewport */
        }

        header {
            background: linear-gradient(to right, #4CAF50, #ffa500); /* Gradient from green to orange */
            color: #fff;
            padding: 20px 0;
            text-align: center;
        }

        footer {
            text-align: center;
            padding: 20px 0;
            background: linear-gradient(to right, #dfc189, #81af83); /* Gradient from orange to green */
            color: #fff;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
            padding: 20px;
            flex: 1; /* Fill remaining vertical space */
            margin-bottom: 80px; /* Adjust for footer space */
        }

        .card {
            width: 300px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 20px;
            text-align: center;
        }

        .card h2 {
            color: #ffa500; /* Orange heading */
        }

        .card p {
            color: #777;
            line-height: 1.6;
        }

        .card a {
            display: block;
            padding: 10px 20px;
            background-color: #4CAF50; /* Green button */
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        .card a:hover {
            background-color: #45a049; /* Darker green on hover */
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to Voting Application</h1>
    </header>

    <div class="container">
        <div class="card">
            <h2>Voter</h2>
            <a href="voter-home-page">Login as Voter</a>
        </div>

        <div class="card">
            <h2>Candidate</h2>
            <a href="candidate-home-page">Login as Candidate</a>
        </div>

        <div class="card">
            <h2>Admin</h2>
            <a href="admin-home-page">Login as Admin</a>
        </div>
    </div>

    <footer>
        <p>&copy; 2023 Your Voting App. All rights reserved.</p>
    </footer>
</body>
</html>