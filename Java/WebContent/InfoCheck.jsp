<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import ="java.sql.*" %>
 <%
 	String uname=session.getAttribute("userid").toString();
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkMeRight",
            "root", "admin");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from user_info where Email='" + uname + "'");
    if (rs.next()) {
    	response.sendRedirect("Booking.jsp");
    }
    else{
    	response.sendRedirect("info.html");
    }
    
  %>