<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.sql.*" %>
    <%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="com.ghrce.MailUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META HTTP-EQUIV="refresh" CONTENT="3;URL=Login.html">
<title>Forgot Password</title>
</head>
<body>
<% 

	String email = request.getParameter("txt");    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkMeRight","root", "admin");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select Password from user where username='" + email + "'");
    
   if (rs.next()) {
	   String[] recipients = new String[]{email};  
       String[] bccRecipients = new String[]{"sharma_monika.ghrcecs@raisoni.net"};  
       String subject = "Password for ParkMeRight";  
       String messageBody = "Your Password for ParkMeRight is"+rs.getString(1);  
 
       new MailUtil().sendMail(recipients, bccRecipients, subject, messageBody);

         
	   
%>
    <div align="center">"Password sent to your registered EmailId!!!"</div>
    <% } else { %>
    <div align="center">EmailId not registered with ParkMeRight!!!</div>
    <% } %>


</body>
</html>