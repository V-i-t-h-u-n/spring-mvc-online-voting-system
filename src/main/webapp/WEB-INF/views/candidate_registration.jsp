<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="voting.app.entities.Party"%>
<%@page import="voting.app.entities.NewElection"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidate Registration</title>
<style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: black;
        }

        * {
            box-sizing: border-box;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
            background-color: white;
        }

        /* Full-width input fields */
         input[type=text],
        input[type=email],
        input[type=date],
        input[type=file],
        select,
        input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus,
        input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        /* Set a style for the submit button */
        .registerbtn {
            background-color: #04AA6D;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .registerbtn:hover {
            opacity: 1;
        }

        /* Add a blue text color to links */
        a {
            color: dodgerblue;
        }

        /* Set a grey background color and center the text of the "sign in" section */
        .signin {
            background-color: #f1f1f1;
            text-align: center;
        }
    </style>

</head>
<body>

<%-- <%
	String ctx = application.getContextPath();
	//out.print(ctx);
	String home = ctx + "candidate-home-page";
	%> --%>
 --%>	
	
	<%
	List<NewElection> candidateList = 
	(List<NewElection>) request.getAttribute("electionList");
	
	List<Party> partyList = (List<Party>) request.getAttribute("partyList");

	%>
	<form action="candidateRegistrationForm" method = "post" 
			enctype="multipart/form-data">
        <div class="container">
            <h1>Register</h1>
            <p> Enter the necessary details to Register</p>
            <hr>

            <label for="name"><b>Name</b></label>
            <input type="text" placeholder="Enter Name" name="candidateName" id="name" required>
            <br>
            <label for="dob"><b>Date of Birth</b></label>
            <input type="date" placeholder="Enter Dob" name="candidateDob" id="dob" required>
            <br><br>
            
            <script>
                document.addEventListener('DOMContentLoaded',function(){
                        var today=new Date();
                        var year=today.getFullYear()-25;
                        var month=String(today.getMonth()+1).padStart(2,0);
                        var day=String(today.getDate()).padStart(2,0);

                        var formatedToday=year+'-'+month+'-'+day;

                        document.getElementById('dob').max=formatedToday;
                });
            </script>
            
              
              <label for="party-name"><b>Choose a Party</b></label>
		        <select name="partyName" id="party-name">
		            <% 
		                Set<String> uniquePartyNames = new HashSet<>();
		                for(Party party : partyList) {
		                    for(NewElection candidate : candidateList) {
		                        if (party.getElectionId() == candidate.getElectionId()) {
		                            uniquePartyNames.add(party.getPartyName());
		                            break;
		                        }
		                    }
		                }
		                
		                for (String uniqueName : uniquePartyNames) {
		            %>
		                    <option value="<%= uniqueName %>">
		                        <%= uniqueName %>
		                    </option>
		            <%  } %>
		        </select>
           
           	<label for="email"><b>Email</b></label>
            <input type="email" placeholder="Enter email" name="email" id="email" required>
            <br>
           
            <label for="constitution"><b>Constitution</b></label>
            <input type="text" placeholder="Enter Participating constitution" name="constitution" id="party-name" required>
            <br>
            <!-- <label for="manifesto"><b>Party Manifesto</b></label>
            <input type="file" placeholder="Choose Party Manifesto" name="partyManifesto" id="manifesto" accept=".jpg, .jpeg, .png" required>
            <br><br>
            <label for="logo"><b>Party Logo</b></label>
            <input type="file" placeholder="Choose Party Logo" name="partyLogo" id="logo" accept=".jpg, .jpeg, .png, .pdf" required>
            <br><br> -->
            
            <label for="proof"><b>Choose your Id proof</b></label>
            <input type="file" placeholder="Choose id proof" 
            name="idProof" id="proof"  required>
            <br><br>
            
            <label for="dp"><b>Choose your Display Image</b></label>
            <input type="file" placeholder="Choose id Display Image" 
            name="dp" id="dp"  required>
            <br><br>
            
              <label for="electionList"><b>Choose an Election</b></label>
		        <select name="electionId" id="electionList">
		            <% for(int i = 0; i < candidateList.size(); i++) {
		                NewElection candidate = candidateList.get(i);
		                for (Party party : partyList) {
		                    if (party.getElectionId() == candidate.getElectionId()) {
		            %>
		                        <option value="<%= candidate.getElectionId() + "," + candidate.getElectionName() %>">
		                            <%= candidate.getElectionName() + " - " + candidate.getElectionDate() %>
		                        </option>
		            <%          break;
		                    }
		                }
		            } %>
		        </select>
           
            <button type="submit" class="registerbtn">Register</button>
        </div>

    </form>
	
</body>
</html>


<%--  <label for="party-name"><b>Choose a Party</b></label>
             <select name="partyName" id="party-name">
            <%
            	for(int i = 0;i < partyList.size();i++){
            		
            %>
               <option value = "<%= partyList.get(i).getPartyName() %>">
               		<%= partyList.get(i).getPartyName() %>
               </option>
            <%
            	}
            %>
              </select> --%>

<%-- <label for="electionList"><b>Choose a Election</b></label>
             <select name="electionId" id="electionList">
      			
            <%
            	for(int i = 0;i < candidateList.size();i++){
            %>
            
                <option value="<%=candidateList.get(i).getElectionId()
                	+ ","+ candidateList.get(i).getElectionName()
                %>">
                	<%= candidateList.get(i).getElectionName() %>
                	
                	 
                </option>
                
                
            <%
            	}
            
            %>
              </select> --%>