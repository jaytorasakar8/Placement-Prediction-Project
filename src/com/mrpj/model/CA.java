package com.mrpj.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CA {
	Connection con;
	String user = "root";
	String password = "";
	String url = "jdbc:mysql://localhost:3306/";
	String dbname = "placement_db";
	String driver = "com.mysql.jdbc.Driver";
	

	private void dbConnect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url + dbname, user, password);
		con.setAutoCommit(false);
	}

	private void dbClose() throws SQLException {//committing normally 
		con.commit();
		con.close();
		con=null;
	}
	private void dbRClose() throws SQLException {//Rollback 
		con.rollback();
		con.close();
		con=null;
	}

	public String validateUser(String email, String password) {
		
		try {
			dbConnect();
		String sql="SELECT * FROM company WHERE email=? and password=? ";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, email);
		pstm.setString(2, password);
		ResultSet rst = pstm.executeQuery();
		if(rst.next()){
				dbClose();
				return email;
		}
		else{
		dbClose();
		return "Oops..! Invalid User.";
		}
		}catch (Exception e) {
			try {
				dbRClose();
			} catch (Exception e1) {
			e1.printStackTrace();	
			return "Oops..! An Error Occured.";
			}
			e.printStackTrace();
			return "Oops..! An Error Occured.";
		}
		
	}

	String roundOffTo2DecPlaces(Double val)
	{
	    return String.format("%.2f", val);
	}
	
	public String getCGPI(String studentID) {
		try {
			dbConnect();
		String sql="SELECT * FROM academic WHERE student_id=?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, studentID);
		ResultSet rst = pstm.executeQuery();
		if(rst.next()){
			double s1=Double.parseDouble(rst.getString("sem1"));
			double s2=Double.parseDouble(rst.getString("sem2"));
			double s3=Double.parseDouble(rst.getString("sem3"));
			double s4=Double.parseDouble(rst.getString("sem4"));
			double s5=Double.parseDouble(rst.getString("sem5"));
			double s6=Double.parseDouble(rst.getString("sem6"));
			String cgpi=""+roundOffTo2DecPlaces((s1+s2+s3+s4+s5+s6)/6);
				dbClose();
				return cgpi;
		}
		else{
		dbClose();
		return null;
		}
		}catch (Exception e) {
			try {
				dbRClose();
			} catch (Exception e1) {
			e1.printStackTrace();	
			return "Oops..! An Error Occured.";
			}
			e.printStackTrace();
			return "Oops..! An Error Occured.";
		}
	}

}
