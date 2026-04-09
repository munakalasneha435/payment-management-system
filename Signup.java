package org.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		long number = Long.parseLong(req.getParameter("number"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/payment_db","root","root");

			PreparedStatement ps = con.prepareStatement(
				"INSERT INTO users VALUES(?,?,?,?)");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setLong(4, number);

			ps.executeUpdate();

			resp.sendRedirect("Login.html");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
