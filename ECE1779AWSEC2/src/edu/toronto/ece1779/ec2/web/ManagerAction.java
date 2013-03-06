package edu.toronto.ece1779.ec2.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.ec2.entity.Worker;
import edu.toronto.ece1779.ec2.service.ManagerService;
import edu.toronto.ece1779.ec2.service.ManagerServiceImpl;

public class ManagerAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private int increaseNumber;
	private int reduceNumber;
	private float thresholdGrow;
	private float thresholdShrink;
	private int ratioExpand;
	private int ratioShrink;
	private List<Worker> workers;
	
	//TODO for all methods: check session, return ERROR if session expired
	
	public String refresh() {
		ManagerService managerService = new ManagerServiceImpl();	
		this.workers = managerService.retrieveRunningWorkersInfo();
		return SUCCESS;
	}
	
	
	public String adjustWorkerPool() {
		if(increaseNumber != 0 && reduceNumber != 0) {
			addActionError(getText("failure.increase.reduce.can.not.both.exist"));
    		return ERROR;
		}
		
		ManagerService managerService = new ManagerServiceImpl();	
		if(increaseNumber != 0) {
			if(increaseNumber > 5) {
				addActionError(getText("failure.over.limit"));
	    		return ERROR;
			}
			managerService.increaseWorkers(increaseNumber);
		}
		else if(reduceNumber != 0) {
			int runningWorkerCount = managerService.retrieveRunningWorkersInfo().size();
			if(runningWorkerCount == 1) {
				addActionError(getText("failure.only.one.instance.running"));
	    		return ERROR;
			} else if(runningWorkerCount < reduceNumber) {
				addActionError(getText("failure.not.enough.instances.running"));
				return ERROR;
			} else if(runningWorkerCount == reduceNumber) {
				addActionError(getText("failure.one.instance.kept.running"));
				return ERROR;
			}
			
			managerService.reduceWorkers(reduceNumber);
		}
		
		refresh();
		return SUCCESS;
	}
	
	
	public String configure() {
		System.out.println("thresholdGrow: " + thresholdGrow);
		System.out.println("thresholdShrink: " + thresholdShrink);
		System.out.println("ratioExpand: " + ratioExpand);
		System.out.println("ratioShrink: " + ratioShrink);
		
		refresh();
		return SUCCESS;
	}

	



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIncreaseNumber() {
		return increaseNumber;
	}

	public void setIncreaseNumber(int increaseNumber) {
		this.increaseNumber = increaseNumber;
	}

	public int getReduceNumber() {
		return reduceNumber;
	}

	public void setReduceNumber(int reduceNumber) {
		this.reduceNumber = reduceNumber;
	}

	public float getThresholdGrow() {
		return thresholdGrow;
	}


	public void setThresholdGrow(float thresholdGrow) {
		this.thresholdGrow = thresholdGrow;
	}


	public float getThresholdShrink() {
		return thresholdShrink;
	}


	public void setThresholdShrink(float thresholdShrink) {
		this.thresholdShrink = thresholdShrink;
	}


	public int getRatioExpand() {
		return ratioExpand;
	}


	public void setRatioExpand(int ratioExpand) {
		this.ratioExpand = ratioExpand;
	}


	public int getRatioShrink() {
		return ratioShrink;
	}


	public void setRatioShrink(int ratioShrink) {
		this.ratioShrink = ratioShrink;
	}


	public List<Worker> getWorkers() {
		return workers;
	}


	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	
	
}
