package com.cysbersoft.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cysbersof.connection.MySQLConnection;
import com.cysbersof.pojo.User;

public class LoginDAO {

	Connection con = MySQLConnection.getConnection();
	
	public User getUsers(String email, String password) {
		User users = null;
		
		Connection con = MySQLConnection.getConnection();
		String sql = "select * from users where email = ? and password = ? ";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				users = new User();
				
				users.setEmail(result.getString("email"));
				users.setFullname(result.getString("fullname"));
				
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
}
