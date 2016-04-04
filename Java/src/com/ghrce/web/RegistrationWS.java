package com.ghrce.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.ghrce.DBAdapter;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.ghrce.bean.User;
@Path("/register")
public class RegistrationWS {

	@POST
	@Path("/post")
	@Consumes("application/json")
	
	public Response registerUser(User user)
	{
		Connection connection;
		Statement st;
		ResultSet rs;
		PreparedStatement preparedStatement;
		int val;
		try{
			
			String fname=user.getFname();
			String lname=user.getLname();
			String email=user.getEmail();
			String mobile=user.getMobile();
			String pass=user.getPassword();
			connection=DBAdapter.getConnection();
			st=connection.createStatement();
			val=st.executeUpdate("insert into user values('"+email+"','"+pass+"')");
			if(val==1)
			{
				rs=(ResultSet) st.executeQuery("select userid from user where Username='"+email+"' AND Password='"+pass+"'");
				rs.next();
				String userid=rs.getString("UserId");
				String insertTableSQL = "INSERT INTO user_info"
						+ "(UserId, Fname, Lname, Phone,Email) VALUES"
						+ "(?,?,?,?,?)";
				preparedStatement = (PreparedStatement) connection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, userid);
				preparedStatement.setString(2, fname);
				preparedStatement.setString(3, lname);
				preparedStatement.setString(4, mobile);
				preparedStatement.setString(5, email);
				// execute insert SQL stetement
				val=preparedStatement .executeUpdate();
				
				rs.close();
				st.close();
				connection.close();
				
				if(val==1)
					return Response.status(201).entity("true").build();
				else
					return Response.status(401).entity("false").build();
			}
			else
			{
				return Response.status(401).entity("false").build();
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	

}
