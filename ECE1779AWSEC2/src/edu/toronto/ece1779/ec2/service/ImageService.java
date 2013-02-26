package edu.toronto.ece1779.ec2.service;

import java.util.List;

import edu.toronto.ece1779.ec2.entity.Image;

public interface ImageService {
	public List<Image> getUserImages(int userId);

	public void addImage(Image image);
}
