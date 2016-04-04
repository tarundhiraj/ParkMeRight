package com.ghrce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.ghrce.bean.Booking;
import com.ghrce.dao.impl.BookingImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
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
	 * @param js 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try{
			String jsonData = request.getParameter("jsonData");
			JSONObject js=new JSONObject(jsonData) ;
			System.out.println(jsonData);
			int slot=js.getInt("slotNo");
			
			BookingImpl bk=new BookingImpl();
			Booking booking = bk.getBookingDetails(slot);
			
			// bk.setSlot(slot);
			
			Gson gson = new Gson();
			 //String json = null;
			 //System.out.println(bk.getBookingId() + ":" + bk.getRate() +":" + bk.getSlot() + ":" + bk.getVehicle());
			 
			String json = gson.toJson(booking);
			System.out.println(json);
			
			PrintWriter out = response.getWriter();
			
			out.println(json);
		}catch (JSONException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
