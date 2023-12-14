<%@page import="java.io.IOException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="com.itextpdf.text.DocumentException"%>
<%@page import="com.itextpdf.text.Element"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.util.Base64"%>
<%@page import="voting.app.entities.VoterDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voter Voting Status</title>

<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        .certificate {
            border: 5px solid #0056b3;
            border-radius: 15px;
            padding: 20px;
            margin: 20px auto;
            max-width: 600px;
            background-color: #fff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        .certificate-header {
            color: #0056b3;
            font-size: 24px;
            margin-bottom: 10px;
        }
        .voter-name {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
        }
        .profile-pic-container {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .profile-pic {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            border: 5px solid #0056b3;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        .details {
            margin-bottom: 15px;
        }
        .voted-success {
            font-size: 20px;
            color: #008000;
            font-weight: bold;
            margin-top: 10px;
        }
        .voted-not-success {
            font-size: 20px;
            color: #ff0000;
            font-weight: bold;
            margin-top: 10px;
        }
        .footer {
            margin-top: 20px;
            font-style: italic;
            color: #888;
        }
        hr {
            border: 0;
            border-top: 1px solid #ddd;
        }
    </style>
</head>
<body>
    
    <%
        List<VoterDetails> voterDetails = (List<VoterDetails>) request.getAttribute("voterDetails");
    	String base64IdProof = Base64.getEncoder().encodeToString(
			(voterDetails.get(0).getVoterProfilePic()
					.getBytes(1, (int) 
					(voterDetails.get(0).getVoterProfilePic()
							.length()))));
    
    
    
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        for (VoterDetails voter : voterDetails) {
            PdfPCell cell = new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            Paragraph heading1 = new Paragraph("Online Voting System", titleFont);
            heading1.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(heading1);

            Paragraph heading2 = new Paragraph("Voter Status Slip", titleFont);
            heading2.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(heading2);

            byte[] decodedImage = Base64.getDecoder().decode(base64IdProof);
            
            Image image = Image.getInstance(decodedImage);
            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleAbsolute(100f, 100f);
            cell.addElement(image);
           
			String date = voter.getCurrentDateTime().toString().split("T")[0];
			String time = voter.getCurrentDateTime().toString().split("T")[1];
            Paragraph name = new Paragraph("Name: " + voter.getVoterName(), regularFont);
            Paragraph age = new Paragraph("Dob: " + voter.getVoterDateOfBirth(), regularFont);
            Paragraph constitution = new Paragraph("Constitution: " + voter.getConstitution(), regularFont);
            Paragraph electionDate = new Paragraph("Election Date: " + voter.getElectionStartDate(), regularFont);
            Paragraph electionName = new Paragraph("Election Name: " + voter.getElectionName(), regularFont);
            Paragraph timeoOfVoting = new Paragraph("Time of Voting: " + time + "  Date: " + date  , regularFont);
            Paragraph hasVoted = new Paragraph(" Voting Status: " + (voter.isStatus() ? "Voted" : "Not Voted"), regularFont);
            
            cell.addElement(name);
            cell.addElement(age);
            cell.addElement(constitution);
            cell.addElement(electionDate);
            cell.addElement(electionName);
            cell.addElement(timeoOfVoting);
            cell.addElement(hasVoted);


            table.addCell(cell);
            document.add(table);
            document.add(new Paragraph("\n"));
        }

        document.close();

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        String base64EncodedPDF = Base64.getEncoder().encodeToString(pdfBytes);
    %>
    
     <% for (VoterDetails voter : voterDetails) { %>
        <div class="certificate">
            <div class="certificate-header">Certificate of Voting</div>
            <div class="voter-name"><%= voter.getVoterName() %></div>
            <div class="profile-pic-container">
                <img class="profile-pic" src="data:image/png;base64, 
				<%=base64IdProof%>" alt="Profile Pic">
            </div>
            <div class="details">
                <div class="voter-details">Date of Birth: <%= voter.getVoterDateOfBirth() %></div>
                <div class="voter-details">Constitution: <%= voter.getConstitution() %></div>
                <div class="voter-details">Election Name: <%= voter.getElectionName() %></div>
                <div class="voter-details">Election Start Date: <%= voter.getElectionStartDate() %></div>
            </div>
            <% if (voter.isStatus()) { %>
                <div class="voted-success">Status: Voted Successfully</div>
            <% } else { %>
                <div class="voted-not-success">Status: Vote Not Cast Yet</div>
            <% } %>
            <div class="footer">Current Date Time: <%= voter.getCurrentDateTime() %></div>
        </div>
        <hr>
    <% } %>
	
    

    <a href="data:application/pdf;base64,<%= base64EncodedPDF %>" download="voter_details.pdf">Download PDF</a>
    
    
    
    
</body>
</html>
