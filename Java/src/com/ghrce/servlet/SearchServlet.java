package com.ghrce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ghrce.DBAdapter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		try {
			Connection connection=(Connection) DBAdapter.getConnection();
			Statement stmt = null;
			ResultSet rs;
			String email=request.getParameter("search");
			String sql = "SELECT * FROM booking WHERE Email='"+email+"'";
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			rs=(ResultSet) stmt.executeQuery(sql);
			
			out.println("<div id='search'> <section class='container'>	<h2>Customer Search</h2><table class='order-table table'>");
			out.println("<thead>");
			out.println("<tr>");
			out.println("<th>Booking-ID</th>");
			out.println("<th>User_ID</th>");
			out.println("<th>Floor_No</th>");
			out.println("<th>Slot_No</th>");
			out.println("<th>Date</th>");
			out.println("<th>Booking_Time</th>");
			out.println("<th>Vehicle_Type</th>");
			out.println("<th>Vehicle_No</th>");
			out.println("<th>Entry_Time</th>");
			out.println("<th>Duration</th>");
			out.println("<th>Extension</th>");
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			if(rs.next())
				{
				out.println("<tr>");

				out.println("<td>"+rs.getString(1)+"</td>");

				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");

				out.println("<td>"+ rs.getString(4)+"</td>");
				out.println("<td> "+rs.getString(5)+"</td>");

				out.println("<td> "+rs.getString(6)+"</td>");
				out.println("<td>"+rs.getString(7)+"</td>");

				out.println("<td> "+rs.getString(8)+"</td>");
				out.println("<td> "+ rs.getString(9)+"</td>");

				out.println("<td> "+ rs.getString(10)+"</td>");
				out.println("<td> "+rs.getString(11)+"</td>");
						
				out.println("</tr>");
			} 
			else
			{
				out.println("No such booking exists");
			}
			out.println("</tbody>");
			out.println("</table>");

			out.println("</section>");
			out.println("</div>");
			
			out.println("<a href='Booking.jsp'>Back</a>");
			
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	}


