<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidate Details Update</title>
</head>
<body>
  <h1>Account Update</h1>
    <form action="verifyEmail" method="post">
        <label for="email">Enter your email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <input type="submit" value="Send OTP">
        <%@ include file = "message.jsp" %>
    </form>
</body>
</html>