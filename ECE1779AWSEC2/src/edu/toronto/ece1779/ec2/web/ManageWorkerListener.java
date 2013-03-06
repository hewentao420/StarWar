package edu.toronto.ece1779.ec2.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ManageWorkerListener implements ServletContextListener{
	
	 public void contextInitialized(ServletContextEvent arg0) {
		 ManageWorkerThread manageWorkerThread = new ManageWorkerThread();
		 new Thread(manageWorkerThread).start();
	 }
	 
	 public void contextDestroyed(ServletContextEvent sce) {
		 
     }
}
