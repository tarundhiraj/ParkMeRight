<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Profile</title>
<link href="css/profile.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="Booking_files/css3menu0/style.css" type="text/css" />
<style type="text/css">
._css3m {
	display:none
}
</style>

<link rel="stylesheet" href="css/flat-ui.css" type="text/css" />

</head>
<body>
<%
	String name=session.getAttribute("userid").toString();
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/ParkMeRight", "root", "admin");
	String sql = "SELECT * FROM user_info WHERE Email='"+name+"'";
						
	Statement statement=con.createStatement();
	ResultSet rs=statement.executeQuery(sql);
    rs.next();

	
%>

<div id="head">&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<img src="images/logo.jpg" width="380" height="70" alt="logo" /></div>

<div align="right">
  <ul id="css3menu0" class="topmenu" >
    <li class="topmenu"> <a class="pressed" href="#" style="height:32px;line-height:32px;"><span><img src="Booking_files/css3menu0/service.png" alt=""/>
      <%= rs.getString(3) %>

</span></a>
      <ul>
        <li class="subfirst"><a href="Booking.jsp"><img src="Booking_files/css3menu0/home.png" alt=""/>Home</a></li>
        <li><a href="Profile.jsp"><img src="Booking_files/css3menu0/service1.png" alt=""/>Profile</a></li>
        <li class="sublast"><a href="Logout.jsp"><img src="Booking_files/css3menu0/register.png" alt=""/>Logout</a></li>
      </ul>
    </li>
  </ul>
</div>
<!-- End css3menu.com BODY section -->


<div class="tagline"><h2 align="center"><strong><%=rs.getString(3)+" "+rs.getString(4)%></strong></h2>
</div>

     <section class="prof">
        <div class='titulo'>Information Submitted</div>
		<form method='post' action='#'>
          <label>First Name:</label><input type='text' disabled="disabled" name='FirstName' value="<%= rs.getString(3) %>"><br/>
		  <br/><label>Last Name:</label><input type='text' disabled="disabled"  name='LastName' value="<%= rs.getString(4) %>"><br/>
		  <br/><label>Staff ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type='text' disabled="disabled" name='StaffId' value="<%= rs.getString(2) %>"><br/>
		  <br/><label>Contact:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type='text' disabled="disabled" name='Phone' value="<%= rs.getString(5) %>"><br/>
		  <br/><label>Email ID:&nbsp;&nbsp;&nbsp;</label><input type='text' disabled="disabled" name='EmailId' value="<%= rs.getString(6) %>"><br/>
		  
		</form>
	</section>
<div id="foot"></div>
</body>
</html>