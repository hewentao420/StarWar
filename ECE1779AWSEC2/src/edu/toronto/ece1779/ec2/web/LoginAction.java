package edu.toronto.ece1779.ec2.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.ec2.entity.User;
import edu.toronto.ece1779.ec2.service.UserService;
import edu.toronto.ece1779.ec2.service.UserServiceImpl;

public class LoginAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	public static final String USER_LOGIN_SUCCESS = "user_login_success";
	public static final String MANAGER_LOGIN_SUCCESS = "manager_login_success";
	public static final String LOGIN_FAILURE = "login_failure";
	
	private String username;
    private String password; 
    private String retypedPassword;
 
    
    public String authenticate() {
    	String managerName = ServletActionContext.getServletContext().getInitParameter("managerName");
    	String managerPassword = ServletActionContext.getServletContext().getInitParameter("managerPassword");
    	if(managerName.equals(username) && managerPassword.equals(password)) {
    		return MANAGER_LOGIN_SUCCESS;
    	}
    	
    	User user = new User(username, password);
    	UserService userService = new UserServiceImpl();
    	boolean isAuthenticated = userService.authenticate(user);
    	if(isAuthenticated){
    		return USER_LOGIN_SUCCESS;
    	}
    	else {
    		addActionError(getText("failure.login"));
    		return LOGIN_FAILURE;
    	}
    }
    
    
    public String createAccount() {
    	if(!password.equals(retypedPassword)) {
    		addActionError(getText("failure.password.not.match"));
    		return LOGIN_FAILURE;
    	}
    	
    	User user = new User(username, password);	
    	UserService userService = new UserServiceImpl();
    	boolean isExistingUserName = userService.createAccount(user);
    	if(isExistingUserName){
    		addActionError(getText("failure.createAccount"));
    		return LOGIN_FAILURE;
    	}
    	else{
    		return USER_LOGIN_SUCCESS;
    	}
    }
 
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

	public String getRetypedPassword() {
		return retypedPassword;
	}

	public void setRetypedPassword(String retypedPassword) {
		this.retypedPassword = retypedPassword;
	}
    
}
