package edu.toronto.ece1779.ec2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.toronto.ece1779.ec2.entity.Image;
import edu.toronto.ece1779.ec2.entity.User;

public class ImageDAOImpl implements ImageDAO{

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet rs = null;
	
	@Override
	public List<Image> getUserImages(int userId) {
		try {
			String queryString = "SELECT * FROM images WHERE userId = ?"; 
			connection = ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, userId);
            rs = ptmt.executeQuery();
            
            List<Image> images = new ArrayList<Image>();
            
            while(rs.next()) {
            	Image image = new Image();
            	image.setId(rs.getInt(1));
            	image.setUserId(rs.getInt(2));
            	image.setKey1(rs.getString(3));
            	image.setKey2(rs.getString(4));
            	image.setKey3(rs.getString(5));
            	image.setKey4(rs.getString(6));
            	
            	images.add(image);
            }
            return images;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return null;
	}

	@Override
	public void insertImage(Image image) {
		try {
			String queryString = "INSERT INTO images(userId, key1,key2,key3,key4) VALUES(?,?,?,?,?)"; 
			connection = ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, image.getUserId());
			ptmt.setString(2, image.getKey1());
			ptmt.setString(3, image.getKey2());
			ptmt.setString(4, image.getKey3());
			ptmt.setString(5, image.getKey4());
            ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	
	private void closeAll() {
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

}
