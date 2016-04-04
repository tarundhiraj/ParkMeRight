package com.ghrce.dao.impl	;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ghrce.DBAdapter;
import com.ghrce.dao.ParkingDAO;

public class ParkingDAOImpl implements ParkingDAO {
	
	Connection connection = DBAdapter.getConnection();
	Statement st;
	ResultSet rs;
	@Override
	public int getBookedSlots() {
		// TODO Auto-generated method stub
		int count=0;
		try{
			st = connection.createStatement();
			rs = st.executeQuery("select * from floorplan where Floor_No='1'");
			while(rs.next()){
				if(rs.getInt(3)==0)
					count++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return count;
	}
	
	@Override
	public String getAllSlots() {
		
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		
		try {
			st = connection.createStatement();
			
			ResultSet rs;
			rs = st.executeQuery("select * from floorplan where Floor_No='1'");

			jsonArray = new JSONArray();
			
			while(rs.next()){
				jsonObject = new JSONObject();
				
				jsonObject.put("floorNo", rs.getInt(1));
				jsonObject.put("slotNo", rs.getInt(2));
				jsonObject.put("available", rs.getInt(3));
				
				jsonArray.put(jsonObject);
			}
			
			//System.out.println(jsonObject.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return jsonArray.toString();
	}

}
