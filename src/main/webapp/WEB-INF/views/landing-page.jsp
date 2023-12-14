<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Voting System</title>

<style>
    /* General styles */
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }

    /* Header styles */
    header {
      background-color: #f5ad42;
      color: #fff;
      text-align: center;
      padding: 20px;
    }

    h1, h3 {
      margin: 10px 0;
    }

    /* Navigation bar styles */
    nav ul {
      list-style: none;
      padding: 0;
      display: flex;
      justify-content: center;
      background-color: #f5ad42;
      padding: 10px;
    }

    nav ul li {
      margin: 0 10px;
    }

    nav ul li a {
      text-decoration: none;
      color: #333;
      padding: 5px 10px;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    nav ul li a:hover {
      background-color: #ffe082;
    }

    /* Main content styles */
    main {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-wrap: wrap;
      padding: 20px;
    }

    .card {
      background-color: #f9a825;
      border: 1px solid #f9a825;
      padding: 20px;
      margin: 10px;
      width: 250px;
      height: 140px;
      border-radius: 8px;
      text-align: center;
      transition: background-color 0.3s ease;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }

    .card a {
      text-decoration: none;
      color: #fff;
      padding: 8px 12px;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .card:hover {
      background-color: #ffca28;
    }

    /* Footer styles */
    footer {
      background-color: #f9a825;
      color: #fff;
      text-align: center;
      padding: 10px;
      position: fixed;
      bottom: 0;
      width: 100%;
    }
  </style>
</head>
<body>
	<header>
		<h1>Welcome to Voting system!</h1>
		<h3>Navigation Links</h3>
		<nav>
			<ul>
				<li><a href="election-commissioner-login">Election
						Commissioner Login</a></li>
				<li><a href="regional-login/regional-commissioner-login">Regional
						Commissioner Login</a></li>
				<li><a href="regional-login/regional-commissioner-registration">
				Regional Commissioner Registration</a></li>
			</ul>
		</nav>
	</header>

	<main>
		<div class="card">
			<h2>Candidate Registration</h2>
			<a href="candidate/candidate_registration">Candidate Registration</a>
		</div>

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
			<a href="candidate/candidate_list">See List</a>
		</div>
		<a href = "home-page">Home page</a>
	</main>
</body>
</html>