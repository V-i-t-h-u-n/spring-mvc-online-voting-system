<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Regional Manager Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
     <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            width: 300px;
            text-align: center;
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        h2 {
            color: #e67e22;
            margin-bottom: 20px;
        }

        .login-form {
            display: flex;
            flex-direction: column;
        }

        input[type="text"],
        input[type="password"] {
            margin: 10px 0;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .captcha-container {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-top: 20px;
        }

        #captchaDisplay {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 10px;
        }

        .refresh-captcha {
            border: none;
            background: none;
            cursor: pointer;
        }

        .refresh-icon {
            color: #e67e22;
        }

        button[type="submit"] {
            margin: 20px 0;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #e67e22;
            color: #fff;
            cursor: pointer;
        }

        a {
            color: #e67e22;
            text-decoration: none;
        }

        .forget-password,
        .sign-up {
            margin-top: 10px;
        }
        
        
        .refresh-captcha {
            border: none;
            background: none;
            cursor: pointer;
            margin-left: 10px;
        }
    </style> 
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1.
response.setHeader("Pragma","no-cache");//HTTP 1.0.
%>
    <div class="login-container">
        <h2>Regional Manager login</h2>
        <form class="login-form" action="regionalLoginForm" method="post" onsubmit="return validateCaptcha()">
            <input type="text" placeholder="Enter login Email" name="email" required>
            <input type="password" placeholder="Password" name="password" required><br>
            <label for="userInput">Captcha</label>
            <div class="captcha-container">
                <span id="captchaDisplay">Captcha</span>
                <input type="text" id="userInput" placeholder="Enter Captcha" required>
                <button type="button" class="refresh-captcha" onclick="initCaptcha()">
                    <i class="fas fa-redo"></i>
                </button>
            </div>
            <button type="submit">Login</button>
        </form>
        <%@ include file = "message.jsp" %>
        
    </div>

    <script>
    function generateCaptcha() {
        const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        let captcha = '';
        for (let i = 0; i < 4; i++) {
            captcha += characters.charAt(Math.floor(Math.random() * characters.length));
        }
        return captcha;
    }
    function initCaptcha() {
        const captchaElement = document.getElementById('captchaDisplay');
        captchaElement.textContent = generateCaptcha();
    }
    
    document.addEventListener('DOMContentLoaded', function() {
        initCaptcha();
    });

        function validateCaptcha() {
            const userInput = document.getElementById('userInput').value;
            const captchaText = document.getElementById('captchaDisplay').textContent;

            if (userInput !== captchaText) {
                alert('Captcha is incorrect. Please try again.');
                initCaptcha(); // Refresh captcha
                return false; // Prevent form submission
            }

            return true; // Allow form submission
        }
    </script>
</body>
<!-- <style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #e8e2e2;
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

.login-container {
	background-color: #cdebb6;
	padding: 20px;
	box-shadow: 0 0 10px rgba(227, 222, 222, 0.1);
	border-radius: 8px;
	width: 300px;
	text-align: center;
}

.login-container h2 {
	color: #333;
}

.login-form {
	display: flex;
	flex-direction: column;
	margin-top: 20px;
}

.login-form input {
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.login-form button {
	background-color: #333;
	color: #fff;
	padding: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.login-form button:hover {
	background-color: #555;
}

.forget-password {
	margin-top: 10px;
	color: #333;
}

.sign-up {
	margin-top: 10px;
	color: #333;
}
</style>

</head>
<body>

	<div class="login-container">
		<h2>Regional Manager login</h2>
		<form class="login-form" action = "regionalLoginForm" method = "post">
			<input type="text" placeholder="Login ID" name = "username" required> 
			<input type="password" placeholder="Password" name = "password" required>
			<div class="container">
				<h4>Captcha</h4>
				<p class="cap" id="captcha"></p>
				<input type="text" id="userInput" placeholder="Enter Captcha"
					required>
			</div>

			<script>
				function generateCaptcha() {
					const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
					let captcha = '';
					for (let i = 0; i < 4; i++) {
						captcha += characters.charAt(Math.floor(Math.random()
								* characters.length));
					}
					return captcha;
				}
				function initCaptcha() {
					const captchaElement = document.getElementById('captcha');
					captchaElement.textContent = generateCaptcha();
				}
				function checkCaptcha() {
					const userInput = document.getElementById('userInput').value;
					const captchaText = document.getElementById('captcha').textContent;

					if (userInput === captchaText) {
					} else {
						alert('Captcha is incorrect. Please try again.');
						initCaptcha();
					}
				}
				document.addEventListener('DOMContentLoaded', function() {
					initCaptcha();
				});
			</script>

			<button type="submit" onclick="checkCaptcha()">Login</button>
		</form>
		<a href="Forgetpassword.html" class="forget-password">Forget
			Password?</a>
		<p class="sign-up">
			Don't have an account? <a href="Registration form.html">Sign Up</a>
		</p>
	</div>

</body> -->
</html>