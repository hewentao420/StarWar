package edu.toronto.ece1779.ec2.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
import com.amazonaws.services.cloudwatch.model.ListMetricsRequest;
import com.amazonaws.services.cloudwatch.model.ListMetricsResult;
import com.amazonaws.services.cloudwatch.model.Metric;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.awsAccess.AwsAccessManager;

public class ManagerAction extends ActionSupport{

	public static final String IMAGE_ID = "ami-394add50";
	public static final String KEY_PAIR_NAME = "myKeys";
	
	private static final long serialVersionUID = 1L;
	
	public String createInstances() {
		BasicAWSCredentials awsCredentials = AwsAccessManager.getInstance().getAWSCredentials();
		AmazonEC2 ec2 = new AmazonEC2Client(awsCredentials);
		
		try{
			RunInstancesRequest request = new RunInstancesRequest(IMAGE_ID, 1, 1);
			request.setKeyName(KEY_PAIR_NAME);
			RunInstancesResult result = ec2.runInstances(request);
			Reservation reservation = result.getReservation();
			List<Instance> instances = reservation.getInstances();
			Instance instance = instances.get(0);
			System.out.println("Infomation of the Created Instance: " + instance.toString());
		} catch (AmazonClientException ace) {
            System.out.println("Error Message: " + ace.getMessage());
		}
		
		return "test";
	}
	
	public String startInstances() {
		BasicAWSCredentials awsCredentials = AwsAccessManager.getInstance().getAWSCredentials();
		AmazonEC2 ec2 = new AmazonEC2Client(awsCredentials);
		
		try{
			StartInstancesRequest request = new StartInstancesRequest();
			List<String> instanceIds = new ArrayList<String>();
			instanceIds.add("i-3c68f34f");//an existing worker
			instanceIds.add("i-2268f351");//an existing worker
			request.setInstanceIds(instanceIds);
			ec2.startInstances(request);
			System.out.println("Starting instance i-3c68f34f and i-2268f351...");
		} catch(AmazonClientException ace) {
            System.out.println("Error Message: " + ace.getMessage());
		}
		
		return "test";
	}
	
	public String stopInstances() {
		BasicAWSCredentials awsCredentials = AwsAccessManager.getInstance().getAWSCredentials();
		AmazonEC2 ec2 = new AmazonEC2Client(awsCredentials);
		
		try{
			StopInstancesRequest request = new StopInstancesRequest();
			List<String> instanceIds = new ArrayList<String>();
			instanceIds.add("i-3c68f34f");//an existing worker
			instanceIds.add("i-2268f351");//an existing worker
			request.setInstanceIds(instanceIds);
			ec2.stopInstances(request);
			System.out.println("Stopping instance i-3c68f34f and i-2268f351...");
		} catch(AmazonClientException ace) {
            System.out.println("Error Message: " + ace.getMessage());
		}
		
		return "test";
	}
	
    public String watchCloud(){
    	BasicAWSCredentials awsCredentials = AwsAccessManager.getInstance().getAWSCredentials();
        AmazonCloudWatch cw = new AmazonCloudWatchClient(awsCredentials);
        
        try {
        	ListMetricsRequest listMetricsRequest = new ListMetricsRequest();
        	listMetricsRequest.setMetricName("CPUUtilization");
        	listMetricsRequest.setNamespace("AWS/EC2");
        	ListMetricsResult result = cw.listMetrics(listMetricsRequest);
        	java.util.List<Metric> 	metrics = result.getMetrics();
        	for (Metric metric : metrics) {
        		String namespace = metric.getNamespace();
        		String metricName = metric.getMetricName();
        		List<Dimension> dimensions = metric.getDimensions();
            	GetMetricStatisticsRequest statisticsRequest = new GetMetricStatisticsRequest();
            	statisticsRequest.setNamespace(namespace);
            	statisticsRequest.setMetricName(metricName);
            	statisticsRequest.setDimensions(dimensions);
            	Date endTime = new Date();
            	Date startTime = new Date();
            	startTime.setTime(endTime.getTime()-1200000);
            	statisticsRequest.setStartTime(startTime);
            	statisticsRequest.setEndTime(endTime);
            	statisticsRequest.setPeriod(60);
            	Vector<String>statistics = new Vector<String>();
            	statistics.add("Maximum");
            	statisticsRequest.setStatistics(statistics);
            	GetMetricStatisticsResult stats = cw.getMetricStatistics(statisticsRequest);
            	
            	System.out.print("Namespace = " + namespace + " Metric = " + metricName + " Dimensions = " + dimensions);
            	System.out.print("Values = " + stats.toString());
            }
        } catch (AmazonServiceException ase) {
        	System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon EC2, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with EC2, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        
        return "test";
    }
	
	
}
