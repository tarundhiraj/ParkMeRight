package com.ghrce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAdapter {
	private static Connection connection = null;
	
	public static Connection getConnection(){
		if(connection == null){
			
		// This will load the MySQL driver, each DB has its own driver
	      try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 // Setup the connection with the DB
		    
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkMeRight",
			            "root", "admin");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return connection;
	}
}
