/**
 * 
 */
package com.ghrce.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import com.ghrce.DBAdapter;
import com.ghrce.bean.Booking;


public class BookingImpl  {
	
		
	private Connection connection;
	private Statement st;
	private Statement stmt;
	private ResultSet rs;
	private ResultSet rese;
	
	
	
	/*public BookingImpl() {
		try
		{
			connection=DBAdapter.getConnection();
			st=connection.createStatement();
			stmt=connection.createStatement();
			rs=st.executeQuery("select * from booking");
			rese=stmt.executeQuery("select * from vehicle where Floor_No='1'");
			
			
			//bookingid 
			System.out.println(rs.getFetchSize() == 0);
			
			if(rs.getFetchSize() == 0){
				this.bookingId=1;
			}
			else
				{
				rs.last();
				this.bookingId = rs.getInt(1)+1;
				}
				
			
			//type of vehicle
			rese.next();
			String slot=rese.getString(2);
			if(slot.equalsIgnoreCase("M"))
				this.vehicle = "SUV" ;
			else if(slot.equalsIgnoreCase("S"))
				this.vehicle = "SEDAN" ;
			else this.vehicle="NANO";
			
			//rate
			this.rate = rese.getFloat(3);
			
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}*/

	
	public Booking getBookingDetails(int slotNo) throws SQLException {
		Booking booking = new Booking();
		booking.setSlot(slotNo);
		
		try
		{
			connection=DBAdapter.getConnection();
			st=connection.createStatement();
			stmt=connection.createStatement();
			rs=st.executeQuery("select * from booking");
			rese=stmt.executeQuery("select * from vehicle where Floor_No='1'");
			
			
			//bookingid 
			System.out.println(rs.getFetchSize() == 0);
			
			rs.last();
			int book=rs.getInt(1);
			book++;
			booking.setBookingId(book);
				
			
			//type of vehicle
			rese.next();
			String slot=rese.getString(2);
			if(slot.equalsIgnoreCase("M"))
				booking.setVehicle("SUV") ;
			else if(slot.equalsIgnoreCase("S"))
				booking.setVehicle("SEDAN") ;
			else booking.setVehicle("NANO");
			
			//rate
			booking.setRate(rese.getFloat(3));
			
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
		return booking;
	}
	
	

	
	
	
}
