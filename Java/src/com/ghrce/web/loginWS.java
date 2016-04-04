package com.ghrce.web;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ghrce.DBAdapter;
import com.mysql.jdbc.ResultSet;
import com.ghrce.bean.UserLogin;

@Path("/login")
public class loginWS {

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response checkValidity(UserLogin ul)
	{
		Connection connection;
		Statement st;
		ResultSet rs;
		try{
			
			
			connection=DBAdapter.getConnection();
			st=connection.createStatement();
			rs=(ResultSet) st.executeQuery("select userid from user where Username='"+ul.getUsername()+"' AND Password='"+ul.getPassword()+"'");
			if(rs.next())
			{
				rs.close();
				st.close();
				connection.close();
				return Response.status(201).entity("true").build();
			}
			else
			{
				rs.close();
				st.close();
				connection.close();
				return Response.status(401).entity("false").build();
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	@GET
	@Path("/get")
	@Produces("application/json")
	public UserLogin getProductInJSON() {
		System.out.println("get request called");
		UserLogin userLogin = new UserLogin();
		userLogin.setPassword("abc");
		userLogin.setUsername("xyz");
		
		return userLogin; 

	}
}
