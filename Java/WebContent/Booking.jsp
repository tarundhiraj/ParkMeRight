<%@page import="org.json.JSONObject"%>
<%@page import="com.ghrce.dao.impl.ParkingDAOImpl"%>
<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,com.ghrce.dao.impl.*"%>
<%@page import="org.json.JSONObject" %>

<%
String name=session.getAttribute("userid").toString();

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkMeRight",
        "root", "admin");
Statement st = con.createStatement();
ResultSet rs;
rs = st.executeQuery("select * from floorplan where Floor_No='1'");
ParkingDAOImpl pdi=new ParkingDAOImpl();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Booking</title>
<!-- Start css3menu.com HEAD section -->
<link rel="stylesheet" href="Booking_files/css3menu0/style.css"
	type="text/css" />
<style type="text/css">
._css3m {
	display: none
}
</style>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/book.js"></script>

<link rel="stylesheet" href="css/book.css" type="text/css" />
<link rel="stylesheet" href="css/flat-ui.css" type="text/css" />
<!-- End css3menu.com HEAD section -->


</head>
<body>
	<div id="head">
		&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<img src="images/logo.jpg" width="380" height="70" alt="logo" />
<form action="Search.jsp" method="post">
<input type="text" id="search" name="search" class="form-control input-sm" placeholder="Search Customer" >
<input id="clicksearch" type="submit"/>
</form>	

	</div>
	
	<!-- Start css3menu.com BODY section -->
	<div align="right">
		<ul id="css3menu0" class="topmenu">
			<li class="topmenu"><a class="pressed" href="#"
				style="height: 32px; line-height: 32px;"><span><img
						src="Booking_files/css3menu0/service.png" alt="" /> 
						
<%
 	
 	String sql1 = "SELECT * FROM user_info WHERE Email='" + name + "'";

 	Statement statement = con.createStatement();
 	ResultSet rs1 = statement.executeQuery(sql1);
 	rs1.next();
 %> <%=rs1.getString(3)%> </span></a>
				<ul>
					<li class="subfirst"><a href="Booking.jsp"><img
							src="Booking_files/css3menu0/home.png" alt="" />Home</a></li>
					<li><a href="Profile.jsp"><img
							src="Booking_files/css3menu0/service1.png" alt="" />Profile</a></li>
					
					<li class="sublast"><a href="Logout.jsp"><img
							src="Booking_files/css3menu0/register.png" alt="" />Logout</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- End css3menu.com BODY section -->
	<br />
	<br />
	<br />
	<br />
	
	<div id="container">
		<div id="lefttab">
			<div class="tabBox">
				<ul class="tabs">
					<li class="selected"><a href="#">Floor One</a></li>
					<li><a href="#">Floor Two</a></li>

				</ul>
				<div class="content">
					<h6>Choose slot by clicking the corresponding slot in the
						layout below:</h6>
					<div id="holder">
						<ul id="place">
						</ul>
					</div>


				</div>

				<div id="info">
					&nbsp;Total Slots: 20<br /> <br />&nbsp;Occupied: <%=pdi.getBookedSlots()%><br />&nbsp;Empty:&nbsp;&nbsp;&nbsp;&nbsp;<%=20-pdi.getBookedSlots()%>
				</div>
				<div id="legend" style="float: left;">
					&nbsp; &nbsp;Legend:

					<ul id="seatDescription">
						<li
							style="background: url(images/avail.gif) no-repeat scroll 0 0 transparent; ">Available
							Slot&emsp;</li>
						<li
							style="background: url(images/unavail.gif) no-repeat scroll 0 0 transparent; ">Booked
							Slot&nbsp;&nbsp;&nbsp;</li>
						<li
							style="background: url(images/book.gif) no-repeat scroll 0 0 transparent; ">Selected
							Slot&emsp;</li>
					</ul>



				</div>
			</div>




		</div>


		<div id="righttab" align="center">

			<br /> Booking Id <input id="bookingid" type="text" class="form-control input-sm"
				placeholder="Booking Id" readonly="readonly" /> <br /> <br /> Duration &emsp; <select id="duration">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>

			</select> <br /> <br /> Vehicle Type <input id="vehicletype" type="text"
				class="form-control input-sm" placeholder="Vehicle Type" disabled="disabled" /> <br /><br />
				Vehicle No. <input id="vehicleno" type="text"
				class="form-control input-sm" placeholder="Vehicle Type"/> <br />

			<hr />
			Floor <input id="floorno" type="text" class="form-control input-sm"
				placeholder="Floor"  disabled="disabled" /> <br /> <br /> Slot&nbsp;&nbsp;&nbsp;<input id="slot" 
				type="text" class="form-control input-sm" placeholder="Slot"  disabled="disabled" /> <br />
			<br /> Rate&nbsp;&nbsp;<input id="rate" type="text"
				class="form-control input-sm" placeholder="Rate"  disabled="disabled"/>
			<hr />

			Total&emsp;&nbsp;&nbsp;<input id="total" type="text"
				class="form-control input-sm" placeholder="Total"  disabled="disabled"/> <br /><br/>
				
			<button id="pay" class="btn btn-success">Pay & Book</button>
		</div>
	</div>
	<div id="foot"></div>



</body>
</html>
