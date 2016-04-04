package com.ghrce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.ghrce.DBAdapter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String jsondata = request.getParameter("jsondata");
			JSONObject js=new JSONObject(jsondata) ;
			Connection connection=(Connection) DBAdapter.getConnection();
			Statement st=(Statement) connection.createStatement();
			PreparedStatement ps;
			HttpSession ssn = request.getSession();
			String name=ssn.getAttribute("userid").toString();
			PrintWriter out = response.getWriter();
			
			out.println(js);
			
			int slot=(int) js.get("slot");
			String sqlQuery="UPDATE floorplan SET `Available`='0' WHERE `Floor_No`='1' and`Slot_No`='"+slot+"'";
			st.executeUpdate(sqlQuery);
			
			DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
			Date d=new Date();
			Calendar c=Calendar.getInstance();
			ps=connection.prepareStatement("insert into booking values(?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println(name);
			ps.setInt(1,(int) js.get("bookingId"));
			ps.setString(2, name);
			ps.setInt(3, 1);
			ps.setInt(4,(int) js.get("slot"));
			
			java.sql.Date sqlDate = new java.sql.Date(d.getTime());
			ps.setDate(5, sqlDate );
			
			if(js.get("vehicle").equals("SUV"))
				ps.setObject(7, 'M', java.sql.Types.CHAR);
			else
				ps.setObject(7, 'S', java.sql.Types.CHAR);
			ps.setString(8,(String) js.get("vehicleno"));
			java.sql.Time sqlTime = new java.sql.Time(c.getTime().getTime());
			ps.setTime(9, sqlTime);
			ps.setTime(6,sqlTime);
			ps.setInt(10, Integer.parseInt(js.get("duration").toString()));
			ps.setFloat(11, (float) 0.0);
			ps.execute();
				
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
