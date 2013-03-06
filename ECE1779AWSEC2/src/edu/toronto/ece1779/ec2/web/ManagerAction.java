package edu.toronto.ece1779.ec2.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.ec2.entity.ManagerConfig;
import edu.toronto.ece1779.ec2.entity.User;
import edu.toronto.ece1779.ec2.entity.Worker;
import edu.toronto.ece1779.ec2.service.ManagerService;
import edu.toronto.ece1779.ec2.service.ManagerServiceImpl;

public class ManagerAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private int increaseNumber;
	private int reduceNumber;
	private double thresholdGrow;
	private double thresholdShrink;
	private int ratioExpand;
	private int ratioShrink;
	private List<Worker> workers;
	
	
	public String adjustWorkerPool() {
		if(increaseNumber != 0 && reduceNumber != 0) {
			addActionError(getText("failure.increase.reduce.can.not.both.exist"));
			refresh();
			return ERROR;
		}
		
		ManagerService managerService = new ManagerServiceImpl();	
		if(increaseNumber != 0) {
			if(increaseNumber > 5) {
				addActionError(getText("failure.over.limit"));
				refresh();
				return ERROR;
			}
			
			int runningWorkerCount = managerService.retrieveRunningWorkersInfo().size();
  			 if(runningWorkerCount + increaseNumber > 20) {
  				increaseNumber = 20 - runningWorkerCount;
  			 }
			managerService.increaseWorkers(increaseNumber);
			addActionError(getText("info.request.increase.processing"));
		}
		else if(reduceNumber != 0) {
			int runningWorkerCount = managerService.retrieveRunningWorkersInfo().size();
			if(runningWorkerCount == 1) {
				addActionError(getText("failure.only.one.instance.running"));
				refresh();
				return ERROR;
			} else if(runningWorkerCount < reduceNumber) {
				addActionError(getText("failure.not.enough.instances.running"));
				refresh();
				return ERROR;
			} else if(runningWorkerCount == reduceNumber) {
				addActionError(getText("failure.one.instance.kept.running"));
				refresh();
				return ERROR;
			}
			
			managerService.reduceWorkers(reduceNumber);
			addActionError(getText("info.request.reduce.processing"));
		}
		
		refresh();
		return SUCCESS;
	}
	
	
	public String configure() {
		if(thresholdGrow < thresholdShrink){
			addActionError(getText("failure.grow.less.than.shrink"));
			refresh();
			return ERROR;
		}
		
		ManagerConfig config = new ManagerConfig(thresholdGrow, thresholdShrink, ratioExpand, ratioShrink);
		ManagerService managerService = new ManagerServiceImpl();
		managerService.updateManagerConfig(config);
	
		refresh();
		addActionError(getText("info.configuration.saved"));
		return SUCCESS;
	}

    
	public String refresh() {
		ManagerService managerService = new ManagerServiceImpl();	
		this.workers = managerService.retrieveRunningWorkersInfo();
		
    	ManagerConfig config = managerService.retrieveManagerConfig();
    	this.thresholdGrow = config.getThresholdGrow();
    	this.thresholdShrink = config.getThresholdShrink();
    	this.ratioExpand = config.getRatioExpand();
    	this.ratioShrink = config.getRatioShrink();
    	
    	@SuppressWarnings("unchecked")
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		User user = (User) session.get("user");
		this.username = user.getName();
    	
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

	public double getThresholdGrow() {
		return thresholdGrow;
	}


	public void setThresholdGrow(double thresholdGrow) {
		this.thresholdGrow = thresholdGrow;
	}


	public double getThresholdShrink() {
		return thresholdShrink;
	}


	public void setThresholdShrink(double thresholdShrink) {
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
