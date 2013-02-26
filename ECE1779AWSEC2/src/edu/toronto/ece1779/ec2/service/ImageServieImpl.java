package edu.toronto.ece1779.ec2.service;

import java.util.List;

import edu.toronto.ece1779.ec2.dao.ImageDAO;
import edu.toronto.ece1779.ec2.dao.ImageDAOImpl;
import edu.toronto.ece1779.ec2.entity.Image;

public class ImageServieImpl implements ImageService {

	@Override
	public List<Image> getUserImages(int userId) {
		ImageDAO imageDao = new ImageDAOImpl();
		return imageDao.getUserImages(userId);
	}

	@Override
	public void addImage(Image image) {
		ImageDAO imageDao = new ImageDAOImpl();
		imageDao.insertImage(image);
	}

	@Override
	public Image getImage(int imageId) {
		// TODO Auto-generated method stub
		ImageDAO imageDao = new ImageDAOImpl();
		return imageDao.getImage(imageId);
	}

}
