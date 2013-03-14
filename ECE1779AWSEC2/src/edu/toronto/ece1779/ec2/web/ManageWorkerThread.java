package edu.toronto.ece1779.ec2.web;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import edu.toronto.ece1779.ec2.entity.ManagerConfig;
import edu.toronto.ece1779.ec2.service.ManagerService;
import edu.toronto.ece1779.ec2.service.ManagerServiceImpl;

public class ManageWorkerThread implements Runnable{

	public void run() {
        try{
       	 ManagerService managerService = new ManagerServiceImpl();
       	
       	 while(true) {
    		ManagerConfig config = managerService.retrieveManagerConfig();

       		 Map<String, Double> cpuUsageMap = managerService.retrieveCPUUsageMap();
       		 double cpuUsage = 0;       		 
       		 double total = 0;
       		 Set<String> keys = cpuUsageMap.keySet();
       		 for(Iterator<String> i = keys.iterator(); i.hasNext();) {
       			 double value = (Double)cpuUsageMap.get((String)i.next());
       			 total = total + value;
       		 }
       		
       		 int divide = managerService.retrieveRunningWorkersInfo().size();
       		 if(divide != 0) {
       			 cpuUsage = total/divide;
       			 System.out.println("CPU usage divided by: " + divide);
       		 }
       		 
       		 System.out.println("ManageWorkerListener:");
       		 System.out.println("thresholdGrow:" + config.getThresholdGrow());
       		 System.out.println("thresholdShrink:" + config.getThresholdShrink());
       		 System.out.println("ratioExpand:" + config.getRatioExpand());
       		 System.out.println("ratioShrink:" + config.getRatioShrink());
       		 System.out.println("cpuUsage:" + cpuUsage);
       		 
       		 //cpuUsage = 60;//For testing purpose. To be removed.
       		 
       		 if(config.getThresholdGrow() != 0 && config.getRatioExpand() != 0 && cpuUsage > config.getThresholdGrow()) {
       			 int runningWorkerCount = managerService.retrieveRunningWorkersInfo().size();
       			 int increaseNumber = runningWorkerCount * (config.getRatioExpand()-1);
       			 
       			 if(runningWorkerCount + increaseNumber > 20) {
       				increaseNumber = 20 - runningWorkerCount;
       			 }
       			 
       			 managerService.increaseWorkers(increaseNumber);
       			 System.out.println("ManageWorkerListener: increasing " + increaseNumber + " workers");
       		 } 
       		 else if(config.getThresholdShrink() != 0 && config.getRatioShrink() != 0 && cpuUsage < config.getThresholdShrink()) {
       			 int runningWorkerCount = managerService.retrieveRunningWorkersInfo().size();
       			 int shrinkCount = runningWorkerCount*(config.getRatioShrink()-1)/config.getRatioShrink();
       			 if(shrinkCount > 0 && shrinkCount < runningWorkerCount) {
       				 managerService.reduceWorkers(shrinkCount);
       				 System.out.println("ManageWorkerListener: reducing " + shrinkCount + " workers");
       			 }
       		 }
       	
       		Thread.sleep(90000);//TODO need to make this number bigger
       		System.out.println("ManageWorkerListener: sleeping...");
       	 }
        } catch(Exception e) {
       	 System.out.println("Error Message: " + e.getMessage());
        }
	}
	
}
