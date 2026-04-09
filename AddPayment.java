package org.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddPayment")
public class AddPayment extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		int id = Integer.parseInt(req.getParameter("paymentId"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		String method = req.getParameter("method");
		String status = req.getParameter("status");
		String date = req.getParameter("date");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_db","root","root");

			PreparedStatement ps = con.prepareStatement("INSERT INTO payments VALUES(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setInt(2, amount);
			ps.setString(3, method);
			ps.setString(4, status);
			ps.setString(5, date);

			ps.executeUpdate();

			resp.sendRedirect("AddPayment.html");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
