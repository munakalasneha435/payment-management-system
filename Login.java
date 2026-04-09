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
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		PrintWriter out = resp.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/payment_db","root","root");

			PreparedStatement ps = con.prepareStatement(
				"SELECT * FROM users WHERE email=?");

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				String dbPassword = rs.getString("password");

				if(dbPassword.equals(password)) {

					HttpSession session = req.getSession();
					session.setAttribute("name", rs.getString("name"));
					session.setAttribute("email", email);
					session.setAttribute("number", rs.getLong("number"));

					resp.sendRedirect("Homepage");

				} else {
					out.println("<h2>Invalid Password</h2>");
				}

			} else {
				out.println("<h2>User not found</h2>");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}