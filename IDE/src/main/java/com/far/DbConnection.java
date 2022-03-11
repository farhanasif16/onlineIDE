package com.far;

import java.sql.Connection;
import java.sql.DriverManager;
public class DbConnection {

	public static Connection getConnection() {
		Connection connection=null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","AsifFarhan@221");
		}catch(Exception ex) {
			
		}
		return connection;
	}
	
}