package edu.toronto.ece1779.ec2.dao;

import java.util.List;

import edu.toronto.ece1779.ec2.entity.Image;

public interface ImageDAO {

	List<Image> getUserImages(int userId);
}
