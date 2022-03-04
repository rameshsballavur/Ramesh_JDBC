package com.jdbc.project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Database{
	String url ="jdbc:mysql://localhost:3306/appusers";
	String user="root";
	String password="Rsb@mysql3909";
	
	Connection cn = null;

	public void connect() {
		
		try {
			cn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to MySQL DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String name, String email,String password, String mobile, String gender ) {
		System.out.println("insert data to database");
		
		
		String sql = "insert into appusers.users values(0,?,?,?,?,?)";
	
		connect();
		
		try {
			PreparedStatement psm = cn.prepareStatement(sql);
			psm.setString(1, name);
			psm.setString(2, email);
			psm.setString(3, password);
			psm.setString(4, mobile);
			psm.setString(5, gender);
			
			System.out.println(psm);
			                  
			psm.executeUpdate();
			System.out.println("data inserted to database successfully");
		} catch (SQLException e) {
			e.getMessage();
			System.out.println("userdetails are already exist in database table!");
		}
		
		disConnect();
	}
	
	public void update(String newEmail, String oldEmail) {
		String updateQuery = "update appusers.users set email="+"'"+newEmail+"'"
								+"where email="+"'"+oldEmail+"'";
		connect();
		
		Statement stmt;
		try {
			stmt = cn.createStatement();
			stmt.executeUpdate(updateQuery);
			
			System.out.println("Emai-id updated successfully.");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		disConnect();
	}
	
	public void delete(String email) {
		String DeleteQuery = "delete from appusers.users where email = "+"'"+email+"'";
		
		connect();
		try {
			Statement stmt = cn.createStatement();
			stmt.executeUpdate(DeleteQuery);
			
			System.out.println("user data deleted successfully.");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		disConnect();
		
		
	}
	
	public void select(String email) {
		
		String oneQuery = "select name,email,mobile,gender from appusers.users where "
				+"email = "+"'"+email+"'";
		
		connect();
		
		Statement stmt;
		try {
			stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(oneQuery);
			
			while(rs.next()) {
				System.out.println("Name: "+rs.getString("name"));
				System.out.println("Email-id: "+rs.getString("email"));
				System.out.println("Mobile Number: "+rs.getString("mobile"));
				System.out.println("Gender: "+rs.getString("gender"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnect();
	}
	
	public int select(String email,String paswd) {
		
		String user = "select * from appusers.users where email = "
		                +"'"+email+"'"+" and password = "+"'"+paswd+"'";
		
		connect();
		
		System.out.println(user);
		
		int count=0;
		try {
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(user);
			
			while(rs.next()) {
				count++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnect();
		return count;
	}
	
	public void disConnect() {
		try {
			cn.close();
			System.out.println("Disconnected from MySQL DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

class UserService{
	Database db = new Database();
	
	Scanner scan = new Scanner(System.in);
	
	public void signUp() {
		System.out.println("User signUp()"+"\n");
		
		System.out.println("Enter your name");
		String name = scan.next();
		
		System.out.println("Enter your Email-id");
		String emial = scan.next();
		
		System.out.println("Enter your password");
		String password = scan.next();
		
		System.out.println("Enter your mobile no.");
		String mobile = scan.next();
		
		System.out.println("Enter your Gender");
		String gender = scan.next();
		
		
		db.insert(name, emial, password, mobile, gender);
		
	}
	
	public void login() {
		System.out.println("Enter your Email-id: ");
		String email = scan.next();
			
		System.out.println("Enter our password: ");
		String paswd = scan.next();
		
		int count = db.select(email, paswd);
		
		if(count==1) {
			System.out.println("login successful");
		}
		else {
			System.out.println("login unsuccessful");
		}
	}
	
	
	public void updateProfile() {
		
		System.out.println("Enter your old Email-id : ");
		String oldEmail=scan.next();
		
		System.out.println("Enter your New Email-id");
		String newEmail = scan.next();
		
		db.update(newEmail, oldEmail);
}
	
	public void deleteProfile() {
		System.out.println("Enter Email-id : ");
		String email = scan.next();
		
		db.delete(email);
		
	}
	
	public void viewProfile() {
		System.out.println("Enter your Email-id: ");
		String email = scan.next();
		
		
		db.select(email);
	}
	
	public void deleteAccount() {
		System.out.println("delteAccont() of UserService");
	}
}

public class MainClass {

	public static void main(String[] args) {
		UserService us = new UserService();
		us.signUp();
//		us.login();
//		us.viewProfile(); 
//		us.updateProfile();
//		us.deleteProfile();
//		us.deleteAccount();

	}

}
