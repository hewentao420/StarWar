package edu.toronto.ece1779.ec2.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.ec2.entity.Image;
import edu.toronto.ece1779.ec2.entity.User;
import edu.toronto.ece1779.ec2.service.ImageService;
import edu.toronto.ece1779.ec2.service.ImageServieImpl;

public class UserAction extends ActionSupport{
	
	public static final String USER_AUTHENTICATE_FAILURE = "user_authenticate_failure";	
	public static final String USER_AUTHENTICATE_SUCCESS = "user_authenticate_success";
	public static final String FILE_UPLOAD_SUCCESS = "file_upload_success";
	public static final String FILE_UPLOAD_FAILURE = "file_upload_failure";

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
		
		if(fileUpload == null){
			addActionError(getText("failure.no.file"));
			return FILE_UPLOAD_FAILURE;
		}
		
		return FILE_UPLOAD_SUCCESS;
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
