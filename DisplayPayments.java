package org.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayPayments")
public class DisplayPayments extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_db","root","root");

			PreparedStatement ps = con.prepareStatement("SELECT * FROM payments");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				out.println("<h3>Id: "+rs.getInt(1)+" Amount: "+rs.getInt(2)+
				" Method: "+rs.getString(3)+" Status: "+rs.getString(4)+
				" Date: "+rs.getString(5)+"</h3>");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
