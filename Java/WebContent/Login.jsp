<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("pass");
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkMeRight",
            "root", "admin");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from user where username='" + userid + "' and password='" + pwd + "'");
    if (rs.next()) {
    	session.setAttribute("userid", userid);
        
        response.sendRedirect("Welcome.html");
    } else {
  %>      
    	<html xmlns="http://www.w3.org/1999/xhtml">
    	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    	<title>Welcome To ParkMeRight</title>
    	<link href="css/login.css" rel="stylesheet" type="text/css" />

    	<script type="text/javascript">
    	    function SubmitFrm(){
    	           window.location = "ForogtPassword.jsp";
    	    }
    	</script>
    	</head>
    	<body background="images/background.jpg">
    	<br/> <br/>
    	<div class="tagline"><h1 align="center"><strong>ParkMeRight</strong></h1>
    	</div>
    	  
    	    <section class="login">
    	      <div class="titulo">Staff Login</div>
    	      <form action="http://localhost:8080/ParkMeRight/Login.jsp" method="post" enctype="application/x-www-form-urlencoded">
    	        <input type="text" name="uname" required title="Username required" placeholder="Username or email" data-icon="U" />
    	        <input type="password" name="pass" required title="Password required" placeholder="Password" data-icon="x" />
    	        <br />
    	        <font color="red">Incorrect Username or Password</font>
    	        &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
    	         <input type="submit" align="center" value="LOGIN"></input>
    	                
    	      </form>
    	      <div class="olvido">
    	          <div class="col"><a href="#" title="forgot password" onclick="javascript:SubmitFrm()">Forgot Password?</a></div>
    			 </div>
    	    </section>

    	<br/> <br/> <br/> <br/>
    	<div class="tail">

    	  <h6 align="center"><strong>A smarter way to manage your Multi-Storey Parking Lot.</strong></h6>
    	</div>
    	</body>
    	</html>

    	
    	
  <%	
    }
   
%>