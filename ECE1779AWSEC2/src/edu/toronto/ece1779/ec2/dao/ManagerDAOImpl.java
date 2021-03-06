package edu.toronto.ece1779.ec2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.toronto.ece1779.ec2.entity.ManagerConfig;

/**
 * !!!NOTE!!!: Ideally there should be a table to store manager's configurations. Here we are using Users table for now.
 */
public class ManagerDAOImpl implements ManagerDAO{

	Connection connection = null;
	PreparedStatement ptmt = null;
	PreparedStatement ptmt2 = null;
	ResultSet rs = null;
	
	@Override
	public void updateManagerConfig(ManagerConfig config) {
		try {
			String queryString = "UPDATE users SET password=? WHERE login=?"; 
			connection = ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
 
			ptmt.setString(1, Double.toString(config.getThresholdGrow()));
			ptmt.setString(2, "thresholdGrow");
            ptmt.executeUpdate();
     
			ptmt.setString(1, Double.toString(config.getThresholdShrink()));
			ptmt.setString(2, "thresholdShrink");
            ptmt.executeUpdate();
            
			ptmt.setString(1, Integer.toString(config.getRatioExpand()));
			ptmt.setString(2, "ratioExpand");
            ptmt.executeUpdate();
            
			ptmt.setString(1, Integer.toString(config.getRatioShrink()));
			ptmt.setString(2, "ratioShrink");
            ptmt.executeUpdate();       
        	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	@Override
	public ManagerConfig retrieveManagerConfig() {
		ManagerConfig config = new ManagerConfig();
		try {
			String queryString = "SELECT password FROM users WHERE login = ?";
			String insertString = "insert into users (login, password) values (?,?)";
			connection = ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt2 = connection.prepareStatement(insertString);
 
			ptmt.setString(1, "thresholdGrow");
            rs = ptmt.executeQuery();
			if (rs.next()) {
				config.setThresholdGrow(Double.parseDouble(rs.getString(1)));
			} else {
				config.setThresholdGrow(0.0);
				ptmt2.setString(1, "thresholdGrow");
				ptmt2.setString(2, "0.0");
	            ptmt2.executeUpdate();
			}          	
            
            ptmt.setString(1, "thresholdShrink");
            rs = ptmt.executeQuery();
			if (rs.next()) {
				config.setThresholdShrink(Double.parseDouble(rs.getString(1)));
			} else {
				config.setThresholdShrink(0.0);
				ptmt2.setString(1, "thresholdShrink");
				ptmt2.setString(2, "0.0");
	            ptmt2.executeUpdate();
			}          	
            
            ptmt.setString(1, "ratioExpand");
            rs = ptmt.executeQuery();
			if (rs.next()) {
				config.setRatioExpand(Integer.parseInt(rs.getString(1)));
			} else {
				config.setRatioExpand(0);
				ptmt2.setString(1, "ratioExpand");
				ptmt2.setString(2, "0");
	            ptmt2.executeUpdate();
			}           	
            
            ptmt.setString(1, "ratioShrink");
            rs = ptmt.executeQuery();
			if (rs.next()) {
				config.setRatioShrink(Integer.parseInt(rs.getString(1)));
			} else {
				config.setRatioShrink(0);
				ptmt2.setString(1, "ratioShrink");
				ptmt2.setString(2, "0");
	            ptmt2.executeUpdate();
			}
        	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return config;
	}

	
	private void closeAll() {
		try {
		     if (ptmt != null)
		    	 ptmt.close();
		     if (ptmt2 != null)
		    	 ptmt2.close();
		     if (connection != null)
		         connection.close();
		} catch (SQLException e) {
		     e.printStackTrace();
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}
}
