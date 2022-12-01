package com.DAO;

import java.sql.*;

public class DAO {
	
	String url = "jdbc:mysql://localhost:3306/pipedPiper";
	String uname = "arhant";
	String pass = "tt";

	public boolean check_usr(String Email, String PSWD) throws ClassNotFoundException, SQLException {
		
		boolean isIt = false;
		String query = "Select * from Users Where Email='" + Email + "' and PSWD='" + PSWD + "'";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			isIt = true;
		}else {
			isIt = false;
		}
		
		st.close();
		con.close();
		
		return isIt;
		
	}
	
	public String get_name(String Email) throws SQLException, ClassNotFoundException {
		
		String name = null;
		String query = "Select FirstName from Users Where Email='" + Email + "'";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			name = rs.getString(1);
		}else {
			name = null;
		}
		
		st.close();
		con.close();
		
		return name;
		
	}
	
	public void add_usr(String FirstName, String LastName, String Email, String PSWD) throws ClassNotFoundException, SQLException {
		
		String query = "insert into Users (FirstName, LastName, Email, PSWD, PassHashed) values (?, ?, ?, ?, sha1(?))";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, FirstName);
		st.setString(2, LastName);
		st.setString(3, Email);
		st.setString(4, PSWD);
		st.setString(5, PSWD);
		
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}
	
	public void add_data(String Email, String FileName, String FileLocation) throws ClassNotFoundException, SQLException {
		
		String query = "insert into UsersData (Email, FileName, FileLocation) values (?, ?, ?)";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, Email);
		st.setString(2, FileName);
		st.setString(3, FileLocation);
		
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}
	
}
