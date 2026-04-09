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

@WebServlet("/PaymentById")
public class PaymentById extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		int id = Integer.parseInt(req.getParameter("paymentId"));
		PrintWriter out = resp.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_db","root","root");

			PreparedStatement ps = con.prepareStatement("SELECT * FROM payments WHERE paymentId=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				out.println("<h2>Payment Found</h2>");
				out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3));
			} else {
				out.println("<h2>Payment Not Found</h2>");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
