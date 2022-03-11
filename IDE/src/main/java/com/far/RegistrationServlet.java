package com.far;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RegistrationServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String name=req.getParameter("name");
		String userid=req.getParameter("id");
		String pass=req.getParameter("pass");
		String repass=req.getParameter("repass");
		String mob_num=req.getParameter("mob_num");
		PrintWriter out=res.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=DbConnection.getConnection();
		if(!pass.equals(repass)) {
			out.print("Password doesn't match");
		}
		try {
			String q1="SELECT userid FROM User WHERE userid=?";
			PreparedStatement stmt1=con.prepareStatement(q1);
			stmt1.setString(1,userid);
			ResultSet rs=stmt1.executeQuery();
			if(rs.next()) {
				out.print("USER ALREADY EXIST");
			}
			else {
				String q2="INSERT INTO User VALUES(?,?,?,?)";
				PreparedStatement stmt2=con.prepareStatement(q2);
				stmt2.setString(1,name);
				stmt2.setString(2,userid);
				stmt2.setString(3,pass);
				stmt2.setString(4,mob_num);
				stmt2.executeUpdate();
					out.println("<h1>REGISTERED SUCCESSFULLY</h1>");
					out.print("<a href=\"Login.html\">Login</href>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
