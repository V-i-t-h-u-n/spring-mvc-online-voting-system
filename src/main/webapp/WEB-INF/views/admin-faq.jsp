<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin FAQ's</title>

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f8f8f8;
}

.faq-container {
	width: 80%;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
}

h2 {
	margin-top: 20px;
}

.faq {
	margin-bottom: 30px;
}

.faq p {
	margin: 10px 0;
}
</style>
</head>
<body>
<div class="faq-container">
    <h1>Admin Roles FAQ</h1>

    <div class="faq">
      <h2>1. What are the roles of Root Admin and Sub-Admin in the system?</h2>
      <p>Both Root Admin and Sub-Admin have similar responsibilities, including approving voters, candidates, creating elections, managing parties, and viewing election results.</p>
    </div>

    <div class="faq">
      <h2>2. Is there any difference between Root Admin and Sub-Admin privileges?</h2>
      <p>Yes, the Root Admin has the privilege to approve Sub-Admin accounts. Additionally, only the Root Admin can register Sub-Admins into the system.</p>
    </div>

    <div class="faq">
      <h2>3. How can a Sub-Admin register themselves in the system?</h2>
      <p>Sub-Admins have the ability to self-register by following a specific registration process. However, their approval is subject to confirmation by the Root Admin.</p>
    </div>

    <div class="faq">
      <h2>4. Can Root Admin and Sub-Admin update their profiles?</h2>
      <p>Yes, both Root Admin and Sub-Admin can update their profiles, including personal information and contact details, to ensure accuracy in their administrative roles.</p>
    </div>

    <div class="faq">
      <h2>5. How are login credentials managed for Admin accounts?</h2>
      <p>The login credentials (username and password) are stored securely as hashes in the system. The Admins are responsible for their login details and are informed of any changes made.</p>
    </div>

    <div class="faq">
      <h2>6. What actions can Root Admin and Sub-Admin take in case of forgotten passwords or login issues?</h2>
      <p>Both types of Admins can reset their passwords using a secure "Forgot Password" feature or contact system support for assistance with login issues.</p>
    </div>

    <div class="faq">
      <h2>7. Is there any data shared between Root Admin and Sub-Admin accounts?</h2>
      <p>Access to specific administrative functionalities, approval rights, and administrative controls are unique to each respective account type and are not shared between the two roles.</p>
    </div>

    <div class="faq">
      <h2>8. How are administrative actions audited or tracked in the system?</h2>
      <p>The system maintains an audit trail to track administrative actions performed by both Root Admin and Sub-Admins for transparency and accountability purposes.</p>
    </div>

    <div class="faq">
      <h2>9. Are there any limitations to what Root Admin and Sub-Admins can view or modify within the system?</h2>
      <p>Each Admin role has predefined permissions that limit their actions to specific administrative tasks, ensuring controlled access to system functionalities.</p>
    </div>

    <div class="faq">
      <h2>10. How is data confidentiality maintained for Admin accounts?</h2>
      <p>The system employs robust security measures to maintain the confidentiality of Admin data and ensures that sensitive information remains protected.</p>
    </div>

  </div>

</body>
</html>