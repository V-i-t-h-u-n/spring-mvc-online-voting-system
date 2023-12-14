package voting.app.utilities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	public void sendEmail(String recipientEmail,String OTP) {

		final String username = "javaonlinevotingsystem@gmail.com"; // Your email
		final String password = "asbcagksrhoyjvyz"; // Your password

		Properties props = new Properties();
		 props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com"); // Gmail's SMTP host
	        props.put("mail.smtp.port", "587"); // Gmail's SMTP port

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Verification Email");
			message.setText("Dear Candidate," + "\n\n Please Enter the OTP to verify your email."
					+ "Your one time password id : " + OTP);

			Transport.send(message);

			System.out.println("Email sent successfully!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
