package edu.toronto.ece1779.awsAccess;

import com.amazonaws.auth.BasicAWSCredentials;

public class AwsAccessManager {
	String accessKey = "AKIAJ2K4T2V4TYO6FHLA";
	String secretKey = "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";
	BasicAWSCredentials awsCredentials;
	
	private static AwsAccessManager awsAccessManager;
	
	private AwsAccessManager(){
		awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
	}
	
	public static AwsAccessManager getInstance(){
		if(awsAccessManager==null){
			awsAccessManager = new AwsAccessManager();
		}
		
		return awsAccessManager;
	}
	
	public BasicAWSCredentials getAWSCredentials(){
		return awsCredentials;
	}
}
