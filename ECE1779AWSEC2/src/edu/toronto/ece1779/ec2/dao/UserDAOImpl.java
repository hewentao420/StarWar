package edu.toronto.ece1779.ec2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.toronto.ece1779.ec2.entity.User;

public class UserDAOImpl implements UserDAO {

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet rs = null;
	

	public boolean authenticate(User user) {
		boolean isAuthenticated = false;
		try {
			String queryString = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ?"; 
			connection = ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, user.getName());
            ptmt.setString(2, user.getPassword());
            rs = ptmt.executeQuery();
            if(rs.next()) {
            	int count = rs.getInt(1);
            	if(count > 0)
            		isAuthenticated = true;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
                if(rs != null)
                	rs.close();
				if (ptmt != null)
                	 ptmt.close();
                 if (connection != null)
                     connection.close();
			} catch (SQLException e) {
                 e.printStackTrace();
			} catch (Exception e) {
                 e.printStackTrace();
			}
		}
		return isAuthenticated;
	}
	
	
	public boolean createAccount(User user) {
		boolean isExistingUserName = false;
		try {
			String queryString = "INSERT INTO users(login, password) VALUES(?,?)"; 
			connection = ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, user.getName());
            ptmt.setString(2, user.getPassword());
            ptmt.executeUpdate();
		} catch (SQLException e) {//TODO Need to eat up the specific exception
			isExistingUserName = true;
			e.printStackTrace();
		} finally {
			try {
                 if (ptmt != null)
                	 ptmt.close();
                 if (connection != null)
                     connection.close();
			} catch (SQLException e) {
                 e.printStackTrace();
			} catch (Exception e) {
                 e.printStackTrace();
			}
		}
		return isExistingUserName;
	}
	
}
