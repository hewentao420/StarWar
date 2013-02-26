package edu.toronto.ece1779.ec2.dao;

import edu.toronto.ece1779.ec2.entity.User;

public interface UserDAO {

	public boolean authenticate(User user);
	
	public boolean createAccount(User user);
	
	public User getUser(String userName);
	
}
