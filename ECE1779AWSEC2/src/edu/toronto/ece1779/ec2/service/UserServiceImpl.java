package edu.toronto.ece1779.ec2.service;

import edu.toronto.ece1779.ec2.dao.UserDAO;
import edu.toronto.ece1779.ec2.dao.UserDAOImpl;
import edu.toronto.ece1779.ec2.entity.User;

public class UserServiceImpl implements UserService {

	public boolean authenticate(User user) {
		UserDAO userDAO = new UserDAOImpl();
		return userDAO.authenticate(user);
	}


	public boolean createAccount(User user) {
		UserDAO userDAO = new UserDAOImpl();
		return userDAO.createAccount(user);
	}

}
