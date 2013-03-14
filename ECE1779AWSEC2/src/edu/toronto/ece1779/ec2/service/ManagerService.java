package edu.toronto.ece1779.ec2.service;

import java.util.List;
import java.util.Map;

import edu.toronto.ece1779.ec2.entity.ManagerConfig;
import edu.toronto.ece1779.ec2.entity.Worker;

public interface ManagerService {

	public List<Worker> retrieveRunningWorkersInfo();
	
	public Map<String, Double> retrieveCPUUsageMap();
	
	public List<Worker> retrieveWorkersInfo();
	
	public void increaseWorkers(int number);
	
	public void reduceWorkers(int number);
	
	public void updateManagerConfig(ManagerConfig config);
	
	public ManagerConfig retrieveManagerConfig();
	
	public void addOrRemoveInstancesToLoadBalancer(List<String> ids, boolean add);
	
}
