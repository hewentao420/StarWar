package edu.toronto.ece1779.ec2.web;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.awsAccess.AwsAccessManager;
import edu.toronto.ece1779.ec2.entity.Image;
import edu.toronto.ece1779.ec2.entity.User;
import edu.toronto.ece1779.ec2.service.ImageService;
import edu.toronto.ece1779.ec2.service.ImageServieImpl;

public class UserAction extends ActionSupport{
	
	public static final String USER_AUTHENTICATE_FAILURE = "user_authenticate_failure";	
	public static final String USER_AUTHENTICATE_SUCCESS = "user_authenticate_success";
	public static final String FILE_UPLOAD_SUCCESS = "file_upload_success";
	public static final String FILE_UPLOAD_FAILURE = "file_upload_failure";
	
	public static final String BUCKET_NAME = "group14_images";

	private static final long serialVersionUID = 1L;
	
	private List<Image> images;
	private User user;
	private File fileUpload;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public String enterUserUI(){
		Map<String,Object> session = ServletActionContext.getContext().getSession();
		User user = (User) session.get("user");
		String type = (String) session.get("type");
		
		if(user == null){
			addActionError(getText("failure.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}
		
		if(!type.equals("user")){
			addActionError(getText("failure.type.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}
		
		ImageService imageService = new ImageServieImpl(); 
		images = imageService.getUserImages(user.getId());
		
		return USER_AUTHENTICATE_SUCCESS;
	}
	
	public String uploadImage(){
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		User user = (User) session.get("user");
		String type = (String) session.get("type");
		
		if(user == null){
			addActionError(getText("failure.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}
		
		if(!type.equals("user")){
			addActionError(getText("failure.type.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}
		
		//check whether a file was uploaded
		if(fileUpload == null){
			addActionError(getText("failure.no.file"));
			return FILE_UPLOAD_FAILURE;
		}
		
		//TODO:check whether the file is an image
		
		String originImageKey = "origin_" + UUID.randomUUID();
		s3SaveFile(fileUpload,originImageKey);
		
		return FILE_UPLOAD_SUCCESS;
	}

	private void s3SaveFile(File file, String key) {
		BasicAWSCredentials awsCredentials = AwsAccessManager.getInstance().getAWSCredentials();

    	AmazonS3 s3 = new AmazonS3Client(awsCredentials);
        String bucketName = BUCKET_NAME;
        s3.putObject(new PutObjectRequest(bucketName, key, file));
        s3.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	
	
}
