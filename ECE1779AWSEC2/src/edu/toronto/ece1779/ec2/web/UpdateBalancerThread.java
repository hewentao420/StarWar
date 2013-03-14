package edu.toronto.ece1779.ec2.web;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.model.Instance;

import edu.toronto.ece1779.ec2.service.ManagerService;
import edu.toronto.ece1779.ec2.service.ManagerServiceImpl;

public class UpdateBalancerThread implements Runnable {

	public List<Instance> instances;
	
	public UpdateBalancerThread(List<Instance> instances) {
		this.instances = instances;
	}
	
	public void run() {
		try {
			List<String> ids = new ArrayList<String>();
			for(int i=0; i<instances.size(); i++) {
				ids.add(instances.get(i).getInstanceId());
			}
			
			System.out.println("UpdateBalancerThread sleeping ...");
			Thread.sleep(120000);
			ManagerService managerService = new ManagerServiceImpl();
			System.out.println("UpdateBalancerThread ids: " + ids.toString());
			managerService.addOrRemoveInstancesToLoadBalancer(ids, true);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
