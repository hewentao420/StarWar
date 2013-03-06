package edu.toronto.ece1779.ec2.dao;

import edu.toronto.ece1779.ec2.entity.ManagerConfig;

public interface ManagerDAO {

	public void updateManagerConfig(ManagerConfig config);
	public ManagerConfig retrieveManagerConfig();
	
}
