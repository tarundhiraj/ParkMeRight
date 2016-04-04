<%@page import="org.json.JSONObject"%>
<%@page import="com.ghrce.dao.impl.ParkingDAOImpl"%>
<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,com.ghrce.dao.impl.*"%>
<%@page import="org.json.JSONObject" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="Booking_files/css3menu0/style.css" type="text/css" />
<style type="text/css">
._css3m {
	display:none
}
</style>

<link rel="stylesheet" href="css/flat-ui.css" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Find The Customer</title>
<link href="css/search.css" rel="stylesheet" type="text/css" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript">
(function(document) {
	'use strict';

	var LightTableFilter = (function(Arr) {

		var _input;

		function _onInputEvent(e) {
			_input = e.target;
			var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
			Arr.forEach.call(tables, function(table) {
				Arr.forEach.call(table.tBodies, function(tbody) {
					Arr.forEach.call(tbody.rows, _filter);
				});
			});
		}

		function _filter(row) {
			var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
			row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
		}

		return {
			init: function() {
				var inputs = document.getElementsByClassName('light-table-filter');
				Arr.forEach.call(inputs, function(input) {
					input.oninput = _onInputEvent;
				});
			}
		};
	})(Array.prototype);

	document.addEventListener('readystatechange', function() {
		if (document.readyState === 'complete') {
			LightTableFilter.init();
		}
	});

})(document);
</script>
</head>
<body>
<%
	String name=session.getAttribute("userid").toString();
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/ParkMeRight", "root", "admin");
	String email=request.getParameter("search");
	String sql = "SELECT * FROM booking WHERE UserId='"+email+"'";
					
	Statement statement=con.createStatement();
	ResultSet rs=statement.executeQuery(sql);
    

	
%>
<div id="head">&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<img src="images/logo.jpg" width="380" height="70" alt="logo" /></div>


<div id="search">
<
<section class="container">

	<h2>Customer Search</h2>

	
	<table class="order-table table">
		<thead>
			<tr>
				<th>Booking-ID</th>
				<th>User_ID</th>
				<th>Floor_No</th>
				<th>Slot_No</th>
				<th>Date</th>
				<th>Booking_Time</th>
				<th>Vehicle_Type</th>
				<th>Vehicle_No</th>
				<th>Entry_Time</th>
				<th>Duration</th>
				<th>Extension</th>
			</tr>
		</thead>
		<tbody>
			<% while(rs.next()){ %>

			<tr>

			<td> <%= rs.getString(1)%></td>

			<td> <%= rs.getString(2)%></td>
			<td> <%= rs.getString(3)%></td>

			<td> <%= rs.getString(4)%></td>
			<td> <%= rs.getString(5)%></td>

			<td> <%= rs.getString(6)%></td>
			<td> <%= rs.getString(7)%></td>

			<td> <%= rs.getString(8)%></td>
			<td> <%= rs.getString(9)%></td>

			<td> <%= rs.getString(10)%></td>
			<td> <%= rs.getString(11)%></td>

			</tr>
			<%} %>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>

</section>
</div>

</br></br></br>
<a href="Booking.jsp">Back</a>
</br>
</br>
</br></br></br>
</br>
<div id="foot"></div>
</body>

</html>