package org.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdatePayment")
public class UpdatePayment extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		int id = Integer.parseInt(req.getParameter("paymentId"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		String method = req.getParameter("method");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_db","root","root");

			PreparedStatement ps = con.prepareStatement(
				"UPDATE payments SET amount=?, method=? WHERE paymentId=?"
			);

			ps.setInt(1, amount);
			ps.setString(2, method);
			ps.setInt(3, id);

			ps.executeUpdate();

			resp.sendRedirect("DisplayPayments");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}