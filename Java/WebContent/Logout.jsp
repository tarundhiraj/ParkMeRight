<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
session.setAttribute("userid", null);
session.invalidate();
response.sendRedirect("Login.html");
%>