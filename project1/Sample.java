package com.jdbc.project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Sample {

	public static void main(String[] args) {
		System.out.println("program starts...");
		
		String url ="jdbc:mysql://localhost:3306/appusers";
		String user="root";
		String password="Rsb@mysql3909";
		
	
		
		Connection cn = null;
		
		try {
			cn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to MySQL DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			cn.close();
			System.out.println("Disconnected from MySQL DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("program ends...");
	}

}
