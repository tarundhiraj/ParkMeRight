package com.ghrce.web;

import java.sql.Connection;
import com.ghrce.bean.BookingDetail;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.ghrce.DBAdapter;


@Path("/booking")
public class BookingWS {

	@POST
	@Path("/post")
	@Consumes("application/json")
	
	public Response bookSlot(BookingDetail bt) throws SQLException
	{
		Connection connection;
		Statement st;
		connection=(Connection) DBAdapter.getConnection();
		
		st=connection.createStatement();
		int val = st.executeUpdate("insert into booking values('"+bt.getBookingid()+"','"+bt.getUserid()+"'"
				+ ",'"+bt.getFloorno()+"','"+bt.getSlotno()+"','"+bt.getDate()+"','"+bt.getBookingtime()+"','"+bt.getType()+"','"+bt.getVehicleno()+"'"
						+ ",'"+bt.getEntrytime()+"','"+bt.getDuration()+"','"+bt.getExtension()+"')");
		
		
		st.close();
		connection.close();
		
		if(val==1){
			return Response.status(201).entity("true").build();
		}
		else
		{
			return Response.status(201).entity("false").build();
		}
		
		
	}
}
