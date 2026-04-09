package org.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeletePayment")
public class DeletePayment extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		int id = Integer.parseInt(req.getParameter("paymentId"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_db","root","root");

			PreparedStatement ps = con.prepareStatement("DELETE FROM payments WHERE paymentId=?");
			ps.setInt(1, id);

			ps.executeUpdate();

			resp.sendRedirect("DisplayPayments");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}