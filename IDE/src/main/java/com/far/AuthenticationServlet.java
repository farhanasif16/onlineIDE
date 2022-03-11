package com.far;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String id=req.getParameter("id");
		String pass=req.getParameter("pass");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","AsifFarhan@221");
			String query="SELECT * FROM User WHERE userid=?";
			PreparedStatement stmt=DbConnection.getConnection().prepareStatement(query);
			stmt.setString(1,id);
			PrintWriter out=res.getWriter();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				if(rs.getString(3).equals(pass)) {
					Cookie ci=new Cookie("id","farhanasif16");
					Cookie cp=new Cookie("pass","AsifFarhan@221");
					Cookie cn=new Cookie("name",rs.getString(1));
					res.addCookie(ci);
					res.addCookie(cp);
					res.addCookie(cn);
					res.sendRedirect("Authentication.jsp");
				}
				else {
					out.print("<h1>Incorrect Password</h1>");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}