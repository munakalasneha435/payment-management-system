package org.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(false);

		if(session == null) {
			resp.sendRedirect("Login.html");
			return;
		}

		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		long number = (Long) session.getAttribute("number");

		out.println("<h1>Profile</h1>");
		out.println("<h3>Name: " + name + "</h3>");
		out.println("<h3>Email: " + email + "</h3>");
		out.println("<h3>Number: " + number + "</h3>");

		out.println("<a href='Homepage'>Home</a><br>");
		out.println("<a href='Logout'>Logout</a>");
	}
}