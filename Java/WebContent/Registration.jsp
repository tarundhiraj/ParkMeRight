<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%
	String fname = request.getParameter("FirstName");
	String lname = request.getParameter("LastName");
	String stid = request.getParameter("StaffId");
	String fon = request.getParameter("Phone");
	String email = request.getParameter("EmailId");
	out.println(fname+lname+stid+fon+email);
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/ParkMeRight", "root", "admin");
	
	Statement st=con.createStatement();
	int val;
	val= st.executeUpdate("INSERT INTO `parkmeright`.`user_info` (`UserId`, `Fname`, `Lname`, `Phone`, `Email`) VALUES ('"+stid+"', '"+fname+"', '"+lname+"', '"+fon+"', '"+email+"')");
	
	
	response.sendRedirect("Booking.jsp");
%>