package com.ghrce.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.ghrce.DBAdapter;
import com.mysql.jdbc.ResultSet;

@Path("/forgotpassword")

	
public class ForgotPasswordWS {

	@GET
	@Path("/{param}")
	
	public Response emailCheck(@PathParam("param") String email)
	{
		Connection connection;
		Statement st;
		ResultSet rs;
		try{
			
			
			connection=DBAdapter.getConnection();
			st=connection.createStatement();
			rs=(ResultSet) st.executeQuery("select UserId from user_info where Email='"+email+"'");
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
			((Throwable) e).printStackTrace();
		}
		return null;
		
		
	}
}

